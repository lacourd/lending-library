package lacourd.lendinglibrary.models;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import lacourd.lendinglibrary.models.bggapi.BGGItem;
import lacourd.lendinglibrary.models.bggapi.BGGName;
import lacourd.lendinglibrary.models.bggapi.BGGSearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.io.StringReader;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import org.apache.commons.lang3.StringEscapeUtils;

@Service
public class BGGApiService {

    private static final Logger logger = Logger.getLogger(BGGApiService.class.getName());

    static {
        // Set the logging level (FINE, INFO, WARNING, SEVERE, etc.)
        logger.setLevel(Level.INFO);

        // Create a console handler and set its logging level
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.ALL);

        // Add the console handler to the logger
        logger.addHandler(consoleHandler);
    }

    private final RestTemplate restTemplate;

    private String gameId;

    public String getGameId() {
        return gameId;
    }

    @Autowired
    public BGGApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String searchGameAndGetCoverImage(String gameTitle) {
        // Step 1: Search for the game and retrieve the game ID with exact match
        String searchUrlWithExact = "https://boardgamegeek.com/xmlapi2/search?type=boardgame&query=" + gameTitle + "&exact=1";
        ResponseEntity<String> searchResponseExact = restTemplate.getForEntity(searchUrlWithExact, String.class);
//        System.out.println(searchResponseExact);

        String xmlResponse = Objects.requireNonNull(searchResponseExact.getBody());
//        System.out.println(xmlResponse);
        // Preprocess the XML response
        String processedXml = preprocessXml(xmlResponse);
//        System.out.println(processedXml);
        // Extract game ID after preprocessing
        String gameId = extractGameIdFromSearchResponse(processedXml);

        System.out.println(gameId);
        if (gameId == null) {
            // No exact match found, try searching without exact match
            String searchUrlWithoutExact = "https://boardgamegeek.com/xmlapi2/search?type=boardgame&query=" + gameTitle;
            ResponseEntity<String> searchResponseWithoutExact = restTemplate.getForEntity(searchUrlWithoutExact, String.class);
            xmlResponse = Objects.requireNonNull(searchResponseWithoutExact.getBody());

            // Preprocess the XML response
            processedXml = preprocessXml(xmlResponse);

            // Extract game ID after preprocessing
            gameId = extractGameIdFromSearchResponse(processedXml);
        }

        if (gameId != null) {
            // Step 2: Get game details using the game ID
            String gameDetailsUrl = "https://www.boardgamegeek.com/xmlapi2/thing?id=" + gameId;
            ResponseEntity<String> gameDetailsResponse = restTemplate.getForEntity(gameDetailsUrl, String.class);

            xmlResponse = Objects.requireNonNull(gameDetailsResponse.getBody());

            // Preprocess the XML response
            processedXml = preprocessXml(xmlResponse);

            return extractCoverImageUrlFromGameDetails(processedXml);
        }

        return null; // Handle if game ID not found or other error cases
    }



    // Helper method to extract game ID from search response
    private String extractGameIdFromSearchResponse(String searchResponse) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(BGGSearchResult.class, BGGItem.class);
//            System.out.println(searchResponse);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            BGGSearchResult result = (BGGSearchResult) unmarshaller.unmarshal(new StringReader(searchResponse));

            // Log the unmarshalled result
            logUnmarshalledResult(result);

            List<BGGItem> items = result.getItems();
            System.out.println(items.toString());

            if (items != null && !items.isEmpty()) {
                gameId = items.get(0).getId();
                return gameId;
            }
        } catch (JAXBException e) {
            e.printStackTrace();
            // Log detailed JAXB errors
            logger.log(Level.SEVERE, "JAXB Unmarshal Error", e);
        }

        gameId = null;
        return gameId;
    }


    // Helper method to extract cover image URL from game details response
    private String extractCoverImageUrlFromGameDetails(String gameDetailsResponse) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(BGGItem.class, BGGName.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            BGGItem item = (BGGItem) unmarshaller.unmarshal(new StringReader(gameDetailsResponse));
            if (item != null) {
                return item.getImage(); // Assuming 'getImage()' method exists in BGGItem class
            }
        } catch (JAXBException e) {
            e.printStackTrace();
            // Log detailed JAXB errors
            logger.log(Level.SEVERE, "JAXB Unmarshal Error", e);
        }

        return null;
    }

    //Helper method to preprocess XML
    private String preprocessXml(String xmlResponse) {
        // Replace problematic entity references
        String processedXml = xmlResponse
                .replaceAll("&amp;#10;", "\n")  // Replace &#10; with newline
                .replaceAll("&lt;br&gt;", "\n") // Replace &lt;br&gt; with newline (if needed)
                .replaceAll("<!--.*?-->", "")  // Remove comments (if needed)
                .replaceAll("&", "&amp;");  // Replace & with ampersand (if needed)

        // You can add more replacements as needed

        // Unescape other HTML entities
        processedXml = StringEscapeUtils.unescapeHtml4(processedXml);

        return processedXml;
    }

    private void logUnmarshalledResult(BGGSearchResult result) {
        // Log the unmarshalled BGGSearchResult object
        if (result != null) {
            // Log details about BGGSearchResult
            logger.info("Unmarshalled BGGSearchResult:");
//            logger.info("Total Items: " + result.getItems());
            // Add more log statements as needed based on the structure of BGGSearchResult
            // Log details about each BGGItem
            List<BGGItem> items = result.getItems();
            if (items != null) {
                for (int i = 0; i < items.size(); i++) {
                    BGGItem item = items.get(i);
                    logger.info("Item " + (i + 1) + ": " + item.toString());
                    // Add more log statements based on the structure of BGGItem
                }
            } else {
                logger.warning("BGGItem list is null");
            }
        } else {
            logger.warning("Unmarshalled BGGSearchResult is null");
        }
    }
}
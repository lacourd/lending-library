package lacourd.lendinglibrary.models;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import lacourd.lendinglibrary.models.bggapi.*;
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

    public BGGGameData searchGameAndGetData(String gameTitle) {
        String searchUrlWithExact = "https://boardgamegeek.com/xmlapi2/search?type=boardgame&query=" + gameTitle + "&exact=1";
        String gameId = extractGameIdFromSearchUrl(searchUrlWithExact);

        if (gameId == null) {
            String searchUrlWithoutExact = "https://boardgamegeek.com/xmlapi2/search?type=boardgame&query=" + gameTitle;
            gameId = extractGameIdFromSearchUrl(searchUrlWithoutExact);
        }

        if (gameId != null) {
            String gameDetailsUrl = "https://www.boardgamegeek.com/xmlapi2/thing?id=" + gameId;
            BGGItem gameInformation = extractItemFromGameDetailsUrl(gameDetailsUrl);
            String coverImageUrl = gameInformation.getImage();
            String thumbnailUrl = gameInformation.getThumbnail();
            String bggDescription = gameInformation.getDescription();

            return new BGGGameData(gameId, coverImageUrl, thumbnailUrl, bggDescription);
        }
        return null;
    }

    public String searchGameAndGetCoverImage(String gameTitle) {
        // Step 1: Search for the game and retrieve the game ID with exact match
        System.out.println(gameTitle);
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
            if (items != null) {
                System.out.println(items.toString());
            }

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
            JAXBContext jaxbContext = JAXBContext.newInstance(BGGItems.class, BGGItem.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            StringReader reader = new StringReader(gameDetailsResponse);
            BGGItems response = (BGGItems) unmarshaller.unmarshal(reader);
            List<BGGItem> bggItems = response.getItems();
            String thumbnail = bggItems.get(0).getThumbnail();
            String image = bggItems.get(0).getImage();
                return image;
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

    private ResponseEntity<String> convertUrlToResponseEntity(String url) {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        return responseEntity;
    }

    private String extractGameIdFromSearchUrl(String url) {
        List<BGGItem> bggItems = extractBGGItemListFromApiQuery(url);
        if (bggItems != null && bggItems.size() == 1) {
            gameId = bggItems.get(0).getId();
            return gameId;
        }
        if (bggItems != null && bggItems.size() > 1) {
            String title = url.split("=")[2];
            if (title.contains("&exact")) {
                title = title.substring(0, title.length() - 6);
            }
            BGGItem bestMatch = BGGItemMatcher.findBestMatch(bggItems, title);
            return bestMatch.getId();
        }

        gameId = null;
        return gameId;
    }

    private BGGItem extractItemFromGameDetailsUrl(String gameDetailsUrl) {
        List<BGGItem> bggItems = extractBGGItemListFromApiQuery(gameDetailsUrl);
        return bggItems.get(0);
    }

    private List<BGGItem> extractBGGItemListFromApiQuery(String url) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(BGGItems.class, BGGItem.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            StringReader reader = new StringReader(convertUrlToResponseEntity(url).getBody());
            BGGItems response = (BGGItems) unmarshaller.unmarshal(reader);
            return response.getItems();
        } catch (JAXBException e) {
            e.printStackTrace();
            // Log detailed JAXB errors
            logger.log(Level.SEVERE, "JAXB Unmarshal Error", e);
        }

        return null;
    }
}
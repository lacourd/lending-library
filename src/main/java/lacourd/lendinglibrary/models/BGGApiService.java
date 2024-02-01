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
import java.util.logging.Logger;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;

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

    public BGGGameData searchGameAndGetBGGData(String gameTitle) {
        // Step 1: Search for the game and retrieve the game ID with exact match
        ResponseEntity<String> searchResponseExact = restTemplate.getForEntity(createSearchUrl(gameTitle, true), String.class);

        // Extract game ID
        String gameId = extractGameIdFromSearchResponse(searchResponseExact.getBody());

        if (gameId == null) {
            // No exact match found, try searching without exact match
            ResponseEntity<String> searchResponseWithoutExact = restTemplate.getForEntity(createSearchUrl(gameTitle, false), String.class);

            // Extract game ID
            gameId = extractGameIdFromSearchResponse(searchResponseWithoutExact.getBody());
        }

        if (gameId != null) {
            // Step 2: Get game details using the game ID
            ResponseEntity<String> gameDetailsResponse = restTemplate.getForEntity(createGameDetailsUrl(gameId), String.class);

            return extractGameDataFromGameDetails(gameDetailsResponse.getBody());
        }

        return null; // Handle if game ID not found or other error cases
    }



    // Helper method to extract game ID from search response
    private String extractGameIdFromSearchResponse(String searchResponse) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(BGGSearchResult.class, BGGItem.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            BGGSearchResult result = (BGGSearchResult) unmarshaller.unmarshal(new StringReader(searchResponse));

            List<BGGItem> items = result.getItems();
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


    // Helper method to extract BGGGameData from game details response
    private BGGGameData extractGameDataFromGameDetails(String gameDetailsResponse) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(BGGItems.class, BGGItem.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            StringReader reader = new StringReader(gameDetailsResponse);
            BGGItems response = (BGGItems) unmarshaller.unmarshal(reader);
            List<BGGItem> bggItems = response.getItems();
            String thumbnail = bggItems.get(0).getThumbnail();
            String coverImage = bggItems.get(0).getImage();
            String description = bggItems.get(0).getDescription();
            BGGGameData bggGameData = new BGGGameData(gameId, coverImage, thumbnail, description);
            return bggGameData;
        } catch (JAXBException e) {
            e.printStackTrace();
            // Log detailed JAXB errors
            logger.log(Level.SEVERE, "JAXB Unmarshal Error", e);
        }

        return null;
    }

    private String createSearchUrl(String gameTitle, boolean exactMatch) {
        String exactMatchQueryParam = exactMatch ? "&exact=1" : "";
        return "https://boardgamegeek.com/xmlapi2/search?type=boardgame&query=" + gameTitle + exactMatchQueryParam;
    }

    private String createGameDetailsUrl(String gameId) {
        return "https://www.boardgamegeek.com/xmlapi2/thing?id=" + gameId;
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
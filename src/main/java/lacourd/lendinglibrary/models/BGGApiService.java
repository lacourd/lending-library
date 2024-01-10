package lacourd.lendinglibrary.models;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.io.StringReader;
import java.util.Objects;

@Service
public class BGGApiService {

    private final RestTemplate restTemplate;

    @Autowired
    public BGGApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String searchGameAndGetCoverImage(String gameTitle) {
        // Step 1: Search for the game and retrieve the game ID
        String searchUrl = "https://www.boardgamegeek.com/xmlapi/search?search=" + gameTitle;

        ResponseEntity<String> searchResponse = restTemplate.getForEntity(searchUrl, String.class);
        String gameId = extractGameIdFromSearchResponse(Objects.requireNonNull(searchResponse.getBody()));

        if (gameId != null) {
            // Step 2: Get game details using the game ID
            String gameDetailsUrl = "https://www.boardgamegeek.com/xmlapi/boardgame/" + gameId;

            ResponseEntity<String> gameDetailsResponse = restTemplate.getForEntity(gameDetailsUrl, String.class);
            return extractCoverImageUrlFromGameDetails(Objects.requireNonNull(gameDetailsResponse.getBody()));
        }

        return null; // Handle if game ID not found or other error cases
    }

    // Helper method to extract game ID from search response
    private String extractGameIdFromSearchResponse(String searchResponse) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(SearchResult.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            SearchResult result = (SearchResult) unmarshaller.unmarshal(new StringReader(searchResponse));
            if (result.getItems().size() > 0) {
                return result.getItems().get(0).getObjectId();
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return null;
    }

    // Helper method to extract cover image URL from game details response
    private String extractCoverImageUrlFromGameDetails(String gameDetailsResponse) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(BGGGameDetails.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            BGGGameDetails details = (BGGGameDetails) unmarshaller.unmarshal(new StringReader(gameDetailsResponse));
            if (details.getBoardGames().size() > 0) {
                return details.getBoardGames().get(0).getImage(); // Assuming 'getImage()' method exists in BGGGameDetails class
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return null;
    }
}
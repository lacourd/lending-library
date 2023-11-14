package lacourd.lendinglibrary.models;

import java.net.URI;
import java.net.http.HttpRequest;

public class BGGApi {
    private static final String BASE_URL = "https://api.boardgamegeek.com/xmlapi";

    public static String getCoverImageURL(String gameName) {
//        String url = BASE_URL + "/boardgame/" + gameId + "?key=YOUR_API_KEY";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL+"/search?search="+gameName))
                .build();

        // Make a request to the API and get the response
        Request request = new Request(url);
        byte[] response = request.execute().getContentAsBytes();

        // Parse the XML response and get the cover image URL
        XMLParser parser = new XMLParser();
        Document document = parser.parse(response);
        Element boardgameElement = document.getElementsByTagName("boardgame").item(0);
        Element imageElement = boardgameElement.getElementsByTagName("image").item(0);
        String coverImageURL = imageElement.getTextContent();

        return coverImageURL;
    }
}

package lacourd.lendinglibrary.models;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

    public class BGGApiServiceTest {

        @Mock
        private RestTemplate restTemplateMock = Mockito.mock(RestTemplate.class);

        @Test
        public void testSearchGameAndGetCoverImage() {
            // Mock the response for the search API call
            String searchGameTitle = "Catan";
            String searchResponse = "<boardgames termsofuse=\"https://boardgamegeek.com/xmlapi/termsofuse\">" +
                    "   <boardgame objectid=\"110308\">\n" +
                    "<name primary=\"true\">7 Wonders: Catan</name>\n" +
                    "<yearpublished>2011</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"123386\">\n" +
                    "<name primary=\"true\">Baden-Württemberg Catan</name>\n" +
                    "<yearpublished>2012</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"13\">\n" +
                    "<name primary=\"true\">CATAN</name>\n" +
                    "<yearpublished>1995</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"17419\">\n" +
                    "<name primary=\"true\">CATAN 3D Collector's Edition</name>\n" +
                    "<yearpublished>2005</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"26352\">\n" +
                    "<name primary=\"true\">Catan Austria / Wien meets Catan</name>\n" +
                    "<yearpublished>2004</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"278\">\n" +
                    "<name primary=\"true\">Catan Card Game</name>\n" +
                    "<yearpublished>1996</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"21817\">\n" +
                    "<name primary=\"true\">Catan Card Game: Artisans & Benefactors</name>\n" +
                    "<yearpublished>2006</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"12543\">\n" +
                    "<name primary=\"true\">Catan Card Game: Barbarians & Traders Upgrade Kit</name>\n" +
                    "<yearpublished>2003</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"2915\">\n" +
                    "<name primary=\"true\">Catan Card Game: Expansion Set</name>\n" +
                    "<yearpublished>2002</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"27710\">\n" +
                    "<name primary=\"true\">Catan Dice Game</name>\n" +
                    "<yearpublished>2007</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"47410\">\n" +
                    "<name primary=\"true\">Catan Dice Game Deluxe Edition</name>\n" +
                    "<yearpublished>2009</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"39624\">\n" +
                    "<name primary=\"true\">Catan Dice Game XXL Variant</name>\n" +
                    "<yearpublished>2008</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"86008\">\n" +
                    "<name primary=\"true\">Catan Geographies: Austria</name>\n" +
                    "<yearpublished>2010</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"60134\">\n" +
                    "<name primary=\"true\">Catan Geographies: Bayern Edition</name>\n" +
                    "<yearpublished>2009</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"149857\">\n" +
                    "<name primary=\"true\">Catan Geographies: Corsica</name>\n" +
                    "<yearpublished>2013</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"173651\">\n" +
                    "<name primary=\"true\">Catan Geographies: Georgia</name>\n" +
                    "<yearpublished>2015</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"38749\">\n" +
                    "<name primary=\"true\">Catan Geographies: Germany</name>\n" +
                    "<yearpublished>2008</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"202788\">\n" +
                    "<name primary=\"true\">Catan Geographies: Kennessee</name>\n" +
                    "<yearpublished>2016</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"131362\">\n" +
                    "<name primary=\"true\">Catan Geographies: Mallorca</name>\n" +
                    "<yearpublished>2012</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"39093\">\n" +
                    "<name primary=\"true\">Catan Geographies: North Rhine – Westphalia</name>\n" +
                    "<yearpublished>2008</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"187366\">\n" +
                    "<name primary=\"true\">Catan Geographies: Rickshaw Run</name>\n" +
                    "<yearpublished>2015</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"32270\">\n" +
                    "<name primary=\"true\">Catan Geographies: Settlers of Hesse</name>\n" +
                    "<yearpublished>2007</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"169486\">\n" +
                    "<name primary=\"true\">Catan Geographies: The Carolinas</name>\n" +
                    "<yearpublished>2014</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"103091\">\n" +
                    "<name primary=\"true\">Catan Histories: Merchants of Europe</name>\n" +
                    "<yearpublished>2011</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"244144\">\n" +
                    "<name primary=\"true\">Catan Histories: Rise of the Inkas</name>\n" +
                    "<yearpublished>2018</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"67239\">\n" +
                    "<name primary=\"true\">\n" +
                    "Catan Histories: Settlers of America – Trails to Rails\n" +
                    "</name>\n" +
                    "<yearpublished>2010</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"25234\">\n" +
                    "<name primary=\"true\">Catan Histories: Struggle for Rome</name>\n" +
                    "<yearpublished>2006</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"291296\">\n" +
                    "<name primary=\"true\">\n" +
                    "Catan Histories: Struggle for Rome – Terror of the Legions expansion\n" +
                    "</name>\n" +
                    "<yearpublished>2006</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"184842\">\n" +
                    "<name primary=\"true\">Catan Junior</name>\n" +
                    "<yearpublished>2014</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"140743\">\n" +
                    "<name primary=\"true\">Catan Junior Madagascar</name>\n" +
                    "<yearpublished>2012</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"269978\">\n" +
                    "<name primary=\"true\">Catan Junior Mitbringspiel</name>\n" +
                    "<yearpublished>2019</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"211081\">\n" +
                    "<name primary=\"true\">Catan Länderszenarien: Polen</name>\n" +
                    "<yearpublished>2016</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"292851\">\n" +
                    "<name primary=\"true\">Catan Rhein-Main-Neckar</name>\n" +
                    "<yearpublished>2019</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"259397\">\n" +
                    "<name primary=\"true\">Catan Scenario: Crop Trust</name>\n" +
                    "<yearpublished>2018</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"222582\">\n" +
                    "<name primary=\"true\">Catan Scenario: Durango</name>\n" +
                    "<yearpublished>2016</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"368580\">\n" +
                    "<name primary=\"true\">Catan Scenario: Fenni's Shearing Season</name>\n" +
                    "<yearpublished>2021</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"374983\">\n" +
                    "<name primary=\"true\">Catan Scenario: Malta</name>\n" +
                    "<yearpublished>2022</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"189097\">\n" +
                    "<name primary=\"true\">Catan Scenarios: Easter Bunny</name>\n" +
                    "<yearpublished>2015</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"307518\">\n" +
                    "<name primary=\"true\">Catan Scenarios: #WeStayHome</name>\n" +
                    "<yearpublished>2020</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"186395\">\n" +
                    "<name primary=\"true\">Catan Scenarios: Big Game Big Honor</name>\n" +
                    "<yearpublished>2015</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"196928\">\n" +
                    "<name primary=\"true\">Catan Scenarios: Catanimals</name>\n" +
                    "<yearpublished>2010</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"131958\">\n" +
                    "<name primary=\"true\">Catan Scenarios: Frenemies</name>\n" +
                    "<yearpublished>2012</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"305516\">\n" +
                    "<name primary=\"true\">Catan Scenarios: Global Warming</name>\n" +
                    "<yearpublished>2020</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"86669\">\n" +
                    "<name primary=\"true\">Catan Scenarios: Helpers of Catan</name>\n" +
                    "<yearpublished>2010</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"110794\">\n" +
                    "<name primary=\"true\">Catan Scenarios: Oil Springs</name>\n" +
                    "<yearpublished>2011</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"167573\">\n" +
                    "<name>Catan World Championship Berlin 2014 Special</name>\n" +
                    "<yearpublished>2014</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"298278\">\n" +
                    "<name primary=\"true\">Catan: 25 Jahre Jubiläums-Edition</name>\n" +
                    "<yearpublished>2020</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"305668\">\n" +
                    "<name primary=\"true\">Catan: 25th Anniversary Edition</name>\n" +
                    "<yearpublished>2020</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"338697\">\n" +
                    "<name primary=\"true\">CATAN: 3D Edition</name>\n" +
                    "<yearpublished>2021</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"358860\">\n" +
                    "<name primary=\"true\">\n" +
                    "CATAN: 3D Expansions – Seafarers + Cities & Knights\n" +
                    "</name>\n" +
                    "<yearpublished>2022</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"2807\">\n" +
                    "<name primary=\"true\">Catan: 5-6 Player Extension</name>\n" +
                    "<yearpublished>1996</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"183139\">\n" +
                    "<name primary=\"true\">Catan: 999 Games 25 jaar Expansion</name>\n" +
                    "<yearpublished>2015</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"161527\">\n" +
                    "<name primary=\"true\">Catan: Ancient Egypt</name>\n" +
                    "<yearpublished>2014</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"372510\">\n" +
                    "<name primary=\"true\">Catan: Aufbruch der Menschheit – Der Schamane</name>\n" +
                    "<yearpublished>2022</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"182880\">\n" +
                    "<name primary=\"true\">Catan: Big Box</name>\n" +
                    "<yearpublished>2015</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"191710\">\n" +
                    "<name primary=\"true\">Catan: Big Box</name>\n" +
                    "<yearpublished>2016</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"269980\">\n" +
                    "<name primary=\"true\">Catan: Big Box</name>\n" +
                    "<yearpublished>2019</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"394723\">\n" +
                    "<name primary=\"true\">CATAN: Big Box</name>\n" +
                    "<yearpublished>2023</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"265030\">\n" +
                    "<name primary=\"true\">Catan: Big Box Jubileumeditie</name>\n" +
                    "<yearpublished>2018</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"194097\">\n" +
                    "<name primary=\"true\">Catan: Big Game Event Kit</name>\n" +
                    "<yearpublished>2016</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"104774\">\n" +
                    "<name primary=\"true\">Catan: Catakatoa</name>\n" +
                    "<yearpublished>2005</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"176966\">\n" +
                    "<name primary=\"true\">Catan: Catan Day 2015 Exclusive Expansion</name>\n" +
                    "<yearpublished>2015</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"181471\">\n" +
                    "<name primary=\"true\">Catan: Chocolate Edition</name>\n" +
                    "<yearpublished>2015</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"926\">\n" +
                    "<name primary=\"true\">CATAN: Cities & Knights</name>\n" +
                    "<yearpublished>1998</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"4101\">\n" +
                    "<name primary=\"true\">Catan: Cities & Knights – 5-6 Player Extension</name>\n" +
                    "<yearpublished>2000</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"265141\">\n" +
                    "<name primary=\"true\">Catan: Cities & Knights – Legend of the Conquerors</name>\n" +
                    "<yearpublished>2019</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"284815\">\n" +
                    "<name primary=\"true\">Catan: Core + China Map</name>\n" +
                    "<yearpublished>2019</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"394739\">\n" +
                    "<name primary=\"true\">CATAN: Das Duell – Die Big Box</name>\n" +
                    "<yearpublished>2023</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"324882\">\n" +
                    "<name primary=\"true\">Catan: Das Duell – Finstere & Goldene Zeiten</name>\n" +
                    "<yearpublished>2021</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"211557\">\n" +
                    "<name primary=\"true\">\n" +
                    "Catan: Das Duell – Sonderkarte 2016: 20 Jahre Catan, das Spiel für 2\n" +
                    "</name>\n" +
                    "<yearpublished>2016</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"212234\">\n" +
                    "<name primary=\"true\">\n" +
                    "Catan: Das Duell – Sonderkarte 2016: Michael der Wagemutige\n" +
                    "</name>\n" +
                    "<yearpublished>2016</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"238673\">\n" +
                    "<name primary=\"true\">\n" +
                    "Catan: Das Duell – Sonderkarte 2017: Isabell, die Handelsagentin\n" +
                    "</name>\n" +
                    "<yearpublished>2017</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"350674\">\n" +
                    "<name primary=\"true\">\n" +
                    "Catan: Das Duell – Sonderkarte 2021: 25 Jahre CATAN-Kartenspiel\n" +
                    "</name>\n" +
                    "<yearpublished>2021</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"350673\">\n" +
                    "<name primary=\"true\">Catan: Das Duell – Sonderkarte 2021: Weinberg</name>\n" +
                    "<yearpublished>2021</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"372567\">\n" +
                    "<name primary=\"true\">\n" +
                    "Catan: Das Duell – Sonderkarte 2022: Sonja, die Schäferin\n" +
                    "</name>\n" +
                    "<yearpublished>2022</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"402962\">\n" +
                    "<name primary=\"true\">\n" +
                    "Catan: Das Duell – Sonderkarte 2023: Klaus, Hüter von Catan\n" +
                    "</name>\n" +
                    "<yearpublished>2023</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"358858\">\n" +
                    "<name primary=\"true\">CATAN: Dawn of Humankind</name>\n" +
                    "<yearpublished>2022</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"384112\">\n" +
                    "<name primary=\"true\">CATAN: Dawn of Humankind – Promo #1</name>\n" +
                    "<yearpublished>2022</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"73809\">\n" +
                    "<name primary=\"true\">Catan: Delmarva</name>\n" +
                    "<yearpublished>2010</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"352073\">\n" +
                    "<name primary=\"true\">Catan: Die Stoffräuber</name>\n" +
                    "<yearpublished>2011</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"20038\">\n" +
                    "<name primary=\"true\">Catan: Event Cards</name>\n" +
                    "<yearpublished>2005</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"135378\">\n" +
                    "<name primary=\"true\">Catan: Explorers & Pirates</name>\n" +
                    "<yearpublished>2013</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"144419\">\n" +
                    "<name primary=\"true\">Catan: Explorers & Pirates – 5-6 Player Extension</name>\n" +
                    "<yearpublished>2013</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"147240\">\n" +
                    "<name primary=\"true\">Catan: Family Edition</name>\n" +
                    "<yearpublished>2012</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"325333\">\n" +
                    "<name primary=\"true\">Catan: First Adventure</name>\n" +
                    "<yearpublished>2020</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"238135\">\n" +
                    "<name primary=\"true\">CATAN: Hawai'i Scenario</name>\n" +
                    "<yearpublished>2017</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"250619\">\n" +
                    "<name primary=\"true\">Catan: Het Duel Big Box</name>\n" +
                    "<yearpublished>2017</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"263939\">\n" +
                    "<name primary=\"true\">Catan: High Priests of the Inkas</name>\n" +
                    "<yearpublished>2018</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"54528\">\n" +
                    "<name primary=\"true\">Catan: Indiana & Ohio</name>\n" +
                    "<yearpublished>2009</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"125921\">\n" +
                    "<name primary=\"true\">Catan: Junior</name>\n" +
                    "<yearpublished>2011</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"408727\">\n" +
                    "<name primary=\"true\">CATAN: New Energies</name>\n" +
                    "<yearpublished>2024</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"143249\">\n" +
                    "<name primary=\"true\">Catan: New England</name>\n" +
                    "<yearpublished>2013</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"132481\">\n" +
                    "<name primary=\"true\">Catan: New York</name>\n" +
                    "<yearpublished>2012</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"101942\">\n" +
                    "<name primary=\"true\">Catan: Penn-Jersey</name>\n" +
                    "<yearpublished>2011</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"222396\">\n" +
                    "<name primary=\"true\">Catan: Playmat Atoll</name>\n" +
                    "<yearpublished>2016</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"222394\">\n" +
                    "<name primary=\"true\">Catan: Playmat Desert</name>\n" +
                    "<yearpublished>2016</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"222393\">\n" +
                    "<name primary=\"true\">Catan: Playmat Gold</name>\n" +
                    "<yearpublished>2016</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"222395\">\n" +
                    "<name primary=\"true\">Catan: Playmat Island</name>\n" +
                    "<yearpublished>2016</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"3972\">\n" +
                    "<name primary=\"true\">Catan: Portable Edition</name>\n" +
                    "<yearpublished>2002</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"325\">\n" +
                    "<name primary=\"true\">Catan: Seafarers</name>\n" +
                    "<yearpublished>1997</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"223171\">\n" +
                    "<name primary=\"true\">\n" +
                    "Catan: Seafarers Scenario – Legend of the Sea Robbers\n" +
                    "</name>\n" +
                    "<yearpublished>2017</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"4103\">\n" +
                    "<name primary=\"true\">Catan: Seafarers – 5-6 Player Extension</name>\n" +
                    "<yearpublished>1999</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"217947\">\n" +
                    "<name primary=\"true\">Catan: Seefahrer – 20 Jahre Jubiläums-Edition</name>\n" +
                    "<yearpublished>2017</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"381626\">\n" +
                    "<name primary=\"true\">CATAN: Soccer Fever Scenario</name>\n" +
                    "<yearpublished>2023</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"282853\">\n" +
                    "<name primary=\"true\">CATAN: Starfarers</name>\n" +
                    "<yearpublished>2019</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"376805\">\n" +
                    "<name primary=\"true\">CATAN: Starfarers –  New Encounters</name>\n" +
                    "<yearpublished>2023</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"383481\">\n" +
                    "<name primary=\"true\">CATAN: Starfarers Duel</name>\n" +
                    "<yearpublished>2023</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"316229\">\n" +
                    "<name primary=\"true\">Catan: Starfarers – 5-6 Player Extension</name>\n" +
                    "<yearpublished>2020</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"293002\">\n" +
                    "<name primary=\"true\">Catan: Starfarers – Asteroid Fields</name>\n" +
                    "<yearpublished>2019</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"324313\">\n" +
                    "<name primary=\"true\">Catan: Starfarers – Space Jump</name>\n" +
                    "<yearpublished>2020</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"351565\">\n" +
                    "<name primary=\"true\">\n" +
                    "Catan: Sternenfahrer – Begegnungskarten für Sternenfahrer: Erster Kontakt\n" +
                    "</name>\n" +
                    "<yearpublished>2021</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"402919\">\n" +
                    "<name primary=\"true\">\n" +
                    "CATAN: Sternenfahrer – Das Duell: Glücksspielplanet Promo\n" +
                    "</name>\n" +
                    "<yearpublished>2023</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"271159\">\n" +
                    "<name primary=\"true\">Catan: Szenario Der Kölner Dom</name>\n" +
                    "<yearpublished>2018</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"367791\">\n" +
                    "<name primary=\"true\">CATAN: The Helpers</name>\n" +
                    "<yearpublished>2022</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"27760\">\n" +
                    "<name primary=\"true\">CATAN: Traders & Barbarians</name>\n" +
                    "<yearpublished>2007</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"34691\">\n" +
                    "<name primary=\"true\">Catan: Traders & Barbarians – 5-6 Player Extension</name>\n" +
                    "<yearpublished>2008</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"172994\">\n" +
                    "<name primary=\"true\">Catan: Traveler – Compact Edition</name>\n" +
                    "<yearpublished>2015</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"56157\">\n" +
                    "<name primary=\"true\">CATAN: Treasures, Dragons & Adventurers</name>\n" +
                    "<yearpublished>2009</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"341591\">\n" +
                    "<name primary=\"true\">CATAN: Zusatzmaterial für Das Duell – Bonus Box</name>\n" +
                    "<yearpublished>2021</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"38845\">\n" +
                    "<name primary=\"true\">Les Colons de Catane: Le jeu de dés</name>\n" +
                    "<yearpublished>2007</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"13831\">\n" +
                    "<name primary=\"true\">The Communication in Catan</name>\n" +
                    "<yearpublished>2000</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"118173\">\n" +
                    "<name primary=\"true\">\n" +
                    "Die Fürsten von Catan: Sonderkarte 2011 – Carol, die Spieleerklärerin\n" +
                    "</name>\n" +
                    "<yearpublished>2011</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"168216\">\n" +
                    "<name primary=\"true\">\n" +
                    "Die Fürsten von Catan: Sonderkarte 2014 – Arnd, der Fischer\n" +
                    "</name>\n" +
                    "<yearpublished>2014</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"131707\">\n" +
                    "<name primary=\"true\">\n" +
                    "Die Fürsten von Catan: Sonderkarte Frühjahr 2012 – Catan Mobil\n" +
                    "</name>\n" +
                    "<yearpublished>2012</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"229218\">\n" +
                    "<name primary=\"true\">\n" +
                    "A Game of Thrones: Catan – Brotherhood of the Watch\n" +
                    "</name>\n" +
                    "<yearpublished>2017</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"259396\">\n" +
                    "<name primary=\"true\">\n" +
                    "A Game of Thrones: Catan – Brotherhood of the Watch: 5-6 Player Extension\n" +
                    "</name>\n" +
                    "<yearpublished>2018</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"282492\">\n" +
                    "<name primary=\"true\">\n" +
                    "A Game of Thrones: Catan – Brotherhood of the Watch: Bran Stark Promo\n" +
                    "</name>\n" +
                    "<yearpublished>2019</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"279058\">\n" +
                    "<name primary=\"true\">\n" +
                    "A Game of Thrones: Catan – Brotherhood of the Watch: Hodor Promo\n" +
                    "</name>\n" +
                    "<yearpublished>2019</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"264009\">\n" +
                    "<name primary=\"true\">\n" +
                    "A Game of Thrones: Catan – Brotherhood of the Watch: Minor Houses of the North Promo\n" +
                    "</name>\n" +
                    "<yearpublished>2017</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"128751\">\n" +
                    "<name primary=\"true\">\n" +
                    "Heroes & Capitols (fan expansion for Settlers of Catan)\n" +
                    "</name>\n" +
                    "<yearpublished>2012</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"91061\">\n" +
                    "<name primary=\"true\">\n" +
                    "Hexen, Zauberer & Drachen (fan expansion for Catan: Cities and Knights)\n" +
                    "</name>\n" +
                    "<yearpublished>2001</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"5824\">\n" +
                    "<name primary=\"true\">The Kids of Catan</name>\n" +
                    "<yearpublished>2003</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"89606\">\n" +
                    "<name primary=\"true\">\n" +
                    "Kirche, Glaube & Reformation (fan expansion for Catan: Cities and Knights)\n" +
                    "</name>\n" +
                    "<yearpublished>2002</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"21101\">\n" +
                    "<name primary=\"true\">De Kolonisten van Catan: De Diamanten</name>\n" +
                    "<yearpublished>2005</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"41161\">\n" +
                    "<name primary=\"true\">\n" +
                    "De Kolonisten van Catan: De drie Handelsteden van Noord-Nederland\n" +
                    "</name>\n" +
                    "<yearpublished>2009</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"21097\">\n" +
                    "<name primary=\"true\">De Kolonisten van Catan: De Koloniën</name>\n" +
                    "<yearpublished>2005</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"21443\">\n" +
                    "<name primary=\"true\">De Kolonisten van Catan: De Specialisten</name>\n" +
                    "<yearpublished>2005</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"21100\">\n" +
                    "<name primary=\"true\">De Kolonisten van Catan: De Wereldwonderen</name>\n" +
                    "<yearpublished>2005</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"21098\">\n" +
                    "<name primary=\"true\">De Kolonisten van Catan: De Woestijnruiters</name>\n" +
                    "<yearpublished>2005</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"21099\">\n" +
                    "<name primary=\"true\">De Kolonisten van Catan: Het Grote Kanaal</name>\n" +
                    "<yearpublished>2005</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"37774\">\n" +
                    "<name primary=\"true\">\n" +
                    "De Kolonisten van Catan: Het Kaartspel – Goud & Piraten\n" +
                    "</name>\n" +
                    "<yearpublished>2001</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"33562\">\n" +
                    "<name primary=\"true\">De Kolonisten van Catan: Wegwerpcatan</name>\n" +
                    "<yearpublished>2007</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"90100\">\n" +
                    "<name primary=\"true\">\n" +
                    "Die Pioniere (fan expansion for The Settlers of Catan)\n" +
                    "</name>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"66056\">\n" +
                    "<name primary=\"true\">Rivals for Catan</name>\n" +
                    "<yearpublished>2010</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"93401\">\n" +
                    "<name primary=\"true\">Rivals for Catan: Age of Darkness</name>\n" +
                    "<yearpublished>2011</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"127437\">\n" +
                    "<name primary=\"true\">Rivals for Catan: Age of Enlightenment</name>\n" +
                    "<yearpublished>2012</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"133042\">\n" +
                    "<name primary=\"true\">\n" +
                    "The Rivals for Catan: Alexander and Sebastian, the Bookkeepers\n" +
                    "</name>\n" +
                    "<yearpublished>2012</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"88610\">\n" +
                    "<name primary=\"true\">The Rivals for Catan: Axel the Innovator</name>\n" +
                    "<yearpublished>2010</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"203219\">\n" +
                    "<name primary=\"true\">Rivals for Catan: Deluxe</name>\n" +
                    "<yearpublished>2016</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"247494\">\n" +
                    "<name primary=\"true\">Rivals for Catan: Deluxe – Promo Card Pack</name>\n" +
                    "<yearpublished>2016</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"149833\">\n" +
                    "<name primary=\"true\">The Rivals for Catan: Gavin the Polyglot</name>\n" +
                    "<yearpublished>2013</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"186788\">\n" +
                    "<name primary=\"true\">The Rivals for Catan: Heiko the Master Swimmer</name>\n" +
                    "<yearpublished>2015</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"348682\">\n" +
                    "<name primary=\"true\">Rivals for Catan: Hostel Scenario</name>\n" +
                    "<yearpublished>2020</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"111255\">\n" +
                    "<name primary=\"true\">The Rivals for Catan: Johannes the Advocate</name>\n" +
                    "<yearpublished>2011</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"186215\">\n" +
                    "<name primary=\"true\">\n" +
                    "The Rivals for Catan: Participation in the Big Game\n" +
                    "</name>\n" +
                    "<yearpublished>2015</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"186789\">\n" +
                    "<name primary=\"true\">The Rivals for Catan: Ron the Well-Travelled Man</name>\n" +
                    "<yearpublished>2015</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"22598\">\n" +
                    "<name primary=\"true\">\n" +
                    "Saggsen-Gadan: De säggs'schn Siedler / Catan-OFFENSIVE in Chemnitz\n" +
                    "</name>\n" +
                    "<yearpublished>2005</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"152959\">\n" +
                    "<name primary=\"true\">The Settlers of Catan</name>\n" +
                    "<yearpublished>2008</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"293222\">\n" +
                    "<name primary=\"true\">Settlers of Catan Scenario: The Jungle</name>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"293224\">\n" +
                    "<name primary=\"true\">Settlers of Catan Scenario: The Volcano</name>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"38821\">\n" +
                    "<name primary=\"true\">Settlers of Catan: Gallery Edition</name>\n" +
                    "<yearpublished>2008</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"20899\">\n" +
                    "<name primary=\"true\">Settlers of Catan: Rockman Edition</name>\n" +
                    "<yearpublished>2005</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"19343\">\n" +
                    "<name primary=\"true\">The Settlers of Catan: The Fishermen of Catan</name>\n" +
                    "<yearpublished>2005</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"20247\">\n" +
                    "<name primary=\"true\">The Settlers of Catan: The Great River</name>\n" +
                    "<yearpublished>2005</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"10817\">\n" +
                    "<name primary=\"true\">Settlers of New Catan (and extra modules)</name>\n" +
                    "<yearpublished>2004</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"21046\">\n" +
                    "<name primary=\"true\">\n" +
                    "Die Siedler von Catan: Atlantis – Szenarien & Varianten\n" +
                    "</name>\n" +
                    "<yearpublished>2005</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"1137\">\n" +
                    "<name primary=\"true\">Die Siedler von Catan: Das Buch zum Spielen</name>\n" +
                    "<yearpublished>2000</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"22766\">\n" +
                    "<name primary=\"true\">\n" +
                    "Die Siedler von Catan: Das Kartenspiel – 10th Anniversary Special Edition Tin Box\n" +
                    "</name>\n" +
                    "<yearpublished>2006</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"250661\">\n" +
                    "<name primary=\"true\">Die Siedler von Catan: Das Kartenspiel – Plus Pack</name>\n" +
                    "<yearpublished>1999</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"122250\">\n" +
                    "<name primary=\"true\">\n" +
                    "Die Siedler von Catan: Das Kartenspiel – Sonderkarte 2002 – Johanna, die Kriegerin\n" +
                    "</name>\n" +
                    "<yearpublished>2002</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"97596\">\n" +
                    "<name primary=\"true\">\n" +
                    "Die Siedler von Catan: Das Kartenspiel – Sonderkarte 2003 – Guido, der große Diplomat\n" +
                    "</name>\n" +
                    "<yearpublished>2003</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"97594\">\n" +
                    "<name primary=\"true\">\n" +
                    "Die Siedler von Catan: Das Kartenspiel – Sonderkarte 2004 – Benjamin, der treue Vasall\n" +
                    "</name>\n" +
                    "<yearpublished>2004</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"97593\">\n" +
                    "<name primary=\"true\">\n" +
                    "Die Siedler von Catan: Das Kartenspiel – Sonderkarte 2005 – Irmgard, Bewahrerin des Lichts\n" +
                    "</name>\n" +
                    "<yearpublished>2005</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"97591\">\n" +
                    "<name primary=\"true\">\n" +
                    "Die Siedler von Catan: Das Kartenspiel – Sonderkarte 2006 – Emi'Ly, Bewahrerin der Harmonie\n" +
                    "</name>\n" +
                    "<yearpublished>2006</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"97590\">\n" +
                    "<name primary=\"true\">\n" +
                    "Die Siedler von Catan: Das Kartenspiel – Sonderkarte 2007 – Arnd, der findige Knappe\n" +
                    "</name>\n" +
                    "<yearpublished>2007</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"97541\">\n" +
                    "<name primary=\"true\">\n" +
                    "Die Siedler von Catan: Das Kartenspiel – Sonderkarte 2008 – Gustav und Reiner, Helfer der Ratlosen\n" +
                    "</name>\n" +
                    "<yearpublished>2008</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"97540\">\n" +
                    "<name primary=\"true\">\n" +
                    "Die Siedler von Catan: Das Kartenspiel – Sonderkarte 2009 – Franz vohm Winkel\n" +
                    "</name>\n" +
                    "<yearpublished>2009</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"137594\">\n" +
                    "<name primary=\"true\">Die Siedler von Catan: Das Mini-Spiel</name>\n" +
                    "<yearpublished>2010</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"132700\">\n" +
                    "<name primary=\"true\">\n" +
                    "Die Siedler von Catan: Das schnelle Kartenspiel – Stadtausbau Kathedrale\n" +
                    "</name>\n" +
                    "<yearpublished>2012</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"5227\">\n" +
                    "<name primary=\"true\">\n" +
                    "Die Siedler von Catan: Das Turnier-Set zum Kartenspiel\n" +
                    "</name>\n" +
                    "<yearpublished>1997</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"393302\">\n" +
                    "<name primary=\"true\">\n" +
                    "Die Siedler von Catan: Das Turnier-Set zum Kartenspiel – Updatepack\n" +
                    "</name>\n" +
                    "<yearpublished>1998</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"84977\">\n" +
                    "<name primary=\"true\">Die Siedler von Catan: Der Schokoladenmarkt</name>\n" +
                    "<yearpublished>2010</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"26079\">\n" +
                    "<name primary=\"true\">Die Siedler von Catan: Die große Karawane</name>\n" +
                    "<yearpublished>2006</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"37969\">\n" +
                    "<name>Die Siedler von Catan: Die Siedler von Luxemburg</name>\n" +
                    "<yearpublished>2008</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"64159\">\n" +
                    "<name primary=\"true\">Die Siedler von Catan: Einsteiger-Variante</name>\n" +
                    "<yearpublished>2009</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"167836\">\n" +
                    "<name primary=\"true\">Die Siedler von Catan: Hispania Edition</name>\n" +
                    "<yearpublished>2014</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"306\">\n" +
                    "<name primary=\"true\">Die Siedler von Catan: Historische Szenarien</name>\n" +
                    "<yearpublished>1998</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"1361\">\n" +
                    "<name primary=\"true\">Die Siedler von Catan: Historische Szenarien II</name>\n" +
                    "<yearpublished>2001</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"167841\">\n" +
                    "<name primary=\"true\">Die Siedler von Catan: Hochzeitsturm</name>\n" +
                    "<yearpublished>2014</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"27766\">\n" +
                    "<name primary=\"true\">Die Siedler von Catan: Junior</name>\n" +
                    "<yearpublished>2007</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"29715\">\n" +
                    "<name primary=\"true\">\n" +
                    "Die Siedler von Catan: Kartenspiel – Handel & Wandel\n" +
                    "</name>\n" +
                    "<yearpublished>1999</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"155354\">\n" +
                    "<name primary=\"true\">\n" +
                    "Die Siedler von Catan: Kartenspiel – Kämpfer & Kaufleute\n" +
                    "</name>\n" +
                    "<yearpublished>2003</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"154961\">\n" +
                    "<name primary=\"true\">\n" +
                    "Die Siedler von Catan: Kartenspiel – Magier & Forscher\n" +
                    "</name>\n" +
                    "<yearpublished>2003</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"29716\">\n" +
                    "<name primary=\"true\">\n" +
                    "Die Siedler von Catan: Kartenspiel – Politik & Intrige\n" +
                    "</name>\n" +
                    "<yearpublished>1999</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"29718\">\n" +
                    "<name primary=\"true\">\n" +
                    "Die Siedler von Catan: Kartenspiel – Ritter & Händler\n" +
                    "</name>\n" +
                    "<yearpublished>1999</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"29717\">\n" +
                    "<name primary=\"true\">\n" +
                    "Die Siedler von Catan: Kartenspiel – Wissenschaft & Fortschritt\n" +
                    "</name>\n" +
                    "<yearpublished>1999</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"29714\">\n" +
                    "<name primary=\"true\">\n" +
                    "Die Siedler von Catan: Kartenspiel – Zauberer & Drachen\n" +
                    "</name>\n" +
                    "<yearpublished>1999</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"19008\">\n" +
                    "<name primary=\"true\">Die Siedler von Catan: Paper & Pencil</name>\n" +
                    "<yearpublished>2005</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"31933\">\n" +
                    "<name primary=\"true\">\n" +
                    "Die Siedler von Catan: Renaissance in der Steiermark & Burgbau auf Chaffenberch\n" +
                    "</name>\n" +
                    "<yearpublished>2007</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"37690\">\n" +
                    "<name primary=\"true\">\n" +
                    "Die Siedler von Catan: Rincewind und der Tourist / Die Gilden von Ankh-Morpork\n" +
                    "</name>\n" +
                    "<yearpublished>2008</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"111377\">\n" +
                    "<name primary=\"true\">Die Siedler von Catan: Thüringen Edition</name>\n" +
                    "<yearpublished>2011</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"399807\">\n" +
                    "<name primary=\"true\">The Siege (fan expansion for Catan)</name>\n" +
                    "<yearpublished>2023</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"24511\">\n" +
                    "<name primary=\"true\">Simply Catan</name>\n" +
                    "<yearpublished>2006</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"117985\">\n" +
                    "<name primary=\"true\">Star Trek: Catan</name>\n" +
                    "<yearpublished>2012</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"143948\">\n" +
                    "<name primary=\"true\">Star Trek: Catan – Federation Space</name>\n" +
                    "<yearpublished>2013</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"1897\">\n" +
                    "<name primary=\"true\">The Starfarers of Catan</name>\n" +
                    "<yearpublished>1999</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"4100\">\n" +
                    "<name primary=\"true\">Starfarers of Catan: 5-6 Player Expansion</name>\n" +
                    "<yearpublished>2001</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"2338\">\n" +
                    "<name primary=\"true\">Starship Catan</name>\n" +
                    "<yearpublished>2001</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"28115\">\n" +
                    "<name primary=\"true\">Starship Catan: 1st Mission – The Space Amoeba</name>\n" +
                    "<yearpublished>2003</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"28201\">\n" +
                    "<name primary=\"true\">Starship Catan: 2. Mission – The Asteroid</name>\n" +
                    "<yearpublished>2003</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"28200\">\n" +
                    "<name primary=\"true\">\n" +
                    "Starship Catan: 3. Mission – The Diplomatic Station\n" +
                    "</name>\n" +
                    "<yearpublished>2004</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"91534\">\n" +
                    "<name primary=\"true\">Struggle for Catan</name>\n" +
                    "<yearpublished>2011</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"144418\">\n" +
                    "<name primary=\"true\">Wien Catan</name>\n" +
                    "<yearpublished>2013</yearpublished>\n" +
                    "</boardgame>\n" +
                    "<boardgame objectid=\"134277\">\n" +
                    "<name primary=\"true\">World Wonders (fan expansion for Catan)</name>\n" +
                    "<yearpublished>2012</yearpublished>\n" +
                    "</boardgame>\n" +
                    "</boardgames>";
            ResponseEntity<String> searchResponseEntity = new ResponseEntity<>(searchResponse, HttpStatus.OK);

            Mockito.when(restTemplateMock.getForEntity(
                    eq("https://www.boardgamegeek.com/xmlapi/search?type=boardgame&query=" + searchGameTitle),
                    eq(String.class)
            )).thenReturn(searchResponseEntity);

            // Mock the response for the game details API call
            String gameDetailsResponse = "<boardgames termsofuse=\"https://boardgamegeek.com/xmlapi/termsofuse\">" +
                    "<boardgame objectid=\"13\">\n" +
                    "<yearpublished>1995</yearpublished>\n" +
                    "<minplayers>3</minplayers>\n" +
                    "<maxplayers>4</maxplayers>\n" +
                    "<playingtime>120</playingtime>\n" +
                    "<minplaytime>60</minplaytime>\n" +
                    "<maxplaytime>120</maxplaytime>\n" +
                    "<age>10</age>\n" +
                    "<name sortindex=\"1\">Catan</name>\n" +
                    "<name primary=\"true\" sortindex=\"1\">CATAN</name>\n" +
                    "<name sortindex=\"1\">Catan (Колонизаторы)</name>\n" +
                    "<name sortindex=\"1\">Catan telepesei</name>\n" +
                    "<name sortindex=\"1\">Catan: Das Spiel</name>\n" +
                    "<name sortindex=\"1\">Catan: Die Bordspel</name>\n" +
                    "<name sortindex=\"1\">Catan: El Juego</name>\n" +
                    "<name sortindex=\"1\">Catan: Gra planszowa</name>\n" +
                    "<name sortindex=\"1\">Catan: Il Gioco</name>\n" +
                    "<name sortindex=\"1\">Catan: Landnemarnir</name>\n" +
                    "<name sortindex=\"1\">Catan: O Jogo</name>\n" +
                    "<name sortindex=\"1\">Catan: Osnovna igra</name>\n" +
                    "<name sortindex=\"1\">Catan: Základní hra</name>\n" +
                    "<name sortindex=\"1\">Catane</name>\n" +
                    "<name sortindex=\"1\">Catanin Uudisasukkaat</name>\n" +
                    "<name sortindex=\"3\">I Coloni di Catan</name>\n" +
                    "<name sortindex=\"3\">I Coloni di Katan</name>\n" +
                    "<name sortindex=\"1\">Coloniștii din Catan</name>\n" +
                    "<name sortindex=\"1\">Colonizadores de Catan</name>\n" +
                    "<name sortindex=\"5\">Los Colonos de Catán</name>\n" +
                    "<name sortindex=\"5\">Les Colons de Catane</name>\n" +
                    "<name sortindex=\"5\">Les Colons de Katane</name>\n" +
                    "<name sortindex=\"4\">Os Descobridores de Catan</name>\n" +
                    "<name sortindex=\"5\">Los Descubridores de Catán</name>\n" +
                    "<name sortindex=\"1\">Els Colons de Catan</name>\n" +
                    "<name sortindex=\"1\">Katan</name>\n" +
                    "<name sortindex=\"1\">Katan no Kaitakusya</name>\n" +
                    "<name sortindex=\"1\">Katanas ieceïotâji</name>\n" +
                    "<name sortindex=\"1\">Katanas Ieceļotāji</name>\n" +
                    "<name sortindex=\"1\">Katani Asustajad</name>\n" +
                    "<name sortindex=\"1\">Katano salos naujakuriai</name>\n" +
                    "<name sortindex=\"1\">Katano Salos Naujakuriai</name>\n" +
                    "<name sortindex=\"4\">De Kolonisten van Catan</name>\n" +
                    "<name sortindex=\"1\">Naseljenci otoka Catan</name>\n" +
                    "<name sortindex=\"1\">Naseljenici ostrva Katan</name>\n" +
                    "<name sortindex=\"1\">Naseljenici ostrva Katan / Насељеници острва Катан</name>\n" +
                    "<name sortindex=\"1\">Naseljenici Otoka Catan</name>\n" +
                    "<name sortindex=\"5\">Die Ontdekkers van Catan</name>\n" +
                    "<name sortindex=\"1\">Osadníci z Katanu</name>\n" +
                    "<name sortindex=\"1\">Osadnicy z Catanu</name>\n" +
                    "<name sortindex=\"5\">The Settlers</name>\n" +
                    "<name sortindex=\"1\">Settlers fra Catan</name>\n" +
                    "<name sortindex=\"5\">The Settlers fra Catan</name>\n" +
                    "<name sortindex=\"1\">Settlers från Catan</name>\n" +
                    "<name sortindex=\"5\">The Settlers från Catan</name>\n" +
                    "<name sortindex=\"5\">The Settlers of Catan</name>\n" +
                    "<name sortindex=\"5\">Die Siedler von Catan</name>\n" +
                    "<name sortindex=\"1\">Οι Άποικοι του Κατάν</name>\n" +
                    "<name sortindex=\"1\">Заселниците на Катан</name>\n" +
                    "<name sortindex=\"1\">Колонизаторы</name>\n" +
                    "<name sortindex=\"1\">המתיישבים של קטאן</name>\n" +
                    "<name sortindex=\"1\">นักบุกเบิกแห่ง Catan</name>\n" +
                    "<name sortindex=\"1\">カタンの開拓者</name>\n" +
                    "<name sortindex=\"1\">カタンの開拓者たち</name>\n" +
                    "<name sortindex=\"1\">カタンの開拓者たち スタンダード版</name>\n" +
                    "<name sortindex=\"1\">卡坦 : 基礎</name>\n" +
                    "<name sortindex=\"1\">卡坦岛</name>\n" +
                    "<name sortindex=\"1\">卡坦島</name>\n" +
                    "<name sortindex=\"1\">카탄</name>\n" +
                    "<name sortindex=\"1\">카탄: 보드게임의 제왕</name>\n" +
                    "<name sortindex=\"1\">카탄의 개척자</name>\n" +
                    "<description>\n" +
                    "In CATAN (formerly The Settlers of Catan), players try to be the dominant force on the island of Catan by building settlements, cities, and roads. On each turn dice are rolled to determine what resources the island produces. Players build by spending resources (sheep, wheat, wood, brick and ore) that are depicted by these resource cards; each land type, with the exception of the unproductive desert, produces a specific resource: hills produce brick, forests produce wood, mountains produce ore, fields produce wheat, and pastures produce sheep.<br/><br/>Set-up includes randomly placing large hexagonal tiles (each showing a resource or the desert) in a honeycomb shape and surrounding them with water tiles, some of which contain ports of exchange. Number disks, which will correspond to die rolls (two 6-sided dice are used), are placed on each resource tile. Each player is given two settlements (think: houses) and roads (sticks) which are, in turn, placed on intersections and borders of the resource tiles. Players collect a hand of resource cards based on which hex tiles their last-placed house is adjacent to. A robber pawn is placed on the desert tile.<br/><br/>A turn consists of possibly playing a development card, rolling the dice, everyone (perhaps) collecting resource cards based on the roll and position of houses (or upgraded cities&mdash;think: hotels) unless a 7 is rolled, turning in resource cards (if possible and desired) for improvements, trading cards at a port, and trading resource cards with other players. If a 7 is rolled, the active player moves the robber to a new hex tile and steals resource cards from other players who have built structures adjacent to that tile.<br/><br/>Points are accumulated by building settlements and cities, having the longest road and the largest army (from some of the development cards), and gathering certain development cards that simply award victory points. When a player has gathered 10 points (some of which may be held in secret), he announces his total and claims the win.<br/><br/>CATAN has won multiple awards and is one of the most popular games in recent history due to its amazing ability to appeal to experienced gamers as well as those new to the hobby.<br/><br/>Die Siedler von Catan was originally published by KOSMOS and has gone through multiple editions. It was licensed by Mayfair and has undergone four editions as The Settlers of Catan. In 2015, it was formally renamed CATAN to better represent itself as the core and base game of the CATAN series. It has been re-published in two travel editions, portable edition and compact edition, as a special gallery edition (replaced in 2009 with a family edition), as an anniversary wooden edition, as a deluxe 3D collector's edition, in the basic Simply Catan, as a beginner version, and with an entirely new theme in Japan and Asia as Settlers of Catan: Rockman Edition. Numerous spin-offs and expansions have also been made for the game.<br/><br/>\n" +
                    "</description>\n" +
                    "<thumbnail>\n" +
                    "https://cf.geekdo-images.com/W3Bsga_uLP9kO91gZ7H8yw__thumb/img/8a9HeqFydO7Uun_le9bXWPnidcA=/fit-in/200x150/filters:strip_icc()/pic2419375.jpg\n" +
                    "</thumbnail>\n" +
                    "<image>\n" +
                    "https://cf.geekdo-images.com/W3Bsga_uLP9kO91gZ7H8yw__original/img/xV7oisd3RQ8R-k18cdWAYthHXsA=/0x0/filters:format(jpeg)/pic2419375.jpg\n" +
                    "</image>\n" +
                    "<boardgamepublisher objectid=\"37\">KOSMOS</boardgamepublisher>\n" +
                    "<boardgamepodcastepisode objectid=\"403923\">#10 - This Years Challenge March 1 of 2</boardgamepodcastepisode>\n" +
                    "<boardgamepodcastepisode objectid=\"176324\">\n" +
                    "#15: Catan on tap, Seeland, ...and then we held hands, bottle shares\n" +
                    "</boardgamepodcastepisode>\n" +
                    "<cardset objectid=\"84288\">15th Anniversary Edition</cardset>\n" +
                    "<boardgamehonor objectid=\"19836\">\n" +
                    "1995 Deutscher Spiele Preis Best Family/Adult Game Winner\n" +
                    "</boardgamehonor>\n" +
                    "<boardgamehonor objectid=\"19705\">1995 Essener Feder Best Written Rules Winner</boardgamehonor>\n" +
                    "<boardgamehonor objectid=\"13411\">1995 Meeples' Choice Award</boardgamehonor>\n" +
                    "<boardgamehonor objectid=\"8678\">1995 Spiel des Jahres Winner</boardgamehonor>\n" +
                    "<boardgamehonor objectid=\"12999\">\n" +
                    "1996 Origins Awards Best Fantasy or Science Fiction Board Game Winner\n" +
                    "</boardgamehonor>\n" +
                    "<boardgameexpansion objectid=\"167903\">20 Jahre Darmstadt Spielt</boardgameexpansion>\n" +
                    "<boardgamehonor objectid=\"20960\">2001 Origins Awards Hall of Fame Inductee</boardgamehonor>\n" +
                    "<boardgamehonor objectid=\"19625\">\n" +
                    "2002 Japan Boardgame Prize Best Japanese Game Nominee\n" +
                    "</boardgamehonor>\n" +
                    "<boardgamehonor objectid=\"13155\">2004 Hra roku Nominee</boardgamehonor>\n" +
                    "<boardgamehonor objectid=\"13156\">2004 Hra roku Winner</boardgamehonor>\n" +
                    "<boardgamehonor objectid=\"10365\">2005 Gra Roku Game of the Year Winner</boardgamehonor>\n" +
                    "<boardgamehonor objectid=\"65419\">\n" +
                    "2011 Jocul Anului în România Best Game in Romanian Finalist\n" +
                    "</boardgamehonor>\n" +
                    "<boardgamehonor objectid=\"65424\">\n" +
                    "2011 Jocul Anului în România Best Game in Romanian Winner\n" +
                    "</boardgamehonor>\n" +
                    "<boardgamehonor objectid=\"22601\">\n" +
                    "2011 Ludo Award Best Board Game Editor's Choice Winner\n" +
                    "</boardgamehonor>\n" +
                    "<boardgamehonor objectid=\"21707\">\n" +
                    "2012 JoTa Best Game Released in Brazil Critic Award\n" +
                    "</boardgamehonor>\n" +
                    "<boardgamehonor objectid=\"21716\">2012 JoTa Best Game Released in Brazil Nominee</boardgamehonor>\n" +
                    "<boardgamepodcastepisode objectid=\"174614\">\n" +
                    "3. Devon Dice Podcast Presents... a review of 2015 part 1\n" +
                    "</boardgamepodcastepisode>\n" +
                    "<boardgamepodcastepisode objectid=\"205070\">\n" +
                    "44 – The Family Gamers Podcast – Hosting a Game Night\n" +
                    "</boardgamepodcastepisode>\n" +
                    "<boardgamepublisher objectid=\"267\">999 Games</boardgamepublisher>\n" +
                    "<boardgameversion objectid=\"416798\">Afrikaans edition</boardgameversion>\n" +
                    "<boardgameversion objectid=\"467634\">Afrikaans edition</boardgameversion>\n" +
                    "<boardgamepublisher objectid=\"4304\">Albi</boardgamepublisher>\n" +
                    "<boardgamefamily objectid=\"7481\">Animals: Sheep</boardgamefamily>\n" +
                    "<boardgameversion objectid=\"485967\">Arabic/English edition</boardgameversion>\n" +
                    "<boardgamepublisher objectid=\"157\">Asmodee</boardgamepublisher>\n" +
                    "<boardgamepublisher objectid=\"7340\">Astrel Games</boardgamepublisher>\n" +
                    "<boardgameimplementation objectid=\"123386\">Baden-Württemberg Catan</boardgameimplementation>\n" +
                    "<boardgameartist objectid=\"12036\">Volkan Baga</boardgameartist>\n" +
                    "<boardgameversion objectid=\"24623\">Baltic first edition</boardgameversion>\n" +
                    "<boardgameversion objectid=\"350700\">Baltic second edition</boardgameversion>\n" +
                    "<boardgameversion objectid=\"349585\">Basque edition</boardgameversion>\n" +
                    "<boardgamepodcastepisode objectid=\"135834\">BGA Ep 54 - If You Like Settlers of Catan, Try...</boardgamepodcastepisode>\n" +
                    "<boardgamepodcastepisode objectid=\"177525\">BGA Episode 100 - Top 100 Games of All Time</boardgamepodcastepisode>\n" +
                    "<boardgamepodcastepisode objectid=\"86305\">\n" +
                    "BGTG - May 2, 2005 (SR: Settlers, Dancing Dice, Basari, Ra, San Marco, Xe Queo!, Ingenious, Star Wars Ep. 2 Card Game, Kapitan Wackelpudding)\n" +
                    "</boardgamepodcastepisode>\n" +
                    "<boardgamepodcastepisode objectid=\"86191\">BGTG 115 - Spiel des Jahres, Then &amp; Now</boardgamepodcastepisode>\n" +
                    "<boardgamepodcastepisode objectid=\"3316\">BGWS 067 – Dicing for Resources</boardgamepodcastepisode>\n" +
                    "<boardgamepodcastepisode objectid=\"21487\">BGWS 070: Breaking Up the Monopoly</boardgamepodcastepisode>\n" +
                    "<boardgamepodcastepisode objectid=\"325098\">Black Friday Bonus Episode</boardgamepodcastepisode>\n" +
                    "<boardgamepodcastepisode objectid=\"184704\">Boards Alive Monster Mash-Up - Episode 6</boardgamepodcastepisode>\n" +
                    "<boardgamepodcastepisode objectid=\"59712\">Bonus Episode - Dice Decks</boardgamepodcastepisode>\n" +
                    "<boardgamepublisher objectid=\"7162\">Brain Games</boardgamepublisher>\n" +
                    "<boardgameexpansion objectid=\"178656\">Brettspiel Adventskalender 2015</boardgameexpansion>\n" +
                    "<boardgamepublisher objectid=\"9068\">Broadway Toys LTD</boardgamepublisher>\n" +
                    "<boardgameversion objectid=\"213544\">Bulgarian edition</boardgameversion>\n" +
                    "<boardgameversion objectid=\"296764\">Bulgarian edition 2013</boardgameversion>\n" +
                    "<boardgamepublisher objectid=\"4358\">Capcom Co., Ltd.</boardgamepublisher>\n" +
                    "<boardgameversion objectid=\"467003\">Capcom Japanese edition</boardgameversion>\n" +
                    "<boardgameversion objectid=\"65814\">Catalan edition</boardgameversion>\n" +
                    "<videogamebg objectid=\"69075\">Catan (2007)</videogamebg>\n" +
                    "<videogamebg objectid=\"227878\">Catan (2017 / Mobile)</videogamebg>\n" +
                    "<boardgamecompilation objectid=\"17419\">CATAN 3D Collector's Edition</boardgamecompilation>\n" +
                    "<boardgameexpansion objectid=\"26352\">Catan Austria / Wien meets Catan</boardgameexpansion>\n" +
                    "<videogamebg objectid=\"141934\">Catan Creators Edition</videogamebg>\n" +
                    "<boardgameexpansion objectid=\"86008\">Catan Geographies: Austria</boardgameexpansion>\n" +
                    "<boardgameexpansion objectid=\"60134\">Catan Geographies: Bayern Edition</boardgameexpansion>\n" +
                    "<boardgameexpansion objectid=\"149857\">Catan Geographies: Corsica</boardgameexpansion>\n" +
                    "<boardgameexpansion objectid=\"173651\">Catan Geographies: Georgia</boardgameexpansion>\n" +
                    "<boardgameimplementation objectid=\"38749\">Catan Geographies: Germany</boardgameimplementation>\n" +
                    "<boardgameexpansion objectid=\"202788\">Catan Geographies: Kennessee</boardgameexpansion>\n" +
                    "<boardgameexpansion objectid=\"131362\">Catan Geographies: Mallorca</boardgameexpansion>\n" +
                    "<boardgameexpansion objectid=\"39093\">Catan Geographies: North Rhine – Westphalia</boardgameexpansion>\n" +
                    "<boardgameexpansion objectid=\"187366\">Catan Geographies: Rickshaw Run</boardgameexpansion>\n" +
                    "<boardgameexpansion objectid=\"32270\">Catan Geographies: Settlers of Hesse</boardgameexpansion>\n" +
                    "<boardgameexpansion objectid=\"169486\">Catan Geographies: The Carolinas</boardgameexpansion>\n" +
                    "<boardgameimplementation objectid=\"103091\">Catan Histories: Merchants of Europe</boardgameimplementation>\n" +
                    "<boardgameimplementation objectid=\"244144\">Catan Histories: Rise of the Inkas</boardgameimplementation>\n" +
                    "<boardgameimplementation objectid=\"67239\">\n" +
                    "Catan Histories: Settlers of America – Trails to Rails\n" +
                    "</boardgameimplementation>\n" +
                    "<boardgameimplementation objectid=\"25234\">Catan Histories: Struggle for Rome</boardgameimplementation>\n" +
                    "<boardgameexpansion objectid=\"211081\">Catan Länderszenarien: Polen</boardgameexpansion>\n" +
                    "<boardgameexpansion objectid=\"292851\">Catan Rhein-Main-Neckar</boardgameexpansion>\n" +
                    "<boardgameexpansion objectid=\"259397\">Catan Scenario: Crop Trust</boardgameexpansion>\n" +
                    "<boardgameexpansion objectid=\"222582\">Catan Scenario: Durango</boardgameexpansion>\n" +
                    "<boardgameexpansion objectid=\"368580\">Catan Scenario: Fenni's Shearing Season</boardgameexpansion>\n" +
                    "<boardgameexpansion objectid=\"374983\">Catan Scenario: Malta</boardgameexpansion>\n" +
                    "<boardgameexpansion objectid=\"189097\">Catan Scenarios: Easter Bunny</boardgameexpansion>\n" +
                    "<boardgameexpansion objectid=\"307518\">Catan Scenarios: #WeStayHome</boardgameexpansion>\n" +
                    "<boardgameexpansion objectid=\"186395\">Catan Scenarios: Big Game Big Honor</boardgameexpansion>\n" +
                    "<boardgameexpansion objectid=\"196928\">Catan Scenarios: Catanimals</boardgameexpansion>\n" +
                    "<boardgameexpansion objectid=\"131958\">Catan Scenarios: Frenemies</boardgameexpansion>\n" +
                    "<boardgameexpansion objectid=\"305516\">Catan Scenarios: Global Warming</boardgameexpansion>\n" +
                    "<boardgameexpansion objectid=\"86669\">Catan Scenarios: Helpers of Catan</boardgameexpansion>\n" +
                    "<boardgameexpansion objectid=\"110794\">Catan Scenarios: Oil Springs</boardgameexpansion>\n" +
                    "<boardgamepublisher objectid=\"31418\">Catan Studio</boardgamepublisher>\n" +
                    "<boardgameversion objectid=\"347121\">Catan Studio English edition 2016</boardgameversion>\n" +
                    "<boardgameversion objectid=\"495726\">Catan Studio English edition 2019</boardgameversion>\n" +
                    "<boardgameversion objectid=\"568647\">Catan Studio English edition 2020</boardgameversion>\n" +
                    "<videogamebg objectid=\"240406\">Catan Universe</videogamebg>\n" +
                    "<boardgameaccessory objectid=\"399943\">\n" +
                    "Catan x Goat Simulator 3: Resource Replacement Cards\n" +
                    "</boardgameaccessory>\n" +
                    "<boardgamecompilation objectid=\"298278\">Catan: 25 Jahre Jubiläums-Edition</boardgamecompilation>\n" +
                    "<boardgamecompilation objectid=\"305668\">Catan: 25th Anniversary Edition</boardgamecompilation>\n" +
                    "<boardgameaccessory objectid=\"273558\">Catan: 3D Clay Pieces</boardgameaccessory>\n" +
                    "<boardgamecompilation objectid=\"338697\">CATAN: 3D Edition</boardgamecompilation>\n" +
                    "<boardgameexpansion objectid=\"2807\">Catan: 5-6 Player Extension</boardgameexpansion>\n" +
                    "<boardgameexpansion objectid=\"183139\">Catan: 999 Games 25 jaar Expansion</boardgameexpansion>\n" +
                    "<boardgameimplementation objectid=\"161527\">Catan: Ancient Egypt</boardgameimplementation>\n" +
                    "<boardgameaccessory objectid=\"280966\">Catan: Autumn Catan Hexes</boardgameaccessory>\n" +
                    "<boardgamecompilation objectid=\"182880\">Catan: Big Box</boardgamecompilation>\n" +
                    "<boardgamecompilation objectid=\"191710\">Catan: Big Box</boardgamecompilation>\n" +
                    "<boardgamecompilation objectid=\"269980\">Catan: Big Box</boardgamecompilation>\n" +
                    "<boardgamecompilation objectid=\"394723\">CATAN: Big Box</boardgamecompilation>\n" +
                    "<boardgamecompilation objectid=\"265030\">Catan: Big Box Jubileumeditie</boardgamecompilation>\n" +
                    "<boardgameimplementation objectid=\"194097\">Catan: Big Game Event Kit</boardgameimplementation>\n" +
                    "<boardgameaccessory objectid=\"145942\">Catan: Boards</boardgameaccessory>\n" +
                    "<boardgameexpansion objectid=\"104774\">Catan: Catakatoa</boardgameexpansion>\n" +
                    "<boardgameexpansion objectid=\"176966\">Catan: Catan Day 2015 Exclusive Expansion</boardgameexpansion>\n" +
                    "<boardgameexpansion objectid=\"926\">CATAN: Cities & Knights</boardgameexpansion>\n" +
                    "<boardgameexpansion objectid=\"4101\">Catan: Cities & Knights – 5-6 Player Extension</boardgameexpansion>\n" +
                    "<boardgameexpansion objectid=\"265141\">Catan: Cities & Knights – Legend of the Conquerors</boardgameexpansion>\n" +
                    "<boardgameaccessory objectid=\"140408\">Catan: Cities & Knights – Wooden Viking Pieces</boardgameaccessory>\n" +
                    "<boardgameimplementation objectid=\"284815\">Catan: Core + China Map</boardgameimplementation>\n" +
                    "<boardgameexpansion objectid=\"73809\">Catan: Delmarva</boardgameexpansion>\n" +
                    "<boardgameexpansion objectid=\"352073\">Catan: Die Stoffräuber</boardgameexpansion>\n" +
                    "<boardgameaccessory objectid=\"306290\">Catan: e-Raptor Insert</boardgameaccessory>\n" +
                    "<boardgameexpansion objectid=\"20038\">Catan: Event Cards</boardgameexpansion>\n" +
                    "<boardgameexpansion objectid=\"135378\">Catan: Explorers & Pirates</boardgameexpansion>\n" +
                    "<boardgameexpansion objectid=\"144419\">Catan: Explorers & Pirates – 5-6 Player Extension</boardgameexpansion>\n" +
                    "<boardgameaccessory objectid=\"173657\">Catan: Explorers & Pirates – Wooden Viking Pieces</boardgameaccessory>\n" +
                    "<boardgameimplementation objectid=\"147240\">Catan: Family Edition</boardgameimplementation>\n" +
                    "<boardgameaccessory objectid=\"399098\">CATAN: Family Promo Card</boardgameaccessory>\n" +
                    "<boardgameexpansion objectid=\"238135\">CATAN: Hawai'i Scenario</boardgameexpansion>\n" +
                    "<boardgameaccessory objectid=\"365835\">Catan: Hexadocks Base Set</boardgameaccessory>\n" +
                    "<boardgameaccessory objectid=\"365837\">Catan: Hexadocks Extension Set</boardgameaccessory>\n" +
                    "<boardgameaccessory objectid=\"365755\">Catan: Hexatower</boardgameaccessory>\n" +
                    "<boardgameexpansion objectid=\"263939\">Catan: High Priests of the Inkas</boardgameexpansion>\n" +
                    "<boardgameexpansion objectid=\"54528\">Catan: Indiana & Ohio</boardgameexpansion>\n" +
                    "<boardgameaccessory objectid=\"346990\">Catan: Laserox Organizer</boardgameaccessory>\n" +
                    "<boardgameexpansion objectid=\"143249\">Catan: New England</boardgameexpansion>\n" +
                    "<boardgameexpansion objectid=\"132481\">Catan: New York</boardgameexpansion>\n" +
                    "<boardgameexpansion objectid=\"101942\">Catan: Penn-Jersey</boardgameexpansion>\n" +
                    "<boardgameaccessory objectid=\"243110\">Catan: Pewter Player Pieces</boardgameaccessory>\n" +
                    "<boardgameexpansion objectid=\"222396\">Catan: Playmat Atoll</boardgameexpansion>\n" +
                    "<boardgameexpansion objectid=\"222394\">Catan: Playmat Desert</boardgameexpansion>\n" +
                    "<boardgameexpansion objectid=\"222393\">Catan: Playmat Gold</boardgameexpansion>\n" +
                    "<boardgameexpansion objectid=\"222395\">Catan: Playmat Island</boardgameexpansion>\n" +
                    "<boardgameimplementation objectid=\"3972\">Catan: Portable Edition</boardgameimplementation>\n" +
                    "<boardgameaccessory objectid=\"273551\">Catan: Resource Token Set</boardgameaccessory>\n" +
                    "<boardgameaccessory objectid=\"273549\">Catan: Robber Token</boardgameaccessory>\n" +
                    "<boardgameexpansion objectid=\"325\">Catan: Seafarers</boardgameexpansion>\n" +
                    "<boardgameexpansion objectid=\"4103\">Catan: Seafarers – 5-6 Player Extension</boardgameexpansion>\n" +
                    "<boardgameaccessory objectid=\"140409\">Catan: Seafarers – Wooden Viking Pieces</boardgameaccessory>\n" +
                    "<boardgameexpansion objectid=\"223171\">\n" +
                    "Catan: Seafarers Scenario – Legend of the Sea Robbers\n" +
                    "</boardgameexpansion>\n" +
                    "<boardgameexpansion objectid=\"217947\">Catan: Seefahrer – 20 Jahre Jubiläums-Edition</boardgameexpansion>\n" +
                    "<boardgameaccessory objectid=\"271278\">Catan: Settlers Organizer</boardgameaccessory>\n" +
                    "<boardgameexpansion objectid=\"381626\">CATAN: Soccer Fever Scenario</boardgameexpansion>\n" +
                    "<boardgameimplementation objectid=\"282853\">CATAN: Starfarers</boardgameimplementation>\n" +
                    "<boardgameaccessory objectid=\"331315\">Catan: Summer Catan Hexes</boardgameaccessory>\n" +
                    "<boardgameexpansion objectid=\"271159\">Catan: Szenario Der Kölner Dom</boardgameexpansion>\n" +
                    "<boardgameaccessory objectid=\"139876\">Catan: Table of Catan</boardgameaccessory>\n" +
                    "<videogamebg objectid=\"84431\">Catan: The First Island</videogamebg>\n" +
                    "<boardgameexpansion objectid=\"367791\">CATAN: The Helpers</boardgameexpansion>\n" +
                    "<videogamebg objectid=\"80050\">Catan: The Seafarers</videogamebg>\n" +
                    "<boardgameaccessory objectid=\"273592\">Catan: Tower Rex Card Holder</boardgameaccessory>\n" +
                    "<boardgameexpansion objectid=\"27760\">CATAN: Traders & Barbarians</boardgameexpansion>\n" +
                    "<boardgameexpansion objectid=\"34691\">Catan: Traders & Barbarians – 5-6 Player Extension</boardgameexpansion>\n" +
                    "<boardgameaccessory objectid=\"365838\">Catan: Trading Post Convertible Card Tray</boardgameaccessory>\n" +
                    "<boardgameaccessory objectid=\"273557\">Catan: Trading Ship Figurines</boardgameaccessory>\n" +
                    "<boardgameimplementation objectid=\"172994\">Catan: Traveler – Compact Edition</boardgameimplementation>\n" +
                    "<boardgameexpansion objectid=\"56157\">CATAN: Treasures, Dragons & Adventurers</boardgameexpansion>\n" +
                    "<boardgameaccessory objectid=\"360479\">\n" +
                    "Catan: Treasures, Dragons & Adventurers – Wooden Pieces\n" +
                    "</boardgameaccessory>\n" +
                    "<boardgameaccessory objectid=\"182444\">Catan: Wooden Pieces Basic Set (4-Players)</boardgameaccessory>\n" +
                    "<boardgameaccessory objectid=\"182448\">Catan: Wooden Pieces for 5-6 Players</boardgameaccessory>\n" +
                    "<boardgameaccessory objectid=\"140406\">Catan: Wooden Viking Pieces (Basic and Expansion)</boardgameaccessory>\n" +
                    "<boardgameaccessory objectid=\"140407\">Catan: Wooden Viking Pieces (Basic Only)</boardgameaccessory>\n" +
                    "<boardgameaccessory objectid=\"141863\">Catan: Yucatan Adventure Board</boardgameaccessory>\n" +
                    "<boardgameaccessory objectid=\"140260\">Catan: Yucatan Wooden Token Set (125 Tokens)</boardgameaccessory>\n" +
                    "<boardgameaccessory objectid=\"140259\">Catan: Yucatan Wooden Token Set (95 Pack)</boardgameaccessory>\n" +
                    "<boardgameaccessory objectid=\"393266\">Catan: ZV3DCreations Insert</boardgameaccessory>\n" +
                    "<boardgamemechanic objectid=\"2956\">Chaining</boardgamemechanic>\n" +
                    "<boardgameversion objectid=\"567299\">Chinese edition</boardgameversion>\n" +
                    "<boardgameversion objectid=\"43527\">Chinese edition 2009</boardgameversion>\n" +
                    "<boardgameversion objectid=\"562983\">Chinese edition 2021</boardgameversion>\n" +
                    "<boardgameimplementation objectid=\"13831\">The Communication in Catan</boardgameimplementation>\n" +
                    "<boardgamepublisher objectid=\"4335\">Competo / Marektoy</boardgamepublisher>\n" +
                    "<cardset objectid=\"84286\">Competo Finnish eds (1995, 2011)</cardset>\n" +
                    "<boardgamefamily objectid=\"67874\">Components: Hexagonal Tiles</boardgamefamily>\n" +
                    "<boardgamefamily objectid=\"68769\">Components: Wooden pieces & boards</boardgamefamily>\n" +
                    "<boardgameversion objectid=\"280462\">Croatian edition</boardgameversion>\n" +
                    "<boardgameversion objectid=\"390664\">Croatian/Serbian/Slovenian edition</boardgameversion>\n" +
                    "<boardgamepodcastepisode objectid=\"187712\">\n" +
                    "Cult of the New Board Game Podcast Episode 026 – Random Top 10 Games\n" +
                    "</boardgamepodcastepisode>\n" +
                    "<boardgameversion objectid=\"29557\">Czech edition</boardgameversion>\n" +
                    "<boardgameversion objectid=\"674578\">Czech edition 2021</boardgameversion>\n" +
                    "<boardgameversion objectid=\"599539\">Czech/Slovak second edition</boardgameversion>\n" +
                    "<boardgameversion objectid=\"70067\">Danish edition</boardgameversion>\n" +
                    "<boardgameversion objectid=\"301655\">Danish edition 1996</boardgameversion>\n" +
                    "<boardgameversion objectid=\"274025\">Danish edition 2010</boardgameversion>\n" +
                    "<boardgameversion objectid=\"542435\">Danish edition 2015</boardgameversion>\n" +
                    "<boardgamepublisher objectid=\"496\">danspil</boardgamepublisher>\n" +
                    "<cardset objectid=\"84289\">\n" +
                    "Danspil 'The Settlers fra Catan' Danish 1st ed (1996)\n" +
                    "</cardset>\n" +
                    "<boardgamepodcastepisode objectid=\"107954\">DdJ 030. Michael Menzel</boardgamepodcastepisode>\n" +
                    "<boardgamepodcastepisode objectid=\"495740\">DDP 106 Trees, Bees, Worms, and space in-between</boardgamepodcastepisode>\n" +
                    "<boardgamepublisher objectid=\"41\">Descartes Editeur</boardgamepublisher>\n" +
                    "<boardgamepublisher objectid=\"2366\">Devir</boardgamepublisher>\n" +
                    "<boardgameversion objectid=\"517244\">Devir Portuguese edition 2019</boardgameversion>\n" +
                    "<boardgamepublisher objectid=\"26990\">Dexy Co</boardgamepublisher>\n" +
                    "<boardgamemechanic objectid=\"2072\">Dice Rolling</boardgamemechanic>\n" +
                    "<boardgamefamily objectid=\"70360\">Digital Implementations: Board Game Arena</boardgamefamily>\n" +
                    "<boardgamefamily objectid=\"77349\">Digital Implementations: Steam</boardgamefamily>\n" +
                    "<boardgameartist objectid=\"12382\">Tanja Donner</boardgameartist>\n" +
                    "<boardgamepodcastepisode objectid=\"107984\">DTS003 - Dice Tower Showdown, Episode # 3</boardgamepodcastepisode>\n" +
                    "<boardgameversion objectid=\"650135\">Dutch edition 2010</boardgameversion>\n" +
                    "<boardgameversion objectid=\"343852\">Dutch edition 2016</boardgameversion>\n" +
                    "<boardgameversion objectid=\"20781\">Dutch first edition 1998</boardgameversion>\n" +
                    "<boardgameversion objectid=\"33795\">Dutch second edition 1999</boardgameversion>\n" +
                    "<boardgameversion objectid=\"173152\">Dutch third edition 2012</boardgameversion>\n" +
                    "<boardgamecategory objectid=\"1021\">Economic</boardgamecategory>\n" +
                    "<boardgameversion objectid=\"20630\">English first edition 1996</boardgameversion>\n" +
                    "<boardgameversion objectid=\"23765\">English fourth edition 2007</boardgameversion>\n" +
                    "<boardgameversion objectid=\"261544\">English fourth edition 2012</boardgameversion>\n" +
                    "<boardgameversion objectid=\"42682\">\n" +
                    "English second edition with alternate colors and 8p optional rules\n" +
                    "</boardgameversion>\n" +
                    "<boardgameversion objectid=\"24859\">English third edition 1997</boardgameversion>\n" +
                    "<boardgameversion objectid=\"265696\">\n" +
                    "English third edition 2003 with B&W rules and card box\n" +
                    "</boardgameversion>\n" +
                    "<boardgameversion objectid=\"249269\">\n" +
                    "English third edition 2003 with color rules and card box\n" +
                    "</boardgameversion>\n" +
                    "<boardgamepublisher objectid=\"6784\">Enigma (Bergsala Enigma)</boardgamepublisher>\n" +
                    "<boardgamepodcastepisode objectid=\"168189\">Ep 017 – I, Geek interviews Seth Jaffee</boardgamepodcastepisode>\n" +
                    "<boardgamepodcastepisode objectid=\"171288\">Ep 022 – Ringing in 2016 with Board Gaming!</boardgamepodcastepisode>\n" +
                    "<boardgamepodcastepisode objectid=\"179856\">\n" +
                    "Ep 031 – Asmodee, Apps, and our Top 5 Euro Games of All Time\n" +
                    "</boardgamepodcastepisode>\n" +
                    "<boardgamepodcastepisode objectid=\"377454\">\n" +
                    "Ep 33- A Christmas Trainwreck &amp; An Important Announcement\n" +
                    "</boardgamepodcastepisode>\n" +
                    "<boardgamepodcastepisode objectid=\"473747\">Ep 34- We're Back! (Sort of)</boardgamepodcastepisode>\n" +
                    "<boardgamepodcastepisode objectid=\"450741\">Ep 37: Top 50 Games [50-41]</boardgamepodcastepisode>\n" +
                    "<boardgamepodcastepisode objectid=\"227764\">Ep. 13 - Friends Are Always Welcome!</boardgamepodcastepisode>\n" +
                    "<boardgamepodcastepisode objectid=\"227747\">Episode #29: Relaxing Games</boardgamepodcastepisode>\n" +
                    "<boardgamepodcastepisode objectid=\"99070\">Episode #40 - Top Ten Route-Building Games</boardgamepodcastepisode>\n" +
                    "<boardgamepodcastepisode objectid=\"436119\">\n" +
                    "Episode 0: Introduction &amp; Top 5 Games That Influenced Us\n" +
                    "</boardgamepodcastepisode>\n" +
                    "<boardgamepodcastepisode objectid=\"371997\">\n" +
                    "EPISODE 100!! Carcassonne, Scotland Yard, Risk: Godstorm, Cosmic Encounter, Acquire, Ticket to Ride, Arkham Horror, Catan, Lords of Waterdeep\n" +
                    "</boardgamepodcastepisode>\n" +
                    "<boardgamepodcastepisode objectid=\"370484\">Episode 117 - The Evolution of ‘Euros’</boardgamepodcastepisode>\n" +
                    "<boardgamepodcastepisode objectid=\"163512\">Episode 12 - Catan and Ticket to Ride</boardgamepodcastepisode>\n" +
                    "<boardgamepodcastepisode objectid=\"450650\">\n" +
                    "Episode 16: Spiel des Jahres Predictions and Top 5 Winners &amp; Nominees\n" +
                    "</boardgamepodcastepisode>\n" +
                    "<boardgamepodcastepisode objectid=\"118428\">Episode 17 – We review Settlers of Catan</boardgamepodcastepisode>\n" +
                    "<boardgamepodcastepisode objectid=\"253101\">\n" +
                    "Episode 2. Monopoly And Monopoly Deal - 5:08:2018 6.23 Pm\n" +
                    "</boardgamepodcastepisode>\n" +
                    "<boardgamepodcastepisode objectid=\"116654\">Episode 20: The Settlers of Catan/Time Management</boardgamepodcastepisode>\n" +
                    "<boardgamepodcastepisode objectid=\"170395\">Episode 24 - Effective Games Evangelism</boardgamepodcastepisode>\n" +
                    "<boardgamepodcastepisode objectid=\"174305\">Episode 24 - Holiday Gaming</boardgamepodcastepisode>\n" +
                    "<boardgamepodcastepisode objectid=\"370133\">Episode 28 - Negotiation Games</boardgamepodcastepisode>\n" +
                    "<boardgamepodcastepisode objectid=\"77849\">Episode 36 – The Settlers of Catan</boardgamepodcastepisode>\n" +
                    "<boardgamepodcastepisode objectid=\"117961\">Episode 37 - Sheep</boardgamepodcastepisode>\n" +
                    "<boardgamepodcastepisode objectid=\"11842\">Episode 4: Catan</boardgamepodcastepisode>\n" +
                    "<boardgamepodcastepisode objectid=\"181071\">Episode 41 - Eurogames and Ameritrash</boardgamepodcastepisode>\n" +
                    "<boardgamepodcastepisode objectid=\"187300\">Episode 7 - Spiel des Jahres</boardgamepodcastepisode>\n" +
                    "<boardgamepodcastepisode objectid=\"110473\">\n" +
                    "Episode 7 -- The Philosophy Hour, or Reasons Not To Be An Idiot.\n" +
                    "</boardgamepodcastepisode>\n" +
                    "<boardgamepodcastepisode objectid=\"116267\">Episode 72 March Craziness Dice Game Style Part 1</boardgamepodcastepisode>\n" +
                    "<boardgamepodcastepisode objectid=\"127464\">\n" +
                    "Episode 83-- Live from GEN CON Part 2-- from the show floor\n" +
                    "</boardgamepodcastepisode>\n" +
                    "<boardgamepodcastepisode objectid=\"325567\">Episode 94 - Did You Notice A Theme?</boardgamepodcastepisode>\n" +
                    "<boardgamepodcastepisode objectid=\"87237\">Episodio 7 – Los primeros juegos</boardgamepodcastepisode>\n" +
                    "<boardgameversion objectid=\"24583\">Estonian edition</boardgameversion>\n" +
                    "<boardgamepublisher objectid=\"116\">Eurogames</boardgamepublisher>\n" +
                    "<boardgamesubdomain objectid=\"5499\">Family Games</boardgamesubdomain>\n" +
                    "<boardgameartist objectid=\"13034\">Pete Fenlon</boardgameartist>\n" +
                    "<boardgamepublisher objectid=\"5657\">Filosofia Éditions</boardgamepublisher>\n" +
                    "<boardgameversion objectid=\"26517\">Finnish first edition with SdJ logo</boardgameversion>\n" +
                    "<boardgameversion objectid=\"93425\">Finnish second edition</boardgameversion>\n" +
                    "<boardgameversion objectid=\"410629\">French edition</boardgameversion>\n" +
                    "<boardgameversion objectid=\"27323\">French edition 1995</boardgameversion>\n" +
                    "<boardgameversion objectid=\"96056\">French edition 2002</boardgameversion>\n" +
                    "<boardgameversion objectid=\"56163\">French edition 2006</boardgameversion>\n" +
                    "<boardgameversion objectid=\"227973\">French edition 2006</boardgameversion>\n" +
                    "<boardgameversion objectid=\"57861\">French edition 2010</boardgameversion>\n" +
                    "<boardgamepublisher objectid=\"4617\">Galakta</boardgamepublisher>\n" +
                    "<cardset objectid=\"84287\">\n" +
                    "Galakta 'Osadnicy z Catanu' Polish 1st ed (2005) and Grow Jogos 'Colonizadores de Catan' Brazilian Portuguese 1st ed (2011)\n" +
                    "</cardset>\n" +
                    "<boardgameimplementation objectid=\"229218\">\n" +
                    "A Game of Thrones: Catan – Brotherhood of the Watch\n" +
                    "</boardgameimplementation>\n" +
                    "<boardgamepodcastepisode objectid=\"152198\">Game State Ep 2 - Is Catan A Good Gateway Game?</boardgamepodcastepisode>\n" +
                    "<boardgamefamily objectid=\"3\">Game: Catan</boardgamefamily>\n" +
                    "<boardgamepodcastepisode objectid=\"108719\">\n" +
                    "Garrett's Games 388 - Expansions and Alternatives: Qwirkle, Catan, and Carcassonne\n" +
                    "</boardgamepodcastepisode>\n" +
                    "<boardgamepodcastepisode objectid=\"161891\">Garrett's Games 481 - Meeplefest Night 2, Part 3</boardgamepodcastepisode>\n" +
                    "<boardgamepodcastepisode objectid=\"162207\">\n" +
                    "Garrett's Games 482 - Meeplefest Night 2, The Final Chapter\n" +
                    "</boardgamepodcastepisode>\n" +
                    "<boardgameversion objectid=\"20899\">German 10th anniversary edition</boardgameversion>\n" +
                    "<boardgameversion objectid=\"24505\">German edition 1998 with SdJ</boardgameversion>\n" +
                    "<boardgameversion objectid=\"24565\">German edition 2003</boardgameversion>\n" +
                    "<boardgameversion objectid=\"482670\">German edition 2007</boardgameversion>\n" +
                    "<boardgameversion objectid=\"91116\">German edition 2010 with plastic pieces</boardgameversion>\n" +
                    "<boardgameversion objectid=\"214260\">German edition 2013 with \"Play it Smart\" app</boardgameversion>\n" +
                    "<boardgameversion objectid=\"265909\">German edition 2015</boardgameversion>\n" +
                    "<boardgameversion objectid=\"679663\">German edition 2022</boardgameversion>\n" +
                    "<boardgameversion objectid=\"91120\">German edition with KOSMOS logo</boardgameversion>\n" +
                    "<boardgameversion objectid=\"24497\">German first edition with Franckh logo</boardgameversion>\n" +
                    "<boardgamepodcastepisode objectid=\"7853\">German Gaming News #12</boardgamepodcastepisode>\n" +
                    "<boardgameversion objectid=\"136094\">German Panasonic Promotional edition</boardgameversion>\n" +
                    "<boardgameversion objectid=\"24501\">German second edition with SdJ</boardgameversion>\n" +
                    "<boardgamepublisher objectid=\"5530\">Giochi Uniti</boardgamepublisher>\n" +
                    "<boardgamepublisher objectid=\"21640\">GP Games</boardgamepublisher>\n" +
                    "<boardgameversion objectid=\"29397\">Greek edition 2001</boardgameversion>\n" +
                    "<boardgameversion objectid=\"279532\">Greek edition 2015</boardgameversion>\n" +
                    "<boardgameversion objectid=\"206512\">Greek second edition 2011</boardgameversion>\n" +
                    "<boardgamepublisher objectid=\"858\">Grow Jogos e Brinquedos</boardgamepublisher>\n" +
                    "<boardgamepodcastepisode objectid=\"67354\">\n" +
                    "Guia de compras y recomendaciones de juegos de mesa 2011 Parte 1\n" +
                    "</boardgamepodcastepisode>\n" +
                    "<boardgameexpansion objectid=\"42147\">Der Hafenmeister</boardgameexpansion>\n" +
                    "<boardgamepublisher objectid=\"23382\">HaKubia</boardgamepublisher>\n" +
                    "<boardgamepublisher objectid=\"4417\">Hanayama</boardgamepublisher>\n" +
                    "<boardgameartist objectid=\"5442\">Jason Hawkins</boardgameartist>\n" +
                    "<boardgameversion objectid=\"177963\">Hebrew edition</boardgameversion>\n" +
                    "<boardgameexpansion objectid=\"128751\">\n" +
                    "Heroes & Capitols (fan expansion for Settlers of Catan)\n" +
                    "</boardgameexpansion>\n" +
                    "<boardgamemechanic objectid=\"2026\">Hexagon Grid</boardgamemechanic>\n" +
                    "<boardgameexpansion objectid=\"91061\">\n" +
                    "Hexen, Zauberer & Drachen (fan expansion for Catan: Cities and Knights)\n" +
                    "</boardgameexpansion>\n" +
                    "<boardgamepodcastepisode objectid=\"173281\">HLG 1: De Start</boardgamepodcastepisode>\n" +
                    "<boardgamepublisher objectid=\"18852\">Hobby World</boardgamepublisher>\n" +
                    "<boardgameversion objectid=\"30533\">Hungarian first edition</boardgameversion>\n" +
                    "<boardgameversion objectid=\"177964\">Hungarian second edition</boardgameversion>\n" +
                    "<boardgameversion objectid=\"310828\">Hungarian third edition</boardgameversion>\n" +
                    "<boardgamepodcastepisode objectid=\"342285\">Hvis du kan lide …</boardgamepodcastepisode>\n" +
                    "<boardgamepodcastepisode objectid=\"103961\">\n" +
                    "I've Been Diced! episode 47: Ignoble successes and serious SF games\n" +
                    "</boardgamepodcastepisode>\n" +
                    "<boardgamepodcastepisode objectid=\"446012\">I’ve Been Diced! episode 10: Gateway games</boardgamepodcastepisode>\n" +
                    "<boardgameversion objectid=\"36846\">Icelandic edition</boardgameversion>\n" +
                    "<boardgamepublisher objectid=\"8822\">Ideal Board Games</boardgamepublisher>\n" +
                    "<boardgamepublisher objectid=\"31372\">Igroljub</boardgamepublisher>\n" +
                    "<boardgamepodcastepisode objectid=\"128924\">Il Giocatore - Episodio 4 - Juegos Introductorios</boardgamepodcastepisode>\n" +
                    "<boardgameaccessory objectid=\"20890\">\n" +
                    "Im Zeichen des Sechsecks: Klaus Teuber & Die Siedler von Catan\n" +
                    "</boardgameaccessory>\n" +
                    "<boardgamemechanic objectid=\"2902\">Income</boardgamemechanic>\n" +
                    "<boardgamepublisher objectid=\"29036\">IntelliGames.BG</boardgamepublisher>\n" +
                    "<boardgamefamily objectid=\"79690\">Islands: Fictional</boardgamefamily>\n" +
                    "<boardgamepublisher objectid=\"5020\">Ísöld ehf.</boardgamepublisher>\n" +
                    "<boardgameversion objectid=\"85369\">Italian edition</boardgameversion>\n" +
                    "<boardgameversion objectid=\"299310\">Italian edition 2016</boardgameversion>\n" +
                    "<boardgameversion objectid=\"29559\">Italian first edition</boardgameversion>\n" +
                    "<boardgameversion objectid=\"53013\">Japanese edition 1996</boardgameversion>\n" +
                    "<boardgameversion objectid=\"47810\">Japanese edition 2004</boardgameversion>\n" +
                    "<boardgameversion objectid=\"263124\">Japanese edition 2010</boardgameversion>\n" +
                    "<boardgamepublisher objectid=\"6214\">Kaissa Chess & Games</boardgamepublisher>\n" +
                    "<boardgameexpansion objectid=\"95940\">Katani pankur</boardgameexpansion>\n" +
                    "<boardgameimplementation objectid=\"5824\">The Kids of Catan</boardgameimplementation>\n" +
                    "<boardgameartist objectid=\"26874\">Michaela Kienle</boardgameartist>\n" +
                    "<boardgameexpansion objectid=\"89606\">\n" +
                    "Kirche, Glaube & Reformation (fan expansion for Catan: Cities and Knights)\n" +
                    "</boardgameexpansion>\n" +
                    "<boardgamepodcastepisode objectid=\"274874\">Klemens Franz</boardgamepodcastepisode>\n" +
                    "<boardgameartist objectid=\"133362\">Andreas Klober</boardgameartist>\n" +
                    "<boardgameexpansion objectid=\"21101\">De Kolonisten van Catan: De Diamanten</boardgameexpansion>\n" +
                    "<boardgameexpansion objectid=\"41161\">\n" +
                    "De Kolonisten van Catan: De drie Handelsteden van Noord-Nederland\n" +
                    "</boardgameexpansion>\n" +
                    "<boardgameexpansion objectid=\"21097\">De Kolonisten van Catan: De Koloniën</boardgameexpansion>\n" +
                    "<boardgameexpansion objectid=\"21443\">De Kolonisten van Catan: De Specialisten</boardgameexpansion>\n" +
                    "<boardgameexpansion objectid=\"21100\">De Kolonisten van Catan: De Wereldwonderen</boardgameexpansion>\n" +
                    "<boardgameexpansion objectid=\"21098\">De Kolonisten van Catan: De Woestijnruiters</boardgameexpansion>\n" +
                    "<boardgameaccessory objectid=\"192180\">De Kolonisten Van Catan: Dobbelbeker</boardgameaccessory>\n" +
                    "<boardgameexpansion objectid=\"21099\">De Kolonisten van Catan: Het Grote Kanaal</boardgameexpansion>\n" +
                    "<boardgameimplementation objectid=\"52825\">De Kolonisten van de Lage Landen</boardgameimplementation>\n" +
                    "<boardgamepublisher objectid=\"8291\">Korea Boardgames</boardgamepublisher>\n" +
                    "<boardgameversion objectid=\"574392\">Korean edition 2007</boardgameversion>\n" +
                    "<boardgameversion objectid=\"513125\">Korean edition 2015</boardgameversion>\n" +
                    "<boardgameversion objectid=\"129766\">Korean revised edition 2010</boardgameversion>\n" +
                    "<boardgameversion objectid=\"578004\">Kosmos French edition 2017</boardgameversion>\n" +
                    "<boardgamepublisher objectid=\"2654\">L&M Games</boardgamepublisher>\n" +
                    "<boardgamepublisher objectid=\"5597\">Laser plus</boardgamepublisher>\n" +
                    "<boardgameversion objectid=\"24595\">Latvian/Russian edition</boardgameversion>\n" +
                    "<boardgamepublisher objectid=\"3218\">Lautapelit.fi</boardgamepublisher>\n" +
                    "<boardgameartist objectid=\"4959\">Harald Lieske</boardgameartist>\n" +
                    "<boardgameversion objectid=\"350937\">Lithuanian edition</boardgameversion>\n" +
                    "<boardgamepublisher objectid=\"3533\">Logojogos</boardgamepublisher>\n" +
                    "<boardgamepodcastepisode objectid=\"33356\">Love Talk with Kartok (Happy Valentine's Day!)</boardgamepodcastepisode>\n" +
                    "<boardgamepodcastepisode objectid=\"116605\">\n" +
                    "Ludology Episode 78 - Here We Are Now, Entertain Us\n" +
                    "</boardgamepodcastepisode>\n" +
                    "<boardgamepodcastepisode objectid=\"337458\">Mål og Point</boardgamepodcastepisode>\n" +
                    "<cardset objectid=\"84280\">\n" +
                    "Mayfair English 3rd (2003), 4th (2007) 5th (2015), and 6th (2016) eds\n" +
                    "</cardset>\n" +
                    "<boardgameversion objectid=\"265709\">Mayfair English edition 2015</boardgameversion>\n" +
                    "<boardgameexpansion objectid=\"144851\">Mayfair Game Variants & Mini-Expansions Set #1</boardgameexpansion>\n" +
                    "<boardgamepublisher objectid=\"10\">Mayfair Games</boardgamepublisher>\n" +
                    "<boardgameexpansion objectid=\"167573\">\n" +
                    "Mayfair Games' Limited Edition Promo Expansion Set #17\n" +
                    "</boardgameexpansion>\n" +
                    "<boardgameartist objectid=\"11825\">Michael Menzel</boardgameartist>\n" +
                    "<boardgamepublisher objectid=\"51614\">MIPL</boardgamepublisher>\n" +
                    "<boardgamefamily objectid=\"78198\">Misc: Watch It Played How To Videos</boardgamefamily>\n" +
                    "<boardgamepodcastepisode objectid=\"122910\">\n" +
                    "MN 0005 Our Origin Story and Our 7 Most Played Games\n" +
                    "</boardgamepodcastepisode>\n" +
                    "<boardgamepodcastepisode objectid=\"443438\">Modern Classics</boardgamepodcastepisode>\n" +
                    "<boardgamemechanic objectid=\"2011\">Modular Board</boardgamemechanic>\n" +
                    "<boardgamecategory objectid=\"1026\">Negotiation</boardgamecategory>\n" +
                    "<boardgamemechanic objectid=\"2915\">Negotiation</boardgamemechanic>\n" +
                    "<boardgamemechanic objectid=\"2081\">Network and Route Building</boardgamemechanic>\n" +
                    "<boardgamepublisher objectid=\"24886\">Ninive Games</boardgamepublisher>\n" +
                    "<boardgameversion objectid=\"258507\">Norwegian edition 2010</boardgameversion>\n" +
                    "<boardgameversion objectid=\"538633\">Norwegian edition 2017</boardgameversion>\n" +
                    "<boardgameversion objectid=\"30615\">Norwegian first edition</boardgameversion>\n" +
                    "<boardgamepodcastepisode objectid=\"116967\">OBG 130: Back In Box</boardgamepodcastepisode>\n" +
                    "<boardgamepublisher objectid=\"2528\">Paper Iyagi</boardgamepublisher>\n" +
                    "<boardgameversion objectid=\"574390\">Paper Iyagi Korean edition</boardgameversion>\n" +
                    "<boardgamepodcastepisode objectid=\"163045\">Papstinenser er i luften</boardgamepodcastepisode>\n" +
                    "<boardgamepublisher objectid=\"22\">Piatnik</boardgamepublisher>\n" +
                    "<boardgameexpansion objectid=\"90100\">\n" +
                    "Die Pioniere (fan expansion for The Settlers of Catan)\n" +
                    "</boardgameexpansion>\n" +
                    "<boardgameversion objectid=\"24758\">Polish edition 2005</boardgameversion>\n" +
                    "<boardgameversion objectid=\"120561\">Polish edition 2011</boardgameversion>\n" +
                    "<boardgameversion objectid=\"301799\">Polish edition 2015</boardgameversion>\n" +
                    "<boardgameversion objectid=\"101625\">Portuguese edition</boardgameversion>\n" +
                    "<boardgameversion objectid=\"33218\">Portuguese edition 1998</boardgameversion>\n" +
                    "<boardgameversion objectid=\"72400\">Portuguese edition 2002</boardgameversion>\n" +
                    "<boardgameversion objectid=\"280464\">Portuguese edition 2011</boardgameversion>\n" +
                    "<boardgameversion objectid=\"289671\">Portuguese edition 2015</boardgameversion>\n" +
                    "<boardgameartist objectid=\"24191\">Marion Pott</boardgameartist>\n" +
                    "<boardgamefamily objectid=\"11505\">Promotional: Promo Board Games</boardgamefamily>\n" +
                    "<boardgamepodcastepisode objectid=\"252794\">\n" +
                    "Przystanek Planszówka – odcinek 80 – Osadnicy z Catanu\n" +
                    "</boardgamepodcastepisode>\n" +
                    "<boardgamemechanic objectid=\"2876\">Race</boardgamemechanic>\n" +
                    "<boardgamemechanic objectid=\"2909\">Random Production</boardgamemechanic>\n" +
                    "<boardgameartist objectid=\"11898\">Andreas Resch</boardgameartist>\n" +
                    "<boardgamepodcastepisode objectid=\"113301\">RJ30: Shopping victorious</boardgamepodcastepisode>\n" +
                    "<boardgamepodcastepisode objectid=\"115954\">\n" +
                    "RJ32: La community manager, els LCG, les marietes i un tal Ignacy\n" +
                    "</boardgamepodcastepisode>\n" +
                    "<boardgamepodcastepisode objectid=\"138853\">RJ45: Barcelona meets Cannes</boardgamepodcastepisode>\n" +
                    "<boardgamepodcastepisode objectid=\"195124\">RJ61: Especial Dau Barcelona 2016</boardgamepodcastepisode>\n" +
                    "<boardgamepodcastepisode objectid=\"232657\">Roll for the Galaxy</boardgamepodcastepisode>\n" +
                    "<boardgameversion objectid=\"38174\">Romanian edition 2006</boardgameversion>\n" +
                    "<boardgameversion objectid=\"75933\">Romanian edition 2010</boardgameversion>\n" +
                    "<boardgameversion objectid=\"445459\">Romanian edition 2015</boardgameversion>\n" +
                    "<boardgamepodcastepisode objectid=\"294673\">\n" +
                    "Round 2, Extra Turn: \"Settlers of Catan\" with Jesse (and special guest Lumin)\n" +
                    "</boardgamepodcastepisode>\n" +
                    "<boardgameversion objectid=\"29560\">Russian edition</boardgameversion>\n" +
                    "<boardgameversion objectid=\"266025\">Russian edition 2004</boardgameversion>\n" +
                    "<boardgameversion objectid=\"311420\">Russian edition 2015</boardgameversion>\n" +
                    "<boardgameversion objectid=\"420200\">Russian edition 2016</boardgameversion>\n" +
                    "<boardgameversion objectid=\"96496\">Russian first edition 2002</boardgameversion>\n" +
                    "<boardgameversion objectid=\"100024\">Russian fourth edition 2011</boardgameversion>\n" +
                    "<boardgameexpansion objectid=\"22598\">\n" +
                    "Saggsen-Gadan: De säggs'schn Siedler / Catan-OFFENSIVE in Chemnitz\n" +
                    "</boardgameexpansion>\n" +
                    "<boardgameartist objectid=\"25612\">Matt Schwabel</boardgameartist>\n" +
                    "<boardgameversion objectid=\"232725\">Serbian edition 2012</boardgameversion>\n" +
                    "<boardgameversion objectid=\"636971\">Serbian/Macedonian edition</boardgameversion>\n" +
                    "<boardgamepodcastepisode objectid=\"5746\">Session Impressions: Settlers of Catan</boardgamepodcastepisode>\n" +
                    "<boardgameimplementation objectid=\"3655\">The Settlers of Canaan</boardgameimplementation>\n" +
                    "<boardgamepodcastepisode objectid=\"15297\">Settlers of Catan</boardgamepodcastepisode>\n" +
                    "<boardgamecompilation objectid=\"152959\">The Settlers of Catan</boardgamecompilation>\n" +
                    "<boardgameexpansion objectid=\"293222\">Settlers of Catan Scenario: The Jungle</boardgameexpansion>\n" +
                    "<boardgameexpansion objectid=\"293224\">Settlers of Catan Scenario: The Volcano</boardgameexpansion>\n" +
                    "<boardgamepodcastepisode objectid=\"62105\">Settlers of Catan with Glen Whitman from FRINGE!</boardgamepodcastepisode>\n" +
                    "<boardgameaccessory objectid=\"139613\">The Settlers of Catan: Building Costs Coasters</boardgameaccessory>\n" +
                    "<boardgameimplementation objectid=\"38821\">Settlers of Catan: Gallery Edition</boardgameimplementation>\n" +
                    "<boardgameaccessory objectid=\"140404\">The Settlers of Catan: Game Cards</boardgameaccessory>\n" +
                    "<boardgameaccessory objectid=\"138407\">Settlers of Catan: Pre-2007 Adapter Kit</boardgameaccessory>\n" +
                    "<boardgameaccessory objectid=\"139612\">The Settlers of Catan: Robber Trio</boardgameaccessory>\n" +
                    "<boardgameimplementation objectid=\"20899\">Settlers of Catan: Rockman Edition</boardgameimplementation>\n" +
                    "<boardgameexpansion objectid=\"19343\">The Settlers of Catan: The Fishermen of Catan</boardgameexpansion>\n" +
                    "<boardgameexpansion objectid=\"20247\">The Settlers of Catan: The Great River</boardgameexpansion>\n" +
                    "<boardgameexpansion objectid=\"10817\">Settlers of New Catan (and extra modules)</boardgameexpansion>\n" +
                    "<boardgameimplementation objectid=\"4394\">The Settlers of the Stone Age</boardgameimplementation>\n" +
                    "<boardgameimplementation objectid=\"6778\">The Settlers of Zarahemla</boardgameimplementation>\n" +
                    "<boardgameexpansion objectid=\"21046\">\n" +
                    "Die Siedler von Catan: Atlantis – Szenarien & Varianten\n" +
                    "</boardgameexpansion>\n" +
                    "<boardgameaccessory objectid=\"193277\">Die Siedler von Catan: Club-Paket</boardgameaccessory>\n" +
                    "<boardgameexpansion objectid=\"1137\">Die Siedler von Catan: Das Buch zum Spielen</boardgameexpansion>\n" +
                    "<boardgameaccessory objectid=\"151252\">Die Siedler von Catan: Der Catanische Rat</boardgameaccessory>\n" +
                    "<boardgameexpansion objectid=\"84977\">Die Siedler von Catan: Der Schokoladenmarkt</boardgameexpansion>\n" +
                    "<boardgameexpansion objectid=\"26079\">Die Siedler von Catan: Die große Karawane</boardgameexpansion>\n" +
                    "<boardgameexpansion objectid=\"167836\">Die Siedler von Catan: Hispania Edition</boardgameexpansion>\n" +
                    "<boardgameexpansion objectid=\"306\">Die Siedler von Catan: Historische Szenarien</boardgameexpansion>\n" +
                    "<boardgameexpansion objectid=\"1361\">Die Siedler von Catan: Historische Szenarien II</boardgameexpansion>\n" +
                    "<boardgameexpansion objectid=\"167841\">Die Siedler von Catan: Hochzeitsturm</boardgameexpansion>\n" +
                    "<boardgameimplementation objectid=\"27766\">Die Siedler von Catan: Junior</boardgameimplementation>\n" +
                    "<boardgameaccessory objectid=\"369439\">Die Siedler von Catan: Kompatibilitäts-Kit</boardgameaccessory>\n" +
                    "<boardgameexpansion objectid=\"31933\">\n" +
                    "Die Siedler von Catan: Renaissance in der Steiermark & Burgbau auf Chaffenberch\n" +
                    "</boardgameexpansion>\n" +
                    "<boardgameexpansion objectid=\"37690\">\n" +
                    "Die Siedler von Catan: Rincewind und der Tourist / Die Gilden von Ankh-Morpork\n" +
                    "</boardgameexpansion>\n" +
                    "<boardgameexpansion objectid=\"111377\">Die Siedler von Catan: Thüringen Edition</boardgameexpansion>\n" +
                    "<boardgameexpansion objectid=\"37969\">Die Siedler von Luxemburg</boardgameexpansion>\n" +
                    "<boardgameimplementation objectid=\"189\">Die Siedler von Nürnberg</boardgameimplementation>\n" +
                    "<boardgameexpansion objectid=\"399807\">The Siege (fan expansion for Catan)</boardgameexpansion>\n" +
                    "<boardgameimplementation objectid=\"24511\">Simply Catan</boardgameimplementation>\n" +
                    "<boardgameversion objectid=\"230164\">Slovenian fifth edition</boardgameversion>\n" +
                    "<boardgameversion objectid=\"280465\">Slovenian first edition</boardgameversion>\n" +
                    "<boardgameversion objectid=\"56099\">Slovenian third edition</boardgameversion>\n" +
                    "<boardgamepublisher objectid=\"8313\">Smart Ltd</boardgamepublisher>\n" +
                    "<boardgameversion objectid=\"392462\">Spanish edition 2015</boardgameversion>\n" +
                    "<boardgameversion objectid=\"29562\">Spanish first edition</boardgameversion>\n" +
                    "<boardgameversion objectid=\"33981\">Spanish second edition 2002</boardgameversion>\n" +
                    "<boardgameversion objectid=\"87592\">Spanish third edition 2011</boardgameversion>\n" +
                    "<boardgamegraphicdesigner objectid=\"158336\">Az Sperry</boardgamegraphicdesigner>\n" +
                    "<boardgamepublisher objectid=\"44204\">Spilbræt.dk</boardgamepublisher>\n" +
                    "<boardgameimplementation objectid=\"117985\">Star Trek: Catan</boardgameimplementation>\n" +
                    "<boardgameimplementation objectid=\"1897\">The Starfarers of Catan</boardgameimplementation>\n" +
                    "<boardgamesubdomain objectid=\"5497\">Strategy Games</boardgamesubdomain>\n" +
                    "<boardgamepublisher objectid=\"9235\">Stupor Mundi</boardgamepublisher>\n" +
                    "<boardgamepublisher objectid=\"48279\">SuperHeated Neurons</boardgamepublisher>\n" +
                    "<boardgamepublisher objectid=\"9234\">Swan Panasia Co., Ltd.</boardgamepublisher>\n" +
                    "<boardgameversion objectid=\"608490\">Swedish edition 2010</boardgameversion>\n" +
                    "<boardgameversion objectid=\"129861\">Swedish edition 2011</boardgameversion>\n" +
                    "<boardgameversion objectid=\"447042\">Swedish edition 2015</boardgameversion>\n" +
                    "<boardgameversion objectid=\"639020\">Swedish edition 2017</boardgameversion>\n" +
                    "<boardgameversion objectid=\"271196\">Swedish first edition</boardgameversion>\n" +
                    "<boardgamepodcastepisode objectid=\"168252\">T.I.M.E Stories and Literally the Worst Games</boardgamepodcastepisode>\n" +
                    "<boardgamedesigner objectid=\"11\">Klaus Teuber</boardgamedesigner>\n" +
                    "<boardgamepodcastepisode objectid=\"221652\">TGT 066: Getting Non-Gamers to Game</boardgamepodcastepisode>\n" +
                    "<boardgamepodcastepisode objectid=\"225082\">TGT 071: Dice Games</boardgamepodcastepisode>\n" +
                    "<boardgamepodcastepisode objectid=\"228774\">TGT 076: Gaming Vocabulary</boardgamepodcastepisode>\n" +
                    "<boardgamepodcastepisode objectid=\"231353\">TGT 080: Groking Games</boardgamepodcastepisode>\n" +
                    "<boardgamepodcastepisode objectid=\"235197\">TGT 087: The Wirecutter Article</boardgamepodcastepisode>\n" +
                    "<boardgamepodcastepisode objectid=\"241817\">TGT 093: King Making</boardgamepodcastepisode>\n" +
                    "<boardgamepodcastepisode objectid=\"251418\">TGT 101 On Topic: Euro Games</boardgamepodcastepisode>\n" +
                    "<boardgamepodcastepisode objectid=\"253700\">TGT 104 On Topic: Euro, Work, and Why Feedback</boardgamepodcastepisode>\n" +
                    "<boardgamepodcastepisode objectid=\"255089\">\n" +
                    "TGT 106 On Topic: Playing Older Games You’ve Missed\n" +
                    "</boardgamepodcastepisode>\n" +
                    "<boardgameversion objectid=\"203087\">Thai edition 2012</boardgameversion>\n" +
                    "<boardgamepodcastepisode objectid=\"152649\">\n" +
                    "The Good, The Board and the Learned: Episode 16 “Teaching”\n" +
                    "</boardgamepodcastepisode>\n" +
                    "<boardgamepodcastepisode objectid=\"44887\">The Messy Game Room Episode 23</boardgamepodcastepisode>\n" +
                    "<boardgamefamily objectid=\"21622\">Theme: Colonial</boardgamefamily>\n" +
                    "<boardgamepublisher objectid=\"42\">Tilsit</boardgamepublisher>\n" +
                    "<boardgameversion objectid=\"261432\">Tilsit Italian edition</boardgameversion>\n" +
                    "<boardgamepublisher objectid=\"18477\">Top Toys</boardgamepublisher>\n" +
                    "<boardgameversion objectid=\"311017\">Top Toys Spanish edition</boardgameversion>\n" +
                    "<boardgamemechanic objectid=\"2008\">Trading</boardgamemechanic>\n" +
                    "<boardgamepodcastepisode objectid=\"294195\">True Awkward</boardgamepodcastepisode>\n" +
                    "<boardgamepublisher objectid=\"13481\">TRY SOFT</boardgamepublisher>\n" +
                    "<boardgamemechanic objectid=\"2897\">Variable Set-up</boardgamemechanic>\n" +
                    "<boardgamepublisher objectid=\"14078\">Vennerød Forlag AS</boardgamepublisher>\n" +
                    "<boardgameartist objectid=\"11883\">Franz Vohwinkel</boardgameartist>\n" +
                    "<boardgameartist objectid=\"12063\">Stephen Graham Walsh</boardgameartist>\n" +
                    "<boardgameimplementation objectid=\"5549\">Das Wasser des Lebens</boardgameimplementation>\n" +
                    "<boardgamepodcastepisode objectid=\"140948\">\n" +
                    "What Did You Play This Week Podcast Thing Week 21!!!\n" +
                    "</boardgamepodcastepisode>\n" +
                    "<boardgamepodcastepisode objectid=\"164589\">\n" +
                    "What Did You Play This Week Podcast Thing Week 49!! Featuring Chris and Joe from Cardboard Architects, Eric Booth, AnnaBeth and Kerensa\n" +
                    "</boardgamepodcastepisode>\n" +
                    "<boardgamepodcastepisode objectid=\"171041\">\n" +
                    "What Did You Play This Week Podcast Thing Week 59!!! A Contest Inside!\n" +
                    "</boardgamepodcastepisode>\n" +
                    "<boardgameimplementation objectid=\"144418\">Wien Catan</boardgameimplementation>\n" +
                    "<boardgameexpansion objectid=\"134277\">World Wonders (fan expansion for Catan)</boardgameexpansion>\n" +
                    "<boardgamepodcastepisode objectid=\"174118\">제99회 집중탐구 - Catan Family</boardgamepodcastepisode>\n" +
                    "<poll name=\"suggested_numplayers\" title=\"User Suggested Number of Players\" totalvotes=\"1927\">\n" +
                    "<results numplayers=\"1\">\n" +
                    "<result value=\"Best\" numvotes=\"1\"/>\n" +
                    "<result value=\"Recommended\" numvotes=\"3\"/>\n" +
                    "<result value=\"Not Recommended\" numvotes=\"1347\"/>\n" +
                    "</results>\n" +
                    "<results numplayers=\"2\">\n" +
                    "<result value=\"Best\" numvotes=\"14\"/>\n" +
                    "<result value=\"Recommended\" numvotes=\"231\"/>\n" +
                    "<result value=\"Not Recommended\" numvotes=\"1208\"/>\n" +
                    "</results>\n" +
                    "<results numplayers=\"3\">\n" +
                    "<result value=\"Best\" numvotes=\"645\"/>\n" +
                    "<result value=\"Recommended\" numvotes=\"1030\"/>\n" +
                    "<result value=\"Not Recommended\" numvotes=\"96\"/>\n" +
                    "</results>\n" +
                    "<results numplayers=\"4\">\n" +
                    "<result value=\"Best\" numvotes=\"1373\"/>\n" +
                    "<result value=\"Recommended\" numvotes=\"419\"/>\n" +
                    "<result value=\"Not Recommended\" numvotes=\"41\"/>\n" +
                    "</results>\n" +
                    "<results numplayers=\"4+\">\n" +
                    "<result value=\"Best\" numvotes=\"58\"/>\n" +
                    "<result value=\"Recommended\" numvotes=\"178\"/>\n" +
                    "<result value=\"Not Recommended\" numvotes=\"844\"/>\n" +
                    "</results>\n" +
                    "</poll>\n" +
                    "<poll name=\"language_dependence\" title=\"Language Dependence\" totalvotes=\"341\">\n" +
                    "<results>\n" +
                    "<result level=\"1\" value=\"No necessary in-game text\" numvotes=\"21\"/>\n" +
                    "<result level=\"2\" value=\"Some necessary text - easily memorized or small crib sheet\" numvotes=\"290\"/>\n" +
                    "<result level=\"3\" value=\"Moderate in-game text - needs crib sheet or paste ups\" numvotes=\"29\"/>\n" +
                    "<result level=\"4\" value=\"Extensive use of text - massive conversion needed to be playable\" numvotes=\"1\"/>\n" +
                    "<result level=\"5\" value=\"Unplayable in another language\" numvotes=\"0\"/>\n" +
                    "</results>\n" +
                    "</poll>\n" +
                    "<poll name=\"suggested_playerage\" title=\"User Suggested Player Age\" totalvotes=\"540\">\n" +
                    "<results>\n" +
                    "<result value=\"2\" numvotes=\"1\"/>\n" +
                    "<result value=\"3\" numvotes=\"0\"/>\n" +
                    "<result value=\"4\" numvotes=\"2\"/>\n" +
                    "<result value=\"5\" numvotes=\"8\"/>\n" +
                    "<result value=\"6\" numvotes=\"55\"/>\n" +
                    "<result value=\"8\" numvotes=\"257\"/>\n" +
                    "<result value=\"10\" numvotes=\"169\"/>\n" +
                    "<result value=\"12\" numvotes=\"41\"/>\n" +
                    "<result value=\"14\" numvotes=\"4\"/>\n" +
                    "<result value=\"16\" numvotes=\"2\"/>\n" +
                    "<result value=\"18\" numvotes=\"1\"/>\n" +
                    "<result value=\"21 and up\" numvotes=\"0\"/>\n" +
                    "</results>\n" +
                    "</poll>\n" +
                    "</boardgame>\n" +
                    "</boardgames>";
            ResponseEntity<String> gameDetailsResponseEntity = new ResponseEntity<>(gameDetailsResponse, HttpStatus.OK);

            Mockito.when(restTemplateMock.getForEntity(
                    any(String.class),
                    eq(String.class)
            )).thenReturn(gameDetailsResponseEntity);

            // Create BoardGameService instance with mocked RestTemplate
            BGGApiService boardGameService = new BGGApiService(restTemplateMock);

            // Call the method under test
            String coverImageUrl = boardGameService.searchGameAndGetCoverImage(searchGameTitle);

            // Perform assertions based on expected behavior
            // Add your assertions here based on what you expect 'coverImageUrl' to be
            assertEquals("https://cf.geekdo-images.com/W3Bsga_uLP9kO91gZ7H8yw__original/img/xV7oisd3RQ8R-k18cdWAYthHXsA=/0x0/filters:format(jpeg)/pic2419375.jpg", coverImageUrl);
        }
    }



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
import static org.mockito.Mockito.times;

public class BGGApiServiceTest {

        @Mock
        private RestTemplate restTemplateMock = Mockito.mock(RestTemplate.class);

        @Test
        public void testSearchGameAndGetCoverImage() {
            // Mock the response for the search API call
            String searchGameTitle = "Kingdomino";
            String searchResponse = "<items total=\"8\" termsofuse=\"https://boardgamegeek.com/xmlapi/termsofuse\">\n" +
                    "<item type=\"boardgame\" id=\"336104\">\n" +
                    "<name type=\"primary\" value=\"Heroes and Divided Lands (fan expansion for Kingdomino and Queendomino)\"/>\n" +
                    "<yearpublished value=\"2021\"/>\n" +
                    "</item>\n" +
                    "<item type=\"boardgame\" id=\"204583\">\n" +
                    "<name type=\"primary\" value=\"Kingdomino\"/>\n" +
                    "<yearpublished value=\"2016\"/>\n" +
                    "</item>\n" +
                    "<item type=\"boardgame\" id=\"281960\">\n" +
                    "<name type=\"primary\" value=\"Kingdomino Duel\"/>\n" +
                    "<yearpublished value=\"2019\"/>\n" +
                    "</item>\n" +
                    "<item type=\"boardgame\" id=\"260941\">\n" +
                    "<name type=\"primary\" value=\"Kingdomino for 2 players\"/>\n" +
                    "<yearpublished value=\"2018\"/>\n" +
                    "</item>\n" +
                    "<item type=\"boardgame\" id=\"340041\">\n" +
                    "<name type=\"primary\" value=\"Kingdomino Origins\"/>\n" +
                    "<yearpublished value=\"2021\"/>\n" +
                    "</item>\n" +
                    "<item type=\"boardgame\" id=\"240909\">\n" +
                    "<name type=\"primary\" value=\"Kingdomino: Age of Giants\"/>\n" +
                    "<yearpublished value=\"2018\"/>\n" +
                    "</item>\n" +
                    "<item type=\"boardgame\" id=\"306171\">\n" +
                    "<name type=\"primary\" value=\"Kingdomino: The Court\"/>\n" +
                    "<yearpublished value=\"2020\"/>\n" +
                    "</item>\n" +
                    "<item type=\"boardgame\" id=\"332979\">\n" +
                    "<name type=\"primary\" value=\"Royal Wedding XXL (fan expansion for Kingdomino)\"/>\n" +
                    "<yearpublished value=\"2020\"/>\n" +
                    "</item>\n" +
                    "</items>";

            ResponseEntity<String> searchResponseEntity = new ResponseEntity<>(searchResponse, HttpStatus.OK);

            Mockito.when(restTemplateMock.getForEntity(
                    eq("https://boardgamegeek.com/xmlapi2/search?type=boardgame&query=" + searchGameTitle + "&exact=1"),
                    eq(String.class)
            )).thenReturn(searchResponseEntity);

            // Mock the response for the game details API call
            String gameDetailsResponse = "<items termsofuse=\"https://boardgamegeek.com/xmlapi/termsofuse\">\n" +
                    "<item type=\"boardgame\" id=\"204583\">\n" +
                    "<thumbnail>\n" +
                    "https://cf.geekdo-images.com/3h9W8BfB_rltQ48EBmHliw__thumb/img/RGpbcY90eBcNLXbHLMBwLrr2uzo=/fit-in/200x150/filters:strip_icc()/pic3132685.png\n" +
                    "</thumbnail>\n" +
                    "<image>\n" +
                    "https://cf.geekdo-images.com/3h9W8BfB_rltQ48EBmHliw__original/img/DDk3fDr0FkDpkYAcny5LeekB2qg=/0x0/filters:format(png)/pic3132685.png\n" +
                    "</image>\n" +
                    "<name type=\"primary\" sortindex=\"1\" value=\"Kingdomino\"/>\n" +
                    "<name type=\"alternate\" sortindex=\"1\" value=\"K-Domino'Z\"/>\n" +
                    "<name type=\"alternate\" sortindex=\"1\" value=\"Kingdomino XXL\"/>\n" +
                    "<name type=\"alternate\" sortindex=\"1\" value=\"Kingdomino: Expo 2020\"/>\n" +
                    "<name type=\"alternate\" sortindex=\"1\" value=\"Kingdomino: Ένα Ντόμινο για το Βασιλιά\"/>\n" +
                    "<name type=\"alternate\" sortindex=\"1\" value=\"Kingdomino. Доміношне Королівство\"/>\n" +
                    "<name type=\"alternate\" sortindex=\"1\" value=\"Доміношне Королівство\"/>\n" +
                    "<name type=\"alternate\" sortindex=\"1\" value=\"Кингдомино\"/>\n" +
                    "<name type=\"alternate\" sortindex=\"1\" value=\"Лоскутное Королевство\"/>\n" +
                    "<name type=\"alternate\" sortindex=\"1\" value=\"Поскутное Королевство\"/>\n" +
                    "<name type=\"alternate\" sortindex=\"1\" value=\"קינגדומינו\"/>\n" +
                    "<name type=\"alternate\" sortindex=\"1\" value=\"キングドミノ\"/>\n" +
                    "<name type=\"alternate\" sortindex=\"1\" value=\"多米諾王國\"/>\n" +
                    "<name type=\"alternate\" sortindex=\"1\" value=\"킹도미노\"/>\n" +
                    "<description>\n" +
                    "In Kingdomino, you are a lord seeking new lands in which to expand your kingdom. You must explore all the lands, including wheat fields, lakes, and mountains, in order to spot the best plots, while competing with other lords to acquire them first.\n\nThe game uses tiles with two sections, similar to Dominoes. Each turn, each player will select a new domino to connect to their existing kingdom, making sure at least one of its sides connects to a matching terrain type already in play. The order of who picks first depends on which tile was previously chosen, with better tiles forcing players to pick later in the next round. The game ends when each player has completed a 5x5 grid (or failed to do so), and points are counted based on number of connecting tiles and valuable crown symbols.\n\n\n" +
                    "</description>\n" +
                    "<yearpublished value=\"2016\"/>\n" +
                    "<minplayers value=\"2\"/>\n" +
                    "<maxplayers value=\"4\"/>\n" +
                    "<poll name=\"suggested_numplayers\" title=\"User Suggested Number of Players\" totalvotes=\"666\">\n" +
                    "<results numplayers=\"1\">\n" +
                    "<result value=\"Best\" numvotes=\"2\"/>\n" +
                    "<result value=\"Recommended\" numvotes=\"16\"/>\n" +
                    "<result value=\"Not Recommended\" numvotes=\"427\"/>\n" +
                    "</results>\n" +
                    "<results numplayers=\"2\">\n" +
                    "<result value=\"Best\" numvotes=\"418\"/>\n" +
                    "<result value=\"Recommended\" numvotes=\"188\"/>\n" +
                    "<result value=\"Not Recommended\" numvotes=\"20\"/>\n" +
                    "</results>\n" +
                    "<results numplayers=\"3\">\n" +
                    "<result value=\"Best\" numvotes=\"130\"/>\n" +
                    "<result value=\"Recommended\" numvotes=\"392\"/>\n" +
                    "<result value=\"Not Recommended\" numvotes=\"60\"/>\n" +
                    "</results>\n" +
                    "<results numplayers=\"4\">\n" +
                    "<result value=\"Best\" numvotes=\"394\"/>\n" +
                    "<result value=\"Recommended\" numvotes=\"190\"/>\n" +
                    "<result value=\"Not Recommended\" numvotes=\"16\"/>\n" +
                    "</results>\n" +
                    "<results numplayers=\"4+\">\n" +
                    "<result value=\"Best\" numvotes=\"0\"/>\n" +
                    "<result value=\"Recommended\" numvotes=\"9\"/>\n" +
                    "<result value=\"Not Recommended\" numvotes=\"359\"/>\n" +
                    "</results>\n" +
                    "</poll>\n" +
                    "<playingtime value=\"25\"/>\n" +
                    "<minplaytime value=\"15\"/>\n" +
                    "<maxplaytime value=\"25\"/>\n" +
                    "<minage value=\"8\"/>\n" +
                    "<poll name=\"suggested_playerage\" title=\"User Suggested Player Age\" totalvotes=\"289\">\n" +
                    "<results>\n" +
                    "<result value=\"2\" numvotes=\"1\"/>\n" +
                    "<result value=\"3\" numvotes=\"4\"/>\n" +
                    "<result value=\"4\" numvotes=\"20\"/>\n" +
                    "<result value=\"5\" numvotes=\"49\"/>\n" +
                    "<result value=\"6\" numvotes=\"139\"/>\n" +
                    "<result value=\"8\" numvotes=\"71\"/>\n" +
                    "<result value=\"10\" numvotes=\"5\"/>\n" +
                    "<result value=\"12\" numvotes=\"0\"/>\n" +
                    "<result value=\"14\" numvotes=\"0\"/>\n" +
                    "<result value=\"16\" numvotes=\"0\"/>\n" +
                    "<result value=\"18\" numvotes=\"0\"/>\n" +
                    "<result value=\"21 and up\" numvotes=\"0\"/>\n" +
                    "</results>\n" +
                    "</poll>\n" +
                    "<poll name=\"language_dependence\" title=\"Language Dependence\" totalvotes=\"54\">\n" +
                    "<results>\n" +
                    "<result level=\"1\" value=\"No necessary in-game text\" numvotes=\"54\"/>\n" +
                    "<result level=\"2\" value=\"Some necessary text - easily memorized or small crib sheet\" numvotes=\"0\"/>\n" +
                    "<result level=\"3\" value=\"Moderate in-game text - needs crib sheet or paste ups\" numvotes=\"0\"/>\n" +
                    "<result level=\"4\" value=\"Extensive use of text - massive conversion needed to be playable\" numvotes=\"0\"/>\n" +
                    "<result level=\"5\" value=\"Unplayable in another language\" numvotes=\"0\"/>\n" +
                    "</results>\n" +
                    "</poll>\n" +
                    "<link type=\"boardgamecategory\" id=\"1029\" value=\"City Building\"/>\n" +
                    "<link type=\"boardgamecategory\" id=\"1035\" value=\"Medieval\"/>\n" +
                    "<link type=\"boardgamecategory\" id=\"1086\" value=\"Territory Building\"/>\n" +
                    "<link type=\"boardgamemechanic\" id=\"2041\" value=\"Open Drafting\"/>\n" +
                    "<link type=\"boardgamemechanic\" id=\"2002\" value=\"Tile Placement\"/>\n" +
                    "<link type=\"boardgamemechanic\" id=\"2826\" value=\"Turn Order: Stat-Based\"/>\n" +
                    "<link type=\"boardgamefamily\" id=\"77906\" value=\"Category: Dized Tutorial\"/>\n" +
                    "<link type=\"boardgamefamily\" id=\"39749\" value=\"Components: 5 x 5 Grids\"/>\n" +
                    "<link type=\"boardgamefamily\" id=\"70360\" value=\"Digital Implementations: Board Game Arena\"/>\n" +
                    "<link type=\"boardgamefamily\" id=\"50195\" value=\"Game: Kingdomino\"/>\n" +
                    "<link type=\"boardgamefamily\" id=\"59705\" value=\"Misc: LongPack Games\"/>\n" +
                    "<link type=\"boardgamefamily\" id=\"44423\" value=\"Series: Europe Collection (Blue Orange Games)\"/>\n" +
                    "<link type=\"boardgameexpansion\" id=\"336104\" value=\"Heroes and Divided Lands (fan expansion for Kingdomino and Queendomino)\"/>\n" +
                    "<link type=\"boardgameexpansion\" id=\"240909\" value=\"Kingdomino: Age of Giants\"/>\n" +
                    "<link type=\"boardgameexpansion\" id=\"306171\" value=\"Kingdomino: The Court\"/>\n" +
                    "<link type=\"boardgameexpansion\" id=\"332979\" value=\"Royal Wedding XXL (fan expansion for Kingdomino)\"/>\n" +
                    "<link type=\"boardgameexpansion\" id=\"293240\" value=\"Super Power\"/>\n" +
                    "<link type=\"boardgameaccessory\" id=\"372422\" value=\"Kingdomino: 2 playmats\"/>\n" +
                    "<link type=\"boardgameaccessory\" id=\"371797\" value=\"Kingdomino: Afternoon Tea Castle\"/>\n" +
                    "<link type=\"boardgameaccessory\" id=\"281873\" value=\"Kingdomino: Chocolate Castle\"/>\n" +
                    "<link type=\"boardgameaccessory\" id=\"206269\" value=\"Kingdomino: Dark Castle\"/>\n" +
                    "<link type=\"boardgameaccessory\" id=\"263710\" value=\"Kingdomino: Dice Tower 2018 Kickstarter Stickers\"/>\n" +
                    "<link type=\"boardgameaccessory\" id=\"294822\" value=\"Kingdomino: Dized Castle\"/>\n" +
                    "<link type=\"boardgameaccessory\" id=\"244069\" value=\"Kingdomino: Game Boy Geek Promo\"/>\n" +
                    "<link type=\"boardgameaccessory\" id=\"235492\" value=\"Kingdomino: Ice Castle\"/>\n" +
                    "<link type=\"boardgameaccessory\" id=\"240510\" value=\"Kingdomino: King Stickers\"/>\n" +
                    "<link type=\"boardgameaccessory\" id=\"366922\" value=\"Kingdomino: MeeplesUpgrade Upgrade Kit Stickers\"/>\n" +
                    "<link type=\"boardgameaccessory\" id=\"342886\" value=\"Kingdomino: Octopus Castle\"/>\n" +
                    "<link type=\"boardgameaccessory\" id=\"342884\" value=\"Kingdomino: Platinium Castle\"/>\n" +
                    "<link type=\"boardgameaccessory\" id=\"227305\" value=\"Kingdomino: Princess Castle\"/>\n" +
                    "<link type=\"boardgameaccessory\" id=\"242403\" value=\"Kingdomino: Promo Castles\"/>\n" +
                    "<link type=\"boardgameaccessory\" id=\"232856\" value=\"Kingdomino: Sand Castle\"/>\n" +
                    "<link type=\"boardgameaccessory\" id=\"244676\" value=\"Kingdomino: Spiel Castle\"/>\n" +
                    "<link type=\"boardgameaccessory\" id=\"233984\" value=\"Kingdomino: Tile Dispenser\"/>\n" +
                    "<link type=\"boardgameaccessory\" id=\"238798\" value=\"Kingdomino: Upgrade Kit\"/>\n" +
                    "<link type=\"boardgameaccessory\" id=\"360268\" value=\"Queendomino: AnnabelleWorks Insert\"/>\n" +
                    "<link type=\"boardgameaccessory\" id=\"253627\" value=\"Queendomino: Chinese Castle\"/>\n" +
                    "<link type=\"boardgameaccessory\" id=\"257902\" value=\"Queendomino: Christmas Castle\"/>\n" +
                    "<link type=\"boardgameaccessory\" id=\"329478\" value=\"Queendomino: Laserox Organizer\"/>\n" +
                    "<link type=\"boardgameaccessory\" id=\"259821\" value=\"Queendomino: Orange Juice Castle\"/>\n" +
                    "<link type=\"boardgameaccessory\" id=\"287775\" value=\"Queendomino: Turtle Castle\"/>\n" +
                    "<link type=\"boardgameintegration\" id=\"232043\" value=\"Queendomino\"/>\n" +
                    "<link type=\"boardgameimplementation\" id=\"281960\" value=\"Kingdomino Duel\"/>\n" +
                    "<link type=\"boardgameimplementation\" id=\"260941\" value=\"Kingdomino for 2 players\"/>\n" +
                    "<link type=\"boardgameimplementation\" id=\"340041\" value=\"Kingdomino Origins\"/>\n" +
                    "<link type=\"boardgamedesigner\" id=\"1727\" value=\"Bruno Cathala\"/>\n" +
                    "<link type=\"boardgameartist\" id=\"51033\" value=\"Cyril Bouquet\"/>\n" +
                    "<link type=\"boardgameartist\" id=\"145454\" value=\"Hervine Galliou\"/>\n" +
                    "<link type=\"boardgamepublisher\" id=\"5022\" value=\"Blue Orange (EU)\"/>\n" +
                    "<link type=\"boardgamepublisher\" id=\"491\" value=\"Blue Orange Games\"/>\n" +
                    "<link type=\"boardgamepublisher\" id=\"44144\" value=\"Buró\"/>\n" +
                    "<link type=\"boardgamepublisher\" id=\"27221\" value=\"Coiledspring Games\"/>\n" +
                    "<link type=\"boardgamepublisher\" id=\"22560\" value=\"Fantasmagoria\"/>\n" +
                    "<link type=\"boardgamepublisher\" id=\"36981\" value=\"Feelindigo\"/>\n" +
                    "<link type=\"boardgamepublisher\" id=\"26380\" value=\"FoxGames\"/>\n" +
                    "<link type=\"boardgamepublisher\" id=\"19047\" value=\"FoxMind Israel\"/>\n" +
                    "<link type=\"boardgamepublisher\" id=\"29907\" value=\"Games Factory Publishing\"/>\n" +
                    "<link type=\"boardgamepublisher\" id=\"8820\" value=\"Gém Klub Kft.\"/>\n" +
                    "<link type=\"boardgamepublisher\" id=\"4785\" value=\"Ghenos Games\"/>\n" +
                    "<link type=\"boardgamepublisher\" id=\"34940\" value=\"Le Grand Massif\"/>\n" +
                    "<link type=\"boardgamepublisher\" id=\"8439\" value=\"Happy Baobab\"/>\n" +
                    "<link type=\"boardgamepublisher\" id=\"9634\" value=\"KADABRA\"/>\n" +
                    "<link type=\"boardgamepublisher\" id=\"6214\" value=\"Kaissa Chess &amp; Games\"/>\n" +
                    "<link type=\"boardgamepublisher\" id=\"3218\" value=\"Lautapelit.fi\"/>\n" +
                    "<link type=\"boardgamepublisher\" id=\"9325\" value=\"Lifestyle Boardgames Ltd\"/>\n" +
                    "<link type=\"boardgamepublisher\" id=\"38392\" value=\"MEBO Games\"/>\n" +
                    "<link type=\"boardgamepublisher\" id=\"7992\" value=\"MINDOK\"/>\n" +
                    "<link type=\"boardgamepublisher\" id=\"51614\" value=\"MIPL\"/>\n" +
                    "<link type=\"boardgamepublisher\" id=\"12047\" value=\"Oliphante\"/>\n" +
                    "<link type=\"boardgamepublisher\" id=\"32581\" value=\"PaperGames (III)\"/>\n" +
                    "<link type=\"boardgamepublisher\" id=\"39\" value=\"Pegasus Spiele\"/>\n" +
                    "<link type=\"boardgamepublisher\" id=\"36366\" value=\"Pridemage Games\"/>\n" +
                    "<link type=\"boardgamepublisher\" id=\"9234\" value=\"Swan Panasia Co., Ltd.\"/>\n" +
                    "<link type=\"boardgamepublisher\" id=\"4932\" value=\"White Goblin Games\"/>\n" +
                    "<link type=\"boardgamepublisher\" id=\"22609\" value=\"テンデイズゲームズ(TendaysGames)\"/>\n" +
                    "</item>\n" +
                    "</items>";
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

            // Add a debug statement to print the extracted gameId
            System.out.println("Extracted gameId: " + boardGameService.getGameId());

            // Verify that the expected API calls were made with the correct parameters
            Mockito.verify(restTemplateMock, times(1)).getForEntity(
                    eq("https://boardgamegeek.com/xmlapi2/search?type=boardgame&query=Kingdomino&exact=1"),
                    eq(String.class)
            );
//            Mockito.verify(restTemplateMock, times(1)).getForEntity(
//                    eq("https://www.boardgamegeek.com/xmlapi2/thing?id=204583"),
//                    eq(String.class)
//            );
            // Add your assertions here based on what you expect 'coverImageUrl' to be
            assertEquals("https://cf.geekdo-images.com/3h9W8BfB_rltQ48EBmHliw__original/img/DDk3fDr0FkDpkYAcny5LeekB2qg=/0x0/filters:format(png)/pic3132685.png", coverImageUrl);
        }
    }



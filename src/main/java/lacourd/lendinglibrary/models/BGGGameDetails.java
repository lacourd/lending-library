package lacourd.lendinglibrary.models;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "boardgames")
@XmlAccessorType(XmlAccessType.FIELD)
public class BGGGameDetails {

    @XmlAttribute
    private String termsofuse;

    @XmlElement(name = "boardgame")
    private List<BGGBoardGame> boardGames;

    // Getters and setters for 'termsofuse' and 'boardGames'
    // ...

    public static class BGGBoardGame {

        @XmlAttribute(name = "objectid")
        private String objectId;

        @XmlElement
        private String yearpublished;

        @XmlElement
        private String minplayers;

        @XmlElement
        private String maxplayers;

        @XmlElement
        private String playingtime;

        @XmlElement
        private String minplaytime;

        @XmlElement
        private String maxplaytime;

        @XmlElement
        private String age;

        @XmlElement(name = "name")
        private Name name;

        @XmlElement
        private String description;

        @XmlElement
        private String thumbnail;

        @XmlElement
        private String image;

        // Other fields and corresponding getters/setters as per the XML structure
        // ...

        public String getObjectId() {
            return objectId;
        }

        public void setObjectId(String objectId) {
            this.objectId = objectId;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        // Other getters and setters for the fields
        // ...
    }

    public static class Name {

        @XmlAttribute
        private String primary;

        @XmlAttribute
        private int sortindex;

        @XmlValue
        private String value;

        // Getters and setters for 'primary', 'sortindex', and 'value'
        // ...

        public String getPrimary() {
            return primary;
        }

        public void setPrimary(String primary) {
            this.primary = primary;
        }

        public int getSortindex() {
            return sortindex;
        }

        public void setSortindex(int sortindex) {
            this.sortindex = sortindex;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public List<BGGBoardGame> getBoardGames() {
        return boardGames;
    }

    public void setBoardGames(List<BGGBoardGame> boardGames) {
        this.boardGames = boardGames;
    }
}

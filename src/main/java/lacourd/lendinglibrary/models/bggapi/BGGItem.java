package lacourd.lendinglibrary.models.bggapi;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "item")
public class BGGItem {

    private String type;
    private String id;
    private String thumbnail;
    private String image;
    private List<BGGName> names;;
    private String description;
//    private int yearPublished;
//    private int minPlayers;
//    private int maxPlayers;
//    private int playingTime;
//    private int minPlayTime;
//    private int maxPlayTime;
//    private int minAge;

    // Other fields as needed...

    @XmlAttribute(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @XmlAttribute(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @XmlElement(name = "thumbnail")
    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    @XmlElement(name = "image")
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @XmlElement(name = "name")
    public List<BGGName> getNames() {
        return names;
    }

    public void setItems(List<BGGName> names) {
        this.names = names;
    }


    @XmlElement(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    @XmlElement(name = "yearpublished")
//    public int getYearPublished() {
//        return yearPublished;
//    }
//
//    public void setYearPublished(int yearPublished) {
//        this.yearPublished = yearPublished;
//    }
//
//    @XmlElement(name = "minplayers")
//    public int getMinPlayers() {
//        return minPlayers;
//    }
//
//    public void setMinPlayers(int minPlayers) {
//        this.minPlayers = minPlayers;
//    }
//
//    @XmlElement(name = "maxplayers")
//    public int getMaxPlayers() {
//        return maxPlayers;
//    }
//
//    public void setMaxPlayers(int maxPlayers) {
//        this.maxPlayers = maxPlayers;
//    }
//
//    @XmlElement(name = "playingtime")
//    public int getPlayingTime() {
//        return playingTime;
//    }
//
//    public void setPlayingTime(int playingTime) {
//        this.playingTime = playingTime;
//    }
//
//    @XmlElement(name = "minplaytime")
//    public int getMinPlayTime() {
//        return minPlayTime;
//    }
//
//    public void setMinPlayTime(int minPlayTime) {
//        this.minPlayTime = minPlayTime;
//    }
//
//    @XmlElement(name = "maxplaytime")
//    public int getMaxPlayTime() {
//        return maxPlayTime;
//    }
//
//    public void setMaxPlayTime(int maxPlayTime) {
//        this.maxPlayTime = maxPlayTime;
//    }
//
//    @XmlElement(name = "minage")
//    public int getMinAge() {
//        return minAge;
//    }
//
//    public void setMinAge(int minAge) {
//        this.minAge = minAge;
//    }

    // Add getters and setters for other fields as needed...

    @Override
    public String toString() {
        return "BGGItem{" +
                "id='" + id + '\'' +
//                "name='" + names.get(0) + '\'' +
                '}';
    }
}


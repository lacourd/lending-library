package lacourd.lendinglibrary.models;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "boardgames")
@XmlAccessorType(XmlAccessType.FIELD)
public class SearchResult {

    @XmlAttribute
    private String termsofuse;

    @XmlElement(name = "boardgame")
    private List<SearchItem> items;

    public String getTermsofuse() {
        return termsofuse;
    }

    public void setTermsofuse(String termsofuse) {
        this.termsofuse = termsofuse;
    }

    public List<SearchItem> getItems() {
        return items;
    }

    public void setItems(List<SearchItem> items) {
        this.items = items;
    }
}

@XmlAccessorType(XmlAccessType.FIELD)
class SearchItem {

    @XmlAttribute(name = "objectid")
    private String objectId;

    @XmlElement(name = "name")
    private Name name;

    @XmlElement
    private String yearpublished; // You can adjust this based on the actual structure

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public String getYearpublished() {
        return yearpublished;
    }

    public void setYearpublished(String yearpublished) {
        this.yearpublished = yearpublished;
    }
}

@XmlAccessorType(XmlAccessType.FIELD)
class Name {

    @XmlAttribute
    private String primary;

    @XmlValue
    private String value;

    public String getPrimary() {
        return primary;
    }

    public void setPrimary(String primary) {
        this.primary = primary;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}


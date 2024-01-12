package lacourd.lendinglibrary.models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "items")
public class BGGSearchResult {

    private List<BGGItem> items;

    private String termsOfUse;

    @XmlElement(name = "item")
    public List<BGGItem> getItems() {
        return items;
    }

    public void setItems(List<BGGItem> items) {
        this.items = items;
    }

    @XmlElement(name = "termsofuse")
    public String getTermsOfUse() {
        return termsOfUse;
    }

    public void setTermsOfUse(String termsOfUse) {
        this.termsOfUse = termsOfUse;
    }
}

package lacourd.lendinglibrary.models.bggapi;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "items")
public class BGGSearchResult {

    private String total;
    private List<BGGItem> items;

    @XmlAttribute(name="total")
    public String getTotal() {return total;}

    public void setTotal(String total) {
        this.total = total;
    }

    @XmlElement(name = "item")
    public List<BGGItem> getItems() {
        return items;
    }

    public void setItems(List<BGGItem> items) {
        this.items = items;
    }

}

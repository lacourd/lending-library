package lacourd.lendinglibrary.models.bggapi;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "items")
public class BGGItems {
    private List<BGGItem> items;

    @XmlElement(name = "item")
    public List<BGGItem> getItems() {
        return items;
    }

    public void setItems(List<BGGItem> items) {
        this.items = items;
    }
}

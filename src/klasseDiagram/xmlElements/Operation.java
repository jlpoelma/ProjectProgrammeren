package klasseDiagram.xmlElements;

import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jonathan on 19/03/2017.
 */
public class Operation extends Attribute {
    private List<Attribute> attributeList = new ArrayList<>();

    public List<Attribute> getAttributeList() {
        return attributeList;
    }
    @XmlElement(name = "attribute")
    public void setAttributeList(List<Attribute> attributeList) {
        this.attributeList = attributeList;
    }
}

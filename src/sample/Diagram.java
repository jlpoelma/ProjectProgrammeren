package sample;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jonathan on 18/03/2017.
 */
@XmlRootElement (name = "diagram")
public class Diagram {

    private List<Box> boxList = new ArrayList<>();
    public List<Box> getBoxList() {
        return boxList;
    }
    @XmlElement (name = "box")
    public void setBoxList(List<Box> boxList) {
        this.boxList = boxList;
    }
}

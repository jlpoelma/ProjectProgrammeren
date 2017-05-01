package uml.xmlElements;

import javafx.beans.InvalidationListener;

import javax.xml.bind.annotation.XmlAttribute;
import java.util.ArrayList;

/**
 * Created by Jonathan Poelman on 19/03/2017.
 */
public class Relation {

    private String type;

    private String with;

    public String getType() {
        return type;
    }
    @XmlAttribute (name = "type")
    public void setType(String type) {
        this.type = type;
    }

    public String getWith() {
        return with;
    }
    @XmlAttribute (name = "with")
    public void setWith(String with) {
        this.with = with;
    }


}

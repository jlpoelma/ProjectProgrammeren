package uml.xmlElements;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * Created by Jonathan Poelman on 19/03/2017.
 */
public class Attribute {

    private String scope;

    private String visibility;

    private String name;

    private String type;

    public String getScope() {
        return scope;
    }
    @XmlAttribute(name = "scope")
    public void setScope(String scope) {
        this.scope = scope;
    }
    public String getVisibility() {
        return visibility;
    }
    @XmlAttribute(name = "visibility")
    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public String getName() {
        return name;
    }
    @XmlAttribute(name = "name")
    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }
    @XmlAttribute(name = "type")
    public void setType(String type) {
        this.type = type;
    }
}

package uml.xmlElements;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jonathan Poelman on 18/03/2017.
 */
public class Box {

    private String name;

    private float col;

    private float row;

    private float width;

    private List<Relation> relationList = new ArrayList<>(); //relaties bijhouden

    public List<Relation> getRelationList() {
        return relationList;
    }
    @XmlElement (name = "relation")
    public void setRelationList(List<Relation> relationList) {
        this.relationList = relationList;
    }

    private List<Attribute> attributeList = new ArrayList<>();

    private List<Operation> operationList = new ArrayList<>();

    public List<Attribute> getAttributeList() {
        return attributeList;
    }
    @XmlElement(name = "attribute")
    public void setAttributeList(List<Attribute> attributeList) {
        this.attributeList = attributeList;
    }

    public String getName() {
        return name;
    }
    @XmlAttribute(name = "name")
    public void setName(String name) {
        this.name = name;
    }

    public float getCol() {
        return col;
    }
    @XmlAttribute(name = "col")
    public void setCol(float col) {
        this.col = col;
    }

    public float getRow() {
        return row;
    }
    @XmlAttribute(name = "row")
    public void setRow(float row) {
        this.row = row;
    }

    public float getWidth() {
        return width;
    }
    @XmlAttribute(name = "width")
    public void setWidth(float width) {
        this.width = width;
    }

    public List<Operation> getOperationList() {
        return operationList;
    }
    @XmlElement(name = "operation")
    public void setOperationList(List<Operation> operationList) {
        this.operationList = operationList;
    }
}

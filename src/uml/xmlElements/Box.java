package uml.xmlElements;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jonathan Poelman on 18/03/2017.
 */
public class Box implements Observable{

    private List<InvalidationListener> listenerList = new ArrayList<>();

    private String name;

    private double col;

    private double row;

    private double width;

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
        if (name != this.name) {
            this.name = name;
            fireInvalidationEvent();
        }
    }

    public double getCol() {
        return col;
    }
    @XmlAttribute(name = "col")
    public void setCol(double col) {
        if (col != this.col) {
            this.col = col;
            fireInvalidationEvent();
        }
    }
    public double getRow() {
        return row;
    }
    @XmlAttribute(name = "row")
    public void setRow(double row) {
        if (row != this.row) {
            this.row = row;
            fireInvalidationEvent();
        }
    }

    public double getWidth() {
        return width;
    }
    @XmlAttribute(name = "width")
    public void setWidth(double width) {

        if (width != this.width) {
            this.width = width;
            fireInvalidationEvent();
        }
    }

    public List<Operation> getOperationList() {
        return operationList;
    }
    @XmlElement(name = "operation")
    public void setOperationList(List<Operation> operationList) {
        this.operationList = operationList;
    }

    @Override
    public void addListener(InvalidationListener listener) {
        listenerList.add(listener);
    }

    @Override
    public void removeListener(InvalidationListener listener) {
        listenerList.remove(listener);
    }

    public List getListenerList(){
        return listenerList;
    }

    private void fireInvalidationEvent(){
            for (InvalidationListener listener: listenerList){
                listener .invalidated(this);
            }
    }

    public void addAttribute(Attribute attribute){
        attributeList.add(attribute);
        fireInvalidationEvent();
    }

    public void addRelation(Relation relation){
        relationList.add(relation);
    }

    public void removeAttribute(Attribute attribute){
        attributeList.remove(attribute);
        fireInvalidationEvent();
    }

    public void addOperation(Operation operation){

        operationList.add(operation);
        fireInvalidationEvent();
    }

    public void removeRelation(Relation relation){
        relationList.remove(relation);
    }

    public void removeOperation(Operation operation){
        operationList.remove(operation);
        fireInvalidationEvent();
    }


}

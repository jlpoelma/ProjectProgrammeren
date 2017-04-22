package uml;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import uml.generators.BoxGenerator;
import uml.xmlElements.Attribute;
import uml.xmlElements.Box;
import uml.xmlElements.Operation;

import java.util.HashMap;

/**
 * Created by Jonathan on 4/21/2017.
 */
public class BoxView extends VBox implements InvalidationListener {

    private VBox top;
    private VBox middle;
    private VBox bottom;
    private Box model;
    private HashMap<String, String> visibilities;

    public BoxView(Box model){
        makeDraggable();
        this.setId("mainBox"); //id voor css toevoegen
        this.model = model;
        model.addListener(this);
        top = new VBox();
        middle = new VBox();
        middle.setId("middle");
        bottom = new VBox();
        getChildren().add(top);
        getChildren().add(middle);
        getChildren().add(bottom);
        visibilities = new HashMap<>();
        visibilities.put("public", "+");
        visibilities.put("private", "-");
        visibilities.put("protected", "#");
        visibilities.put("derived", "/");
        visibilities.put("package", "~");
    }
    @Override
    public void invalidated(Observable observable) {
        setPrefWidth(model.getWidth());
        setLayoutX(model.getCol());
        setLayoutY(model.getRow());
    }

    public void setHeader(String header){
        TextField title = new TextField(header);
        title.setId("header");
        top.getChildren().add(title);
        top.getChildren().add(new Button("edit"));
    }

    public void addAttribute(Attribute attribute){
        Label label = new Label(visibilities.get(attribute.getVisibility()) + attribute.getName() + " : " + attribute.getType());
        middle.getChildren().add(label);
    }

    public void addOperation(Operation operation){
        if (operation.getAttributeList().isEmpty()) { //methode heeft geen attributen
            Label label = new Label(visibilities.get(operation.getVisibility()) + operation.getName() + " : " + operation.getType());
            bottom.getChildren().add(label);
        }
        else{ //methode heeft attributen
            for (Attribute a: operation.getAttributeList()) { //voor elk attribuuut de methode toevoegen
                Label label = new Label(visibilities.get(operation.getVisibility()) + operation.getName() +
                        "(" + a.getName() + " : " + a.getType() + ")" + " : " + operation.getType());
                bottom.getChildren().add(label);
            }
        }
    }

    public Box getModel(){
        return model;
    }

    private class Delta {
        public double x;
        public double y;
    }

    public void makeDraggable(){
        final Delta delta = new Delta();
        this.setOnMousePressed(event -> {
            this.setCursor(Cursor.MOVE);
            delta.x = this.getLayoutX() - event.getSceneX();
            delta.y = this.getLayoutY() - event.getSceneY();
        });

        this.setOnMouseReleased(event -> {
            this.setCursor(Cursor.HAND);
        });

        this.setOnMouseEntered(event -> {
            this.setCursor(Cursor.HAND);
        });

        this.setOnMouseDragged(event -> {
            this.getModel().setCol(delta.x + event.getSceneX());
            this.getModel().setRow(delta.y + event.getSceneY());
        });
    }




}

package uml.views;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import uml.UmlCompanion;
import uml.arrows.Arrow;
import uml.generators.BoxGenerator;
import uml.generators.RelationGenerator;
import uml.popupWindows.AddAttributeDialog;
import uml.popupWindows.RelationDialog;
import uml.xmlElements.*;

import java.util.HashMap;
import java.util.Optional;

/**
 * Created by Jonathan on 4/21/2017.
 */
public class BoxView extends VBox implements InvalidationListener {

    private VBox top;
    private VBox middle;
    private VBox bottom;
    private Box model;
    private HashMap<String, String> visibilities;
    private Diagram diagram;

    public BoxView(Diagram diagram, Box model){
        this.diagram = diagram;
        makeDraggable();
        makeResizable();
        setOnRightClick();
        this.setId("mainBox"); //id voor css toevoegen
        this.model = model;
        model.addListener(this);
        top = new VBox();
        middle = new VBox(5);
        middle.setId("middle");
        bottom = new VBox(5);
        getChildren().add(top);
        getChildren().add(middle);
        getChildren().add(bottom);
        setAttributes();
        setOperations();
        this.invalidated(null);
    }
    @Override
    public void invalidated(Observable observable) {
        setPrefWidth(model.getWidth());
        setLayoutX(model.getCol());
        setLayoutY(model.getRow());
        setHeader(model.getName());
    }

    public void setHeader(String header){
        Label title = new Label(header);
        title.setId("header");
        top.getChildren().setAll(title);
    }

    public void setAttributes(){
        Button add = new Button("Add attribute...");
        middle.getChildren().add(add);
        add.setOnAction(event -> {
            setAttributeButton();
        });
        for (Attribute a: model.getAttributeList()) {
            addAttribute(a, middle);
        }
    }

    public void addAttribute(Attribute attribute, VBox vBox){
        vBox.getChildren().add(vBox.getChildren().size() - 1, new AttributeView(attribute, getModel()));
    }

    public void setOperations(){
        Button add = new Button("Add operation...");
        bottom.getChildren().add(add);
        add.setOnAction(event -> {
            setOperationButton();
        });
        for (Operation o: model.getOperationList()) {
            addAttribute(o, bottom);
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
        setOnMousePressed(event -> {
            this.setCursor(Cursor.MOVE);
            delta.x = this.getLayoutX() - event.getSceneX();
            delta.y = this.getLayoutY() - event.getSceneY();
        });

        setOnMouseReleased(event -> {
            this.setCursor(Cursor.HAND);
        });

        setOnMouseEntered(event -> {
            this.setCursor(Cursor.HAND);
        });

        this.setOnMouseDragged(event -> {
            this.getModel().setCol(delta.x + event.getSceneX());
            this.getModel().setRow(delta.y + event.getSceneY());
        });
    }

    public void makeResizable(){
        Rectangle resizeHandleArea = new Rectangle();
        resizeHandleArea.setWidth(1);
        resizeHandleArea.setOpacity(0.0);
        resizeHandleArea.heightProperty().bind(heightProperty());
        resizeHandleArea.xProperty().bind(layoutXProperty().add(widthProperty()));
        resizeHandleArea.yProperty().bind(layoutYProperty());
        this.parentProperty().addListener((obs, oldParent, newParent) -> {
            if (newParent != null) {
                ((Pane) newParent).getChildren().add(resizeHandleArea);
            }
        });

        final Delta delta = new Delta();
        resizeHandleArea.setOnMousePressed(event -> {
            delta.x = this.getPrefWidth() - event.getSceneX();
        });

        resizeHandleArea.setOnMouseReleased(event -> {
            resizeHandleArea.setCursor(Cursor.HAND);
        });

        resizeHandleArea.setOnMouseEntered(event -> {
            resizeHandleArea.setCursor(Cursor.H_RESIZE);
        });

        resizeHandleArea.setOnMouseDragged(event -> {
            model.setWidth(delta.x + event.getSceneX());
        });
    }

    public void setOnRightClick(){
        ContextMenu contextMenu = new ContextMenu();
        setRenameButton(contextMenu);
        setDeleteButton(contextMenu);
        setCreateRelationButton(contextMenu);
        this.setOnContextMenuRequested(event -> {
            contextMenu.show(this, event.getScreenX(), event.getScreenY());
        });
    }

    public void setRenameButton(ContextMenu contextMenu){
        MenuItem rename = new MenuItem("Rename");
        contextMenu.getItems().add(rename);
        rename.setOnAction(event -> {
            TextInputDialog inputDialog = new TextInputDialog();
            Optional<String> result = inputDialog.showAndWait();
            result.ifPresent(s -> this.getModel().setName(s));
        });
    }

    public void setDeleteButton(ContextMenu contextMenu){
        MenuItem delete = new MenuItem("Delete");
        contextMenu.getItems().add(delete);
        delete.setOnAction(event -> {
            AnchorPane pane = (AnchorPane)getParent();
            for (int i = model.getListenerList().size() - 1; i > 0; i--){
                Arrow arrow = (Arrow)model.getListenerList().get(i);
                arrow.getDestinationModel().removeListener(arrow);
                arrow.getStartModel().removeListener(arrow);
                arrow.remove(pane);
            }
            diagram.removeBox(model);
            pane.getChildren().remove(this);
        });
    }

    public void setAttributeButton(){
            AddAttributeDialog dialog = new AddAttributeDialog();
            dialog.showAndWait();
            if (dialog.getResult() == ButtonType.OK){
                Attribute attribute = new Attribute();
                attribute.setName(dialog.getName().getText());
                attribute.setScope(dialog.getScope().getValue().toString());
                attribute.setType(dialog.getType().getText());
                attribute.setVisibility(dialog.getVisibility().getValue().toString());
                addAttribute(attribute, middle);
                model.addAttribute(attribute);
            }
    }

    public void setCreateRelationButton(ContextMenu contextMenu){
        MenuItem addRelation = new MenuItem("Add relation...");
        contextMenu.getItems().add(addRelation);
        addRelation.setOnAction(event -> {
            RelationDialog dialog = new RelationDialog(getModel().getName());
            dialog.showAndWait();
            if (dialog.getResult() == ButtonType.OK){
                AnchorPane pane = (AnchorPane)getParent();
                Relation relation = new Relation();
                model.addRelation(relation);
                relation.setType(dialog.getType().getValue().toString());
                relation.setWith(dialog.getWith().getValue().toString());
                new RelationGenerator().setArrow(this.getModel(), UmlCompanion.classes.get(relation.getWith()), relation, pane);
            }
        });
    }

    public void setOperationButton(){

    }
}
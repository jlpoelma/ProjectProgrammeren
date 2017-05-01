package uml.views;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import uml.popupWindows.AddAttributeDialog;
import uml.xmlElements.Attribute;
import uml.xmlElements.Box;

import java.util.HashMap;

/**
 * Created by jonathan on 1/05/2017.
 */
public class AttributeView extends HBox implements InvalidationListener{

    protected Label text;
    protected HashMap<String, String> visibilities;
    protected Attribute attribute;
    private Box boxModel;
    private Button edit;
    private Button remove;
    public AttributeView(Attribute attribute, Box model){
        boxModel = model;
        this.attribute = attribute;
        attribute.addListener(this);
        visibilities = new HashMap<>();
        visibilities.put("public", "+");
        visibilities.put("private", "-");
        visibilities.put("protected", "#");
        visibilities.put("derived", "/");
        visibilities.put("package", "~");
        setId("attribute");
        setSpacing(5);
        text = new Label();
        invalidated(null);
        edit = new Button("edit");
        remove = new Button("remove");
        getChildren().addAll(text, edit, remove);
        remove.setOnAction(event -> {
            remove();
        });
        edit.setOnAction(event -> {
            edit();
        });
    }

    @Override
    public void invalidated(Observable observable) {
        text.setText(visibilities.get(attribute.getVisibility()) + attribute.getName() + " : " + attribute.getType());
    }

    public void remove(){
        VBox vBox = (VBox)getParent();
        vBox.getChildren().remove(this);
        attribute.removeListener(this);
        boxModel.removeAttribute(attribute);
    }

    public void edit(){
        AddAttributeDialog dialog = new AddAttributeDialog();
        dialog.getName().setText(attribute.getName());
        dialog.getType().setText(attribute.getType());
        dialog.getScope().setValue(attribute.getScope());
        dialog.getVisibility().setValue(attribute.getVisibility());
        dialog.showAndWait();
        if (dialog.getResult() == ButtonType.OK){
            attribute.setName(dialog.getName().getText());
            attribute.setScope(dialog.getScope().getValue().toString());
            attribute.setType(dialog.getType().getText());
            attribute.setVisibility(dialog.getVisibility().getValue().toString());
        }
    }
}

package uml.popupWindows;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.w3c.dom.Attr;
import uml.xmlElements.Attribute;
import uml.xmlElements.Operation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Jonathan on 5/2/2017.
 */
public class OperationDialog extends AddAttributeDialog {

    private ArrayList<OperationAttributeHBox> attributes;

    private VBox attributeSpace;

    private Button addAttribute;

    public OperationDialog(){
        attributes = new ArrayList<>();
        attributeSpace = new VBox(10);
        addAttribute = new Button("Add Attribute...");
        attributeSpace.getChildren().add(addAttribute);
        inputs.getChildren().add(attributeSpace);
        addAttribute.setOnAction(event -> {
            addAttribute("", "");
        });
    }

    public void addAttribute(String name, String type){
        OperationAttributeHBox attributeHBox = new OperationAttributeHBox();
        attributes.add(attributeHBox);
        attributeHBox.getName().setText(name);
        attributeHBox.getType().setText(type);
        attributeHBox.getRemoveButton().setOnAction(event -> {
            attributeSpace.getChildren().remove(attributeHBox);
            attributeSpace.getScene().getWindow().sizeToScene();
            attributes.remove(attributes);
        });
        attributeSpace.getChildren().add(attributeSpace.getChildren().size() - 1, attributeHBox);
        getDialogPane().getScene().getWindow().sizeToScene();
    }

    public List<Attribute> getAttributes(){
        ArrayList<Attribute> attributeList = new ArrayList<>();
        for (OperationAttributeHBox h: attributes) {
            Attribute a = new Attribute();
            a.setName(h.getType().getText());
            a.setType(h.getName().getText());
            attributeList.add(a);
        }
        return attributeList;
    }

}

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
import java.util.List;

/**
 * Created by Jonathan on 5/2/2017.
 */
public class OperationDialog extends AddAttributeDialog {

    private VBox attributeSpace;

    private Button addAttribute;

    public OperationDialog(){
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
        attributeHBox.getName().setText(name);
        attributeHBox.getType().setText(type);
        attributeSpace.getChildren().add(attributeSpace.getChildren().size() - 1, attributeHBox);
        getDialogPane().getScene().getWindow().sizeToScene();
    }

    public List<Attribute> getAttributes(){
        List<Attribute> attributes = new ArrayList<>();
        List<Node> attributeList = attributeSpace.getChildren().subList(0, attributeSpace.getChildren().size() - 1);
        for (Node n: attributeList) {
            OperationAttributeHBox hBox = (OperationAttributeHBox)n;
            Attribute a = new Attribute();
            a.setName(hBox.getType().getText());
            a.setType(hBox.getName().getText());
            attributes.add(a);
        }
        return attributes;
    }

}

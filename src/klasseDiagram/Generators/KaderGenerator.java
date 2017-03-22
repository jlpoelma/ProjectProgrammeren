package klasseDiagram.Generators;

import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import klasseDiagram.xmlElements.Attribute;
import klasseDiagram.xmlElements.Box;
import klasseDiagram.xmlElements.Diagram;
import klasseDiagram.xmlElements.Operation;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Jonathan on 3/22/2017.
 */
public class KaderGenerator {

    private AnchorPane paneel;
    private Diagram diagram;
    private HashMap<String, String> visibilities;

    public KaderGenerator(AnchorPane paneel, Diagram diagram){
        this.paneel = paneel;
        this.diagram = diagram;
        visibilities = new HashMap<>();
        visibilities.put("public", "+");
        visibilities.put("private", "-");
        visibilities.put("protected", "#");
        visibilities.put("derived", "/");
        visibilities.put("package", "~");
    }

    public void generateKader(){
        for (Box b: diagram.getBoxList()) {
            VBox kader = new VBox();
            kader.setId("kader");
            paneel.getChildren().add(kader);
            kader.setLayoutX(b.getCol());
            kader.setLayoutY(b.getRow());
            kader.setPrefWidth(b.getWidth());
            generateHeader(b.getName(), kader);
            generateAttributes(b.getAttributeList(), kader);
            generateOperations(b.getOperationList(), kader);
        }
    }

    public void generateHeader(String header, VBox kader){
        VBox headerBox = new VBox();
        headerBox.setId("headerBox");
        Label label = new Label(header);
        label.setId("header");
        kader.getChildren().add(headerBox);
        headerBox.getChildren().add(label);
    }

    public void generateAttributes(List<Attribute> attributes, VBox kader){
        VBox attributeBox = new VBox();
        kader.getChildren().add(attributeBox);
        for (Attribute a: attributes
                ) {
            Label label = new Label(visibilities.get(a.getVisibility()) + a.getName() + " : " + a.getType());
            attributeBox.getChildren().add(label);
        }
        attributeBox.setId("midden");
    }

    public void generateOperations(List<Operation> operations, VBox kader){
        VBox operationBox = new VBox();
        kader.getChildren().add(operationBox);
        for (Operation o: operations){
            if (o.getAttributeList().isEmpty()) {
                Label label = new Label(visibilities.get(o.getVisibility()) + o.getName() + " : " + o.getType());
                operationBox.getChildren().add(label);
            }
            else{
                for (Attribute a: o.getAttributeList()) {
                    Label label = new Label(visibilities.get(o.getVisibility()) + o.getName() +
                            "(" + a.getName() + " : " + a.getType() + ")" + " : " + o.getType());
                    operationBox.getChildren().add(label);
                }
            }
        }
    }
}

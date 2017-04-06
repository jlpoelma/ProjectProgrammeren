package klasseDiagram.Generators;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import klasseDiagram.xmlElements.Attribute;
import klasseDiagram.xmlElements.Box;
import klasseDiagram.xmlElements.Diagram;
import klasseDiagram.xmlElements.Operation;

import java.io.File;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Jonathan on 3/22/2017.
 */
public class KaderGenerator {

    private HashMap<String, String> visibilities;

    public KaderGenerator(){
        visibilities = new HashMap<>();
        visibilities.put("public", "+");
        visibilities.put("private", "-");
        visibilities.put("protected", "#");
        visibilities.put("derived", "/");
        visibilities.put("package", "~");
    }

    public HashMap<String, VBox> generateBox(AnchorPane pane, Diagram diagram) {
        HashMap<String, VBox> boxes = new HashMap<>();
        for (Box b : diagram.getBoxList()) {
            VBox kader = new VBox(); //nieuwe box aanmaken
            kader.setId("kader"); //id voor css toevoegen
            pane.getChildren().add(kader); //box toevoegen aan AnchorPane
            kader.setLayoutX(b.getCol()); //positie specifiëren
            kader.setLayoutY(b.getRow());
            kader.setPrefWidth(b.getWidth()); //breedte instellen
            generateHeader(b.getName(), kader); //titel toevoegen
            generateAttributes(b.getAttributeList(), kader); //attributen toevoegen
            generateOperations(b.getOperationList(), kader); //methodes toevoegen
            kader.applyCss(); //css toepassen zodat de hoogte kan opgevraagd worden
            kader.layout();
            boxes.put(b.getName(), kader); /*box aan hashmap met als sleutel de naam toevoegen om makkelijker relaties
             te stellen*/
        }
        return boxes; //hashmap teruggeven voor de RelationGenerator
    }

    public void generateHeader(String header, VBox kader){
        VBox headerBox = new VBox(); //vbox voor header aanmaken
        Label label = new Label(header); //titel instellen
        label.setId("header");
        kader.getChildren().add(headerBox); //headerVBox toevoegen
        headerBox.getChildren().add(label); //titel toevoegen
    }

    public void generateAttributes(List<Attribute> attributes, VBox kader){
        VBox attributeBox = new VBox(); //vbox voor attributen aanmaken
        kader.getChildren().add(attributeBox);
        for (Attribute a: attributes //voor elk attributen de juiste tekst toevoegen
                ) {
            Label label = new Label(visibilities.get(a.getVisibility()) + a.getName() + " : " + a.getType());
            attributeBox.getChildren().add(label);
        }
        attributeBox.setId("middle"); //midden als css-id toevoegen zodat de juiste borders worden ingesteld
    }

    public void generateOperations(List<Operation> operations, VBox kader){
        VBox operationBox = new VBox(); //vbox voor methodes aanmaken
        kader.getChildren().add(operationBox);
        for (Operation o: operations){ //voor elke methodes juiste tekst toevoegen
            if (o.getAttributeList().isEmpty()) { //methode heeft geen attributen
                Label label = new Label(visibilities.get(o.getVisibility()) + o.getName() + " : " + o.getType());
                operationBox.getChildren().add(label);
            }
            else{ //methode heeft attributen
                for (Attribute a: o.getAttributeList()) { //voor elk attribuuut de methode toevoegen
                    Label label = new Label(visibilities.get(o.getVisibility()) + o.getName() +
                            "(" + a.getName() + " : " + a.getType() + ")" + " : " + o.getType());
                    operationBox.getChildren().add(label);
                }
            }
        }
    }
}

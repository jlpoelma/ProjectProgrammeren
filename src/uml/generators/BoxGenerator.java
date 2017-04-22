package uml.generators;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import uml.BoxView;
import uml.xmlElements.Attribute;
import uml.xmlElements.Box;
import uml.xmlElements.Diagram;
import uml.xmlElements.Operation;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Jonathan Poelman on 3/22/2017.
 */
public class BoxGenerator {

    private HashMap<String, String> visibilities;

    public BoxGenerator(){
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
            BoxView kader = new BoxView(b); //nieuwe box aanmaken
            kader.setId("mainBox"); //id voor css toevoegen
            pane.getChildren().add(kader); //box toevoegen aan AnchorPane
            kader.setLayoutX(b.getCol()); //positie specifiëren
            kader.setLayoutY(b.getRow());
            kader.setPrefWidth(b.getWidth()); //breedte instellen
            kader.setHeader(b.getName());
            generateAttributes(b.getAttributeList(), kader); //attributen toevoegen
            generateOperations(b.getOperationList(), kader); //methodes toevoegen
            kader.applyCss(); //css toepassen zodat de hoogte kan opgevraagd worden
            kader.layout();
            boxes.put(b.getName(), kader); /*box aan hashmap met als sleutel de naam toevoegen om makkelijker relaties
             te stellen*/
        }
        return boxes; //hashmap teruggeven voor de RelationGenerator
    }

    public void generateAttributes(List<Attribute> attributes, BoxView kader){
        for (Attribute a: attributes //voor elk attributen de juiste tekst toevoegen
                ) {
            kader.addAttribute(a);
        }
    }

    public void generateOperations(List<Operation> operations, BoxView kader) {
        for (Operation o : operations) { //voor elke methodes juiste tekst toevoegen
            kader.addOperation(o);
        }
    }

    public void makeResizable(BoxView boxView){
    }

}

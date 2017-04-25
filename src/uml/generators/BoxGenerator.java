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

    public HashMap<String, VBox> generateBoxes(AnchorPane pane, Diagram diagram) {
        HashMap<String, VBox> boxes = new HashMap<>();
        for (Box b : diagram.getBoxList()) {
            BoxView kader = createBox(pane, b);
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

    public BoxView createBox(AnchorPane pane, Box box){
        BoxView kader = new BoxView(box);
        pane.getChildren().add(kader); //box toevoegen aan AnchorPane
        return kader;
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


}

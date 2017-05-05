package uml.generators;

import javafx.scene.layout.*;
import uml.views.BoxView;
import uml.xmlElements.Attribute;
import uml.xmlElements.Box;
import uml.xmlElements.Diagram;
import uml.xmlElements.Operation;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Jonathan Poelman on 3/22/2017.
 */
public class BoxManager {

    private HashMap<String, String> visibilities;

    private AnchorPane pane;

    public BoxManager(AnchorPane pane){
        this.pane = pane;
    }

    public HashMap<String, Box> generateBoxes(Diagram diagram) {
        HashMap<String, Box> boxes = new HashMap<>();
        for (Box b : diagram.getBoxList()) {
            BoxView kader = createBox(pane, b, diagram);
            kader.applyCss();
            kader.layout();
            boxes.put(b.getName(), b); /*box aan hashmap met als sleutel de naam toevoegen om makkelijker relaties
             te stellen*/
        }
        return boxes; //hashmap teruggeven voor de RelationGenerator
    }

    public BoxView createBox(AnchorPane pane, Box box, Diagram diagram){
        BoxView kader = new BoxView(diagram, box);
        pane.getChildren().add(kader); //box toevoegen aan AnchorPane
        return kader;
    }

    public void removeBox(BoxView boxView){

    }
}

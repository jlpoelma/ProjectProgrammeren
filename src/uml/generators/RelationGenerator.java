package uml.generators;

import javafx.geometry.Point2D;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import uml.arrows.AssociationDependencyArrow;
import uml.arrows.CompositionAggregationArrow;
import uml.arrows.InheritanceRealizationArrow;
import uml.xmlElements.Box;
import uml.xmlElements.Diagram;
import uml.xmlElements.Relation;

import java.util.HashMap;

/**
 * Created by Jonathan Poelman on 3/22/2017.
 */
public class RelationGenerator {

    public void generateRelation(AnchorPane pane, Diagram diagram, HashMap<String, Box> boxes){
        for (Box b: diagram.getBoxList()) { //voor elke box juiste relaties toevoegen
            for (Relation r : b.getRelationList()) {
                Box startBox = boxes.get(b.getName()); //start-vbox ophalen
                Box destinationBox = boxes.get(r.getWith()); //aankomst-vbox ophalen
                setArrow(startBox, destinationBox, r, pane); //pijl toevoegen aan AnchorPane
            }
        }
    }
    public void setArrow(Box startBox, Box destinationBox, Relation relation, AnchorPane pane){
        if (relation.getType().matches("association|dependency")){
            new AssociationDependencyArrow(startBox, destinationBox, relation.getType().equals("dependency"), relation).create(pane);
        } else if (relation.getType().matches("inheritance|realization")){
            new InheritanceRealizationArrow(startBox, destinationBox, relation.getType().equals("realization"), relation).create(pane);
        } else{
            new CompositionAggregationArrow(startBox, destinationBox, relation.getType().equals("aggregation"), relation).create(pane);
        } //juiste pijl toevoegen afhankelijk van het gespecifieerde type
    }

}

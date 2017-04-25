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

    /*public Point2D calculateMiddle(VBox kader){ //midden van de VBox berekenen
        double x1 = kader.getLayoutX() + kader.getPrefWidth()/2;
        double y1 = kader.getLayoutY() + kader.getBoundsInParent().getHeight()/2;
        return new Point2D(x1, y1); //juiste coördinaat teruggeven
    }*/

    public void generateRelation(AnchorPane pane, Diagram diagram, HashMap<String, Box> boxes){
        for (Box b: diagram.getBoxList()) { //voor elke box juiste relaties toevoegen
            for (Relation r : b.getRelationList()) {
                Box startBox = boxes.get(b.getName()); //start-vbox ophalen
                Box destinationBox = boxes.get(r.getWith()); //aankomst-vbox ophalen
                /*Point2D middleStart = calculateMiddle(startBox); //midden van start en aankomst berekenen
                Point2D middleDestination = calculateMiddle(destinationBox);
                double angleStart = calculateAngle(middleStart, middleDestination); //starthoek van pijl berekenen
                double angleDestination = (angleStart + 180)%360; //aankomsthoek van pijl berekenen
                Point2D start = calculateIntersection(startBox, middleStart, middleDestination, angleStart); /*het snijpunt van de
                pijl met de start- en aankomstVBox berekenen
                Point2D destination = calculateIntersection(destinationBox, middleDestination, middleStart, angleDestination);*/
                setArrow(startBox, destinationBox, r, pane); //pijl toevoegen aan AnchorPane
            }
        }
    }

    /*public double calculateAngle(Point2D start, Point2D end){
        double angle = Math.toDegrees(Math.atan2(-(end.getY() - start.getY()), end.getX() - start.getX()));
        //berekent hoek van rechte
        if (angle < 0){
            angle += 360;
        }
        return angle;
    }

    public Point2D calculateIntersection(VBox box, Point2D start, Point2D destination, double hoek){
        Point2D point1 = new Point2D(box.getLayoutX() + box.getPrefWidth(), box.getLayoutY());
        double angle1 = calculateAngle(start, point1);
        Point2D point2 = new Point2D(box.getLayoutX(), box.getLayoutY());
        double angle2 = calculateAngle(start, point2);
        Point2D point3 = new Point2D(box.getLayoutX(), box.getLayoutY() +
                box.getBoundsInParent().getHeight());
        double angle3 = calculateAngle(start, point3);
        Point2D point4 = new Point2D(box.getLayoutX() + box.getPrefWidth(),
                box.getLayoutY() + box.getBoundsInParent().getHeight());
        double angle4 = calculateAngle(start, point4);
        //alle punten + hoeken van de VBox berekenen tov middelpunt
        double m = (destination.getY() - start.getY())/(destination.getX() - start.getX()); //rico van pijl berekenen
        double x;
        double y;
        if (hoek >= angle4){
            x = point4.getX();
            y = start.getY() - m*(start.getX() - x);
        } else if (hoek >= angle3){
            y = point3.getY();
            x = start.getX() - (start.getY() - y)/m;
        } else if (hoek >= angle2){
            x = point2.getX();
            y = start.getY() - m*(start.getX() - x);
        } else{
            y = point1.getY();
            x = start.getX() - (start.getY() - y)/m;
        } //controleren tussen welke twee punten het snijpunt zit + dit snijpunt berekenen
        return new Point2D(x, y);
    }*/

    public void setArrow(Box startBox, Box destinationBox, Relation relation, AnchorPane pane){
        if (relation.getType().matches("association|dependency")){
            new AssociationDependencyArrow(startBox, destinationBox, relation.getType().equals("dependency")).create(pane);
        } else if (relation.getType().matches("inheritance|realization")){
            new InheritanceRealizationArrow(startBox, destinationBox, relation.getType().equals("realization")).create(pane);
        } else{
            new CompositionAggregationArrow(startBox, destinationBox, relation.getType().equals("aggregation")).create(pane);
        } //juiste pijl toevoegen afhankelijk van het gespecifieerde type
    }

}

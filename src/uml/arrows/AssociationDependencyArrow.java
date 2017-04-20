package uml.arrows;

import javafx.geometry.Point2D;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;

/**
 * Created by Jonathan Poelman on 3/22/2017.
 */
public class AssociationDependencyArrow extends Arrow{

    private Line arrowSide1;
    private Line arrowSide2;

    public AssociationDependencyArrow(Point2D start, Point2D destination, boolean dotted){
        super(start, destination);
        Point2D point = new Point2D(4, 4);


        if (dotted){
            arrowLine.setId("dotted"); //specifieert of de hoofdlijn al dan niet gestippeld is
        }
        arrowSide1 = createArrowSide();
        arrowSide2 = createArrowSide();
        arrowSide1.setStartX(pointRight.getX()); //stelt startpunt van pijlkop in
        arrowSide1.setStartY(pointRight.getY());
        arrowSide2.setStartX(pointLeft.getX());
        arrowSide2.setStartY(pointLeft.getY());
    }

    public Line createArrowSide(){ //maakt een pijlkop zijde aan
        Line arrowSide = new Line();
        arrowSide.setEndY(arrowLine.getEndY()); //stelt eindpunt van pijlkop in (zelfde als hoofdlijn
        arrowSide.setEndX(arrowLine.getEndX());
        return arrowSide;
    }

    public void create(AnchorPane pane){ //voegt hoofdlijn en eindpijltjes toe
        super.create(pane);
        pane.getChildren().add(arrowSide1);
        pane.getChildren().add(arrowSide2);
    }


}

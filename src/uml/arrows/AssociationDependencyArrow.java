package uml.arrows;

import javafx.beans.Observable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import uml.xmlElements.Box;

/**
 * Created by Jonathan Poelman on 3/22/2017.
 */
public class AssociationDependencyArrow extends Arrow{

    private Line arrowSide1;
    private Line arrowSide2;

    public AssociationDependencyArrow(Box start, Box destination, boolean dotted){
        super(start, destination);
        if (dotted){
            arrowLine.setId("dotted"); //specifieert of de hoofdlijn al dan niet gestippeld is
        }
        arrowSide1 = new Line();
        arrowSide2 = new Line();
        modifyArrowSide();
    }

    public void modifyArrowSide(){ //maakt een pijlkop zijde aan
        arrowSide1.setEndY(arrowLine.getEndY()); //stelt eindpunt van pijlkop in (zelfde als hoofdlijn
        arrowSide1.setEndX(arrowLine.getEndX());
        arrowSide2.setEndY(arrowLine.getEndY()); //stelt eindpunt van pijlkop in (zelfde als hoofdlijn
        arrowSide2.setEndX(arrowLine.getEndX());
        arrowSide1.setStartX(pointRight.getX()); //stelt startpunt van pijlkop in
        arrowSide1.setStartY(pointRight.getY());
        arrowSide2.setStartX(pointLeft.getX());
        arrowSide2.setStartY(pointLeft.getY());
    }

    public void create(AnchorPane pane){ //voegt hoofdlijn en eindpijltjes toe
        super.create(pane);
        pane.getChildren().add(arrowSide1);
        pane.getChildren().add(arrowSide2);
    }


    @Override
    public void invalidated(Observable observable) {
        setLine();
        modifyArrowSide();
    }
}

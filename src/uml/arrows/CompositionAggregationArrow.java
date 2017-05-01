package uml.arrows;

import javafx.beans.Observable;
import javafx.geometry.Point2D;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Polygon;
import uml.xmlElements.Box;

/**
 * Created by Jonathan Poelman on 3/04/2017.
 */
public class CompositionAggregationArrow extends Arrow{

    private Polygon arrowHead;
    public CompositionAggregationArrow(Box start, Box destination, boolean white){
        super(start, destination);
        arrowHead = new Polygon(); //polygon voor de ruit aanmaken
        if (white) { //specifieert of ruit al dan niet wit is
            arrowHead.setId("white");
        }
        this.invalidated(null);
    }

    public void modifyArrowHead(){
        double xmiddle = (pointLeft.getX() + pointRight.getX())/2; //middelpunt van ruit berekenen
        double ymiddle = (pointLeft.getY() + pointRight.getY())/2;
        Point2D fourthPoint = rotatePoint(180, arrowLine.getEndX(), arrowLine.getEndY(), xmiddle, ymiddle);
        //vierde punt van ruit berekenen
        arrowHead.getPoints().setAll(arrowLine.getEndX(), arrowLine.getEndY(), //4 punten van ruit instellen
                pointRight.getX(), pointRight.getY(),
                fourthPoint.getX(), fourthPoint.getY(),
                pointLeft.getX(), pointLeft.getY());
    }

    public void create(AnchorPane pane){
        super.create(pane); //ruit + hoofdlijn toevoegen
        pane.getChildren().add(arrowHead);
    }

    public void remove(AnchorPane pane){
        super.remove(pane);
        pane.getChildren().remove(arrowHead);
    }

    @Override
    public void invalidated(Observable observable) {
        setLine();
        modifyArrowHead();
    }
}

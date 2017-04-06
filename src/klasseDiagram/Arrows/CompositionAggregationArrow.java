package klasseDiagram.Arrows;

import javafx.geometry.Point2D;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Polygon;

import java.awt.*;

/**
 * Created by jonathan on 3/04/2017.
 */
public class CompositionAggregationArrow extends Arrow{

    private Polygon arrowHead;
    public CompositionAggregationArrow(Point2D start, Point2D destination, boolean white){
        super(start, destination);
        arrowHead = new Polygon(); //polygon voor de ruit aanmaken
        double xmiddle = (pointLeft.getX() + pointRight.getX())/2; //middelpunt van ruit berekenen
        double ymiddle = (pointLeft.getY() + pointRight.getY())/2;
        Point2D fourthPoint = rotatePoint(180, arrowLine.getEndX(), arrowLine.getEndY(), xmiddle, ymiddle);
        //vierde punt van ruit berekenen
        arrowHead.getPoints().setAll(arrowLine.getEndX(), arrowLine.getEndY(), //4 punten van ruit instellen
                pointRight.getX(), pointRight.getY(),
                fourthPoint.getX(), fourthPoint.getY(),
                pointLeft.getX(), pointLeft.getY());
        if (white) { //specifieert of ruit al dan niet wit is
            arrowHead.setId("white");
        }

    }

    public void create(AnchorPane pane){
        super.create(pane); //ruit + hoofdlijn toevoegen
        pane.getChildren().add(arrowHead);
    }
}

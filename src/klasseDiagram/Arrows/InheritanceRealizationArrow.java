package klasseDiagram.Arrows;


import javafx.geometry.Point2D;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Polygon;

/**
 * Created by jonathan on 3/04/2017.
 */
public class InheritanceRealizationArrow extends Arrow {

    private Polygon arrowHead;

    public InheritanceRealizationArrow(Point2D start, Point2D destination, boolean dotted) {
        super(start, destination);
        arrowHead = new Polygon(); //polygon voor de driehoek aanmaken
        arrowHead.getPoints().setAll(arrowLine.getEndX(), arrowLine.getEndY(), //3 punten van de driehoek instellen
                pointRight.getX(), pointRight.getY(),
                pointLeft.getX(), pointLeft.getY());
            arrowHead.setId("white");
        if (dotted){ //al dan niet de hoofdlijn een stippellijn maken
            arrowLine.setId("dotted");
        }
    }



    public void create(AnchorPane pane){
        super.create(pane);//hoodlijn + driehoek toevoegen aan AnchorPane
        pane.getChildren().add(arrowHead);
    }

}

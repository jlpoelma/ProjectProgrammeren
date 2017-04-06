package klasseDiagram.Arrows;

import javafx.geometry.Point2D;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;

/**
 * Created by jonathan on 3/04/2017.
 */
public abstract class Arrow {

    protected Line arrowLine;
    protected Point2D pointRight;
    protected Point2D pointLeft;

    public Arrow(Point2D start, Point2D aankomst){
        arrowLine = new Line(); //hoofdlijn aanmaken + coordinaten instellen
        arrowLine.setStartX(start.getX());
        arrowLine.setStartY(start.getY());
        arrowLine.setEndX(aankomst.getX());
        arrowLine.setEndY(aankomst.getY());
        generateSidePoints(); //zijpunten voor de pijluiteinden genereren
    }

    public double berekenLengte(double x1, double y1, double x2, double y2){
        return Math.sqrt((x2 - x1)*(x2 - x1) + (y2 - y1)*(y2 - y1)); //berekent lengte van rechte
    }

    public void generateSidePoints(){
        double rico = (arrowLine.getEndY() - arrowLine.getStartY())/(arrowLine.getEndX() - arrowLine.getStartX());
        double x1 = -Math.sqrt(15*15/(rico*rico + 1)) + arrowLine.getEndX(); /*punt op rechte generen die op lengte
        15 van het eindpunt staat*/
        double y1 = -rico*(arrowLine.getEndX() - x1) + arrowLine.getEndY();
        double lengteArrowLine = berekenLengte(arrowLine.getStartX(), arrowLine.getStartY(), arrowLine.getEndX(), arrowLine.getEndY());
        if (berekenLengte(arrowLine.getStartX(), arrowLine.getStartY(), x1, y1) < lengteArrowLine){
            //controleren of punt buiten of op de lijn staat
            pointRight = rotatePoint(30, x1, y1, arrowLine.getEndX(), arrowLine.getEndY());
            pointLeft = rotatePoint(-30, x1, y1, arrowLine.getEndX(), arrowLine.getEndY());
        } else{
            pointRight = rotatePoint(150, x1, y1, arrowLine.getEndX(), arrowLine.getEndY());
            pointLeft = rotatePoint(-150, x1, y1, arrowLine.getEndX(), arrowLine.getEndY());
        }
        //punten roteren naar juiste plaats
    }

    public Point2D rotatePoint(double degrees, double x1, double y1, double x2, double y2){
        //roteert punt tov ander punt
        degrees = Math.toRadians(degrees);
        double xRot = x2 + Math.cos(degrees)*(x1-x2) - Math.sin(degrees)*(y1-y2);
        double yRot = y2 + Math.sin(degrees)*(x1 - x2) + Math.cos(degrees)*(y1 - y2);
        return new Point2D(xRot, yRot);
    }

    public void create(AnchorPane pane){
        pane.getChildren().add(arrowLine);
    }
}

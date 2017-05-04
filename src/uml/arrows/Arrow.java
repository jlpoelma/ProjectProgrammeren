package uml.arrows;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Point2D;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import uml.views.BoxView;
import uml.xmlElements.Box;
import uml.xmlElements.Relation;

/**
 * Created by Jonathan Poelman on 3/04/2017.
 */
public abstract class Arrow {

    protected Line arrowLine;
    protected Point2D pointRight;
    protected Point2D pointLeft;
    private Box startModel;
    private Box destinationModel;
    private BoxView startBox;
    private BoxView destinationBox;
    private Relation relation;
    protected ContextMenu contextMenu;

    public Arrow(Box startModel, Box destinationModel, Relation relation){
        contextMenu = new ContextMenu();
        MenuItem delete = new MenuItem("Delete Relation");
        contextMenu.getItems().add(delete);
        delete.setOnAction(event -> {
            remove((AnchorPane)arrowLine.getParent());
        });
        this.relation = relation;
        this.startModel = startModel;
        this.destinationModel = destinationModel;
        startBox = (BoxView)startModel.getListenerList().get(0);
        destinationBox = (BoxView)destinationModel.getListenerList().get(0);
        startBox.addArrow(this);
        destinationBox.addArrow(this);
        //startModel.addListener(this);
        //destinationModel.addListener(this);
        arrowLine = new Line(); //hoofdlijn aanmaken + coordinaten instellen
        setListeners();
        arrowLine.setOnContextMenuRequested(event ->
                contextMenu.show(arrowLine, event.getScreenX(), event.getScreenY()));
    }


    public Box getStartModel(){
        return startModel;
    }

    public Box getDestinationModel(){
        return destinationModel;
    }

    public void setLine(){
        Point2D middleStart = calculateMiddle(startBox); //midden van start en aankomst berekenen
        Point2D middleDestination = calculateMiddle(destinationBox);
        double angleStart = calculateAngle(middleStart, middleDestination); //starthoek van pijl berekenen
        double angleDestination = (angleStart + 180)%360; //aankomsthoek van pijl berekenen
        Point2D start = calculateIntersection(startBox, middleStart, middleDestination, angleStart); /*het snijpunt van de
                pijl met de start- en aankomstVBox berekenen*/
        Point2D aankomst = calculateIntersection(destinationBox, middleDestination, middleStart, angleDestination);
        arrowLine.setStartX(start.getX());
        arrowLine.setStartY(start.getY());
        arrowLine.setEndX(aankomst.getX());
        arrowLine.setEndY(aankomst.getY());
        generateSidePoints();
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

    public void remove(AnchorPane pane){
        startBox.removeArrow(this);
        destinationBox.removeArrow(this);
        startModel.removeRelation(relation);
        pane.getChildren().remove(arrowLine);
    }

    public Point2D calculateMiddle(VBox kader){ //midden van de VBox berekenen
        double x1 = kader.getLayoutX() + kader.getPrefWidth()/2;
        double y1 = kader.getLayoutY() + kader.getBoundsInParent().getHeight()/2;
        return new Point2D(x1, y1); //juiste coördinaat teruggeven
    }

    public double calculateAngle(Point2D start, Point2D end){
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
        if (hoek >= angle4 || hoek <= angle1){
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
    }

    public void modifyArrowHead(){

    }

    public void redraw(){
        startBox.applyCss();
        startBox.layout();
        destinationBox.applyCss();
        destinationBox.layout();
    }

    public void setListeners(){
        startBox.heightProperty().addListener((observable, oldValue, newValue) -> redraw());
        destinationBox.heightProperty().addListener((observable, oldValue, newValue) -> redraw());
        startBox.prefWidthProperty().addListener(observable -> redraw());
        destinationBox.prefWidthProperty().addListener(observable -> redraw());
        startBox.layoutXProperty().addListener(observable -> redraw());
        startBox.layoutYProperty().addListener(observable -> redraw());
        destinationBox.layoutXProperty().addListener(observable -> redraw());
        destinationBox.layoutYProperty().addListener(observable -> redraw());
    }

}

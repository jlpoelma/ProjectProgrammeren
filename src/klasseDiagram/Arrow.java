package klasseDiagram;

import com.sun.org.apache.xpath.internal.SourceTree;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.scene.transform.Rotate;

/**
 * Created by Jonathan on 3/22/2017.
 */
public class Arrow {

    private Line arrowLine;
    private Line arrowSide1;
    private Line arrowSide2;
    public Arrow(double x1, double y1, double x2, double y2){
        arrowLine = new Line();
        arrowLine.setStartX(x1);
        arrowLine.setStartY(y1);
        arrowLine.setEndX(x2);
        arrowLine.setEndY(y2);
        arrowSide1 = createArrowSide(15);
        arrowSide2 = createArrowSide(15);
        double lengteArrowLine = berekenLengte(arrowLine.getStartX(), arrowLine.getStartY(), arrowLine.getEndX(), arrowLine.getEndY());
        if (berekenLengte(arrowLine.getStartX(), arrowLine.getStartY(), arrowSide1.getStartX(), arrowSide1.getStartY()) < lengteArrowLine){
            arrowSide1.getTransforms().add(new Rotate(30, arrowSide1.getEndX(), arrowSide1.getEndY()));
            arrowSide2.getTransforms().add(new Rotate(-30, arrowSide2.getEndX(), arrowSide2.getEndY()));
        } else{
            arrowSide1.getTransforms().add(new Rotate(150, arrowSide1.getEndX(), arrowSide1.getEndY()));
            arrowSide2.getTransforms().add(new Rotate(-150, arrowSide2.getEndX(), arrowSide2.getEndY()));
        }
    }

    public double rico(){
        return (arrowLine.getEndY() - arrowLine.getStartY())/(arrowLine.getEndX() - arrowLine.getStartX());
    }

    public double berekenLengte(double x1, double y1, double x2, double y2){
        return Math.sqrt((x2 - x1)*(x2 - x1) + (y2 - y1)*(y2 - y1));
    }

    public Line createArrowSide(double length){
        Line arrowSide = new Line();
        arrowSide.setEndY(arrowLine.getEndY());
        arrowSide.setEndX(arrowLine.getEndX());
        double x1 = -Math.sqrt(length*length/(rico()*rico() + 1)) + arrowSide.getEndX();
        arrowSide.setStartX(x1);
        double y1 = -rico()*(arrowSide.getEndX() - x1) + arrowSide.getEndY();
        arrowSide.setStartY(y1);
        return arrowSide;
    }

    public void create(AnchorPane pane){
        pane.getChildren().add(arrowLine);
        System.out.println(pane.getChildren());
        pane.getChildren().add(arrowSide1);
        System.out.println(pane.getChildren());
        pane.getChildren().add(arrowSide2);
        System.out.println(pane.getChildren());
    }


}

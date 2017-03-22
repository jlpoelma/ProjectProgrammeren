package klasseDiagram;

import javafx.scene.shape.Line;

/**
 * Created by Jonathan on 3/22/2017.
 */
public abstract class Arrow {

    private int x1;
    private int x2;
    private int y1;
    private int y2;
    private Line line;
    private Line arrowSide1;
    private Line arrowSide2;

    public Arrow(int x1, int y1, int x2, int y2){
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        line = new Line();
        arrowSide1 = new Line();
        arrowSide2 = new Line();
        line.setStartX(x1);
        line.setStartY(y1);
        line.setEndX(x2);
        line.setEndY(y2);
    }


}

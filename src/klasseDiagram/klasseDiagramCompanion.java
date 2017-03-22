package klasseDiagram;

import javafx.application.Platform;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.transform.Rotate;
import javafx.stage.FileChooser;
import klasseDiagram.Generators.KaderGenerator;
import klasseDiagram.xmlElements.Attribute;
import klasseDiagram.xmlElements.Box;
import klasseDiagram.xmlElements.Diagram;
import klasseDiagram.xmlElements.Operation;

import javax.xml.bind.JAXBContext;
import java.io.File;
import java.util.List;

public class klasseDiagramCompanion {

    public AnchorPane paneel;

    public Line lineTest;
    public Line arrow1;
    public Line arrow2;

    public void initialize(){
        arrow1.setEndX(lineTest.getEndX());
        arrow2.setEndX(lineTest.getEndX());
        arrow1.setStartX(lineTest.getEndX() - 20);
        arrow2.setStartX(lineTest.getEndX() - 20);
        arrow1.getTransforms().add(new Rotate(-30, arrow1.getEndX(), arrow1.getEndY()));
        arrow2.getTransforms().add(new Rotate(30, arrow2.getEndX(), arrow2.getEndY()));
    }

    public Diagram xmlOmzetten(File file) throws Exception{
        JAXBContext jc = JAXBContext.newInstance(Diagram.class);
        Diagram diagram = (Diagram)jc.createUnmarshaller().unmarshal(
                file);
        return diagram;
    }

    public void exitProgram(){
        Platform.exit();
    }

    public void clearScreen(){
        paneel.getChildren().clear();
    }

    public void openFile() throws Exception{
        FileChooser chooser = new FileChooser();
        File file = chooser.showOpenDialog(null);
        Diagram diagram = xmlOmzetten(file);
        new KaderGenerator(paneel, diagram).generateKader();
    }
}

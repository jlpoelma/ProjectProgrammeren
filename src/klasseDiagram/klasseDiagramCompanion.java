package klasseDiagram;

import javafx.application.Platform;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
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
        paneel.applyCss();
        new Arrow(300, 300, 200, 200).create(paneel);
        StackPane tilePane = new StackPane();
        tilePane.setPrefWidth(200);
        tilePane.setLayoutX(50);
        tilePane.setLayoutY(50);
        paneel.getChildren().add(tilePane);
        VBox vboxTest = new VBox();
        vboxTest.getChildren().add(new Label("test"));
        tilePane.getChildren().add(vboxTest);
        System.out.println(tilePane.getHeight());
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
        clearScreen();
        FileChooser chooser = new FileChooser();
        File file = chooser.showOpenDialog(null);
        Diagram diagram = xmlOmzetten(file);
        new KaderGenerator(paneel, diagram).generateKader();
    }

    public void takeScreenshot(){

    }
}

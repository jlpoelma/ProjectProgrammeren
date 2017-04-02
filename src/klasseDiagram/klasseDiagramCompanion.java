package klasseDiagram;

import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.image.WritableImage;
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

import javax.imageio.ImageIO;
import javax.xml.bind.JAXBContext;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class klasseDiagramCompanion {

    public AnchorPane paneel;

    public Line lineTest;
    public Line arrow1;
    public Line arrow2;

    public void initialize(){
        new Arrow(300, 300, 200, 200).create(paneel);
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
        WritableImage image = paneel.snapshot(new SnapshotParameters(), null);
        File file = new File("afbeelding.png");

        try{
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
        }
        catch(IOException e){
            System.out.println(e);
        }

    }
}

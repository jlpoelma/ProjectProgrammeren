package klasseDiagram;

import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import klasseDiagram.Generators.KaderGenerator;
import klasseDiagram.Generators.RelationGenerator;
import klasseDiagram.xmlElements.Diagram;

import javax.imageio.ImageIO;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class KlasseDiagramCompanion {

    public AnchorPane mainPane;

    public void initialize(){
        if (KlasseDiagram.parameters.size() >= 1){ //als xml-bestand wordt gespecifieerd als argument, deze openen
            File file = new File(KlasseDiagram.parameters.get(0));
            drawDiagram(file);
        }
        if (KlasseDiagram.parameters.size() == 2){
            File file = new File(KlasseDiagram.parameters.get(1));
            Platform.runLater(() -> {takeScreenshot(file);});
            exitProgram();

        }
    }

    public Diagram convertXML(File file) { //methode die xml inlaadt in de bijbehorende klassen
        Diagram diagram = null;
        try {
            JAXBContext jc = JAXBContext.newInstance(Diagram.class);
            diagram = (Diagram) jc.createUnmarshaller().unmarshal(
                    file);
            return diagram;
        }
        catch(JAXBException e){
            System.err.println(e.getMessage());
        }
        return  diagram;
    }

    public void exitProgram(){
        Platform.exit();
    }

    public void clearScreen(){
        mainPane.getChildren().clear();
    }

    public void openFile() { //opent bestandsdialoog
        clearScreen();
        FileChooser chooser = new FileChooser();
        File file = chooser.showOpenDialog(null);
        drawDiagram(file);
    }

    public void drawDiagram(File file){
        Diagram diagram = convertXML(file);//xml omzetten
        HashMap<String, VBox> boxes = new KaderGenerator().generateBox(mainPane, diagram);//Boxes genereren
        Platform.runLater(() -> { //zorgt ervoor dat de hoogte achteraf correct kan opgevraagd worden
            new RelationGenerator().generateRelation(mainPane, diagram, boxes); //relaties (pijlen) genereren
        });

    }

    public void takeScreenshot(File file){ //methode die toelaat om een screenshot van de stage te nemen
        WritableImage image = mainPane.snapshot(new SnapshotParameters(), null); //afbeelding aanmaken

        try{
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
            //screenshot naar bestand schrijven en extensie specifiëren
        }
        catch(IOException e){
            System.err.println(e);
        }

    }

    public void saveImage(){
        FileChooser chooser = new FileChooser();
        File file = chooser.showSaveDialog(null);
        takeScreenshot(file);
    }
}

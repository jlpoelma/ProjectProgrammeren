package uml;

import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.ButtonType;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import uml.generators.BoxGenerator;
import uml.generators.RelationGenerator;
import uml.popupWindows.ClassDialog;
import uml.views.BoxView;
import uml.xmlElements.Box;
import uml.xmlElements.Diagram;

import javax.imageio.ImageIO;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Jonathan Poelman
 */

public class UmlCompanion {

    public AnchorPane mainPane;
    private Diagram diagram;
    public static HashMap<String, Box> classes;

    public void initialize(){
        if (Main.parameters.size() >= 1){ //als xml-bestand wordt gespecifieerd als argument, deze openen
            File file = new File(Main.parameters.get(0));
            drawDiagram(file);
        }
        if (Main.parameters.size() == 2){
            File file = new File(Main.parameters.get(1));
            Platform.runLater(() -> {takeScreenshot(file);});
            exitProgram();

        }
    }

    public void convertXML(File file) { //methode die xml inlaadt in de bijbehorende klassen
        try {
            JAXBContext jc = JAXBContext.newInstance(Diagram.class);
            diagram = (Diagram) jc.createUnmarshaller().unmarshal(
                    file);
        }
        catch(JAXBException e){
            System.err.println(e);
        }
    }

    public void exitProgram(){
        Platform.exit();
    }

    public void clearScreen(){
        mainPane.getChildren().clear();
        diagram = null;
        classes = null;
    }

    public void openFile() { //opent bestandsdialoog
        clearScreen();
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML-files", "*.xml"));
        File file = chooser.showOpenDialog(mainPane.getScene().getWindow());
        drawDiagram(file);
    }

    public void drawDiagram(File file){
        convertXML(file);
        classes = new BoxGenerator().generateBoxes(mainPane, diagram);//Boxes genereren
        System.out.println(classes);
        Platform.runLater(() -> { //zorgt ervoor dat de hoogte achteraf correct kan opgevraagd worden
            new RelationGenerator().generateRelation(mainPane, diagram, classes); //relaties (pijlen) genereren
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
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG-files", "*.png"));
        File file = chooser.showSaveDialog(mainPane.getScene().getWindow());
        takeScreenshot(file);
    }

    public void saveFileAs(){
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML-files", "*.xml"));
        File file = chooser.showSaveDialog(mainPane.getScene().getWindow());
        javaToXML(file);
    }

    public void javaToXML(File file){
        try {
            JAXBContext jc = JAXBContext.newInstance(Diagram.class);
            jc.createMarshaller().marshal(diagram, file);
        }
        catch(JAXBException e){
            System.err.println(e);
        }
    }

    public void newDiagram(){
        clearScreen();
        diagram = new Diagram();
        classes = new HashMap<>();
    }

    public void newClass(){

        //Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Delete  ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        ClassDialog input = new ClassDialog();
        input.showAndWait();

        if (input.getResult() == ButtonType.OK){
            Box box = new Box();
            diagram.addBox(box);
            BoxView newBox = new BoxView(diagram, box);
            mainPane.getChildren().add(newBox);
            box.setName(input.getName().getText());
            box.setCol(Double.parseDouble(input.getColumn().getText()));
            box.setRow(Double.parseDouble(input.getRow().getText()));
            box.setWidth(Double.parseDouble(input.getWidthValue().getText()));
            classes.put(box.getName(), box);
        }
    }
}

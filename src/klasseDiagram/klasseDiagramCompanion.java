package klasseDiagram;

import javafx.application.Platform;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import klasseDiagram.xmlElements.Attribute;
import klasseDiagram.xmlElements.Box;
import klasseDiagram.xmlElements.Diagram;
import klasseDiagram.xmlElements.Operation;

import javax.xml.bind.JAXBContext;
import java.io.File;
import java.util.List;

public class klasseDiagramCompanion {

    public AnchorPane paneel;

    public void initialize() throws Exception{

    }

    public Diagram xmlOmzetten(File file) throws Exception{
        JAXBContext jc = JAXBContext.newInstance(Diagram.class);
        Diagram diagram = (Diagram)jc.createUnmarshaller().unmarshal(
                file);
        return diagram;
    }

    public void headerGenerator(String header, VBox kader){
        Label label = new Label(header);
        label.setId("header");
        kader.getChildren().add(label);
        kader.getChildren().add(new Separator());
    }

    public void relationGenerator(){

    }

    public void attributeGenerator(List<Attribute> attributes, VBox kader){
        for (Attribute a: attributes
             ) {
            Label label = new Label("-" + a.getName() + " : " + a.getType());
            kader.getChildren().add(label);
        }
        kader.getChildren().add(new Separator());
    }

    public void operationGenerator(List<Operation> operations, VBox kader){
        for (Operation o: operations){
                Label label = new Label("+" + o.getName() + " : " + o.getType());
                kader.getChildren().add(label);
        }
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
        kaderGenerator(diagram);
    }

    public void kaderGenerator(Diagram diagram){
        for (Box b: diagram.getBoxList()) {
            VBox kader = new VBox();
            paneel.getChildren().add(kader);
            kader.setLayoutX(b.getCol());
            kader.setLayoutY(b.getRow());
            kader.setPrefWidth(b.getWidth());
            headerGenerator(b.getName(), kader);
            attributeGenerator(b.getAttributeList(), kader);
            operationGenerator(b.getOperationList(), kader);
        }
    }
}

package uml.popupWindows;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


/**
 * Created by Jonathan on 5/2/2017.
 */
public class OperationAttributeHBox extends HBox {

    private TextField name;

    private TextField type;

    private Button remove;

    public OperationAttributeHBox(){
        name = new TextField();
        type = new TextField();
        remove = new Button("remove");
        getChildren().add(new Label("Name: "));
        getChildren().add(name);
        getChildren().add(new Label("Type: "));
        getChildren().add(type);
        getChildren().add(remove);
        setSpacing(10);
    }

    public TextField getName(){
        return name;
    }

    public TextField getType(){
        return type;
    }

    public Button getRemoveButton(){
        return remove;
    }

}

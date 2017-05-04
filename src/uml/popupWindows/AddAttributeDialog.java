package uml.popupWindows;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Created by Jonathan on 4/26/2017.
 */
public class AddAttributeDialog extends Dialog {

    public ChoiceBox getScope() {
        return scope;
    }

    public ChoiceBox getVisibility() {
        return visibility;
    }

    public TextField getName() {
        return name;
    }

    public TextField getType() {
        return type;
    }

    private ChoiceBox scope;
    private ChoiceBox visibility;
    private TextField name;
    private TextField type;
    protected VBox inputs;

    public AddAttributeDialog(){
        getDialogPane().getStylesheets().add(getClass().getResource("dialog.css").toExternalForm());
        getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        inputs = new VBox(10);
        inputs.setPadding(new Insets(10, 150, 10, 10));
        scope = new ChoiceBox();
        visibility = new ChoiceBox();
        scope.getItems().addAll("instance", "classifier");
        visibility.getItems().addAll("public", "private", "protected", "package");
        name = new TextField();
        type = new TextField();
        HBox main = new HBox(10);
        main.getChildren().add(new Label("Scope: "));
        main.getChildren().add(scope);
        main.getChildren().add(new Label("Visibility: "));
        main.getChildren().add(visibility);
        main.getChildren().add(new Label("Name: "));
        main.getChildren().add(name);
        main.getChildren().add(new Label("Type: "));
        main.getChildren().add(type);
        inputs.getChildren().add(main);
        getDialogPane().setContent(inputs);
    }
}

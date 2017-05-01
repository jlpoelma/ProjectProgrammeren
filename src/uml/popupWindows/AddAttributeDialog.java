package uml.popupWindows;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

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

    public AddAttributeDialog(){
        getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        GridPane inputs = new GridPane();
        inputs.setHgap(10);
        inputs.setVgap(10);
        inputs.setPadding(new Insets(10, 150, 10, 10));
        scope = new ChoiceBox();
        visibility = new ChoiceBox();
        scope.getItems().addAll("instance", "classifier");
        visibility.getItems().addAll("public", "private", "protected", "package");
        name = new TextField();
        type = new TextField();
        inputs.add(new Label("Scope: "), 0, 0);
        inputs.add(scope, 1, 0);
        inputs.add(new Label("Visibility: "), 2, 0);
        inputs.add(visibility, 3, 0);
        inputs.add(new Label("Name: "), 4, 0);
        inputs.add(name, 5, 0);
        inputs.add(new Label("Type: "), 6, 0);
        inputs.add(type, 7, 0);
        getDialogPane().setContent(inputs);
    }
}

package uml.popupWindows;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import uml.UmlCompanion;

/**
 * Created by jonathan on 22/04/2017.
 */
public class RelationDialog extends Dialog {

    private ChoiceBox with;

    private ChoiceBox type;

    public ChoiceBox getType() {
        return type;
    }

    public ChoiceBox getWith() {
        return with;
    }

    public RelationDialog(String from){
        setTitle("Create relation...");
        getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        GridPane inputs = new GridPane();
        inputs.setHgap(10);
        inputs.setVgap(10);
        inputs.setPadding(new Insets(10, 150, 10, 10));
        with = new ChoiceBox();
        type = new ChoiceBox();
        type.getItems().addAll("association", "inheritance", "realization", "aggregation", "composition", "dependency");
        with.getItems().addAll(UmlCompanion.classes.keySet());
        with.getItems().remove(from);
        inputs.add(new Label("Type"), 0, 0);
        inputs.add(type, 1, 0);
        inputs.add(new Label("With"), 2, 0);
        inputs.add(with, 3, 0);
        getDialogPane().setContent(inputs);
    }
}

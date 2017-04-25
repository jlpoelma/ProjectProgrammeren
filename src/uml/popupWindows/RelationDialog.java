package uml.popupWindows;

import javafx.geometry.Insets;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 * Created by jonathan on 22/04/2017.
 */
public class RelationDialog extends Dialog {

    private TextField with;


    public TextField getWith() {
        return with;
    }

    public void setWith(TextField with) {
        this.with = with;
    }

    public RelationDialog(){
        setTitle("Create relation...");
        getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        GridPane inputs = new GridPane();
        inputs.setHgap(10);
        inputs.setVgap(10);
        inputs.setPadding(new Insets(10, 150, 10, 10));
        with = new TextField();
    }
}

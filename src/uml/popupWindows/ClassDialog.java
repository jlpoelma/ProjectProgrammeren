package uml.popupWindows;

import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import uml.UmlCompanion;

/**
 * Created by jonathan on 22/04/2017.
 */
public class ClassDialog extends Dialog {

    private TextField name;

    public TextField getName() {
        return name;
    }

    public void setName(TextField name) {
        this.name = name;
    }
    public TextField getWidthValue() {
        return width;
    }

    public void setWidth(TextField width) {
        this.width = width;
    }

    public TextField getColumn() {
        return column;
    }

    public void setColumn(TextField column) {
        this.column = column;
    }

    public TextField getRow() {
        return row;
    }

    public void setRow(TextField row) {
        this.row = row;
    }

    private  TextField width;
    private TextField column;
    private TextField row;
    private GridPane inputs;

    public ClassDialog(){
        getDialogPane().getStylesheets().add(getClass().getResource("dialog.css").toExternalForm());
        setTitle("Create class...");
        getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        inputs = new GridPane();
        inputs.setHgap(10);
        inputs.setVgap(10);
        inputs.setPadding(new Insets(10, 150, 10, 10));
        width = new TextField();
        column = new TextField();
        row = new TextField();
        setUpName();
        inputs.add(new Label("width"), 0, 1);
        inputs.add(width, 1, 1);
        inputs.add(new Label("column"), 0, 2);
        inputs.add(column, 1, 2);
        inputs.add(new Label("row"), 0, 3);
        inputs.add(row, 1, 3);
        getDialogPane().lookupButton(ButtonType.OK).disableProperty().bind(
                Bindings.isEmpty(name.textProperty())
                .or(Bindings.isEmpty(width.textProperty()))
                .or(Bindings.isEmpty(column.textProperty()))
                .or(Bindings.isEmpty(row.textProperty()))
        );
        getDialogPane().setContent(inputs);
    }

    public void validateName(String value, Label errorNaam){
        if (UmlCompanion.classes.containsKey(value)){
            name.getStyleClass().add("classNameError");
            errorNaam.setVisible(true);
            getDialogPane().lookupButton(ButtonType.OK).setDisable(true);
        } else{
            name.getStyleClass().remove("classNameError");
            errorNaam.setVisible(false);
            //getDialogPane().lookupButton(ButtonType.OK).setDisable(false);
        }
    }

    public void setUpName(){
        name = new TextField();
        Label errorNaam = new Label("Klasse met dezelfde naam bestaat al.");
        errorNaam.setVisible(false);
        inputs.add(new Label("Name"), 0, 0);
        inputs.add(name, 1, 0);
        inputs.add(errorNaam, 2, 0);
        name.textProperty().addListener(((observable, oldValue, newValue) -> {
            validateName(newValue, errorNaam);
        }));
    }


}

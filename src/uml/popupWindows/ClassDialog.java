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
        name = new TextField();
        inputs.add(new Label("Name"), 0, 0);
        inputs.add(name, 1, 0);
        inputs.add(new Label("width"), 3, 0);
        inputs.add(width, 4, 0);
        inputs.add(new Label("column"), 5, 0);
        inputs.add(column, 6, 0);
        inputs.add(new Label("row"), 7, 0);
        inputs.add(row, 8, 0);
        getDialogPane().setContent(inputs);
        checkErrors();
        setUpName();
    }

    public void validateName(){
        if (UmlCompanion.classes.containsKey(name.getText())){
            name.getStyleClass().add("classNameError");
        } else{
            name.getStyleClass().remove("classNameError");
        }
    }

    public void validateNumberField(TextField field){
        if (checkNumber(field.getText())){
            field.getStyleClass().remove("classNameError");
        } else{
            field.getStyleClass().add("classNameError");
        }
    }

    public void setUpName(){
        name.textProperty().addListener(((observable, oldValue, newValue) -> {
            checkErrors();
            validateName();
        }));
        column.textProperty().addListener(((observable, oldValue, newValue) -> {
            checkErrors();
            validateNumberField(column);
        }));
        row.textProperty().addListener(((observable, oldValue, newValue) -> {
            checkErrors();
            validateNumberField(row);
        }));
        width.textProperty().addListener(((observable, oldValue, newValue) -> {
            checkErrors();
            validateNumberField(width);
        }));
    }

    public void checkErrors(){
        if(!UmlCompanion.classes.containsKey(name.getText()) && !name.getText().isEmpty() && !column.getText().isEmpty() && !row.getText().isEmpty()
                && !width.getText().isEmpty() && checkNumber(column.getText()) && checkNumber(row.getText()) && checkNumber(width.getText())){
            getDialogPane().lookupButton(ButtonType.OK).setDisable(false);
        } else{
            getDialogPane().lookupButton(ButtonType.OK).setDisable(true);
        }
    }

    public boolean checkNumber(String s){
        try{
            Integer.parseInt(s);
        } catch(NumberFormatException | NullPointerException e){
            return false;
        }
        return true;
    }


}

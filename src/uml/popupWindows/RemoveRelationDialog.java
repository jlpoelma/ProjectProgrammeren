package uml.popupWindows;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.CheckBoxListCell;
import uml.xmlElements.Relation;

import java.util.ArrayList;

/**
 * Created by Jonathan on 4/29/2017.
 */
public class RemoveRelationDialog extends Dialog {

    private ListView relations;
    public RemoveRelationDialog(ArrayList<Relation> relationList){
        getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        relations = new ListView();
        relations.getItems().setAll(relationList);
        getDialogPane().setContent(relations);
    }
}

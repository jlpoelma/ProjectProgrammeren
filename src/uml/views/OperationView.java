package uml.views;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.HBox;
import uml.popupWindows.OperationDialog;
import uml.xmlElements.Attribute;
import uml.xmlElements.Box;
import uml.xmlElements.Operation;

/**
 * Created by jonathan on 1/05/2017.
 */
public class OperationView extends AttributeView {

    public OperationView(Operation operation, Box model){

        super(operation, model);
        this .invalidated(null);
    }
    @Override
    public void invalidated(Observable observable) {
        String attributes = "(";
        Operation operation = (Operation)attribute;
        for (Attribute a: operation.getAttributeList()) {
            attributes += a.getName() + " : " + a.getType() + ", ";
        }
        attributes = attributes.replaceAll(", $", "");
        attributes += ")";
        text.setText(visibilities.get(attribute.getVisibility()) + attribute.getName()
            + attributes + " : " + attribute.getType());

    }

    public void edit(){
        OperationDialog dialog = new OperationDialog();
        dialog.getName().setText(attribute.getName());
        dialog.getType().setText(attribute.getType());
        dialog.getScope().setValue(attribute.getScope());
        dialog.getVisibility().setValue(attribute.getVisibility());
        for (Attribute a: ((Operation) attribute).getAttributeList()) {
            dialog.addAttribute(a.getName(), a.getType());

        }
        dialog.showAndWait();
        if (dialog.getResult() == ButtonType.OK){
            attribute.setName(dialog.getName().getText());
            attribute.setScope(dialog.getScope().getValue().toString());
            attribute.setType(dialog.getType().getText());
            attribute.setVisibility(dialog.getVisibility().getValue().toString());
            ((Operation) attribute).setAttributeList(dialog.getAttributes());
        }
    }
}

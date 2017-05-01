package uml.views;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.layout.HBox;
import uml.xmlElements.Attribute;
import uml.xmlElements.Box;
import uml.xmlElements.Operation;

/**
 * Created by jonathan on 1/05/2017.
 */
public class OperationView extends AttributeView{

    public OperationView(Operation operation, Box model){
        super(operation, model);
    }
    @Override
    public void invalidated(Observable observable) {
        String attributes = "(";
        Operation operation = (Operation)attribute;
        for (Attribute a: operation.getAttributeList()) {
            attributes += a.getName() + " : " + a.getType() + ", ";
        }
        attributes = attributes.substring(0, attributes.length() - 2) + ")";
        text.setText(visibilities.get(attribute.getVisibility()) + attribute.getName()
            + attributes + " : " + attribute.getType());
    }
}

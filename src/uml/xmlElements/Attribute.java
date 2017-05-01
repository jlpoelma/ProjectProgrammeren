package uml.xmlElements;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

import javax.xml.bind.annotation.XmlAttribute;
import java.util.ArrayList;

/**
 * Created by Jonathan Poelman on 19/03/2017.
 */
public class Attribute implements Observable{

    private String scope;

    private String visibility;

    private String name;

    private String type;

    private ArrayList<InvalidationListener> listeners = new ArrayList<>();

    public String getScope() {
        return scope;
    }
    @XmlAttribute(name = "scope")
    public void setScope(String scope) {
        if(scope != this.scope) {
            this.scope = scope;
            fireInvalidationEvent();
        }
    }
    public String getVisibility() {
        return visibility;
    }
    @XmlAttribute(name = "visibility")
    public void setVisibility(String visibility) {
        if(visibility != this.visibility) {
            this.visibility = visibility;
            fireInvalidationEvent();
        }
    }

    public String getName() {
        return name;
    }
    @XmlAttribute(name = "name")
    public void setName(String name) {
        if(name != this.name) {
            this.name = name;
            fireInvalidationEvent();
        }
    }

    public String getType() {
        return type;
    }
    @XmlAttribute(name = "type")
    public void setType(String type) {
        if(type != this.type) {
            this.type = type;
            fireInvalidationEvent();
        }
    }

    @Override
    public void addListener(InvalidationListener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(InvalidationListener listener) {
        listeners.remove(listener);
    }

    public void fireInvalidationEvent(){
        for(InvalidationListener i: listeners){
                i .invalidated(this);
        }
    }
}

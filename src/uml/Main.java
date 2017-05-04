package uml;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;

/**
 * Created by Jonathan Poelman
 */

public class Main extends Application {

    public static List<String> parameters;

    private static Stage primaryStage;

    public void init(){
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        parameters = getParameters().getRaw();
        Parent root = FXMLLoader.load(getClass().getResource("uml.fxml"));
        primaryStage.setTitle("UML-generator");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    public static void setTitle(String s){
        if(s.isEmpty()){
            primaryStage.setTitle("UML-generator");
        }
        else {
            primaryStage.setTitle("UML-generator - " + s);
        }
    }
}

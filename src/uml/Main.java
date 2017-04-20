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

    public void init(){
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        parameters = getParameters().getRaw();
        Parent root = FXMLLoader.load(getClass().getResource("uml.fxml"));
        primaryStage.setTitle("Main");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
package sample.Model;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This is the main class that initiates the program.
 */

public class Main extends Application {

    /**
     * This method launches the application
     * @param args The args that are passed to launch the application.
     */

    public static void main(String[] args) throws Exception {
        launch(args);
        }


    /**
     * This initiates the stage being loaded.
     * @param stage This is the stage of the application.
     * @throws IOException This will throw IOExceptions.
     */

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/sample/View/LogIn.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}


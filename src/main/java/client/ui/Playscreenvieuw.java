package client.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Playscreenvieuw extends Application {

    FXMLLoader loader = new FXMLLoader();

    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/views/layout_playscreen.fxml"));
        Scene scene = new Scene(root, 996, 555);
        primaryStage.setTitle("Main Menu");
        primaryStage.setScene(scene);
        //      primaryStage.setFullScreen(true);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

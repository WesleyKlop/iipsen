package client.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class mainplayvieuw extends Application {

    FXMLLoader loader = new FXMLLoader();

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/views/layout_Mainplayscreen.fxml"));
        Scene scene = new Scene(root, 1280, 720);
        primaryStage.setTitle("Main Menu");
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.show();
    }
}
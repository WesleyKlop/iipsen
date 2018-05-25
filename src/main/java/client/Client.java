package client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Client extends Application {

    private GameClient client;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/views/layout_main_menu.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Main Menu");
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.show();
    }
}

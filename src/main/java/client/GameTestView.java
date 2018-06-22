package client;

import game.GameStore;
import game.GameStoreProvider;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GameTestView extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        GameStoreProvider.getInstance().setValue(new GameStore("server ip"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/layout_end_screen.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 1920, 1080);
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.setTitle("GameScreen");
        primaryStage.show();
    }
}

package client;

import game.GameStore;
import game.GameStoreProvider;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GameTestView extends Application {

    private FXMLLoader loader;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        GameStoreProvider.getInstance().setValue(new GameStore());
        loader = new FXMLLoader(getClass().getResource("/views/overlay_pause_menu.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 1920, 1080);
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.setTitle("GameScreen");
        primaryStage.show();

        GameStore store = new GameStore();

    }
}

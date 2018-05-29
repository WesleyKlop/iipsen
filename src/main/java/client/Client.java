package client;

import game.GameState;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import server.Server;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;

public class Client extends Application implements SceneListener {
    private static final Logger Log = LogManager.getLogger(Client.class);

    private static final String DEFAULT_IP = "127.0.0.1";

    private GameClient client;
    private Stage stage;
    private Scene scene;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/views/layout_main_menu.fxml"));
        stage = primaryStage;
        var screenInfo = Screen.getPrimary().getVisualBounds();
        scene = new Scene(root, screenInfo.getWidth(), screenInfo.getHeight());
        primaryStage.setTitle("Main Menu");
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.show();

        connectServer(null);
//        startServer();
    }

    private void startServer() throws MalformedURLException, RemoteException {
        client = new GameClient(new Server(), this);
    }

    private void connectServer(String ip) throws RemoteException {
        if (ip == null)
            ip = DEFAULT_IP;

        client = new GameClient(ip, this);
    }

    private Parent getParent(String layoutName) {
        try {
            return FXMLLoader.load(getClass().getResource("/views/" + layoutName + ".fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void onSceneChange(GameState state) {
        Log.debug("Got new scene {}", state);

        Parent newRoot = null;
        String newTitle = null;
        switch (state) {
            case INIT:
                newTitle = "Ticket To Ride - Connect";
                newRoot = getParent("layout_preferences");
                break;
            case LOBBY:
                newTitle = "Ticket To Ride - Lobby";
                newRoot = getParent("layout_lobby");
                break;
            case GAME:
                newTitle = "Ticket To Ride";
                newRoot = getParent("layout_game");
                // Switch to game view
                break;
            case PAUSED:
                newRoot = getParent("layout_pause");
                // Switch to pause screen
                break;
            case FINISHED:
                newRoot = getParent("layout_finished");
                // Switch to end screen
                break;
        }
        setStage(newRoot, newTitle);
    }

    private void setStage(final Parent newRoot, final String newTitle) {
        Platform.runLater(() -> {
            scene.setRoot(newRoot);
            if (newTitle != null) {
                stage.setTitle(newTitle);
            }
        });
    }
}

package client;

import game.GameState;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import server.Server;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;

public class Client extends Application implements SceneListener {

    private GameClient client;
    private Stage stage;
    private Scene scene;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/views/layout_main_menu.fxml"));
        stage = primaryStage;
        scene = new Scene(root);
        primaryStage.setTitle("Main Menu");
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.show();

        startServer();
    }

    private void startServer() throws MalformedURLException, RemoteException {
        client = new GameClient(new Server(), this);
    }

    private void connectServer(String ip) throws RemoteException {
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
        System.out.println("Got new scene");

        Parent newRoot = null;
        switch (state) {
            case INIT:
                stage.setTitle("Ticket To Ride - Connect");
                newRoot = getParent("layout_preferences");
                // Switch to name/color select
                break;
            case LOBBY:
                stage.setTitle("Ticket To Ride - Lobby");
                newRoot = getParent("layout_lobby");
                // Switch to lobby
                break;
            case GAME:
                stage.setTitle("Ticket To Ride");
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

        scene.setRoot(newRoot);
    }
}

package client;

import client.ui.MainMenuControllers.StartupController;
import game.GameState;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import server.Server;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.RemoteException;

public class Client extends Application implements SceneListener {
    private static final Logger Log = LogManager.getLogger(Client.class);

    private static final String DEFAULT_IP = "127.0.0.1";

    private GameClient client;
    private Stage stage;
    private Scene scene;
    private StartupController rootPaneController;
    // Try to fix scene diffing
    private GameState currentState;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/layout_startup.fxml"));
        Pane rootPane = loader.load();
        rootPaneController = loader.getController();
        rootPaneController.getPreferenceController().joinButton.setOnMouseClicked(e -> {
            try {
                if (rootPaneController.getPreferenceController().checkName()) {
//                    if (rootPaneController.getPreferenceController().checkNameDouble()) {
                    connectServer(rootPaneController.getPreferenceController().ipInput.getText());
                    rootPaneController.getPreferenceController().submitPreferences();
//                    }
                }
            } catch (RemoteException e1) {
                Log.error("FUCK", e1);
            }
        });

        rootPaneController.getPreferenceController().createButton.setOnMouseClicked(e -> {
            try {
                if (rootPaneController.getPreferenceController().checkName()) {
                    startServer();
                    rootPaneController.getPreferenceController().submitPreferences();
                }
            } catch (MalformedURLException | RemoteException | UnknownHostException e1) {
                Log.error(e1);
            }
        });


        stage = primaryStage;
        var screenInfo = Screen.getPrimary().getVisualBounds();
        scene = new Scene(rootPane, screenInfo.getWidth(), screenInfo.getHeight());
        primaryStage.setTitle("Main Menu");
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        primaryStage.show();
    }

    private void startServer() throws MalformedURLException, RemoteException, UnknownHostException {
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
    public void updateSceneState(GameState state) {
        if (this.currentState == state) {
//            Log.warn("I think we're going to set the new scene twice.. {} to {}", this.currentState, state);
            return;
        }
        Log.info("!!! CHANGING SCENE TO {} !!!", state);
        currentState = state;

        Parent newRoot = null;
        String newTitle = null;
        switch (state) {
            case LOBBY:
                rootPaneController.moveMenuDown();
                break;
            case GAME:
                newTitle = "Ticket To Ride";
                newRoot = getParent("layout_full_game");
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
        if (newRoot != null && newTitle != null) {
            setStage(newRoot, newTitle);
        }
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

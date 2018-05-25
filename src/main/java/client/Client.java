package client;

import game.GameStoreProvider;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import server.Server;

import java.net.MalformedURLException;
import java.rmi.RemoteException;

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

        GameStoreProvider.getInstance().subscribe(e -> {
            System.out.println("Got new state");
            System.out.println(e);
        });

        startServer();

    }

    private void startServer() throws MalformedURLException, RemoteException {
        client = new GameClient(new Server());
    }

    private void connectServer(String ip) throws RemoteException {
        client = new GameClient(ip);
    }
}

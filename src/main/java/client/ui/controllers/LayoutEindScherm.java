package client.ui.controllers;

import game.GameStore;
import game.GameStoreProvider;
import game.player.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import util.Observable;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LayoutEindScherm {
    @FXML
    VBox content;
    @FXML
    VBox prizes;

    ArrayList<Label> labelList = new ArrayList<>();
    ArrayList<Image> imageArrayList = new ArrayList<>();
    ArrayList<ImageView> imageViewArrayList = new ArrayList<>();
    List<Player> Players = new ArrayList<>();
    private Observable<GameStore> storeObservable = GameStoreProvider.getInstance();

    public void initialize() throws RemoteException {
        GameStore store = storeObservable.getValue();
        Player player = new Player("Jan", Color.RED);
        Player player2 = new Player("Willem", Color.RED);
        Player player3 = new Player("Peter", Color.RED);
        store.getPlayers().add(player);
        store.getPlayers().add(player2);
        store.getPlayers().add(player3);
        store.getPlayers().get(0).setScore(10);
        store.getPlayers().get(1).setScore(9);
        store.getPlayers().get(2).setScore(900);

        Players = store.getPlayers();
        swap();

        for (int i = 0; i < store.getPlayers().size(); i++) {
            labelList.add(new Label("Naam: " + Players.get(i).getPlayerName() + "                Score: " + Players.get(i).getScore()));
            labelList.get(i).setStyle("-fx-background-color: lightblue; -fx-font-size: 20;-fx-font-weight: Bold;-fx-border-color: black; -fx-border-color: black; -fx-background-radius: 20; -fx-border-radius: 20; -fx-padding: 20");
            String url = "/Prizes/Number_" + (i + 1) + ".png";
            imageArrayList.add(new Image(url));
            imageViewArrayList.add(new ImageView());
            imageViewArrayList.get(i).setImage(imageArrayList.get(i));
            prizes.getChildren().addAll(imageViewArrayList.get(i));
        }

        for (int i = 0; i < store.getPlayers().size(); i++) {
            content.getChildren().addAll(labelList.get(i));
        }
    }

    public void swap() {
        if (Players.get(0).getScore() < Players.get(1).getScore()) {
            Collections.swap(Players, 0, 1);
        }
        if (Players.get(1).getScore() < Players.get(2).getScore()) {
            Collections.swap(Players, 1, 2);
        }
        if (Players.get(0).getScore() < Players.get(1).getScore()) {
            Collections.swap(Players, 0, 1);
        }
    }
}
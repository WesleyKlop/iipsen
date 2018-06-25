package client.ui.controllers;

import game.GameStore;
import game.GameStoreProvider;
import game.player.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import util.Observer;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LayoutEndScreen implements Observer<GameStore> {
    @FXML
    VBox content;
    @FXML
    VBox prizes;

    public void initialize() {
        GameStoreProvider.getInstance().addObserver(this);
        onUpdate(GameStoreProvider.getStore());

    }

    @Override
    public void onUpdate(GameStore store) {
        List<Player> players = store.getPlayerController().getPlayers();
        players.sort(Comparator.comparingInt(Player::getScore));
        Collections.reverse(players);

        for (int i = 0; i < players.size(); i++) {
            var player = players.get(i);
            Label label = new Label("Naam: " + player.getPlayerName() + "                Score: " + player.getScore());
            label.setStyle("-fx-background-color: lightblue; -fx-font-size: 20;-fx-font-weight: Bold;-fx-border-color: black; -fx-background-radius: 20; -fx-border-radius: 20; -fx-padding: 20");
            content.getChildren().add(label);
            prizes.getChildren().add(new ImageView(new Image("/Prizes/Number_" + (i + 1) + ".png")));

        }
    }
}

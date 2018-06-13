package client.ui.controllers;


import game.GameStoreProvider;
import game.actions.RandomCardAction;
import game.player.Player;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;


public class LayoutCardController implements Initializable {
    Player player;
    @FXML
    HBox HBox;
    @FXML
    ImageView black;
    @FXML
    ImageView blue;
    @FXML
    ImageView green;
    @FXML
    ImageView orange;
    @FXML
    ImageView purple;
    @FXML
    ImageView red;
    @FXML
    ImageView white;
    @FXML
    ImageView yellow;
    @FXML
    ImageView locomotive;

    public void initialize(URL url, ResourceBundle bundle) {
        black.setVisible(false);
        blue.setVisible(false);
        green.setVisible(false);
        orange.setVisible(false);
        purple.setVisible(false);
        red.setVisible(false);
        white.setVisible(false);
        yellow.setVisible(false);
        locomotive.setVisible(false);
        Player player = new Player("jan", Color.RED);
        var action = new RandomCardAction(player);
        try {
            GameStoreProvider.sendAction(action);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        System.out.println(player.getCardStack());
    }
}

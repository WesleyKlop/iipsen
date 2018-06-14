package client.ui.controllers;


import game.GameStore;
import game.GameStoreProvider;
import game.actions.RandomCardAction;
import game.player.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import util.Observable;

import java.rmi.RemoteException;
import java.util.List;

import static game.cards.CardType.*;

public class LayoutCardController {
    @FXML
    Label blackammount;
    @FXML
    Label blueammount;
    @FXML
    Label greenammount;
    @FXML
    Label orangeammount;
    @FXML
    Label purpleammount;
    @FXML
    Label redammount;
    @FXML
    Label whiteammount;
    @FXML
    Label yellowammount;
    @FXML
    Label locomotiveammount;

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

    private Observable<GameStore> storeObervable = GameStoreProvider.getInstance();
    private GameStore store = GameStoreProvider.getStore();

    /**
     * Get the player from the gamestore
     *
     * @author Ewout
     */

    public void initialize() {
        CardsOff();
        List<Player> players = store.getPlayers();
        Player player = players.get(0);

        //todo Only gets the first player connected

        /**
         *
         * Player gets 4 cards in the beginning of the game.
         *
         * @author Ewout
         *
         */

        for (int i = 0; i < 4; i++) {
            var action = new RandomCardAction(player);
            try {
                GameStoreProvider.sendAction(action);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        /**
         * When you got a certain card in your hand it becomes visable on screen.
         * The ammount is how much you got.
         *
         * @author Ewout
         */
        if (player.getCardStack().get(CART_BLACK) != null && player.getCardStack().get(CART_BLACK) > 0) {
            blackammount.setText(Integer.toString(player.getCardStack().get(CART_BLACK)));
            black.setVisible(true);
            blackammount.setVisible(true);
        }

        if (player.getCardStack().get(CART_BLUE) != null && player.getCardStack().get(CART_BLUE) > 0) {
            blueammount.setText(Integer.toString(player.getCardStack().get(CART_BLUE)));
            blue.setVisible(true);
            blueammount.setVisible(true);
        }

        if (player.getCardStack().get(CART_GREEN) != null && player.getCardStack().get(CART_GREEN) > 0) {
            greenammount.setText(Integer.toString(player.getCardStack().get(CART_GREEN)));
            green.setVisible(true);
            greenammount.setVisible(true);
        }

        if (player.getCardStack().get(CART_ORANGE) != null && player.getCardStack().get(CART_ORANGE) > 0) {
            orangeammount.setText(Integer.toString(player.getCardStack().get(CART_ORANGE)));
            orange.setVisible(true);
            orangeammount.setVisible(true);
        }

        if (player.getCardStack().get(CART_PURPLE) != null && player.getCardStack().get(CART_PURPLE) > 0) {
            purpleammount.setText(Integer.toString(player.getCardStack().get(CART_PURPLE)));
            purple.setVisible(true);
            purpleammount.setVisible(true);
        }

        if (player.getCardStack().get(CART_RED) != null && player.getCardStack().get(CART_RED) > 0) {
            redammount.setText(Integer.toString(player.getCardStack().get(CART_RED)));
            red.setVisible(true);
            redammount.setVisible(true);
        }

        if (player.getCardStack().get(CART_WHITE) != null && player.getCardStack().get(CART_WHITE) > 0) {
            whiteammount.setText(Integer.toString(player.getCardStack().get(CART_WHITE)));
            white.setVisible(true);
            whiteammount.setVisible(true);
        }

        if (player.getCardStack().get(CART_YELLOW) != null && player.getCardStack().get(CART_YELLOW) > 0) {
            yellowammount.setText(Integer.toString(player.getCardStack().get(CART_YELLOW)));
            yellow.setVisible(true);
            yellowammount.setVisible(true);
        }


        if (player.getCardStack().get(LOCOMOTIVE) != null && player.getCardStack().get(LOCOMOTIVE) > 0) {
            locomotiveammount.setText(Integer.toString(player.getCardStack().get(LOCOMOTIVE)));
            locomotive.setVisible(true);
            locomotiveammount.setVisible(true);
        }


    }

    /**
     * Here you put all the visable cards off, because at the start your hand is empty
     *
     * @author Ewout
     */
    private void CardsOff() {
        black.setVisible(false);
        blue.setVisible(false);
        green.setVisible(false);
        orange.setVisible(false);
        purple.setVisible(false);
        red.setVisible(false);
        white.setVisible(false);
        yellow.setVisible(false);
        locomotive.setVisible(false);
        blackammount.setVisible(false);
        blueammount.setVisible(false);
        greenammount.setVisible(false);
        orangeammount.setVisible(false);
        purpleammount.setVisible(false);
        redammount.setVisible(false);
        whiteammount.setVisible(false);
        yellowammount.setVisible(false);
        locomotiveammount.setVisible(false);
    }

    public void updateScreenCards(Player player) {
    }
}

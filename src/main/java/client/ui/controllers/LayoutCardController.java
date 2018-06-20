package client.ui.controllers;


import game.GameStore;
import game.GameStoreProvider;
import game.actions.GetCardAction;
import game.player.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import util.Observable;

import java.rmi.RemoteException;
import java.util.List;

import static client.UserPreferences.isColorBlind;
import static game.cards.CardType.*;

public class LayoutCardController {
    @FXML
    Label blackammount, blueammount, greenammount, orangeammount, purpleammount, redammount, whiteammount, yellowammount, locomotiveammount;

    @FXML
    VBox black, blue, green, orange, purple, red, white, yellow, locomotive;

    @FXML
    ImageView blackimgv, blueimgv, greenimgv, orangeimgv, purpleimgv, redimgv, whiteimgv, yellowimgv, locomotiveimgv;

    @FXML
    HBox cards;

    Image blackimg, blueimg, greenimg, orangeimg, purpleimg, redimg, whiteimg, yellowimg, locomotiveimg;

    private Observable<GameStore> storeObervable = GameStoreProvider.getInstance();
    private GameStore store = GameStoreProvider.getStore();

    /**
     * Get the player from the gamestore
     *
     * @author Ewout
     */

    public void initialize() {
        checkColorBlind();
        CardsOff();
        List<Player> players = store.getPlayers();

        Player player = players.get(0);

        //TODO Only gets the first player connected

        /**
         *
         * Player gets 4 cards in the beginning of the game.
         *
         * @author Ewout
         *
         */

        for (int i = 0; i < 2; i++) {
            int[] random = {0, 0};
            var action = new GetCardAction(player.getId(), random);
            try {
                GameStoreProvider.sendAction(action);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        updateScreenCards(player);
    }

    private void checkColorBlind() {
        blackimg = new Image(getClass().getResourceAsStream("/cards/" + isColorBlind() + "/CART_BLACK.png"));
        blackimgv.setImage(blackimg);
        blueimg = new Image(getClass().getResourceAsStream("/cards/" + isColorBlind() + "/CART_BLUE.png"));
        blueimgv.setImage(blueimg);
        greenimg = new Image(getClass().getResourceAsStream("/cards/" + isColorBlind() + "/CART_GREEN.png"));
        greenimgv.setImage(greenimg);
        orangeimg = new Image(getClass().getResourceAsStream("/cards/" + isColorBlind() + "/CART_ORANGE.png"));
        orangeimgv.setImage(orangeimg);
        purpleimg = new Image(getClass().getResourceAsStream("/cards/" + isColorBlind() + "/CART_PURPLE.png"));
        purpleimgv.setImage(purpleimg);
        redimg = new Image(getClass().getResourceAsStream("/cards/" + isColorBlind() + "/CART_RED.png"));
        redimgv.setImage(redimg);
        whiteimg = new Image(getClass().getResourceAsStream("/cards/" + isColorBlind() + "/CART_WHITE.png"));
        whiteimgv.setImage(whiteimg);
        yellowimg = new Image(getClass().getResourceAsStream("/cards/" + isColorBlind() + "/CART_YELLOW.png"));
        yellowimgv.setImage(yellowimg);
        locomotiveimg = new Image(getClass().getResourceAsStream("/cards/" + isColorBlind() + "/LOCOMOTIVE.png"));
        locomotiveimgv.setImage(locomotiveimg);
    }

    /**
     * Here you put all the visable cards off, because at the start your hand is empty
     *
     * @author Ewout
     */
    private void CardsOff() {
        cards.getChildren().removeAll(black);
        cards.getChildren().removeAll(blue);
        cards.getChildren().removeAll(green);
        cards.getChildren().removeAll(orange);
        cards.getChildren().removeAll(purple);
        cards.getChildren().removeAll(red);
        cards.getChildren().removeAll(white);
        cards.getChildren().removeAll(yellow);
        cards.getChildren().removeAll(locomotive);
    }

    public void updateScreenCards(Player player) {
        if (player.getCardStack().get(CART_BLACK) != null && player.getCardStack().get(CART_BLACK) > 0) {
            blackammount.setText(Integer.toString(player.getCardStack().get(CART_BLACK)));
            cards.getChildren().addAll(black);
        }

        if (player.getCardStack().get(CART_BLUE) != null && player.getCardStack().get(CART_BLUE) > 0) {
            blueammount.setText(Integer.toString(player.getCardStack().get(CART_BLUE)));
            cards.getChildren().addAll(blue);
        }

        if (player.getCardStack().get(CART_GREEN) != null && player.getCardStack().get(CART_GREEN) > 0) {
            greenammount.setText(Integer.toString(player.getCardStack().get(CART_GREEN)));
            cards.getChildren().addAll(green);
        }

        if (player.getCardStack().get(CART_ORANGE) != null && player.getCardStack().get(CART_ORANGE) > 0) {
            orangeammount.setText(Integer.toString(player.getCardStack().get(CART_ORANGE)));
            cards.getChildren().addAll(orange);
        }

        if (player.getCardStack().get(CART_PURPLE) != null && player.getCardStack().get(CART_PURPLE) > 0) {
            purpleammount.setText(Integer.toString(player.getCardStack().get(CART_PURPLE)));
            cards.getChildren().addAll(purple);
        }

        if (player.getCardStack().get(CART_RED) != null && player.getCardStack().get(CART_RED) > 0) {
            redammount.setText(Integer.toString(player.getCardStack().get(CART_RED)));
            cards.getChildren().addAll(red);
        }

        if (player.getCardStack().get(CART_WHITE) != null && player.getCardStack().get(CART_WHITE) > 0) {
            whiteammount.setText(Integer.toString(player.getCardStack().get(CART_WHITE)));
            cards.getChildren().addAll(white);
        }

        if (player.getCardStack().get(CART_YELLOW) != null && player.getCardStack().get(CART_YELLOW) > 0) {
            yellowammount.setText(Integer.toString(player.getCardStack().get(CART_YELLOW)));
            cards.getChildren().addAll(yellow);
        }

        if (player.getCardStack().get(LOCOMOTIVE) != null && player.getCardStack().get(LOCOMOTIVE) > 0) {
            locomotiveammount.setText(Integer.toString(player.getCardStack().get(LOCOMOTIVE)));
            cards.getChildren().addAll(locomotive);
        }
    }
}

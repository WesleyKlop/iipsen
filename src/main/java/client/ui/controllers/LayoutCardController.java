package client.ui.controllers;


import client.ui.components.PlayerCard;
import game.GameStore;
import game.GameStoreProvider;
import game.cards.Card;
import game.cards.CardType;
import game.player.Player;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.Observer;

import java.util.HashMap;
import java.util.Map;

public class LayoutCardController implements Observer<GameStore> {
    private static final Logger Log = LogManager.getLogger(LayoutCardController.class);
    private final Map<CardType, PlayerCard> views = new HashMap<>();
    @FXML
    HBox cards;

    /**
     * Get the player from the gamestore
     *
     * @author Ewout
     */
    @FXML
    public void initialize() {
        GameStoreProvider.getInstance().addObserver(this);
        onUpdate(GameStoreProvider.getStore());
    }

    public void updateScreenCards(Player player) {
        for (Map.Entry<CardType, Integer> entry : player.getCardStack().entrySet()) {
            if (views.containsKey(entry.getKey())) {
                // Update view
                views.get(entry.getKey()).update(entry.getValue());
            } else {
                // Add new card type to map
                PlayerCard card = new PlayerCard(new Card(entry.getKey()), entry.getValue());
                views.put(entry.getKey(), card);
            }
        }
        // Add new card types to view
        for (PlayerCard playerCard : views.values()) {
            if (!cards.getChildren().contains(playerCard)) {
                cards.getChildren().add(playerCard);
            }
        }

        // Remove cards that are not in the players possession anymore
        cards.getChildren().removeIf(node -> {
            PlayerCard el = (PlayerCard) node;
            return !player.getCardStack().keySet().contains(el.getCardType());
        });
    }

    @Override
    public void onUpdate(GameStore value) {
        Platform.runLater(() -> {
            Log.debug("Updating view...");
            updateScreenCards(GameStoreProvider.getPlayer());
        });
    }

    public void switchColorBlind(Player player) {
        cards.getChildren().remove(0, cards.getChildren().size());
        views.clear();
        updateScreenCards(player);
    }
}

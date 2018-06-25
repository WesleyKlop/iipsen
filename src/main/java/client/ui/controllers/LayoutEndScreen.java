package client.ui.controllers;

import game.location.ELocation;
import game.player.Player;
import game.routecards.RouteCard;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class LayoutEndScreen {
    @FXML
    VBox content;
    @FXML
    VBox prizes;

    private List<Player> players = new ArrayList<>();
    private int[] finishedCards;

    public void initialize() {
        players.add(new Player("Henk", Color.BLUE));
        players.add(new Player("Jamal", Color.BLACK));
        players.add(new Player("Beer", Color.ORANGE));
        players.get(2).getConnectionKeeper().addLocations(ELocation.ALBORG, ELocation.ARHUS);
        players.get(2).addRouteCard(new RouteCard(ELocation.ALBORG, ELocation.ARHUS, 10));
        finishedCards = new int[players.size()];
        startCardPoints();
    }

    private void addPlayers() {
        for (int i = players.size() - 1; i >= 0; i--) {
            Label name = new Label(players.get(i).getPlayerName());
            Label score = new Label(String.valueOf(players.get(i).getScore()));
            HBox total = new HBox(name, score);
            total.setSpacing(10);
            content.getChildren().add(total);
        }
    }

    private void startCardPoints() {
        for (int i = 0; i < players.size(); i++) {
            for (RouteCard card : players.get(i).getRouteCards()) {
                if (players.get(i).getConnectionKeeper().checkForRouteCompleted(card.getStart(), card.getEnd())) {
                    addScore(i, card.getValue());
                    finishedCards[i]++;
                }
            }
        }
        System.out.println("Awarding GlobeTrotter");
        awardGlobeTrotter();
    }

    private void awardGlobeTrotter() {
        int maxAmount = 0;
        int index = 0;
        for (int i = 0; i < finishedCards.length; i++) {
            if (finishedCards[i] > maxAmount) {
                maxAmount = finishedCards[i];
                index = i;
            }
        }
        addScore(index, 10);
        sortPlayers();
    }

    private void sortPlayers() {
        players.sort(Comparator.comparingInt(Player::getScore));
        addPlayers();
    }

    private void addScore(int index, int amount) {
        players.get(index).givePoints(amount);
    }
}

package client.ui.controllers;

import game.location.ELocation;
import game.player.Player;
import game.routecards.RouteCard;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class LayoutEndScreen {

    private static final Logger Log = LogManager.getLogger(LayoutEndScreen.class);
    @FXML
    private VBox playerList;
    private List<Player> playerListArray = new ArrayList<>();//= GameStoreProvider.getStore().getPlayers();
    private Player[] pL = new Player[3];//= new Player[playerListArray.size()];
    private int[] scores = new int[pL.length];
    private int[] routesFinished = new int[pL.length];
    private int globeTrotterWinner;
    private Timeline line = new Timeline();

    public void initialize() {
        playerListArray.add(new Player("Phillip", Color.PINK));
        playerListArray.add(new Player("Chuck", Color.GOLD));
        playerListArray.add(new Player("HAHA BIEER", Color.BISQUE));
        pL = playerListArray.toArray(pL);
        for (int i = 0; i < scores.length; i++) {
            scores[i] = pL[i].getScore();
        }
        pL[2].getConnectionKeeper().addLocations(ELocation.ALBORG, ELocation.ARHUS);
        pL[2].addRouteCard(new RouteCard(ELocation.ALBORG, ELocation.ARHUS, 21));
        pL[0].getConnectionKeeper().addLocations(ELocation.HONNINGSVAG, ELocation.BODEN);
        pL[0].addRouteCard(new RouteCard(ELocation.HONNINGSVAG, ELocation.BODEN, 10));
        pL[1].getConnectionKeeper().addLocations(ELocation.KAJAANI, ELocation.ARHUS);
        pL[1].addRouteCard(new RouteCard(ELocation.KAJAANI, ELocation.ARHUS, 12));
        pL[2].getConnectionKeeper().addLocations(ELocation.ALBORG, ELocation.ARHUS);
        pL[2].addRouteCard(new RouteCard(ELocation.ALBORG, ELocation.ARHUS, 18));
        pL[1].getConnectionKeeper().addLocations(ELocation.HONNINGSVAG, ELocation.BODEN);
        pL[1].addRouteCard(new RouteCard(ELocation.HONNINGSVAG, ELocation.BODEN, 10));
        pL[0].getConnectionKeeper().addLocations(ELocation.KAJAANI, ELocation.ARHUS);
        pL[0].addRouteCard(new RouteCard(ELocation.KAJAANI, ELocation.ARHUS, 12));


        line.setCycleCount(Animation.INDEFINITE);
        addPlayers();

        onMouseClickedAction();

        //constructKeyFramesArray();  // Cycle through the players and check for route cards
    }

    private void addPlayers() {
        for (int i = 0; i < pL.length; i++) {
            Label name = new Label("Name: " + pL[i].getPlayerName());
            Label score = new Label("Score: " + String.valueOf(scores[i]));
            Label routeAmount = new Label("Routes Finished: 0");
            VBox player = new VBox(name, score, routeAmount);
            player.setAlignment(Pos.CENTER);
            Rectangle background = new Rectangle(200, 100);
            background.setFill(pL[i].getColorAsColor());
            StackPane playerPane = new StackPane(background, player);
            playerPane.setId(String.valueOf(i));
            playerPane.setPrefHeight(100);
            playerList.getChildren().add(playerPane);
        }
    }

    @FXML
    private void onMouseClickedAction() {
        constructKeyFramesArray();
    }

    private void constructKeyFramesArray() {
        List<Timeline> timelines = new ArrayList<>();
        int points;
        List<Integer> routes;
        for (int i = 0; i < pL.length; i++) {
            points = 0;
            routes = new ArrayList<>();
            for (int r = 0; r < pL[i].getRouteCards().size(); r++) {
                RouteCard card = pL[i].getRouteCards().get(r);
                if (pL[i].getConnectionKeeper().checkForRouteCompleted(card.getStart(), card.getEnd())) {
                    routesFinished[i]++;
                    points += card.getValue();
                    routes.add(points);
                }
            }
            timelines.add(getAddPointsTimeline(i, points, routes));
        }
        sortPlayers(timelines);
    }


    private Timeline getAddPointsTimeline(int index, int increment, List<Integer> checks) {
        StackPane p = (StackPane) playerList.getChildren().get(index);
        VBox pv = (VBox) p.getChildren().get(1);
        Label target = (Label) pv.getChildren().get(1);
        int targetScore = scores[index] + increment;
        IntegerProperty start = new SimpleIntegerProperty(scores[index]);
        scores[index] = targetScore;
        Timeline timeline = new Timeline();
        timeline.setCycleCount(increment);
        IntegerProperty currentCycle = new SimpleIntegerProperty(0);
        KeyFrame frame = new KeyFrame(
                Duration.millis(50),
                event -> {
                    start.set(start.get() + 1);
                    target.setText("Score: " + start.get());
                    currentCycle.set(currentCycle.get() + 1);
                    if (checks.contains(currentCycle.get())) {
                        addRouteFinished(index);
                    }
                });
        timeline.getKeyFrames().add(frame);
        return timeline;
    }

    private Timeline getPauseTimeLine() {
        Timeline timeline = new Timeline();
        KeyFrame frame = new KeyFrame(Duration.seconds(1));
        timeline.getKeyFrames().add(frame);
        return timeline;
    }

    private void addRouteFinished(int index) {
        StackPane p = (StackPane) playerList.getChildren().get(index);
        VBox pv = (VBox) p.getChildren().get(1);
        Label target = (Label) pv.getChildren().get(2);
        String currentString = target.getText();
        int currentRoutes = Integer.parseInt(currentString.substring(17));
        currentRoutes += 1;
        target.setText("Routes Finished: " + currentRoutes);

    }

    private void loopTheTimeLines(List<Timeline> timelines, SimpleIntegerProperty index) {
        if (timelines.size() > index.get()) {
            Timeline timeline = timelines.get(index.get());
            timeline.play();
            index.set(index.get() + 1);
            timeline.setOnFinished(e -> {
                System.out.println("Next KeyFrame: " + index.get());
                timeline.getOnFinished();
                loopTheTimeLines(timelines, index);
            });
        }
    }

    private void sortPlayers(List<Timeline> timelines) {
        System.out.println("Sorting players");
        boolean switchMade;

        do {
            switchMade = false;
            for (int i = 0; i < 2; i++) {
                System.out.println(i + ": " + scores[i]);
                System.out.println(Integer.sum(i, 1) + ": " + scores[i + 1]);
                if (scores[i] < scores[i + 1]) {
                    System.out.println("Player: " + i + " has a lower score than the player underneath him");
                    switchScorePositions(i, i + 1);
                    switchRoutesFinished(i, i + 1);
                    timelines.add(getMovePlayerUpTimeLine(getNodeById(i + 1)));
                    timelines.add(getMovePlayerDownTimeLine(getNodeById(i)));
                    switchNodes(i, i + 1);
                    switchMade = true;
                }
            }
            System.out.println("For loop finished");
        } while (switchMade);
        awardGlobeTrotter(timelines);
        loopTheTimeLines(timelines, new SimpleIntegerProperty(0));
    }

    private Node getNodeById(int id) {
        for (int i = 0; i < playerList.getChildren().size(); i++) {
            if (Integer.parseInt(playerList.getChildren().get(i).getId()) == id) {
                return playerList.getChildren().get(i);
            }
        }
        return null;
    }

    private void switchScorePositions(int index1, int index2) {
        int temp = scores[index1];
        scores[index1] = scores[index2];
        scores[index2] = temp;
    }

    private void switchRoutesFinished(int index1, int index2) {
        int temp = routesFinished[index1];
        routesFinished[index1] = routesFinished[index2];
        routesFinished[index2] = temp;
    }

    private void switchNodes(int index1, int index2) {
        playerList.getChildren().get(index1).setId(String.valueOf(index2));
        playerList.getChildren().get(index2).setId(String.valueOf(index1));
    }

    private Timeline getMovePlayerUpTimeLine(Node target) {
        KeyFrame frame = new KeyFrame(
                Duration.millis(5),
                event -> {
                    target.setTranslateY(target.getTranslateY() - 1);
                });
        Timeline timeline = new Timeline(frame);
        timeline.setCycleCount((int) playerList.getSpacing() + 100);
        return timeline;
    }

    private Timeline getMovePlayerDownTimeLine(Node target) {
        KeyFrame frame = new KeyFrame(
                Duration.millis(5),
                event -> {
                    target.setTranslateY(target.getTranslateY() + 1);
                });
        Timeline timeline = new Timeline(frame);
        timeline.setCycleCount((int) playerList.getSpacing() + 100);
        return timeline;
    }

    private void awardGlobeTrotter(List<Timeline> timelines) {
        int maxRoutes = 0;
        int indexMaxRoutes = 0;
        for (int i = 0; i < routesFinished.length; i++) {
            if (routesFinished[i] > maxRoutes) {
                indexMaxRoutes = i;
                maxRoutes = routesFinished[i];
            }
        }
    }

}

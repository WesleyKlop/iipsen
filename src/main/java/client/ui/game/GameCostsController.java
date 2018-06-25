package client.ui.game;

import client.ui.dialogs.MessagesControllerProvider;
import game.GameStore;
import game.GameStoreProvider;
import game.actions.Action;
import game.actions.BuildRouteAction;
import game.cards.CardType;
import game.location.ELocation;
import game.player.Player;
import game.routecards.Route;
import game.routecards.RouteType;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.util.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.rmi.RemoteException;

import static client.util.UserPreferences.isColorBlind;

public class GameCostsController {
    @FXML
    StackPane rootPane;
    @FXML
    private HBox trainBox1, trainBox2, trainBox3;
    @FXML
    private Label locations, tunnelWarning, owner;
    @FXML
    private Button buildButton;

    private int currentId;

    private static final Logger Log = LogManager.getLogger(GameCostsController.class);

    @FXML
    public void initialize() {
        locations.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/MavenPro-Medium.ttf"), 25));
    }

    public void showBuildDialog(Route route) {
        resetMessage();
        buildButton.setDisable(route.hasOwner());
        if (route.getOwner() == 0) {
            owner.setText("");
        } else {
            owner.setText(String.format(
                "This route is currently owned by: %s",
                GameStoreProvider.getStore().getPlayerController().getPlayerById(route.getOwner()).getPlayerName())
            );
        }
        updateCurrentId(route.getId());
        addParts(route.getLength(), route.getLocomotiveCost(), route.getCardType());
        setMessageText(route.getLocations());
    }

    private void updateCurrentId(int newId) {
        this.currentId = (newId);
    }

    private void addParts(int l, int locs, CardType cType) {
        int trainWidth = (int) Math.min((trainBox1.getMaxWidth() / Math.min(l, 4)) - ((Math.min(l, 4) - 1) * 10), 250);

        for (int i = 0; i < l; i++) {
            Image image;
            if (i < locs) {
                image = new Image(getClass().getResourceAsStream("/cards/" + isColorBlind() + "/LOCOMOTIVE.png"));
            } else {
                image = new Image(getClass().getResourceAsStream("/cards/" + isColorBlind() + "/" + cType + ".png"));
            }
            ImageView train = new ImageView(image);
            train.setPreserveRatio(true);
            train.setFitWidth(trainWidth);
            if (i < 3) {
                trainBox1.getChildren().add(train);
            } else if (i < 6) {
                trainBox2.getChildren().add(train);
            } else {
                trainBox3.getChildren().add(train);
            }
        }
    }

    private void resetMessage() {
        setRouteWarning("");
        emptyBox(trainBox1);
        emptyBox(trainBox2);
        emptyBox(trainBox3);
    }

    private void emptyBox(HBox box) {
        while (box.getChildren().size() > 0) {
            box.getChildren().remove(0);
        }
    }

    private void setMessageText(ELocation[] locationList) {
        locations.setText(locationList[0] + " --> " + locationList[1]);
    }

    @FXML
    private void buildRoute() throws RemoteException {
        GameStore store = GameStoreProvider.getStore();
        Player player = GameStoreProvider.getPlayer();
        Route route = store.getRouteStore().getRouteById(currentId);
        if (BuildRouteControle(route, player)) {
            int extraCosts = (route.getRouteType() == RouteType.TUNNEL) ? calculateExtraCosts(route.getCardType()) : 0;
            Action buildAction = new BuildRouteAction(player.getId(), route, extraCosts);
            GameStoreProvider.sendAction(buildAction);
            if (route.getRouteType() == RouteType.TUNNEL) {
                closeAnimationWait();
            } else {
                closeAnimation();
            }
        } else {
            locations.setText("Player doesn't have enough cards!");
        }
    }

    @FXML
    private void closeAnimation() {
        MessagesControllerProvider.getMessageController().closeMenu(rootPane);
    }

    private void closeAnimationWait() {
        Transition wait = new TranslateTransition(Duration.seconds(1), rootPane);
        wait.play();
        wait.setOnFinished(e -> closeAnimation());
    }

    private boolean BuildRouteControle(Route route, Player player) {
        return player.getCardStack().containsCards(route.getCostsAsCardStack()) && !route.hasOwner();
    }

    public void setRouteWarning(String text) {
        Platform.runLater(() -> tunnelWarning.setText(text));
    }

    private int calculateExtraCosts(CardType type) {
        int extraCosts = 0;
        for (int i = 0; i < 3; i++) {
            if (GameStoreProvider.getStore().getCardStackController().getRandomCard().getCardType() == type) {
                extraCosts++;
            }
        }
        Log.info("Extra costs for tunnel are {}", extraCosts);
        try {
            MessagesControllerProvider.getMessageController().setBuildRouteWarning("Extra costs for tunnel: " + extraCosts);
        } catch (NullPointerException ex) {
            Log.error("NullPointerError showing BuildRouteWarning... ", ex);
        }
        return extraCosts;
    }
}

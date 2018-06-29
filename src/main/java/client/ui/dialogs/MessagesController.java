package client.ui.dialogs;

import client.ui.game.GameCostsController;
import game.GameStore;
import game.GameStoreProvider;
import game.routecards.Route;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import util.Observer;

public class MessagesController implements Observer<GameStore> {

    @FXML
    private GameCostsController routesController;
    @FXML
    private TrainCardMessageController trainController;
    @FXML
    private RouteCardMessageController routeCardsController;
    @FXML
    private StackPane routes, train, routeCards, notYourTurn, turn;

    /**
     * @author Thom
     */
    public void initialize() {
        GameStoreProvider.getInstance().addObserver(this);
    }


    /**
     * This method opens a message to the player with costs of the route about to be build.
     *
     * @param id the ID of the route to be build;
     */
    public void openBuildMessage(int id) {
        if (isPlayersTurn()) {
            Route route = GameStoreProvider.getStore().getRouteStore().getRouteById(id);
            routesController.showBuildDialog(route);
            openMenu(routes);
            closeMenu(train);
            closeMenu(routeCards);
        } else {
            openNotYourTurnMenu();
        }
    }

    /**
     * Opens a message for train cards to be picked from the bank
     *
     * @param index Takes 2 indexes of the bank cards (0 = random, 1 - 5 = the 5 open cards);
     */

    public void openTrainCardMessage(int[] index) {
        if (isPlayersTurn()) {
            trainController.showDialog(index);
            openMenu(train);
            closeMenu(routes);
            closeMenu(routeCards);
        } else {
            openNotYourTurnMenu();
        }
    }

    /**
     * Opens a message with the 3 cards from the open route cards.
     */
    public void openRouteCardMessage() {
        if (isPlayersTurn()) {
            routeCardsController.showDialog();
            openMenu(routeCards);
            closeMenu(routes);
            closeMenu(train);
        } else {
            openNotYourTurnMenu();
        }
    }

    private void openNotYourTurnMenu() {
        openWaitAndClose(notYourTurn);
    }

    private void openWaitAndClose(Pane menu) {
        TranslateTransition transAni = new TranslateTransition(Duration.millis(500), menu);
        transAni.setToY(-1080);
        transAni.play();
        transAni.setOnFinished(e -> {
            transAni.setDuration(Duration.seconds(1));
            transAni.play();
            transAni.setOnFinished(f -> closeMenu(menu));
        });
    }

    private void openMenu(Pane menu) {
        TranslateTransition transAni = new TranslateTransition(Duration.millis(500), menu);
        transAni.setToY(-1080);
        transAni.play();
    }

    /**
     * Closes a single message
     *
     * @param menu takes the Pane message to be closed
     */
    public void closeMenu(Pane menu) {
        TranslateTransition transAni = new TranslateTransition(Duration.millis(500), menu);
        transAni.setToY(0);
        transAni.play();
    }

    public GameCostsController getGameCostsController() {
        return routesController;
    }

    /**
     * Closes all messages
     */
    public void closeAllMessages() {
        closeMenu(routes);
        closeMenu(train);
        closeMenu(routeCards);
    }

    /**
     * Sets the text for the build route message
     *
     * @param text the text to show
     */
    public void setBuildRouteWarning(String text) {
        routesController.setRouteWarning(text);
    }

    private boolean isPlayersTurn() {
        return GameStoreProvider.getStore().getPlayerController().getCurrentTurn() == GameStoreProvider.getPlayer().getId();
    }

    /**
     * Shows message when it's the players turn.
     *
     * @param store the new GameStore
     */
    @Override
    public void onUpdate(GameStore store) {
        if (store.getPlayerController().getCurrentTurn() == GameStoreProvider.getPlayer().getId()) {
            openWaitAndClose(turn);
        }
    }
}

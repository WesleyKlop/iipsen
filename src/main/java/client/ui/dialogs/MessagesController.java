package client.ui.dialogs;

import client.ui.game.GameCostsController;
import game.GameStore;
import game.GameStoreProvider;
import game.routecards.Route;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
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


    public void initialize() {
        GameStoreProvider.getInstance().addObserver(this);
    }

    public void openBuildMessage(MouseEvent mE) {
        if (isPlayersTurn()) {
            VBox source = (VBox) mE.getSource();
            int id = Integer.parseInt(source.getId());
            Route route = GameStoreProvider.getStore().getRouteStore().getRouteById(id);
            routesController.showBuildDialog(route);
            openMenu(routes);
            closeMenu(train);
            closeMenu(routeCards);
        } else {
            openNotYourTurnMenu();
        }
    }

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

    public void closeMenu(Pane menu) {
        TranslateTransition transAni = new TranslateTransition(Duration.millis(500), menu);
        transAni.setToY(0);
        transAni.play();
    }

    public GameCostsController getGameCostsController() {
        return routesController;
    }

    public void closeAllMenus() {
        closeMenu(routes);
        closeMenu(train);
        closeMenu(routeCards);
    }

    public void setBuildRouteWarning(String text) {
        routesController.setRouteWarning(text);
    }

    private boolean isPlayersTurn() {
        return GameStoreProvider.getStore().getPlayersTurn() == GameStoreProvider.getPlayer().getId();
    }

    @Override
    public void onUpdate(GameStore value) {
        if (GameStoreProvider.getStore().getPlayersTurn() == GameStoreProvider.getPlayer().getId()) {
            openWaitAndClose(turn);
        }
    }
}

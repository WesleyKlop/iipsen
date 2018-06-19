package client.ui;

import game.GameStoreProvider;
import game.routecards.Route;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class MessagesController {

    @FXML
    private GameCostsController routesController;
    @FXML
    private TrainCardMessageController trainController;
    @FXML
    private StackPane routes, train;


    public void initialize() {

    }

    public void openBuildMessage(MouseEvent mE) {
        VBox source = (VBox) mE.getSource();
        int id = Integer.parseInt(source.getId());
        Route route = GameStoreProvider.getStore().getRouteStore().getRouteById(id);
        routesController.ActivationAction(route);
        openMenu(routes);
        closeMenu(train);
    }

    public void openTrainCardMessage(int[] index) {
        trainController.ActivationAction(index);
        openMenu(train);
        closeMenu(routes);
    }

    private void openMenu(StackPane menu) {
        TranslateTransition transAni = new TranslateTransition(Duration.millis(500), menu);
        transAni.setToY(-1080);
        transAni.play();
    }

    public void closeMenu(StackPane menu) {
        TranslateTransition transAni = new TranslateTransition(Duration.millis(500), menu);
        transAni.setToY(0);
        transAni.play();
    }

    public GameCostsController getGameCostsController() {
        return routesController;
    }
}

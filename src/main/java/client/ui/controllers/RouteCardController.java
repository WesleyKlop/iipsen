package client.ui.controllers;

import game.GameStore;
import game.GameStoreProvider;
import game.actions.Action;
import game.actions.SelectRouteCardsAction;
import game.routecards.RouteCard;
import game.routecards.RouteCardStackBank;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;
import util.Observer;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;


public class RouteCardController implements Observer<GameStore> {
    @FXML
    HBox routeCardBox;
    private List<RouteCard> routeCards = new ArrayList<>();
    private List<RouteCard> selectedRouteCards = new ArrayList<>();
    @FXML
    private Pane rootPane;

    @FXML
    private Text textRouteCards;

    public void initialize() {
        var store = GameStoreProvider.getStore();
        GameStoreProvider.getInstance().addObserver(this);
        this.onUpdate(store);
    }

    private void setRouteCardsImg(RouteCardStackBank bank) {
        routeCards.clear();
        for (int i = 0; i < 5; i++) {
            updateCard(i, bank.getRandomRouteCard());
        }
    }

    private void updateCard(int index, RouteCard card) {
        ImageView imageView = (ImageView) routeCardBox.getChildren().get(index);
        imageView.setImage(new Image(getClass().getResourceAsStream(card.getImagePath())));
        routeCards.add(card);
    }


    public void onRouteCardClicked(MouseEvent mouseEvent) {
        ImageView imageView = (ImageView) mouseEvent.getSource();
        int index = Integer.parseInt(imageView.getId());
        RouteCard selectedCard = routeCards.get(index);

        if (!selectedRouteCards.contains(selectedCard)) {
            imageView.setStyle("-fx-effect: dropshadow(three-pass-box, green, 5, 5, 0, 0);");
            selectedRouteCards.add(selectedCard);
        } else {
            imageView.setStyle("");
            selectedRouteCards.remove(selectedCard);
        }
    }

    public void onConfirmClicked(MouseEvent mouseEvent) throws RemoteException {
        GameStoreProvider.getInstance().removeObserver(this);
        if (selectedRouteCards.size() < 2) {
            textRouteCards.setFill(Color.RED);
            return;
        }
        Button button = (Button) mouseEvent.getSource();
        button.setDisable(true);

        Action routeCardAction = new SelectRouteCardsAction(
                GameStoreProvider.getPlayer().getId(),
            selectedRouteCards);
        GameStoreProvider.sendAction(routeCardAction);
        close();
    }

    private void close() {
        TranslateTransition transAni = new TranslateTransition(Duration.millis(350), rootPane);
        transAni.setToY(1080);
        transAni.play();
    }

    @Override
    public void onUpdate(GameStore value) {
        Platform.runLater(() -> setRouteCardsImg(value.getSelectableRouteCards().getBank()));
    }
}

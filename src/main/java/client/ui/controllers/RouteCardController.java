package client.ui.controllers;

import game.GameStore;
import game.GameStoreProvider;
import game.actions.Action;
import game.actions.SelectRouteCardsAction;
import game.routecards.RouteCard;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import util.Observable;

import java.rmi.RemoteException;
import java.util.Arrays;


public class RouteCardController {
    @FXML
    HBox Routecardbox;
    private Observable<GameStore> storeObervable = GameStoreProvider.getInstance();
    private GameStore store = GameStoreProvider.getStore();
    private RouteCard[] routecardtemp = new RouteCard[5];
    boolean[] BooleanRouteCards = new boolean[5];
    boolean currentState = false;
    int routefalse = 0;

    @FXML
    private Text Textroutecards;

    public void initialize() {
        Arrays.fill(BooleanRouteCards, Boolean.FALSE);
        store.getSelectableRouteCards().populatePickableCards();
        setrouteCardsimg();
    }

    private void setrouteCardsimg() {
        for (int i = 0; i < 5; i++) {
            updateCard(i);
        }
    }

    private void updateCard(int index) {
        ImageView imageView = (ImageView) Routecardbox.getChildren().get(index);
        imageView.setImage(new Image(getClass().getResourceAsStream(store.getSelectableRouteCards().getBank().getRandomRouteCard().getImagePath())));
        routecardtemp[index] = store.getSelectableRouteCards().getBank().getRandomRouteCard();
    }


    public void isClicked(MouseEvent mouseEvent) {
        ImageView imageview = (ImageView) mouseEvent.getSource();
        int index = Integer.parseInt(imageview.getId());

        if (BooleanRouteCards[index] == false) {
            imageview.setStyle("-fx-effect: dropshadow(three-pass-box, green, 5, 5, 0, 0);");
            BooleanRouteCards[index] = true;
        } else {
            imageview.setStyle("");
            BooleanRouteCards[index] = false;
        }
    }

    public void ConfirmClicked(MouseEvent mouseEvent) throws RemoteException {
        for (int i = 0; i < BooleanRouteCards.length; i++) {
            if (BooleanRouteCards[i] == false) {
                routefalse++;
            }
        }
        if (routefalse == 5 || routefalse == 4) {
            Textroutecards.setFill(Color.RED);
        }
        if (routefalse < 4) {
            for (int i = 0; i < BooleanRouteCards.length; i++) {
                if (BooleanRouteCards[i] == false) {
                    routecardtemp[i] = null;
                }
            }
            Action RouteCardAction = new SelectRouteCardsAction(store.getPlayersTurn(), routecardtemp);
            GameStoreProvider.sendAction(RouteCardAction);
        }
        routefalse = 0;
    }
}

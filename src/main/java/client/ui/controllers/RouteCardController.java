package client.ui.controllers;

import game.GameStore;
import game.GameStoreProvider;
import game.routecards.RouteCard;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import util.Observable;


public class RouteCardController {
    @FXML
    HBox Routecardbox;
    private Observable<GameStore> storeObervable = GameStoreProvider.getInstance();
    private GameStore store = GameStoreProvider.getStore();
    private RouteCard[] routecardtemp = new RouteCard[5];
    private RouteCard[] routecardchosen = new RouteCard[2];

    public void initialize() {
        // FillRouteCards();
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
        if (routecardchosen[0] == null) {
            routecardchosen[0] = routecardtemp[index];
            imageview.setStyle("-fx-effect: dropshadow(three-pass-box, green, 10, 10, 0, 0);");
        } else if (routecardchosen[0] != null) {
            routecardchosen[1] = routecardtemp[index];
            imageview.setStyle("-fx-effect: dropshadow(three-pass-box, green, 10, 10, 0, 0);");
        }
    }

    public void ConfirmClicked(MouseEvent mouseEvent) {
        store.getPlayerById(0).addRouteCard(routecardchosen[0]);
        store.getPlayerById(0).addRouteCard(routecardchosen[1]);
    }
}

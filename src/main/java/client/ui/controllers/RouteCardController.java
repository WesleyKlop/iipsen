package client.ui.controllers;

import game.GameStore;
import game.GameStoreProvider;
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
    }


    public void isClicked(MouseEvent mouseEvent) {
        ImageView imageview = (ImageView) mouseEvent.getSource();
        if ((mouseEvent.getClickCount()) % 2 == 1) {
            imageview.setStyle("-fx-effect: dropshadow(three-pass-box, green, 10, 10, 0, 0);");
            ;

        } else {
            imageview.setStyle("");
        }

    }
}

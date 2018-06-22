package client.ui;

import game.GameStore;
import game.GameStoreProvider;
import game.player.Player;
import game.routecards.RouteCard;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import util.Observable;
import util.Observer;

import java.util.List;

public class LayoutGameHandRouteCardsController implements Observer<GameStore> {

    private Observable<GameStore> storeObservable = GameStoreProvider.getInstance();
    @FXML
    private HBox routeCardContainer;

    public void initialize() {
        storeObservable.addObserver(this);
    }

    private void emptyRouteContainer() {
        routeCardContainer.getChildren().remove(0, routeCardContainer.getChildren().size());
    }

    @Override
    public void onUpdate(GameStore store) {
        Platform.runLater(() -> {
            emptyRouteContainer();
            Player player = GameStoreProvider.getPlayer();
            List<RouteCard> routeCardsList = player.getRouteCards();
            for (int i = 0; i < routeCardsList.size(); i++) {
                Image image = new Image(getClass().getResourceAsStream(routeCardsList.get(i).getImagePath()));
                ImageView routeCardView = new ImageView(image);
                routeCardContainer.getChildren().add(routeCardView);
            }
        });
    }
}

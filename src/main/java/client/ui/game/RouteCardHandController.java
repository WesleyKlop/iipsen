package client.ui.game;

import game.GameStore;
import game.GameStoreProvider;
import game.routecards.RouteCard;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import util.Observable;
import util.Observer;

import java.util.ArrayList;
import java.util.List;


public class RouteCardHandController implements Observer<GameStore> {

    @FXML
    HBox content;
    List<RouteCard> routecards;
    ArrayList<Image> imageArrayList = new ArrayList<>();
    ArrayList<ImageView> imageViewArrayList = new ArrayList<>();
    private Observable<GameStore> storeObservable = GameStoreProvider.getInstance();
    private GameStore store = storeObservable.getValue();

    public void initialize() {
        GameStoreProvider.getInstance().addObserver(this);
    }

    @Override
    public void onUpdate(GameStore value) {
        System.out.println("update");

        //
        //   for(int i = 0; i < store.getPlayerById(1).getRouteCards().size(); i++) {
        //       if(store.getPlayerById(1).getRouteCards().get(i) != null) {
        //           imageArrayList.add(new Image(store.getPlayerById(1).getRouteCards().get(i).getImagePath()));
        //           imageViewArrayList.add(new ImageView());
        //           imageViewArrayList.get(i).setImage(imageArrayList.get(i));
        //           content.getChildren().add(imageViewArrayList.get(i));
        //       }
        //   }
    }
}

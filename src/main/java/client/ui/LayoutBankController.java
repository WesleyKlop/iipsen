package client.ui;

import game.GameStore;
import game.GameStoreProvider;
import game.actions.Action;
import game.actions.GetCardAction;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.Observable;

import java.rmi.RemoteException;

public class LayoutBankController {

    private static final Logger Log = LogManager.getLogger(LayoutBankController.class);
    @FXML
    private VBox openCards;
    private Observable<GameStore> storeObservable = GameStoreProvider.getInstance();
    private GameStore store = GameStoreProvider.getStore();

    public void initialize() {
        store.getCardStackController().populateOpenCards();
        setCardImages();
        storeObservable.addObserver(gameState -> {
            store = GameStoreProvider.getStore();
            setCardImages();
        });
    }

    private void setCardImages() {
        for (int i = 0; i < openCards.getChildren().size(); i++) {
            updateCard(i);
        }
    }

    private void updateCard(int index) {
        ImageView imageView = (ImageView) openCards.getChildren().get(index);
        imageView.setImage(new Image(getClass().getResourceAsStream(store.getCardStackController().getOpenCards()[index].getPath())));
    }

    @FXML
    private void pickUpOpenCard(MouseEvent mE) throws RemoteException {
        ImageView imageView = (ImageView) mE.getSource();
        int index = Integer.parseInt(imageView.getId());
        Action randomCardAction = new GetCardAction(store.getPlayerById(store.getPLayersTurn()), index);
        GameStoreProvider.sendAction(randomCardAction);
    }

    @FXML
    private void pickUpClosedCard() throws RemoteException {
        Action randomCardAction = new GetCardAction(store.getPlayerById(store.getPLayersTurn()), 0);
        GameStoreProvider.sendAction(randomCardAction);
    }
}

package client.ui.dialogs;

import game.GameStoreProvider;
import game.actions.Action;
import game.actions.GetCardAction;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.rmi.RemoteException;

public class TrainCardMessageController {

    @FXML
    private StackPane rootPane;
    @FXML
    private ImageView card1, card2;
    private Image[] images = new Image[2];
    private int[] indexes = new int[2];

    public void showDialog(int[] index) {
        indexes = index;
        clearMessage();
        for (int i = 0; i < 2; i++) {
            if (index[i] == 0) {
                images[i] = new Image(getClass().getResourceAsStream("/cards/FALSE/BACK.png"));
            } else {
                images[i] = new Image(getClass().getResourceAsStream(GameStoreProvider.getStore().getCardStackController().getOpenCards()[index[i] - 1].getPath()));
            }
        }
        card1.setImage(images[0]);
        card2.setImage(images[1]);
    }

    private void clearMessage() {
        card1.setImage(null);
        card2.setImage(null);
    }

    @FXML
    private void cancel() {
        MessagesControllerProvider.getMessageController().closeMenu(rootPane);
    }

    @FXML
    private void take() throws RemoteException {
        Action action = new GetCardAction(GameStoreProvider.getPlayer().getId(), indexes);
        GameStoreProvider.sendAction(action);
        MessagesControllerProvider.getMessageController().closeMenu(rootPane);
    }
}

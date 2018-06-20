package client.ui;

import game.GameStoreProvider;
import game.actions.Action;
import game.actions.SelectRouteCardsAction;
import game.routecards.RouteCard;
import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class RouteCardMessageController {

    @FXML
    StackPane rootPane;
    @FXML
    Label warningText;
    @FXML
    private HBox routeBox;
    private Image[] images = new Image[3];
    private RouteCard[] routeCards = new RouteCard[3];
    private boolean[] selected = new boolean[3];

    public void showDialog() {
        warningText.setText("");
        resetSelectedList();
        for (int i = 0; i < 3; i++) {
            routeCards[i] = GameStoreProvider.getStore().getSelectableRouteCards().getPickableCards()[i];
            images[i] = new Image(getClass().getResourceAsStream(routeCards[i].getImagePath()));
            ImageView CardView = (ImageView) routeBox.getChildren().get(i);
            CardView.setImage(images[i]);
        }
    }

    private void resetSelectedList() {
        for (int i = 0; i < selected.length; i++) {
            selected[i] = false;
            scaleDown(i);
        }
    }

    @FXML
    private void onMouseClickedAction(MouseEvent mE) {
        ImageView source = (ImageView) mE.getSource();
        int id = Integer.parseInt(source.getId());
        selected[id] = !selected[id];
        if (selected[id]) {
            scaleUp(id);
        } else {
            scaleDown(id);
        }
    }

    private void scaleUp(int id) {
        ScaleTransition scaleAni = new ScaleTransition(Duration.millis(200), routeBox.getChildren().get(id));
        scaleAni.setToX(1.1);
        scaleAni.setToY(1.1);
        scaleAni.play();
    }

    private void scaleDown(int id) {
        ScaleTransition scaleAni = new ScaleTransition(Duration.millis(200), routeBox.getChildren().get(id));
        scaleAni.setToX(1);
        scaleAni.setToY(1);
        scaleAni.play();
    }

    @FXML
    private void sendAction() throws RemoteException {
        int amount = getSelectedAmount();
        if (AtleastOneSelected()) {
            Action action = new SelectRouteCardsAction(GameStoreProvider.getStore().getPlayersTurn(), getSelectedRouteCardStack());
            GameStoreProvider.sendAction(action);
            cancel();
        } else {
            warningText.setText("You need to select at least 1 route");
        }
    }

    private int getSelectedAmount() {
        int selectedAmount = 0;
        for (int i = 0; i < 3; i++) {
            if (selected[i]) {
                selectedAmount++;
            }
        }
        return selectedAmount;
    }

    private boolean AtleastOneSelected() {
        return getSelectedAmount() > 0;
    }

    private List<RouteCard> getSelectedRouteCardStack() {
        List<RouteCard> stack = new ArrayList<>();
        for (int i = 0; i < selected.length; i++) {
            if (selected[i]) {
                stack.add(routeCards[i]);
            }
        }
        return stack;
    }

    @FXML
    private void cancel() {
        MessagesControllerProvider.getMessageController().closeMenu(rootPane);
    }

}

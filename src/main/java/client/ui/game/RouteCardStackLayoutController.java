package client.ui.game;

import client.ui.dialogs.MessagesControllerProvider;
import javafx.fxml.FXML;

public class RouteCardStackLayoutController {

    @FXML
    private void onMouseClickedAction() {
        MessagesControllerProvider.getMessageController().openRouteCardMessage();
    }
}

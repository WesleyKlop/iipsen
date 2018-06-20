package client.ui;

import javafx.fxml.FXML;

public class RouteCardStackLayoutController {

    @FXML
    private void onMouseClickedAction() {
        MessagesControllerProvider.getMessageController().openRouteCardMessage();
    }
}

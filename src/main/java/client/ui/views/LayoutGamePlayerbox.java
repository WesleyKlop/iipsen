package client.ui.views;

import game.player.Player;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class LayoutGamePlayerbox implements Initializable {

    private static final Logger Log = LogManager.getLogger(LayoutGamePlayerbox.class);
    @FXML
    VBox vbox;
    @FXML
    VBox background1;
    @FXML
    VBox background2;
    @FXML
    VBox background3;
    @FXML
    Label playerNameLabel1;
    @FXML
    Label playerNameLabel2;
    @FXML
    Label playerNameLabel3;

    Label[] labels;
    VBox[] backgrounds;


    public void initialize(URL location, ResourceBundle resources) {
        labels = new Label[]{playerNameLabel1, playerNameLabel2, playerNameLabel3};
        backgrounds = new VBox[]{background1, background2, background3};
    }


    public void setPlayers(List<Player> players) {
        for (int i = 0; i < players.size(); i++) {
            labels[i].setText(players.get(i).getPlayerName());
            backgrounds[i].setBackground(new Background(new BackgroundFill(players.get(i).getColorAsColor(), CornerRadii.EMPTY, Insets.EMPTY)));
        }
    }
}

package client.ui.views;

import game.player.Player;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
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
    Text playerNameText1;
    @FXML
    Text playerNameText2;
    @FXML
    Text playerNameText3;

    Text[] texts;
    VBox[] backgrounds;


    public void initialize(URL location, ResourceBundle resources) {
        texts = new Text[]{playerNameText1, playerNameText2, playerNameText3};
        backgrounds = new VBox[]{background1, background2, background3};
    }


    public void setPlayers(List<Player> players) {
        for (int i = 0; i < players.size(); i++) {
            texts[i].setText(players.get(i).getPlayerName());
            backgrounds[i].setBackground(new Background(new BackgroundFill(players.get(i).getColorAsColor(), CornerRadii.EMPTY, Insets.EMPTY)));
        }
    }
}

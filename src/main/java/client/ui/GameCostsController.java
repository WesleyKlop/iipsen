package client.ui;

import game.GameStore;
import game.GameStoreProvider;
import game.actions.Action;
import game.actions.BuildRouteAction;
import game.location.ELocation;
import game.player.Player;
import game.routecards.Route;
import game.routecards.RouteType;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Duration;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.rmi.RemoteException;

import static client.UserPreferences.isColorBlind;

public class GameCostsController {
    @FXML
    StackPane rootPane;
    @FXML
    private HBox trainBox1, trainBox2, trainBox3;
    @FXML
    private Label locations;
    private Image image;

    private int currentId;

    public void initialize() {
        rootPane.setDisable(true);
        locations.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/MavenPro-Medium.ttf"), 25));
    }

    void ActivationAction(MouseEvent mouseEvent) {
        closeAndOpenAnimation(mouseEvent);
    }

    private void fillCostCard(MouseEvent mouseEvent) {
        VBox source = (VBox) mouseEvent.getSource();
        String[] information = getRouteInformation(source);
        addParts(information);
        RouteType routeType = RouteType.valueOf(information[3]);
        String type = upperCaseFirstLetter(routeType.toString());
        ELocation location1 = ELocation.valueOf(information[4]);
        ELocation location2 = ELocation.valueOf(information[5]);
        String loc1 = upperCaseFirstLetter(location1.toString());
        String loc2 = upperCaseFirstLetter(location2.toString());
        locations.setText("This " + type + " route connects " + loc1 + " to " + loc2);
        updateCurrentId(Integer.parseInt(information[6]));
        openAnimation();
    }

    private void updateCurrentId(int newId) {
        this.currentId = (newId);
    }

    private void addParts(String[] information) {
        int length = Integer.parseInt(information[0]);
        int locomotives = Integer.parseInt(information[1]);
        String cartType = information[2];

        int trainWidth = (int) Math.min((trainBox1.getMaxWidth() / Math.min(length, 4)) - ((Math.min(length, 4) - 1) * 10), 250);

        for (int i = 0; i < length; i++) {
            if (i < locomotives) {
                image = new Image(getClass().getResourceAsStream("/cards/" + isColorBlind() + "/LOCOMOTIVE.png"));
            } else {
                image = new Image(getClass().getResourceAsStream("/cards/" + isColorBlind() + "/" + cartType + ".png"));
            }
            ImageView train = new ImageView(image);
            train.setPreserveRatio(true);
            train.setFitWidth(trainWidth);
            if (i < 3) {
                trainBox1.getChildren().add(train);
            } else if (i < 6) {
                trainBox2.getChildren().add(train);
            } else {
                trainBox3.getChildren().add(train);
            }
        }
    }

    private String[] getRouteInformation(VBox source) {
        String[] information = new String[7]; //0 = Length, 1 = Locomotives, 2 = CartType, 3=RouteType, 4 = Location1, 5 = Location2, 6 = Id;
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document routesDoc = dBuilder.parse(getClass().getResourceAsStream("/string/gameRoutes.xml"));
            routesDoc.normalize();

            NodeList nlRoutes = routesDoc.getElementsByTagName("route");

            for (int i = 0; i < nlRoutes.getLength(); i++) {
                if (nlRoutes.item(i).getAttributes().getNamedItem("id").getTextContent().equalsIgnoreCase(source.getId())) {
                    Node nRoute = nlRoutes.item(i);
                    Element eRoute = (Element) nRoute;
                    information[0] = eRoute.getElementsByTagName("length").item(0).getTextContent();
                    information[1] = eRoute.getElementsByTagName("locomotive").item(0).getTextContent();
                    information[2] = eRoute.getElementsByTagName("cartType").item(0).getTextContent();
                    information[3] = eRoute.getElementsByTagName("type").item(0).getTextContent();
                    information[4] = eRoute.getElementsByTagName("location1").item(0).getTextContent();
                    information[5] = eRoute.getElementsByTagName("location2").item(0).getTextContent();
                    information[6] = eRoute.getAttribute("id");

                    return information;
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }


        return information;
    }

    private void openAnimation() {
        rootPane.setDisable(false);
        TranslateTransition transAni = new TranslateTransition(Duration.seconds(0.5), rootPane);
        transAni.setToX(-1080);
        transAni.play();
        transAni.setOnFinished(e -> rootPane.setDisable(false));
    }

    public void closeAnimation() {
        TranslateTransition transAni = new TranslateTransition(Duration.seconds(0.5), rootPane);
        transAni.setToX(0);
        transAni.play();
        transAni.setOnFinished(e -> {
            emptyBox(trainBox1);
            emptyBox(trainBox2);
            emptyBox(trainBox3);
            rootPane.setDisable(true);
        });
    }

    private void closeAndOpenAnimation(MouseEvent mouseEvent) {
        double duration = (rootPane.isDisable()) ? 0.001 : 0.5;
        TranslateTransition transAni = new TranslateTransition(Duration.seconds(duration), rootPane);
        transAni.setToX(0);
        transAni.play();
        transAni.setOnFinished(e -> {
            emptyBox(trainBox1);
            emptyBox(trainBox2);
            emptyBox(trainBox3);
            rootPane.setDisable(true);
            fillCostCard(mouseEvent);
        });
    }

    private void emptyBox(HBox box) {
        while (box.getChildren().size() > 0) {
            box.getChildren().remove(0);
        }
    }

    @FXML
    private void buildRoute() throws RemoteException {
        GameStore store = GameStoreProvider.getStore();
        Player player = store.getPlayerById(store.getPlayersTurn());
        Route route = store.getRouteStore().getRouteById(currentId);
        if (BuildRouteControle(route, player)) {
            Action buildAction = new BuildRouteAction(player, route);
            GameStoreProvider.sendAction(buildAction);
        }
    }

    private String upperCaseFirstLetter(String word) {
        word = word.toLowerCase();
        String firstLetter = word.substring(0, 1).toUpperCase();
        return firstLetter + word.substring(1);
    }

    private boolean BuildRouteControle(Route route, Player player) {
        return player.getCardStack().containsCards(route.getCostsAsCardStack()) && !route.hasOwner();
    }
}

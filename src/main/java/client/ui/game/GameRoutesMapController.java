package client.ui.game;

import client.ui.components.LocationInformation;
import client.ui.dialogs.MessagesControllerProvider;
import client.ui.factories.LocationFactory;
import client.ui.factories.RouteViewFactory;
import game.GameStore;
import game.GameStoreProvider;
import game.routecards.Route;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import util.Observer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * @author Thom
 */
public class GameRoutesMapController implements Observer<GameStore> {

    private static final Logger Log = LogManager.getLogger(GameRoutesMapController.class);
    @FXML
    Label score;
    @FXML
    private Pane mainPane, informationPane, locationPane;
    private LocationInformation locationInformation;
    @FXML
    private GameCostsController route_costsController;

    public void initialize() {
        informationPane.setPickOnBounds(false);
        locationPane.setPickOnBounds(false);
        mainPane.setPickOnBounds(false);
        RouteViewFactory routeViewFactory = new RouteViewFactory(
            getClass().getResourceAsStream("/string/gameRoutes.xml"),
            this::onRouteMouseClicked,
            this::onRouteHoverEnter,
            this::onRouteHoverExit
        );

        LocationFactory locationFactory = new LocationFactory(
            getClass().getResourceAsStream("/string/gameLocations.xml"),
            this::onLocationHoverEnter,
            this::onLocationHoverLeave
        );

        locationInformation = new LocationInformation();

        mainPane.getChildren().addAll(routeViewFactory.getRoutes());
        locationPane.getChildren().addAll(locationFactory.getLocations());
        informationPane.getChildren().add(locationInformation);

        GameStoreProvider.getInstance().addObserver(this);
    }

    private void onRouteHoverEnter(MouseEvent mE) {
        VBox source = (VBox) mE.getSource();
        for (Node child : source.getChildren()) {
            if (!(child instanceof StackPane)) {
                Log.warn("Found child in route that is not a stackPane!");
                continue;
            }
            StackPane cartStack = (StackPane) child;
            Rectangle cart = (Rectangle) cartStack.getChildren().get(0);
            cart.setStroke(Color.WHITE);
        }
    }

    private void onRouteHoverExit(MouseEvent mE) {
        VBox source = (VBox) mE.getSource();
        for (Node child : source.getChildren()) {
            if (!(child instanceof StackPane)) {
                Log.warn("Found child in route that is not a stackPane!");
                continue;
            }
            StackPane cartStack = (StackPane) child;
            Rectangle cart = (Rectangle) cartStack.getChildren().get(0);
            cart.setStroke(Color.BLACK);
        }
    }

    private int[] getLocationPosition(Circle location) {
        String id = location.getId();
        int[] position = {0, 0};

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document locDoc = builder.parse(getClass().getResourceAsStream("/string/gameLocations.xml"));
            locDoc.getDocumentElement().normalize();

            NodeList nListLoc = locDoc.getElementsByTagName("Location");
            for (int i = 0; i < nListLoc.getLength(); i++) {
                if (nListLoc.item(i).getAttributes().getNamedItem("id").getTextContent().equalsIgnoreCase(id)) {
                    Element eLoc = (Element) nListLoc.item(i);
                    position[0] = Integer.parseInt(eLoc.getElementsByTagName("x").item(0).getTextContent());
                    position[1] = Integer.parseInt(eLoc.getElementsByTagName("y").item(0).getTextContent());
                    return position;
                }
            }
        } catch (Exception e) {
            Log.error("Exception found in method getLocationPosition(): " + e.toString());
        }
        return position;
    }

    private void onLocationHoverEnter(MouseEvent mouseEvent) {
        Circle source = (Circle) mouseEvent.getSource();
        source.setStroke(Color.WHITE);
        locationInformation.show(source.getId(), getLocationPosition(source));
    }

    private void onLocationHoverLeave(MouseEvent mouseEvent) {
        Circle source = (Circle) mouseEvent.getSource();
        source.setStroke(Color.ORANGE);
        locationInformation.hide();
    }

    private void onRouteMouseClicked(MouseEvent mouseEvent) {
        MessagesControllerProvider.getMessageController().openBuildMessage(mouseEvent);
    }

    void switchColorBlind(boolean isColorBlind) {
        int opacity = isColorBlind ? 1 : 0;
        for (int i = 0; i < mainPane.getChildren().size(); i++) {
            VBox route = (VBox) mainPane.getChildren().get(i);
            for (int j = 0; j < route.getChildren().size(); j++) {
                StackPane cart = (StackPane) route.getChildren().get(j);
                ImageView routeImage = (ImageView) cart.getChildren().get(1);
                routeImage.setOpacity(opacity);
            }
        }
    }

    @Override
    public void onUpdate(GameStore store) {
        Platform.runLater(() -> {
            for (Node route : mainPane.getChildren()) {
                Route routeObj = store.getRouteStore().getRouteById(Integer.parseInt(route.getId()));
                if (!routeObj.hasOwner()) {
                    continue;
                }

                var player = store.getPlayerById(routeObj.getOwner());
                for (Node node : ((VBox) route).getChildren()) {
                    StackPane cart = (StackPane) node;
                    if (cart.getChildren().size() == 2) {
                        // 2 because of the colorblind overlay!!
                        // If it's not 2 there is probably already a rectangle on it
                        cart.getChildren().add(new Rectangle(9, 22, player.getColorAsColor()));
                    }
                }
            }
        });
    }

}

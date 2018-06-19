package client.ui;

import client.ui.components.LocationInformation;
import client.ui.factories.LocationFactory;
import client.ui.factories.RouteViewFactory;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
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

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * @author Thom
 */
public class GameRoutesMapController {

    private static final Logger Log = LogManager.getLogger(GameRoutesMapController.class);
    @FXML
    Label score;
    @FXML
    private Pane mainPane, informationPane;
    private LocationInformation locationInformation;
    @FXML
    private GameCostsController route_costsController;

    private MessagesController mCon = MessagesControllerProvider.getMessageController();



    public void initialize() {
        informationPane.setPickOnBounds(false);
        mainPane.setPickOnBounds(false);
        RouteViewFactory routeViewFactory = new RouteViewFactory(
            getClass().getResourceAsStream("/string/gameRoutes.xml"),
                this::routeOnMouseClicked,
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
        mainPane.getChildren().addAll(locationFactory.getLocations());
        informationPane.getChildren().add(locationInformation);
    }

    public Pane getMainPane() {
        return mainPane;
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

    private void routeOnMouseClicked(MouseEvent mouseEvent) {
        MessagesControllerProvider.getMessageController().openBuildMessage(mouseEvent);
    }

}
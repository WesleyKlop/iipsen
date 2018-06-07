package client.ui;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * @author Thom
 * @since 6-6-2018
 */
public class GameRoutesMapController {

    private static final Logger Log = LogManager.getLogger(GameRoutesMapController.class);

    private final int CART_LENGTH = 30;
    private final int CART_WIDTH = 13;
    private final int CART_SPACING = 1;
    @FXML
    private Pane mainPane;

    public void initialize() {
        placeRoutes();
        placeLocations();
    }

    /**
     * This method reads every single route node from "/string/gameRoutes.xml"
     * Currently manages the styling of the route as well.
     */
    private void placeRoutes() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document routesDoc = builder.parse(getClass().getResourceAsStream("/string/gameRoutes.xml"));
            routesDoc.getDocumentElement().normalize();

            NodeList nRouteList = routesDoc.getElementsByTagName("route");

            for (int j = 0; j < nRouteList.getLength(); j++) {

                Node routeNode = nRouteList.item(j);
                Element routeElement = (Element) routeNode;

                NodeList nCartList = routeElement.getElementsByTagName("cart");

                int routeX = Integer.parseInt(routeElement.getElementsByTagName("baseX").item(0).getTextContent());
                int routeY = Integer.parseInt(routeElement.getElementsByTagName("baseY").item(0).getTextContent());
                int baseRot = Integer.parseInt(routeElement.getElementsByTagName("baseRot").item(0).getTextContent());
                String id = routeElement.getAttribute("id");

                VBox route = new VBox();
                route.setId(id);
                route.setLayoutX(routeX);
                route.setLayoutY(routeY);
                route.setRotate(baseRot);
                route.setSpacing(CART_SPACING);
                route.setAlignment(Pos.CENTER);
                route.setPickOnBounds(false);
                mainPane.getChildren().add(route);

                String type = routeElement.getElementsByTagName("type").item(0).getTextContent();
                double strokeWidth = (type.equals("tunnel")) ? 3 : 1;
                double arc = (type.equals("ferry")) ? 10 : 0;

                int locomotiveAmount = Integer.decode(routeElement.getElementsByTagName("locomotive").item(0).getTextContent());

                Color routeColor = typeToColor(routeElement.getElementsByTagName("cartType").item(0).getTextContent());

                for (int i = 0; i < nCartList.getLength(); i++) {
                    Element eElement = (Element) nCartList.item(i);
                    int x = Integer.decode(eElement.getAttribute("x"));
                    int y = Integer.decode(eElement.getAttribute("y"));
                    int rot = Integer.decode(eElement.getAttribute("rot"));

                    Rectangle cart = new Rectangle();
                    if (i < locomotiveAmount) {
                        cart.setWidth(CART_WIDTH + 5);
                        cart.setArcWidth(arc + 5);
                        cart.setArcHeight(arc + 5);
                    } else {
                        cart.setWidth(CART_WIDTH);
                        cart.setArcWidth(arc);
                        cart.setArcHeight(arc);
                    }

                    cart.setHeight(CART_LENGTH);
                    cart.setTranslateX(x);
                    cart.setTranslateY(y);
                    cart.setRotate(rot);
                    cart.setFill(routeColor);
                    cart.setStroke(Color.BLACK);
                    cart.setStrokeWidth(strokeWidth);
                    cart.setOnMouseClicked(this::soutCartInformation);
                    cart.setOnMouseEntered(this::cartHoverEnter);
                    cart.setOnMouseExited(this::cartHoverExit);

                    route.getChildren().add(cart);

                }
            }


        } catch (Exception e) {
            Log.debug(e.toString());
        }
    }

    private Color typeToColor(String type) {
        String colorString = type.substring(5);
        if (colorString.equals("ANY")) {
            return Color.GRAY;
        } else {
            return Color.web(colorString);
        }
    }

    private void placeLocations() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document locDoc = builder.parse(getClass().getResourceAsStream("/string/gameLocations.xml"));
            locDoc.getDocumentElement().normalize();

            NodeList NLLocations = locDoc.getElementsByTagName("Location");
            for (int i = 0; i < NLLocations.getLength(); i++) {
                Node LocationNode = NLLocations.item(i);
                Element eLocation = (Element) LocationNode;
                int x = Integer.parseInt(eLocation.getElementsByTagName("x").item(0).getTextContent());
                int y = Integer.parseInt(eLocation.getElementsByTagName("y").item(0).getTextContent());
                String id = eLocation.getAttribute("id");
                int radius = 10;
                Circle location = new Circle(radius);
                location.setCenterX(radius / 2);
                location.setCenterY(radius / 2);
                location.setLayoutX(x);
                location.setLayoutY(y);
                location.setId(id);
                location.setFill(Color.ORANGE);
                location.setStroke(Color.ORANGE);
                location.setStrokeWidth(3);
                location.setOnMouseEntered(this::hoverLocationEnter);
                location.setOnMouseExited(this::hoverLocationExit);
                mainPane.getChildren().add(location);

            }
        } catch (Exception e) {
            Log.error("Exception Found: " + e.toString());
        }
    }

    private void hoverLocationEnter(MouseEvent mouseEvent) {
        Circle source = (Circle) mouseEvent.getSource();
        source.setStroke(Color.WHITE);
    }

    private void hoverLocationExit(MouseEvent mouseEvent) {
        Circle source = (Circle) mouseEvent.getSource();
        source.setStroke(Color.ORANGE);
    }

    private void soutCartInformation(MouseEvent mouseEvent) {
        Rectangle source = (Rectangle) mouseEvent.getSource();
        VBox parent = (VBox) source.getParent();
        soutRouteInformation(parent);
    }

    private void soutRouteInformation(VBox source) {
        String id = source.getId();

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document routesDoc = builder.parse(getClass().getResourceAsStream("/string/gameRoutes.xml"));
            routesDoc.getDocumentElement().normalize();

            NodeList nListRoutes = routesDoc.getElementsByTagName("route");
            for (int i = 0; i < nListRoutes.getLength(); i++) {
                if (nListRoutes.item(i).getAttributes().getNamedItem("id").getTextContent().equalsIgnoreCase(id)) {
                    Element eRoute = (Element) nListRoutes.item(i);

                    String Location1 = eRoute.getElementsByTagName("location1").item(0).getTextContent();
                    String Location2 = eRoute.getElementsByTagName("location2").item(0).getTextContent();
                    String length = eRoute.getElementsByTagName("length").item(0).getTextContent();
                    String locomotives = eRoute.getElementsByTagName("locomotive").item(0).getTextContent();
                    String cartType = eRoute.getElementsByTagName("cartType").item(0).getTextContent();
                    String type = eRoute.getElementsByTagName("type").item(0).getTextContent();
                    Log.debug("This route is a " + type + " connects: " + Location1 + " to " + Location2 + " with " + length + cartType + " of which " + locomotives + " a locomotive");

                    break;
                }
            }

        } catch (Exception e) {
            Log.debug(e.toString());
        }

    }

    private void cartHoverEnter(MouseEvent mouseEvent) {
        Rectangle source = (Rectangle) mouseEvent.getSource();
        VBox parent = (VBox) source.getParent();
        routeHoverEnter(parent);
    }

    private void cartHoverExit(MouseEvent mouseEvent) {
        Rectangle source = (Rectangle) mouseEvent.getSource();
        VBox parent = (VBox) source.getParent();
        routeHoverExit(parent);
    }


    private void routeHoverEnter(VBox source) {
        for (int i = 0; i < source.getChildren().size(); i++) {
            Rectangle child = (Rectangle) source.getChildren().get(i);
            child.setStroke(Color.WHITE);
        }
    }

    private void routeHoverExit(VBox source) {
        for (int i = 0; i < source.getChildren().size(); i++) {
            Rectangle child = (Rectangle) source.getChildren().get(i);
            child.setStroke(Color.BLACK);
        }
    }

}

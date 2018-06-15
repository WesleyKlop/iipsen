package client.ui.factories;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Wesley Klop
 */
public class RouteViewFactory {

    private static final Logger Log = LogManager.getLogger(RouteViewFactory.class);

    private static final int CART_LENGTH = 30;
    private static final int CART_WIDTH = 13;
    private static final int CART_SPACING = 1;

    private final InputStream routeStream;
    private final EventHandler<MouseEvent> onRouteClicked, onHoverEnter, onHoverExit;

    public RouteViewFactory(InputStream routeStream, EventHandler<MouseEvent> onRouteClicked, EventHandler<MouseEvent> onHoverEnter, EventHandler<MouseEvent> onHoverExit) {
        this.routeStream = routeStream;
        this.onRouteClicked = onRouteClicked;
        this.onHoverEnter = onHoverEnter;
        this.onHoverExit = onHoverExit;
    }

    private NodeList getRouteNodes() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(routeStream);
            doc.getDocumentElement().normalize();
            return doc.getElementsByTagName("route");
        } catch (ParserConfigurationException | IOException | SAXException e) {
            Log.error("Failed to parse route xml..", e);
        }
        return null;
    }

    public VBox[] getRoutes() {
        NodeList routeNodes = getRouteNodes();

        if (routeNodes == null) {
            Log.warn("Returning null from getRoutes because we received a null document");
            return null;
        }

        VBox[] routes = new VBox[routeNodes.getLength()];

        for (int i = 0, routeNodesLength = routeNodes.getLength(); i < routeNodesLength; i++) {
            Node routeNode = routeNodes.item(i);
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
            route.setOnMouseEntered(this.onHoverEnter);
            route.setOnMouseExited(this.onHoverExit);
            route.setOnMouseClicked(this.onRouteClicked);
            routes[i] = route;

            String type = routeElement.getElementsByTagName("type").item(0).getTextContent();
            double strokeWidth = (type.equals("TUNNEL")) ? 3 : 1;
            double arc = (type.equals("FERRY")) ? 10 : 0;

            int locomotiveAmount = Integer.parseInt(routeElement.getElementsByTagName("locomotive").item(0).getTextContent());

            Color routeColor = typeToColor(routeElement.getElementsByTagName("cartType").item(0).getTextContent());

            for (int j = 0, cartLength = nCartList.getLength(); j < cartLength; j++) {
                Element eElement = (Element) nCartList.item(j);
                int x = Integer.parseInt(eElement.getAttribute("x"));
                int y = Integer.parseInt(eElement.getAttribute("y"));
                int rot = Integer.parseInt(eElement.getAttribute("rot"));

                Rectangle cart = new Rectangle();
                if (j < locomotiveAmount) {
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

                route.getChildren().add(cart);
            }
        }

        return routes;
    }

    private Color typeToColor(String type) {
        String colorString = type.substring(5);
        if (colorString.equals("ANY")) {
            return Color.GRAY;
        } else {
            return Color.web(colorString);
        }
    }
}

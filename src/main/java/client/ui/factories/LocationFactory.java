package client.ui.factories;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
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
public class LocationFactory {
    private static final Logger Log = LogManager.getLogger(LocationFactory.class);

    private static final int LOCATION_RADIUS = 10;
    private static final int LOCATION_STROKE_WIDTH = 3;

    private final InputStream locationStream;
    private final EventHandler<MouseEvent> onLocationClick, onLocationHoverEnter, onLocationHoverLeave;

    public LocationFactory(InputStream locationStream, EventHandler<MouseEvent> onLocationClick, EventHandler<MouseEvent> onLocationHoverEnter, EventHandler<MouseEvent> onLocationHoverLeave) {
        this.locationStream = locationStream;

        this.onLocationHoverLeave = onLocationHoverLeave;
        this.onLocationClick = onLocationClick;
        this.onLocationHoverEnter = onLocationHoverEnter;
    }

    private NodeList getLocationNodes() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(getClass().getResourceAsStream("/string/gameLocations.xml"));
            doc.getDocumentElement().normalize();
            return doc.getElementsByTagName("Location");
        } catch (ParserConfigurationException | IOException | SAXException e) {
            Log.error("Failed to parse location xml..", e);
        }
        return null;
    }

    public Circle[] getLocations() {
        NodeList locationNodes = getLocationNodes();

        if (locationNodes == null) {
            Log.warn("Returning null from getLocations because we received a null document");
            return null;
        }

        Circle[] locations = new Circle[locationNodes.getLength()];

        for (int i = 0, length = locationNodes.getLength(); i < length; i++) {
            Element eLocation = (Element) locationNodes.item(i);

            int x = Integer.parseInt(eLocation.getElementsByTagName("x").item(0).getTextContent());
            int y = Integer.parseInt(eLocation.getElementsByTagName("y").item(0).getTextContent());
            String id = eLocation.getAttribute("id");

            Circle location = new Circle(LOCATION_RADIUS);
            location.setCenterX(LOCATION_RADIUS / 2);
            location.setCenterY(LOCATION_RADIUS / 2);
            location.setLayoutX(x);
            location.setLayoutY(y);
            location.setId(id);
            location.setFill(Color.GOLD);
            location.setStroke(Color.ORANGE);
            location.setStrokeWidth(LOCATION_STROKE_WIDTH);

            location.setOnMouseEntered(this.onLocationHoverEnter);
            location.setOnMouseExited(this.onLocationHoverLeave);
            location.setOnMouseClicked(this.onLocationClick);

            locations[i] = location;
        }

        return locations;
    }
}

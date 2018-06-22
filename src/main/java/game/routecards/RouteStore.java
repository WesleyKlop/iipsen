package game.routecards;

import game.cards.CardType;
import game.location.ELocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RouteStore implements Serializable {

    private transient static final Logger Log = LogManager.getLogger(RouteStore.class);
    private List<Route> routeList = new ArrayList<>();

    public RouteStore() {
        if (routeList.isEmpty()) {
            InputStream stream = getClass().getResourceAsStream("/string/gameRoutes.xml");
            generateRoutes(stream);
        }
    }

    private void generateRoutes(InputStream stream) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document rDoc = builder.parse(stream);

            NodeList routesList = rDoc.getElementsByTagName("route");

            for (int i = 0; i < routesList.getLength(); i++) {
                Element eRoute = (Element) routesList.item(i);

                int id = Integer.parseInt(eRoute.getAttribute("id"));
                int length = Integer.parseInt(eRoute.getElementsByTagName("length").item(0).getTextContent());
                int locomotives = Integer.parseInt(eRoute.getElementsByTagName("locomotive").item(0).getTextContent());
                ELocation loc1 = ELocation.valueOf(eRoute.getElementsByTagName("location1").item(0).getTextContent());
                ELocation loc2 = ELocation.valueOf(eRoute.getElementsByTagName("location2").item(0).getTextContent());
                RouteType routeType = RouteType.valueOf(eRoute.getElementsByTagName("type").item(0).getTextContent());
                CardType cartType = CardType.valueOf(eRoute.getElementsByTagName("cartType").item(0).getTextContent());

                Route route = new Route(id, length, locomotives, loc1, loc2, cartType, routeType);
                addRoute(route);
            }

        } catch (Exception e) {
            Log.error("Exception found: ", e);
        }
    }

    private void addRoute(Route route) {
        routeList.add(route);
    }

    public Route getRouteById(int id) {
        for (Route route : routeList) {
            if (route.getId() == id) {
                return route;
            }
        }
        return null;
    }
}

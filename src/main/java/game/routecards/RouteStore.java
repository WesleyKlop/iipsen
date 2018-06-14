package game.routecards;

import game.cards.CardType;
import game.location.ELocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class RouteStore {

    private static final Logger Log = LogManager.getLogger(RouteStore.class);
    private List<Route> routeList = new ArrayList<>();
    private InputStream stream = getClass().getResourceAsStream("/string/gameRoutes.xml");

    private int id;
    private int length;
    private int locomotives;
    private ELocation loc1;
    private ELocation loc2;
    private CardType cartType;
    private RouteType routeType;
    private Route route;

    public RouteStore() {
        generateRoutes();
    }

    private void generateRoutes() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document rDoc = builder.parse(stream);

            NodeList routesList = rDoc.getElementsByTagName("route");

            for (int i = 0; i < routesList.getLength(); i++) {
                Node nRoute = routesList.item(i);
                Element eRoute = (Element) nRoute;

                id = Integer.parseInt(eRoute.getAttribute("id"));
                length = Integer.parseInt(eRoute.getElementsByTagName("length").item(0).getTextContent());
                locomotives = Integer.parseInt(eRoute.getElementsByTagName("locomotive").item(0).getTextContent());
                loc1 = ELocation.valueOf(eRoute.getElementsByTagName("location1").item(0).getTextContent());
                loc2 = ELocation.valueOf(eRoute.getElementsByTagName("location2").item(0).getTextContent());
                routeType = RouteType.valueOf(eRoute.getElementsByTagName("type").item(0).getTextContent());
                cartType = CardType.valueOf(eRoute.getElementsByTagName("cartType").item(0).getTextContent());

                route = new Route(id, length, locomotives, loc1, loc2, cartType, routeType);
                addRoute(route);
            }

        } catch (Exception e) {
            Log.error("Exception found: " + e.toString());
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

package client.ui.controllers;

import game.GameStore;
import game.GameStoreProvider;
import game.routecards.RouteCard;
import game.routecards.RouteCardStackBank;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import util.Observable;

public class RouteCardController {
    public RouteCardStackBank bank = new RouteCardStackBank();
    @FXML
    ImageView RC0, RC1, RC2, RC3, RC4;
    Image routecard;
    private Observable<GameStore> storeObervable = GameStoreProvider.getInstance();
    private GameStore store = GameStoreProvider.getStore();
    private RouteCard[] routecards = new RouteCard[5];

    public void initialize() {
        FillRouteCards();
    }

    private void FillRouteCards() {
        for (int i = 0; i < routecards.length; i++) {
            routecards[i] = bank.getRandomRouteCard();
            String start = String.valueOf(routecards[i].getStart());
            String eind = String.valueOf(routecards[i].getEnd());

            String numberAsString = Integer.toString(i);
            String routecardtest = "RC" + i;
            routecard = new Image("routecards/nc-" + start + "-" + eind + ".png");
            switch (routecardtest) {
                case "RC0":
                    RC0.setImage(routecard);
                case "RC1":
                    RC1.setImage(routecard);
                case "RC2":
                    RC2.setImage(routecard);
                case "RC3":
                    RC3.setImage(routecard);
                case "RC4":
                    RC4.setImage(routecard);
            }


        }

        //      routecard = new Image("routecards/nc-alborg-norrkoping.png");
        //      RC0.setImage(routecard);
        //      RandomRouteCard();
        //      RC1.setImage(routecard);
        //     RandomRouteCard();
        //      RC2.setImage(routecard);
        //      RandomRouteCard();
        //      RC3.setImage(routecard);
        //      RandomRouteCard();
        //      RC4.setImage(routecard);
    }

    public void isClicked(MouseEvent mouseEvent) {

    }
}

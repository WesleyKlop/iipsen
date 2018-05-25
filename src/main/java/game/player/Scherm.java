package game.player;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Scherm extends Application {

    protected static TextField naamfield = new TextField();
    protected static Button btn = new Button("Doorgaan");
    protected static Circle figuur = new Circle(5);
    static Circle rood = new Circle(5);
    static Circle blauw = new Circle(5);
    static Circle groen = new Circle(5);
    static Circle geel = new Circle(5);
    static Circle bruin = new Circle(5);
    static Circle Oranje = new Circle(5);
    static Circle Roze = new Circle(5);
    static Circle Paars = new Circle(5);
    static Circle Grijs = new Circle(5);
    static Circle Zwart = new Circle(5);
    static Circle Aquamarine = new Circle(5);
    static Circle Greenyellow = new Circle(5);
    static Circle Teal = new Circle(5);
    static Color kleur;
    private FlowPane flowPane = new FlowPane(Orientation.HORIZONTAL);
    private Label naamlabel = new Label("Naam: ");
    private Label kleurlabel = new Label("Uw kleur is: ");
    private Label kleurlabelkeuze = new Label("Maak uw keuze: ");
    private HBox hbox = new HBox(1);
    private HBox hbox1 = new HBox(1);
    private HBox hbox2 = new HBox(1);
    private HBox hbox3 = new HBox(1);
    private VBox vbox = new VBox(0);

    public static void main(String[] args) {
        launch(args);
    }

    public void maakInhoudVoorScherm() {
        fillFigure.vullen();
        kleurKlikken.Klik();
        sendData.send();

        hbox.getChildren().addAll(naamlabel, naamfield);
        hbox1.getChildren().addAll(kleurlabel, figuur);
        hbox2.getChildren().addAll(btn);
        hbox3.getChildren().addAll(kleurlabelkeuze, rood, blauw, groen, geel, bruin, Oranje, Roze, Paars, Grijs, Zwart, Aquamarine, Greenyellow, Teal);
        vbox.getChildren().addAll(hbox, hbox3, hbox1, hbox2);
        flowPane.getChildren().addAll(vbox);
        flowPane.setPadding(new Insets(5, 5, 5, 5));
        flowPane.setAlignment(Pos.CENTER);
    }

    @Override
    public void start(Stage primaryStage) {
        maakInhoudVoorScherm();

        Scene scene = new Scene(flowPane, 600, 500);
        primaryStage.setTitle("Speler");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
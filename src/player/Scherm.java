package player;

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

	private FlowPane flowPane = new FlowPane(Orientation.HORIZONTAL);
	private Label naamlabel = new Label("Naam:");
	private Label kleurlabelr = new Label("r:");
	private Label kleurlabelg = new Label("g:");
	private Label kleurlabelb = new Label("b:");
	private HBox hbox = new HBox(1);
	private HBox hbox1 = new HBox(1);
	private HBox hbox2 = new HBox(1);
	private HBox hbox3 = new HBox(1);
	private VBox vbox = new VBox(0);
	private TextField naamfield = new TextField();
	protected static TextField kleurfieldr = new TextField("0");
	protected static TextField kleurfieldg = new TextField("0");
	protected static TextField kleurfieldb = new TextField("255");
	private Button btn= new Button("Doorgaan");
	protected static Circle figuur = new Circle(5);
	private Button btnupdate= new Button("Update");
	
	public void maakInhoudVoorScherm() {
		
		Circle rood = new Circle(5);
		Circle blauw = new Circle(5);
		Circle groen = new Circle(5);
		Circle geel = new Circle(5);
		Circle bruin = new Circle(5);
		rood.setFill(Color.RED);
		blauw.setFill(Color.BLUE);
		groen.setFill(Color.GREEN);
		geel.setFill(Color.YELLOW);
		bruin.setFill(Color.BROWN);
		
		rood.setOnMouseClicked(e -> {
		    RGB.setRGB("255","0","0");
		    Kleurfiguur.setKleurfiguur();
		});
		
		blauw.setOnMouseClicked(e -> {
			RGB.setRGB("0","0","255");
			Kleurfiguur.setKleurfiguur();
		});
		
		groen.setOnMouseClicked(e -> {
			RGB.setRGB("0","120","0");
			Kleurfiguur.setKleurfiguur();
		});
		
		geel.setOnMouseClicked(e -> {
			RGB.setRGB("255","255","0");
			Kleurfiguur.setKleurfiguur();
		});
		
		bruin.setOnMouseClicked(e -> {
			RGB.setRGB("165","42","42");
			Kleurfiguur.setKleurfiguur();
		});
		
		String kleurwaarde = "rgb(" + kleurfieldr.getText().trim() + "," + kleurfieldg.getText().trim() + "," + kleurfieldb.getText().trim() + ")";
		Color c = Color.web(kleurwaarde);
		figuur.setFill(c);
		btnupdate.setOnAction(e -> {
			Kleurfiguur.setKleurfiguur();
		});
		btn.setOnAction(e -> {
            String kleur = "rgb(" + kleurfieldr.getText().trim() + "," + kleurfieldg.getText().trim() + "," + kleurfieldb.getText().trim() + ")";
			String naam = naamfield.getText().trim();
            spelerLijst.voegSpelertoe(naam,kleur);
            System.out.println("Welkom: " + spelerLijst.spelerLijst[0].naam + " " + "jouw kleur is: " + spelerLijst.spelerLijst[0].kleur );
		});
		kleurfieldr.setPrefWidth(40);
		kleurfieldg.setPrefWidth(40);
		kleurfieldb.setPrefWidth(40);
	    hbox.getChildren().addAll(naamlabel, naamfield);
	    hbox1.getChildren().addAll(kleurlabelr, kleurfieldr,kleurlabelg,kleurfieldg, kleurlabelb, kleurfieldb, figuur);
	    hbox2.getChildren().addAll(btnupdate, btn);
	    hbox3.getChildren().addAll(rood, blauw, groen, geel, bruin);
	    vbox.getChildren().addAll(hbox,hbox3,hbox1,hbox2);
		flowPane.getChildren().addAll(vbox);
		flowPane.setPadding(new Insets(5,5,5,5));
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

	public static void main(String[] args) {
		launch(args);
	}
}
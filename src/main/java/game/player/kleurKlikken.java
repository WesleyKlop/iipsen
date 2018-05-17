package game.player;

import javafx.scene.paint.Color;

public class kleurKlikken extends Scherm{
	
	public static void Klik() {
	rood.setOnMouseClicked(e -> {
		kleur = Color.RED;
	    Kleurfiguur.setKleurfiguur();
	});
	
	blauw.setOnMouseClicked(e -> {
		kleur = Color.BLUE;
		Kleurfiguur.setKleurfiguur();
	});
	
	groen.setOnMouseClicked(e -> {
		kleur = Color.GREEN;
		Kleurfiguur.setKleurfiguur();
	});
	
	geel.setOnMouseClicked(e -> {
		kleur = Color.YELLOW;
		Kleurfiguur.setKleurfiguur();
	});
	
	bruin.setOnMouseClicked(e -> {
		kleur = Color.BROWN;
		Kleurfiguur.setKleurfiguur();
	});
	
	Oranje.setOnMouseClicked(e -> {
		kleur = Color.ORANGE;
		Kleurfiguur.setKleurfiguur();
	});
	
	Roze.setOnMouseClicked(e -> {
		kleur = Color.PINK;
		Kleurfiguur.setKleurfiguur();
	});
	
	Paars.setOnMouseClicked(e -> {
		kleur = Color.PURPLE;
		Kleurfiguur.setKleurfiguur();
	});
	
	Grijs.setOnMouseClicked(e -> {
		kleur = Color.GREY;
		Kleurfiguur.setKleurfiguur();
	});
	
	Zwart.setOnMouseClicked(e -> {
		kleur = Color.BLACK;
		Kleurfiguur.setKleurfiguur();
	});

	Aquamarine.setOnMouseClicked(e -> {
		kleur = Color.AQUA;
		Kleurfiguur.setKleurfiguur();
	});
	
	Greenyellow.setOnMouseClicked(e -> {
		kleur = Color.GREENYELLOW;
		Kleurfiguur.setKleurfiguur();
	});
	
	Teal.setOnMouseClicked(e -> {
		kleur = Color.TEAL;
		Kleurfiguur.setKleurfiguur();
	});
	}
}

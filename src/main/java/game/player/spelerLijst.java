package game.player;

import javafx.scene.paint.Color;

public class spelerLijst{
	static Speler[] spelerLijst = new Speler[3];
    
	public static void voegSpelertoe(String naam, Color kleur) {
		spelerLijst[0] = new Speler(naam,kleur);
	}
}

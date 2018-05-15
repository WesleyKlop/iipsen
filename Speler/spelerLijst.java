package game;

public class spelerLijst{
	static Speler[] spelerLijst = new Speler[3];
    
	public static void voegSpelertoe(String naam, String kleur) {
		spelerLijst[0] = new Speler(naam,kleur);
	}
}

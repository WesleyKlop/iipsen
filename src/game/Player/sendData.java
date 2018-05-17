package game;

public class sendData extends Scherm{

	public static void send() {
		btn.setOnAction(e -> {
			String naam = naamfield.getText().trim();
            spelerLijst.voegSpelertoe(naam,kleur);
            System.out.println("Welkom: " + spelerLijst.spelerLijst[0].naam + " " + "jouw kleur is: " + spelerLijst.spelerLijst[0].kleur );
		});
		
	}

}

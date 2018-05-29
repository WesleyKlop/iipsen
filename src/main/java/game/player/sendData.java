package game.player;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class sendData extends Scherm {
    private static final Logger Log = LogManager.getLogger(sendData.class);

    public static void send() {
        btn.setOnAction(e -> {
            String naam = naamfield.getText().trim();
            spelerLijst.voegSpelertoe(naam, kleur);
            Log.debug("Welkom: " + spelerLijst.spelerLijst[0].naam + " jouw kleur is: " + spelerLijst.spelerLijst[0].kleur);
        });

    }

}

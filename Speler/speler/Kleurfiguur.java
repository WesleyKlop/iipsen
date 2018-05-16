package speler;

import javafx.scene.paint.Color;

public class Kleurfiguur extends Scherm {
	
	public static void setKleurfiguur() {
	String kleurwaarde = "rgb(" + kleurfieldr.getText().trim() + "," + kleurfieldg.getText().trim() + "," + kleurfieldb.getText().trim() + ")";
	Color c = Color.web(kleurwaarde);
	figuur.setFill(c);
 }
}

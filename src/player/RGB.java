package player;

public class RGB extends Scherm {
	String x;
	String y;
	String z;
	
	public RGB(String x, String y, String z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public static void setRGB(String x, String y, String z) {
	    kleurfieldr.setText(x);
	    kleurfieldg.setText(y);
	    kleurfieldb.setText(z);
	}
}

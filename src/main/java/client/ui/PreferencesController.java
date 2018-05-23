package client.ui;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class PreferencesController {

    // Declare Circles
    public Button submitPreferencesButton;
    public TextField nameField;
    public Circle yellowButton;
    public Circle orangeButton;
    public Circle redButton;
    public Circle redPurpleButton;
    public Circle purpleButton;
    public Circle blueButton;
    public Circle greenButton;
    public Circle whiteButton;
    public Circle blackButton;
    public Circle grayButton;
    public Circle pinkButton;
    public Circle lgreenButton;
    public Circle displayCircle;

    public void submitName(){ submitPreferencesButton.setText(nameField.getText()); }


    // Click methods called from Preferences.FXML
    public void setColorYellow(){ setCircleColor(yellowButton); }
    public void setColorOrange(){ setCircleColor(orangeButton); }
    public void setColorRed(){ setCircleColor(redButton); }
    public void setColorRedPurple(){ setCircleColor(redPurpleButton); }
    public void setColorPurple(){ setCircleColor(purpleButton); }
    public void setColorBlue(){ setCircleColor(blueButton); }
    public void setColorGreen(){ setCircleColor(greenButton); }
    public void setColorWhite(){ setCircleColor(whiteButton); }
    public void setColorBlack(){ setCircleColor(blackButton); }
    public void setColorGray(){ setCircleColor(grayButton); }
    public void setColorPink(){ setCircleColor(pinkButton); }
    public void setColorLGreen(){ setCircleColor(lgreenButton); }

    // Click effect
    public void setCircleColor(Circle circle){
        displayCircle.setFill(circle.getFill());
    }

    // Hover methods called from Preferences.FXML
    public void yellowEnterHover(){ hoverEnterEffect(yellowButton); }
    public void yellowLeaveHover(){ hoverLeaveEffect(yellowButton); }
    public void orangeEnterHover(){ hoverEnterEffect(orangeButton); }
    public void orangeLeaveHover(){ hoverLeaveEffect(orangeButton); }
    public void redEnterHover(){ hoverEnterEffect(redButton); }
    public void redLeaveHover(){ hoverLeaveEffect(redButton); }
    public void redPurpleEnterHover(){ hoverEnterEffect(redPurpleButton); }
    public void redPurpleLeaveHover(){ hoverLeaveEffect(redPurpleButton); }
    public void purpleEnterHover(){ hoverEnterEffect(purpleButton); }
    public void purpleLeaveHover(){ hoverLeaveEffect(purpleButton); }
    public void blueEnterHover(){ hoverEnterEffect(blueButton); }
    public void blueLeaveHover(){ hoverLeaveEffect(blueButton); }
    public void greenEnterHover(){ hoverEnterEffect(greenButton); }
    public void greenLeaveHover(){ hoverLeaveEffect(greenButton); }
    public void whiteEnterHover(){ hoverEnterEffect(whiteButton); }
    public void whiteLeaveHover(){ hoverLeaveEffect(whiteButton); }
    public void blackEnterHover(){ hoverEnterEffect(blackButton); }
    public void blackLeaveHover(){ hoverLeaveEffect(blackButton); }
    public void grayEnterHover(){ hoverEnterEffect(grayButton); }
    public void grayLeaveHover(){ hoverLeaveEffect(grayButton); }
    public void pinkEnterHover(){ hoverEnterEffect(pinkButton); }
    public void pinkLeaveHover(){ hoverLeaveEffect(pinkButton); }
    public void lgreenEnterHover(){ hoverEnterEffect(lgreenButton); }
    public void lgreenLeaveHover(){ hoverLeaveEffect(lgreenButton); }

    // Hover effects
    private void hoverEnterEffect(Circle circle){
        circle.setStroke(Color.YELLOW);
    }
    private void hoverLeaveEffect(Circle circle){
        circle.setStroke(Color.BLACK);
        circle.setStrokeWidth(1);
    }

}

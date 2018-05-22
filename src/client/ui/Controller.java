package client.ui;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.shape.Circle;

public class Controller {

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

    public void setColorYellow(){
        displayCircle.setFill(yellowButton.getFill());
    }
    public void setColorOrange(){
        displayCircle.setFill(orangeButton.getFill());
    }
    public void setColorRed(){
        displayCircle.setFill(redButton.getFill());
    }
    public void setColorRedPurple(){
        displayCircle.setFill(redPurpleButton.getFill());
    }
    public void setColorPurple(){
        displayCircle.setFill(purpleButton.getFill());
    }
    public void setColorBlue(){
        displayCircle.setFill(blueButton.getFill());
    }
    public void setColorGreen(){
        displayCircle.setFill(greenButton.getFill());
    }
    public void setColorWhite(){
        displayCircle.setFill(whiteButton.getFill());
    }
    public void setColorBlack(){
        displayCircle.setFill(blackButton.getFill());
    }
    public void setColorGray(){
        displayCircle.setFill(grayButton.getFill());
    }
    public void setColorPink(){
        displayCircle.setFill(pinkButton.getFill());
    }
    public void setColorLGreen(){
        displayCircle.setFill(lgreenButton.getFill());
    }

}

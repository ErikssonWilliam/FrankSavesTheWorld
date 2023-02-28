package myGraphics;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import myLogics.MainMenu;

public class TextBG extends VBox {


	public void showcase(String myText, MainMenu mM) {

		TextGround text = new TextGround(myText);	
		ReturnButton rB = new ReturnButton(mM);
		
		this.getChildren().add(text);
		this.getChildren().add(rB);
		
		mM.getStage().setScene(new Scene(this));
	}


}

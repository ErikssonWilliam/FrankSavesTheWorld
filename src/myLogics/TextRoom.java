package myLogics;

import java.io.FileNotFoundException;

import javafx.scene.layout.VBox;
import myGraphics.ReturnButton;
import myGraphics.TextGround;

public class TextRoom extends VBox{
	
	public TextRoom(Model model, String myText) throws FileNotFoundException {

    	this.setStyle("-fx-background-color: #f2ca01;");
    	
		    ReturnButton rB = new ReturnButton(model);
			TextGround text = new TextGround(myText);		
			
			this.getChildren().add(text);
			this.getChildren().add(rB);
		}	

}

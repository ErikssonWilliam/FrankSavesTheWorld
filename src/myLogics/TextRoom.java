package myLogics;

import java.io.FileNotFoundException;

import javafx.scene.layout.VBox;
import myGraphics.ReturnButton;
import myGraphics.TextGround;
import myGraphics.emptyRoom;

public class TextRoom extends VBox {

	public TextRoom(Model model, String myText, int fontSize) throws FileNotFoundException {

		this.setStyle("-fx-background-color: #f2ca01;");

		ReturnButton rB = new ReturnButton(model);
		TextGround text = new TextGround(model, myText, fontSize);
		emptyRoom eR = new emptyRoom(model);

		this.getChildren().add(text);
		this.getChildren().add(rB);
		this.getChildren().add(eR);
	}

}

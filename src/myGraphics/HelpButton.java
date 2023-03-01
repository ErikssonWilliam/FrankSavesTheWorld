package myGraphics;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import myGraphics.Frame;
import myGraphics.TextButton;
import myLogics.Model;
import myLogics.stateTextRoom;

public class HelpButton extends TextButton {

	private String myText;
	private stateTextRoom textRoom;
	private GraphicsContext gc = getGraphicsContext2D();
	
	public HelpButton(Model model) throws FileNotFoundException {
		super(model);
		
		Image logo = new Image(new FileInputStream("/home/wiler441/Documents/Frank_Pictures/helpbutton.png"));
		getGraphicsContext2D().drawImage(logo, 0, 5, getWidth(), getHeight());
	
		
		myText = "I need Helf.";
			
		setOnMouseClicked(event -> {
			textRoom = new stateTextRoom(model, myText);

			model.changeState(textRoom);

			Frame frame = new Frame(model);
			model.getMain().setScene(new Scene(frame));
			
			});

	}

}

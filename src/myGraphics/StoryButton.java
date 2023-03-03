package myGraphics;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import myLogics.MainMenu;
import myLogics.Model;
import myLogics.stateTextRoom;

public class StoryButton extends TextButton {
private String myText;
private stateTextRoom textRoom;
private GraphicsContext gc = getGraphicsContext2D();

public StoryButton(Model model) throws FileNotFoundException {
	super(model);

	Image logo = new Image(new FileInputStream("/home/wiler441/Documents/tdde10_project/Frank_Pictures/storybutton.png"));
	getGraphicsContext2D().drawImage(logo, 0, 0, getWidth(), getHeight());

	myText = "The Story about Frank begins in 2000, when\nthe millenia passed and ...";
		
	setOnMouseClicked(event -> {
		textRoom = new stateTextRoom(model, myText);

		model.changeState(textRoom);


		Frame frame = new Frame(model);
		model.getMain().setScene(new Scene(frame));
		
		});
	
}
}

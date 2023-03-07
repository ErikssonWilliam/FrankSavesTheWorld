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

	getGraphicsContext2D().drawImage(model.getPictures().getStoryButton(), 0, 0, getWidth(), getHeight());

	myText = "The Story of Frank begins as far back\n"
			+ "as the 1980's. A little boy named Frank"
			+ "\n(obviously) started learning how "
			+ "\nto optimize his racecar-toys to be as fast"
			+ "\nas possible through primal machine learning."
			+ "\n 15 years later the Corson Intelligence Agency"
			+ "\nhires Frank, now the most optimized person"
			+ "\non earth, to steal back nuclear codes from the"
			+ "\nevil corporation EvilCorp. "
			+ "\nThis is Frank... Saves the world!?";
		
	setOnMouseClicked(event -> {
		textRoom = new stateTextRoom(model, myText);

		model.changeState(textRoom);
		Frame frame = new Frame(model);
		model.getMain().setScene(new Scene(frame));
		
		});
	
}
}

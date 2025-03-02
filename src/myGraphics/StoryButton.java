package myGraphics;

import java.io.FileNotFoundException;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import myLogics.Model;
import myLogics.TextRoom;

/**
 * Writes and activates the story in the textroom when clicked in the menu
 * @author wiler441
 */
public class StoryButton extends Button {
	private String myText;
	private int fontSize;

	public StoryButton(Model model) throws FileNotFoundException {
		super(model);

		getGraphicsContext2D().drawImage(model.getPictures().getStoryButton(), 0, 0, getWidth(), getHeight());

		myText = "The Story of Frank begins as far back\n" + "as the 1980's. A little boy named Frank"
				+ "\n(obviously) started learning how " + "\nto optimize his racecar-toys to be as fast"
				+ "\nas possible through primal machine learning." + "\n 15 years later the Corson Intelligence Agency"
				+ "\nhires Frank, now the most optimized person" + "\non earth, to steal back nuclear codes from the"
				+ "\nevil corporation EvilCorp. " + "\nThis is Frank... Saves the world!?";

		fontSize = 40;
		setOnMouseClicked(event -> {
			HBox frame = new HBox();
			try {
				frame.getChildren().add(new TextRoom(model, myText, fontSize));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			model.getMain().setScene(new Scene(frame));	
		});
	}
}
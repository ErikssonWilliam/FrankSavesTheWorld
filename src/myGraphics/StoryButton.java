package myGraphics;

import java.io.FileNotFoundException;
import javafx.scene.Scene;
import myLogics.Model;
import myLogics.stateTextRoom;

public class StoryButton extends Button {
	private String myText;
	private stateTextRoom stateTextRoom;
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
			stateTextRoom = new stateTextRoom(model, myText, fontSize);

			model.changeState(stateTextRoom);
			Frame frame = new Frame(model);
			model.getMain().setScene(new Scene(frame));

		});

	}
}

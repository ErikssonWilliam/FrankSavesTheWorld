package myGraphics;

import java.io.FileNotFoundException;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.HBox;
import myLogics.Model;
import myLogics.TextRoom;

/**
 * Writes and activates the help-instructions in the textroom when clicked in the menu
 * @author wiler441
 */
public class HelpButton extends Button {

	private String myText;
	private GraphicsContext gc = getGraphicsContext2D();
	private int fontSize;

	public HelpButton(Model model) throws FileNotFoundException {
		super(model);

		gc.drawImage(model.getPictures().getHelpButton(), 0, 0, getWidth(), getHeight());

		this.myText = "Welcome to Frank Saves the World! " + "\nHere are some instructions:"
				+ "\nWhen in game Frank can move around by using the arrow key"
				+ "\nYou can also return to the main menu by pressing the escape key.\n"
				+ "The aim of the game is to make Frank pick up the nuclear key. \n"
				+ "*The Items (EMP & nuclear key) can be picked up by pressing 'E'. \n"
				+ "When Frank has the nuclear key,\n" + " he needs to escape by going to the finishline. \n"
				+ "There are two types of enemies, guards and security cameras. \n"
				+ "Both enemies can see Frank if he touches their flash (yellow light). \n"
				+ "If the enemies discover Frank, it's game over! \n"
				+ "There are two types of power ups which disrupts the enemies. \n"
				+ "- EMP, which turns of the guards flashlights for a limited time .\n"
				+ "When EMP has been pick up, it can be used one time by pressing 'U'. \n"
				+ "- Controlpanel which turns of the security camera for the rest of the game. \n"
				+ "The ControlPanel gets activated by standing right in front of it. \n"
				+ "There are two levels for Frank to complete. \n"
				+ "If Frank completes the first level, he moves on to the second level. \n"
				+ "If Frank completes the second level, \n"
				+ "you win the game and will recieve a score (your total time). \n"
				+ "If the time is among the best, it will be placed on the high scoreboard.\n"
				+ "Good luck in the attempt to make Frank Save the World!";
		fontSize = 27;

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

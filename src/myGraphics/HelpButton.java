package myGraphics;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import myGraphics.Frame;
import myGraphics.Button;
import myLogics.Model;
import myLogics.stateTextRoom;

public class HelpButton extends Button {

	private String myText;
	private stateTextRoom stateTextRoom;
	private GraphicsContext gc = getGraphicsContext2D();
	private int fontSize;

	public HelpButton(Model model) throws FileNotFoundException {
		super(model);
		
		getGraphicsContext2D().drawImage(model.getPictures().getHelpButton(), 0, 0, getWidth(), getHeight());
	
		this.myText = "Welcome to Frank Saves the World! " + "\nHere are some instructions:"
				+ "\nWhen in game Frank can move around by using the arrow key"
				+ "\nYou can also return to the main menu by pressing the escape key."
				+ "The aim of the game is to make Frank pick up the nuclear key. \n"
				+ "*The Items (EMP & nuclear key) can be picked up by pressing 'E'. \n"
				+ "When Frank has the nuclear key, he needs to escape by going to the finishline. \n"
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
				+ "If Frank completes the second level, you win the game and will recieve a score (your total time). \n"
				+ "If the time is among the best, it will be placed on the high scoreboard.\n"
				+ "Good luck in the attempt to make Frank Save the World!";
			fontSize = 30;
			
		setOnMouseClicked(event -> {
			stateTextRoom = new stateTextRoom(model, myText, fontSize);

			model.changeState(stateTextRoom);

			Frame frame = new Frame(model);
			model.getMain().setScene(new Scene(frame));
			
			});

	}

}

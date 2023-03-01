package myGraphics;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import myGraphics.Frame;
import myGraphics.TextButton;
import myLogics.Model;
import myLogics.statePlay;

public class PlayButton extends TextButton {

	private String code;
	private statePlay playRoom;
	private Boolean inGame = false;

	public PlayButton(Model model) throws FileNotFoundException {
		super(model);
		Image logo = new Image(new FileInputStream("/home/wiler441/Documents/Frank_Pictures/playbutton.png"));
		getGraphicsContext2D().drawImage(logo, 0, 5, getWidth(), getHeight());

		
		setOnMouseClicked(event -> {

	    	playRoom = new statePlay(model);
			model.changeState(playRoom);
			Frame frame = new Frame(model);
			Scene scene = new Scene(frame);
			model.getMain().setScene(scene);
			playRoom.keyIntake();
		});

	}
}

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
	private statePlay playstate;
	private Boolean inGame = false;

	public PlayButton(Model model) throws FileNotFoundException {
		super(model);
		Image logo = new Image(new FileInputStream("/home/wiler441/Documents/Frank_Pictures/playbutton.png"));
		getGraphicsContext2D().drawImage(logo, 0, 0, getWidth(), getHeight());

		
		setOnMouseClicked(event -> {

	    	playstate = new statePlay(model);
			model.changeState(playstate);
			Frame frame = new Frame(model);
			Scene scene = new Scene(frame);
			model.getMain().setScene(scene);
			playstate.getPr().StartNewGame(); //Skapa vårat bana 
			//playstate.update();
			playstate.keyIntake();
		});

	}
}

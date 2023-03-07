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
		getGraphicsContext2D().drawImage(model.getPictures().getPlayButton(), 0, 0, getWidth(), getHeight());

		
		setOnMouseClicked(event -> {

	    	playstate = new statePlay(model);
			model.changeState(playstate);
			Frame frame = new Frame(model);
			Scene scene = new Scene(frame);
			model.getMain().setScene(scene);
			
			playstate.initialize(); //Skapa vårat bana 
			playstate.update();
			playstate.keyIntake();
		});

	}
}

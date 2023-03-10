package myGraphics;

import java.io.FileNotFoundException;
import javafx.scene.Scene;
import myLogics.Model;
import myLogics.statePlay;

/**
 * Starts the game when clicked
 * @author wiler441
 */
public class PlayButton extends Button {

	private statePlay playstate;

	public PlayButton(Model model) throws FileNotFoundException {
		super(model);
		getGraphicsContext2D().drawImage(model.getPictures().getPlayButton(), 0, 0, getWidth(), getHeight());

		setOnMouseClicked(event -> {

			playstate = new statePlay(model);
			model.changeState(playstate);
			Frame frame = new Frame(model);
			Scene scene = new Scene(frame);
			model.getMain().setScene(scene);

			playstate.initialize(); 
			playstate.update();
			playstate.keyIntake();
		});
	}
}

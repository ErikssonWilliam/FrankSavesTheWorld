package myGraphics;

import java.io.FileNotFoundException;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import myLogics.Model;
import myLogics.PlayState;


/**
 * Starts the game when clicked
 * @author wiler441
 */
public class PlayButton extends Button {


	public PlayButton(Model model) throws FileNotFoundException {
		super(model);
		getGraphicsContext2D().drawImage(model.getPictures().getPlayButton(), 0, 0, getWidth(), getHeight());

		setOnMouseClicked(event -> {

			PlayState playstate = new PlayState(model);
			model.setPlayState(playstate);
			HBox frame = new HBox();
			frame.getChildren().add(model.getPlayState().getPr());
			model.getMain().setScene(new Scene(frame));
			
			playstate.initialize(); 
			playstate.update();
			playstate.keyIntake();
		});
	}
}

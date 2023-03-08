package myGraphics;

import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import myLogics.Model;
import myLogics.stateTextRoom;

public class ScoreButton extends Button {

	private String myText;
	private stateTextRoom stateTextRoom;
	private GraphicsContext gc = getGraphicsContext2D();
	private ArrayList<String> scores = new ArrayList<String>();
	private int fontSize;

	public ScoreButton(Model model) {
		super(model);

		gc.drawImage(model.getPictures().getScoreButton(), 0, 0, getWidth(), getHeight());

		for (int i = 0; i < model.getHighScores().size(); i++) {
			scores.add("High Score number " + (i + 1) + ": " + model.getHighScores().get(i) + " seconds\n");
			if (i > 0) {
				myText = myText.concat(scores.get(i));
			} else {
				myText = scores.get(i);
			}
		}

		fontSize = 50;
		setOnMouseClicked(event -> {
			stateTextRoom = new stateTextRoom(model, myText, fontSize);

			model.changeState(stateTextRoom);
			Frame frame = new Frame(model);
			model.getMain().setScene(new Scene(frame));

		});
	}

}

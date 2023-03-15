package myGraphics;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.HBox;
import myLogics.Model;
import myLogics.TextRoom;

/**
 * Writes and activates the scores in the textroom when clicked in the menu
 * The scores are displayed here and are taken from the model
 * @author wiler441
 */
public class ScoreButton extends Button {

	private String myText;
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

		fontSize = 60;
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
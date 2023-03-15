package myLogics;

import java.io.FileNotFoundException;

import javafx.scene.layout.VBox;
import myGraphics.ExitButton;
import myGraphics.HelpButton;
import myGraphics.PlayButton;
import myGraphics.ScoreButton;
import myGraphics.StoryButton;
import myGraphics.EmptyRoom;

/**
 * Box that designs the options in the menu
 * @author wiler441
 */
public class Options extends VBox {

	public Options(Model model) throws FileNotFoundException {

		PlayButton myPB = new PlayButton(model);
		this.getChildren().add(myPB);

		StoryButton mySB1 = new StoryButton(model);
		this.getChildren().add(mySB1);

		HelpButton myHB = new HelpButton(model);
		this.getChildren().add(myHB);

		ScoreButton mySB2 = new ScoreButton(model);
		this.getChildren().add(mySB2);

		ExitButton myEB = new ExitButton(model);
		this.getChildren().add(myEB);

		EmptyRoom myEr = new EmptyRoom(model);
		this.getChildren().add(myEr);

		this.setStyle("-fx-background-color: #f2ca01;");
	}
}

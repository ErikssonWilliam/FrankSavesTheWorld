package myGraphics;

import java.io.FileNotFoundException;
import javafx.scene.layout.VBox;
import myLogics.Model;
import myLogics.PlayRoom;

/**
 * Box that builds the result-screen
 * @author wiler441
 */
public class showResult extends VBox {

	public showResult(PlayRoom pr, Model model, Boolean win) {

		this.setStyle("-fx-background-color: #2bcbfd;");

		try {
			GameResult gR = new GameResult(model, win);
			ReturnButton myRB = new ReturnButton(model);
			emptyRoom myEr = new emptyRoom(model);

			this.getChildren().add(gR);
			this.getChildren().add(myRB);
			this.getChildren().add(myEr);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}

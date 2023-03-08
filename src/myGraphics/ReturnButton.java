package myGraphics;

import java.io.FileNotFoundException;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import myLogics.Model;
import myLogics.stateMainMenu;

public class ReturnButton extends Button {

	private stateMainMenu mainMenu;
	private GraphicsContext gc = getGraphicsContext2D();

	public ReturnButton(Model model) throws FileNotFoundException {
		super(model);

		gc.drawImage(model.getPictures().getReturnButton(), 0, 0, getWidth(), getHeight());

		setOnMouseClicked(event -> {
			mainMenu = new stateMainMenu(model);
			model.changeState(mainMenu);

			Frame frame = new Frame(model);
			model.getMain().setScene(new Scene(frame));

		});
	}
}

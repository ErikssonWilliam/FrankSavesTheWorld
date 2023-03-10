package myGraphics;

import java.io.FileNotFoundException;
import javafx.scene.canvas.GraphicsContext;
import myLogics.Model;

/**
 * Exits the game when clicked
 * @author wiler441
 */
public class ExitButton extends Button {

	private GraphicsContext gc = getGraphicsContext2D();

	public ExitButton(Model model) throws FileNotFoundException {
		super(model);

		gc.drawImage(model.getPictures().getExitButton(), 0, 0, getWidth(), getHeight());

		setOnMouseClicked(event -> {
			System.exit(0);
		});
	}
}

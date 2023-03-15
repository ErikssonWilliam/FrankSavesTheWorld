package myGraphics;

import java.io.FileNotFoundException;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.HBox;
import myLogics.MainMenu;
import myLogics.Model;

/**
 * Returns to the main menu when clicked
 * @author wiler441
 */
public class ReturnButton extends Button {

	private GraphicsContext gc = getGraphicsContext2D();

	public ReturnButton(Model model) throws FileNotFoundException {
		super(model);

		gc.drawImage(model.getPictures().getReturnButton(), 0, 0, getWidth(), getHeight());

		setOnMouseClicked(event -> {
			HBox frame = new HBox();
			frame.getChildren().add(new MainMenu(model));
			model.getMain().setScene(new Scene(frame));
		});
	}
}
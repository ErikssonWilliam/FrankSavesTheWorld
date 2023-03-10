package myGraphics;

import java.io.FileNotFoundException;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import myLogics.Model;

/**
 * Draws the logo
 * @author wiler441
 */
public class Logo extends Canvas {

	GraphicsContext context = getGraphicsContext2D();

	public Logo(Model model) throws FileNotFoundException {
		setHeight(model.getFrameHeight());
		setWidth(model.getFrameWidth() - model.getTextButtonWidth());
		getGraphicsContext2D().drawImage(model.getPictures().getLogo(), 0, 0, getWidth(), getHeight());
	}
}

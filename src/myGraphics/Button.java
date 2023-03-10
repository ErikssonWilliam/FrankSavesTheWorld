package myGraphics;

import javafx.scene.canvas.Canvas;
import myLogics.Model;

/**
 * Sets the standard height for every button to extend from
 * @author wiler441
 */
public class Button extends Canvas {

	public Button(Model model) {
		setHeight(model.getTextButtonHeight());
		setWidth(model.getTextButtonWidth());
	}
}

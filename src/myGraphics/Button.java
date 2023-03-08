package myGraphics;

import javafx.scene.canvas.Canvas;
import myLogics.Model;

public class Button extends Canvas {

	public Button(Model model) {
		setHeight(model.getTextButtonHeight());
		setWidth(model.getTextButtonWidth());

	}
}

package myGraphics;

import javafx.scene.canvas.Canvas;
import javafx.scene.layout.HBox;
import myLogics.MainMenu;
import myLogics.Model;

public class Button extends Canvas{

	public Button (Model model) {
	setHeight(model.getTextButtonHeight());
	setWidth(model.getTextButtonWidth());
		
	}
}

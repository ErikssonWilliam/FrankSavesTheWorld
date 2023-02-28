package myGraphics;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import myLogics.MainMenu;


public class ReturnButton extends Canvas{

	private GraphicsContext gc = getGraphicsContext2D();
	
	public ReturnButton(MainMenu mM) {
		setHeight(200);
		setWidth(500);
		gc.setFill(Color.RED);
		gc.fillRect(5, 5, getWidth() - 100, getHeight() - 50);
		
		setOnMouseClicked(event -> {
			mM.run();
		});
	}
}

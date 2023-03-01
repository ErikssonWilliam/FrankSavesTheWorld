package myGraphics;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class TextGround extends Canvas {

	private GraphicsContext gc = getGraphicsContext2D();
	
	public TextGround(String myText) {
		
		setWidth(1500);
		setHeight(600);
		
	gc.setFill(Color.GREEN);
	gc.setFont(new Font (30));
	gc.fillText(myText, getWidth()/4, 100);	
	}
}

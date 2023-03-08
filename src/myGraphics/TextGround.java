package myGraphics;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class TextGround extends Canvas {

	private GraphicsContext gc = getGraphicsContext2D();
	
	public TextGround(String myText, int fontSize) {
		
		setWidth(1520);
		setHeight(780);
		
	gc.setFill(Color.DARKBLUE);
	gc.setFont(new Font (fontSize));
	gc.fillText(myText, getWidth()/4, 100);	
	}
}

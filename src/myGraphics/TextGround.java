package myGraphics;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import myLogics.Model;

public class TextGround extends Canvas {

	private GraphicsContext gc = getGraphicsContext2D();
	
	public TextGround(Model model, String myText, int fontSize) {
		
		setWidth(model.getTextGroundWidth());
		setHeight(model.getTextGroudHeight());
		
	gc.setFill(Color.DARKBLUE);
	gc.setFont(new Font (fontSize));
	gc.fillText(myText, getWidth()/4, 100);	
	}
}

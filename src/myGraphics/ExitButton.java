package myGraphics;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import myLogics.Model;
import myLogics.stateTextRoom;

public class ExitButton extends TextButton {

	private GraphicsContext gc = getGraphicsContext2D();
	
	public ExitButton (Model model) throws FileNotFoundException {
		super(model);
		
		Image exitbutton = new Image(new FileInputStream("/home/wiler441/Documents/Frank_Pictures/exitbutton.png"));
		getGraphicsContext2D().drawImage(exitbutton, 0, 0, getWidth(), getHeight());

		setOnMouseClicked(event -> {
	
	System.exit(0);
			});
		
	}
}

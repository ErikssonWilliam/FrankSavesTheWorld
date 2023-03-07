package myGraphics;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import myLogics.PlayRoom;


public class GameResult extends Canvas {

	private GraphicsContext gc = getGraphicsContext2D();


	
	public GameResult (Boolean win) throws FileNotFoundException {
		
		if (win) {
		Image winscreen= new Image(new FileInputStream("/home/wiler441/Documents/tdde10_project/Frank_Pictures/winscreen.png"));
		gc.drawImage(winscreen, 0, 0, getWidth(), getHeight());
		
	}
}
}

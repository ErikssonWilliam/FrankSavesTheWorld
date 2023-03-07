package myGraphics;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import myLogics.PlayRoom;


public class GameResult extends Canvas {

	private GraphicsContext gc = getGraphicsContext2D();
private PlayRoom pr;

	
	public GameResult (PlayRoom pr, Boolean win) throws FileNotFoundException {
		this.pr = pr;
		if (win) {
		Image winscreen= new Image(new FileInputStream("/home/wiler441/Documents/tdde10_project/Frank_Pictures/winscreen.png"));
		getGraphicsContext2D().drawImage(winscreen, 0, 0, getWidth(), getHeight());
		
	}
}
}
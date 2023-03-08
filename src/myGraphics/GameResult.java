package myGraphics;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import myLogics.Model;
import myLogics.PlayRoom;
import myLogics.stateMainMenu;


public class GameResult extends Canvas {

	private GraphicsContext gc = getGraphicsContext2D();

private Model model;
private Boolean win;
	
	public GameResult (Model model,Boolean win) throws FileNotFoundException {
		this.model=model;
		this.win = win;
		setWidth(1520);
		setHeight(780);
		if (this.win) {
			System.out.println("ladda bild");
		Image winScreen= new Image(new FileInputStream("/home/wiler441/Documents/tdde10_project/Frank_Pictures/winscreen.png"));
		gc.drawImage(winScreen, 0, 0, getWidth(), getHeight());
		
	} else {
		System.out.println("ladda game over");
		Image gameOverScreen= new Image(new FileInputStream("/home/wiler441/Documents/tdde10_project/Frank_Pictures/loosescreen.png"));
		gc.drawImage(gameOverScreen, 0, 0, getWidth(), getHeight());	
	}
	}
	
}

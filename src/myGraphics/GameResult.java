package myGraphics;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import myLogics.Model;

public class GameResult extends Canvas {

	private GraphicsContext gc = getGraphicsContext2D();

	public GameResult(Model model, Boolean win) throws FileNotFoundException {

		setWidth(1520);
		setHeight(780);
		if (win) {
			Image winScreen = new Image(
					new FileInputStream("/home/wiler441/Documents/tdde10_project/Frank_Pictures/winscreen.png"));
			gc.drawImage(winScreen, 0, 0, getWidth(), getHeight());
		} else {
			Image gameOverScreen = new Image(
					new FileInputStream("/home/wiler441/Documents/tdde10_project/Frank_Pictures/loosescreen.png"));
			gc.drawImage(gameOverScreen, 0, 0, getWidth(), getHeight());
		}
	}

}

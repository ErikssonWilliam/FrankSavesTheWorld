package myGraphics;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import myLogics.MainMenu;
import myLogics.Model;
import myLogics.stateMainMenu;


public class ReturnButton extends TextButton{

	private stateMainMenu mainMenu;
	private GraphicsContext gc = getGraphicsContext2D();
	
	public ReturnButton(Model model) throws FileNotFoundException {
		super(model);

		Image button = new Image(new FileInputStream("/home/wiler441/Documents/Frank_Pictures/returnbutton.png"));
		getGraphicsContext2D().drawImage(button, 5, 0, getWidth(), getHeight());
		
		setOnMouseClicked(event -> {
			mainMenu = new stateMainMenu(model);
			model.changeState(mainMenu);
	
			Frame frame = new Frame(model);
			model.getMain().setScene(new Scene(frame));
			
		});
	}
}

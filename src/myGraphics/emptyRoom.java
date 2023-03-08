package myGraphics;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import myLogics.Model;

public class emptyRoom extends Button {

	public emptyRoom(Model model) throws FileNotFoundException {
		super(model);
		// TODO Auto-generated constructor stub
		Image background = new Image(
				new FileInputStream("/home/wiler441/Documents/tdde10_project/Frank_Pictures/background.png"));
		getGraphicsContext2D().drawImage(background, 0, 0, getWidth(), getHeight());
	}
}

package myGraphics;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.image.Image;
import myLogics.Model;

/**
 * this might be strange but for us this 
 * empty box was the only way to guarantee
 * that the return button always showed up
 * @author wiler441
 *
 */
public class EmptyRoom extends Button {

	public EmptyRoom(Model model) throws FileNotFoundException {
		super(model);
		Image background = new Image(
				new FileInputStream("/home/wiler441/Documents/tdde10_project/Frank_Pictures/background.png"));
		getGraphicsContext2D().drawImage(background, 0, 0, getWidth(), getHeight());
	}
}

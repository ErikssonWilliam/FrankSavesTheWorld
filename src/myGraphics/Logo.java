package myGraphics;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import myLogics.Model;

public class Logo extends Canvas{
	
	GraphicsContext context = getGraphicsContext2D();
	
	public Logo (Model model) throws FileNotFoundException   {
		setHeight(model.getFrameHeight());
		setWidth(model.getFrameWidth()-model.getTextButtonWidth());
		System.out.println(getWidth());
		
		String logoPath = "/home/wiler441/Documents/tdde10_project/Frank_Pictures/logo.png";
		Image logo = new Image(new FileInputStream(logoPath));
		getGraphicsContext2D().drawImage(logo, 0, 0, getWidth(), getHeight());
	}
}

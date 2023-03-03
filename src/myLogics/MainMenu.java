package myLogics;


import java.io.FileNotFoundException;

import javafx.scene.layout.HBox;

import myGraphics.Logo;

public class MainMenu extends HBox{

	public MainMenu(Model model) {
		
		try {
			Logo logo = new Logo();
			this.getChildren().add(logo);
		} catch (Exception FileNotFoundException) {
		}
		Options options;
		try {
			options = new Options(model);
			this.getChildren().add(options);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
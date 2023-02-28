package myLogics;

import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import myGraphics.Logo;

public class MainMenu extends HBox{

	private Stage stage;
	private Boolean ifFirst = true;
	private Scene mainMenu;


	public MainMenu(Stage arg0) {
		this.stage = arg0;
	}
	
	
	public void run() {
		
	try {
		Logo logo = new Logo();
		this.getChildren().add(logo);
	} catch (Exception FileNotFoundException) {
	}
	
	Options options = new Options(this);
	this.getChildren().add(options);
	
	if (ifFirst) {
	mainMenu = new Scene(this);
	ifFirst = false;
	}
	
	stage.setTitle("Frank...Saves The World");
	stage.setScene(mainMenu);
    stage.show();
	}


	public Stage getStage() {
		return stage;
	}


	public void setStage(Stage stage) {
		this.stage = stage;
	}
}

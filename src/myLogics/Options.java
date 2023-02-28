package myLogics;

import javafx.scene.layout.VBox;
import myGraphics.StoryButton;
import myGraphics.TextButton;

public class Options extends VBox{
	
//	private PlayButton myPB;
//	private Button myTB;
//	private ExitButton myEB;
	
	public Options(MainMenu mM) {

		StoryButton mySB = new StoryButton(mM);
		this.getChildren().add(mySB);
    	this.setStyle("-fx-background-color: #D3D3D3;");
	}

}

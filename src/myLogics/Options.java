package myLogics;

import java.io.FileNotFoundException;

import javafx.scene.canvas.Canvas;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import myGraphics.ExitButton;
import myGraphics.HelpButton;
import myGraphics.PlayButton;
import myGraphics.StoryButton;
import myGraphics.TextButton;
import myGraphics.emptyRoom;

public class Options extends VBox{
	
//	private PlayButton myPB;
//	private Button myTB;
//	private ExitButton myEB;
	
	public Options(Model model) throws FileNotFoundException {

		PlayButton myPB = new PlayButton(model);
		this.getChildren().add(myPB);
		
		StoryButton mySB = new StoryButton(model);
		this.getChildren().add(mySB);
    	
    	HelpButton myHB = new HelpButton(model);
    	this.getChildren().add(myHB);
    	
    	ExitButton myEB = new ExitButton(model);
    	this.getChildren().add(myEB);

		emptyRoom myEp = new emptyRoom(model);
		this.getChildren().add(myEp);
    	
    	this.setStyle("-fx-background-color: #f2ca01;");
	}

}

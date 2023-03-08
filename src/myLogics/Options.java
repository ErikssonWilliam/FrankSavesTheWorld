package myLogics;

import java.io.FileNotFoundException;


import javafx.scene.layout.VBox;
import myGraphics.ExitButton;
import myGraphics.HelpButton;
import myGraphics.PlayButton;
import myGraphics.ScoreButton;
import myGraphics.StoryButton;

import myGraphics.emptyRoom;

public class Options extends VBox{
	
//	private PlayButton myPB;
//	private Button myTB;
//	private ExitButton myEB;
	
	public Options(Model model) throws FileNotFoundException {

		PlayButton myPB = new PlayButton(model);
		this.getChildren().add(myPB);
		
		StoryButton mySB1 = new StoryButton(model);
		this.getChildren().add(mySB1);
    	
    	HelpButton myHB = new HelpButton(model);
    	this.getChildren().add(myHB);
    	
    	ScoreButton mySB2 = new ScoreButton(model);
    	this.getChildren().add(mySB2);
    	
    	ExitButton myEB = new ExitButton(model);
    	this.getChildren().add(myEB);
    	
		emptyRoom myEp = new emptyRoom(model);
		this.getChildren().add(myEp);
    	
    	this.setStyle("-fx-background-color: #f2ca01;");
	}

}

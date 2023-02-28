package myGraphics;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import myLogics.MainMenu;

public class StoryButton extends TextButton {
private String myText;
private TextBG bg;
private GraphicsContext gc = getGraphicsContext2D();

public StoryButton(MainMenu mM) {
	super(mM);
	setHeight(200);
	setWidth(500);
	gc.setFill(Color.GREEN);
	gc.fillRect(20, 20, getWidth() - 40, getHeight() - 40);
	
	
	myText = "The Story about Frank begins in 2000, when\nthe millenia passed and ...";
		
	setOnMouseClicked(event -> {				
		bg = new TextBG();
		bg.showcase(myText, mM);
		
		});
	
}
}

package myGraphics;

import java.io.FileNotFoundException;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import myLogics.Model;
import myLogics.PlayRoom;

public class showResult extends VBox {

	public showResult(PlayRoom pr, Model model, Boolean win)  {
		
		System.out.println("i showResult");
    	this.setStyle("-fx-background-color: #f2ca01;");
    	
		try {
			GameResult gR = new GameResult(model, win);
			 this.getChildren().add(gR);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			ReturnButton myRB = new ReturnButton(model);
			emptyRoom myEr = new emptyRoom(model);
			System.out.println("lägg till return button");
			this.getChildren().add(myRB);
			this.getChildren().add(myEr);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	System.out.println(this);

   	
	}
}

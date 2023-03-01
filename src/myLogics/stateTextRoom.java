package myLogics;

import java.io.FileNotFoundException;

import javafx.scene.layout.VBox;
import myGraphics.ReturnButton;
import myLogics.Model;
import myLogics.stateOfGame;

public class stateTextRoom extends stateOfGame {

	private TextRoom tr;

	public stateTextRoom(Model model, String myText) {
		super(model);
		try {
			tr = new TextRoom(model, myText);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}


	public TextRoom getTr() {
		return tr;
	}


	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void activate() {
		// TODO Auto-generated method stub

	}

	@Override
	public void deactivate() {
		// TODO Auto-generated method stub

	}
}

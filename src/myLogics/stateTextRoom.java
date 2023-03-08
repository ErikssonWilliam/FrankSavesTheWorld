package myLogics;

import java.io.FileNotFoundException;

import javafx.scene.layout.VBox;
import myGraphics.ReturnButton;
import myLogics.Model;
import myLogics.stateOfGame;

public class stateTextRoom extends stateOfGame {

	private TextRoom tr;

	public stateTextRoom(Model model, String myText, int fontSize) {
		super(model);
		try {
			tr = new TextRoom(model, myText, fontSize);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}


	}


	public TextRoom getTr() {
		return tr;
	}


	@Override
	public void update() {
	//vi har inget som behöver uppdateras i textroom

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

package myLogics;

import java.io.FileNotFoundException;

/**
 * enables state of the TextRoom 
 * @author wiler441
 */
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
	}
}

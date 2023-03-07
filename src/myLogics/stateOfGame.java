package myLogics;

import javafx.scene.Node;
import javafx.scene.input.KeyEvent;

public abstract class stateOfGame {

	protected Model model;
	
	public stateOfGame(Model model) {
		this.model = model;
	}
	
	public abstract void update();
	
	public abstract void activate();
	public abstract void deactivate();

	public Node getTr() {
		return null;
	}

	public Node getMm() {
		return null;
	}
	
	public Node getPr() {
		return null;
	}
	
	public Node getSr() {
		return null;
	}
	

	public void keyPressed(KeyEvent key) {
	}
}
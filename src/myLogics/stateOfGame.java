package myLogics;

import javafx.scene.Node;
import javafx.scene.input.KeyEvent;
/**
 * An abstract state-class for the separate states to build on
 * @author wiler441 & joaan879
 */

public abstract class stateOfGame {

	protected Model model;
	
	public stateOfGame(Model model) {
		this.model = model;
	}
	/**
	 * Could be eliminated but works as a potential updater for different states
	 */
	public abstract void update(); 

	/**
	 * Nulled nodes for the different states
	 */
	
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

	/**
	 * Enables key-pressing in the different states
	 */
	public void keyPressed(KeyEvent key) {
	}
}
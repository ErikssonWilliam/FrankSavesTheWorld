package myLogics;

import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class Model {

	private Stage main;
	private stateOfGame currentState;
	
	public Model(Stage arg0) {
		this.currentState = new stateMainMenu(this);
		this.main = arg0;
	}
	
	public Stage getMain() {
		return main;
	}

	public void changeState(stateOfGame nextState) {

		currentState.deactivate();
		currentState = nextState;
		currentState.activate();
	}
	
	public stateOfGame getCurrentState() {
		return currentState;
	}

	public void update() {
		currentState.update();
	}
	

}

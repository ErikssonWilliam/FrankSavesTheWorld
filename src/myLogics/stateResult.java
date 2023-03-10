package myLogics;

import myGraphics.showResult;

/*
 * enables the state of results, 
 */
public class stateResult extends stateOfGame {

	private showResult sR;

	public stateResult(PlayRoom pr, Model model, Boolean win) {
		super(model);
		this.sR = new showResult(pr, model, win);
	}

	@Override
	public void update() {
	}

	public showResult getSr() {
		return sR;
	}
}

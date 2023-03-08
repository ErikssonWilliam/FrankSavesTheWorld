package myLogics;

import myGraphics.showResult;

public class stateResult extends stateOfGame {

	private showResult sR;

	public stateResult(PlayRoom pr, Model model, Boolean win) {
		super(model);
		this.sR = new showResult(pr, model, win);

		// TODO Auto-generated constructor stub
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

	public showResult getSr() {
		return sR;
	}
}

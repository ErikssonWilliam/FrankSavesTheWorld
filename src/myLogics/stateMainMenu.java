package myLogics;

public class stateMainMenu extends stateOfGame {
	private MainMenu mm; 

	public stateMainMenu(Model model) {
		super(model);
		this.mm = new MainMenu(model);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	public MainMenu getMm() {
		return mm;
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

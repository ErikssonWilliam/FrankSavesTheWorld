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
	}

	public MainMenu getMm() {
		return mm;
	}

	@Override
	public void activate() {

	}

	@Override
	public void deactivate() {
	}

}

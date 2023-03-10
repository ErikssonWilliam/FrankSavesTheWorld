package myLogics;

/**
 * Enables the main menu
 * @author wiler441
 */

public class stateMainMenu extends stateOfGame {
	private MainMenu mm; 

	public stateMainMenu(Model model) {
		super(model);
		this.mm = new MainMenu(model);
	}

	@Override
	public void update() {
	}

	public MainMenu getMm() {
		return mm;
	}
}

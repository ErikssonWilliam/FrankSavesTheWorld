package myLogics;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import myGraphics.Frame;

public class statePlay extends stateOfGame {

	private PlayRoom pr;
	private String informationText;
	private static final String[] levelArray = { "/home/wiler441/Documents/tdde10_project/Levels/firstLevel.txt",
			"/home/wiler441/Documents/tdde10_project/Levels/secondLevel.txt" };

	public static String getLevel(int index) {
		return levelArray[index];
	}

	public statePlay(Model model) {
		super(model);
		this.informationText = "Press Escape To Return To The Menu";
		this.pr = new PlayRoom(model);

	}

	public void initialize() {
		int index;
		if (!pr.getIsSecondLevel()) {
			index = 0;
		} else {
			index = 1;
		}
		String file = this.getLevel(index);
		// this.startTimer
		pr.StartNewGame(file);
	}

	public void keyIntake() {
		model.getMain().getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {

			public void handle(KeyEvent keyEvent) {
				keyPressed(keyEvent);
			}
		});
	}

	public PlayRoom getPr() {
		return pr;
	}

	@Override
	public void keyPressed(KeyEvent key) {
		System.out.println("Trycker på " + key.getCode() + " i PlayState");

		if (key.getCode() == KeyCode.ESCAPE) {
			model.changeState(new stateMainMenu(model));
			Frame frame = new Frame(model);
			model.getMain().setScene(new Scene(frame));
		} else if (key.getCode() == KeyCode.LEFT || key.getCode() == KeyCode.UP || key.getCode() == KeyCode.RIGHT
				|| key.getCode() == KeyCode.DOWN) {
			pr.getFrank().moveTo(key.getCode(), pr);
		} else if (key.getCode() == KeyCode.E) {
			pr.giveFrank();
		}
	}

	@Override
	public void update() {
		pr.update(this);

	}

	@Override
	public void activate() {

	}

	@Override
	public void deactivate() {

	}

}

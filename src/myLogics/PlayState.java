package myLogics;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Collections;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import myGraphics.GameResult;
import myLogics.PlayRoom.GridContent;

/**
 * Enables the state of game (playroom) and certain operations 
 * that do not belong in a specific game.
 * Also changes level
 * @author wiler441
 */
public class PlayState{

	private PlayRoom pr;
	private Model model;
	private GameResult gR;
	private long startTime;
	private static final String[] levelArray = { 
			"/home/wiler441/Documents/tdde10_project/Levels/firstLevel.txt",
			"/home/wiler441/Documents/tdde10_project/Levels/secondLevel.txt" };

	public PlayState(Model model) {
		this.pr = new PlayRoom(model);
		this.model = model;
	}

	public void initialize() throws FileNotFoundException {
		int index;
		if (!pr.getIsSecondLevel()) {
			index = 0;
			this.recordTime(true);
		} else {
			index = 1;
		}
		pr.StartNewGame(this.getLevel(index));
	}

	/**
	 * Records time and sends this to model & or new text file High scores remove
	 * once reached a certain cap decided in model might be models responsibility
	 * 
	 * @param start
	 */
	private void recordTime(Boolean start) {
		if (start) {
			startTime = System.nanoTime();
		} else if (!start) {
			long score = (System.nanoTime() - startTime) / 1000000000;
			model.getHighScores().add(score);
			Collections.sort(model.getHighScores());
			if (model.getHighScores().size() > model.getAmountOfScores()) {
				model.getHighScores().remove(model.getAmountOfScores());
			}
			try {
				ObjectOutputStream outStream = new ObjectOutputStream(new FileOutputStream(model.getFile()));
				outStream.writeObject(model.getHighScores());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * The following 2 methods handles keycodes from keyboard in game
	 */
	public void keyIntake() {
		model.getMain().getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent keyEvent) {
				keyPressed(keyEvent);
			}
		});
	}

	public void keyPressed(KeyEvent key) {

		if (key.getCode() == KeyCode.ESCAPE) {
			HBox frame = new HBox();
			frame.getChildren().add(new MainMenu(model));
			model.getMain().setScene(new Scene(frame));
		} else if (key.getCode() == KeyCode.LEFT || key.getCode() == KeyCode.UP || key.getCode() == KeyCode.RIGHT
				|| key.getCode() == KeyCode.DOWN) {
			pr.getFrank().moveTo(key.getCode(), pr);
		} else if (key.getCode() == KeyCode.E) {
			pr.giveFrank();
		} else if (key.getCode() == KeyCode.U) {
			pr.getFrank().use();
		}
	}

	/**
	 * Changes levels, either to second map or if you win
	 * 
	 * @throws FileNotFoundException
	 */
	public void ChangeLevel() throws FileNotFoundException {

		if ((pr.getCellValue((int) pr.getFrank().getFrankLocation().getX(),
				(int) pr.getFrank().getFrankLocation().getY()) == GridContent.DONE)
				&& pr.getFrank().hasNuclearKey()) {

			if (pr.getIsSecondLevel()) {
				this.recordTime(false);
				pr.DisplayResult(true);
			} else {
				pr.getEnemies().clear();
				pr.setIsSecondLevel(true);
				pr.getFrank().setUsedEMP(false);
				initialize();
			}
		}
	}

	/**
	 * Updates the game
	 */
	public void update() {
		pr.update(this);
	}

	public GameResult getgR() {
		return gR;
	}

	public PlayRoom getPr() {
		return pr;
	}

	public String getLevel(int index) {
		return levelArray[index];
	}
}

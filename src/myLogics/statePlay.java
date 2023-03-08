package myLogics;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Collections;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import myGraphics.Frame;
import myGraphics.GameResult;
import myLogics.PlayRoom.GridContent;

public class statePlay extends stateOfGame {

	private PlayRoom pr;
	private GameResult gR;
	private long startTime;
	private static final String[] levelArray = { "/home/wiler441/Documents/tdde10_project/Levels/firstLevel.txt",
			"/home/wiler441/Documents/tdde10_project/Levels/secondLevel.txt" };

	public static String getLevel(int index) {
		return levelArray[index];
	}

	public statePlay(Model model) {
		super(model);
		this.pr = new PlayRoom(model);

	}

	public void initialize() {
		int index;
		if (!pr.getIsSecondLevel()) {
			index = 0;
			this.recordTime(true);
		} else {
			index = 1;
		}
		String file = this.getLevel(index);
		pr.StartNewGame(file);
	}

	private void recordTime(Boolean start) {
		if (start) {
			startTime = System.nanoTime();
		} else if (!start) {
			long score = (System.nanoTime() - startTime)/1000000000;
			model.getHighScores().add(score);
			Collections.sort(model.getHighScores());
			model.getHighScores().remove(model.getAmountOfScores());
		
			try {
				ObjectOutputStream outStream = new ObjectOutputStream(new FileOutputStream(model.getFile()));
				outStream.writeObject(model.getHighScores());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

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
		

		if (key.getCode() == KeyCode.ESCAPE) {
			model.changeState(new stateMainMenu(model));
			Frame frame = new Frame(model);
			model.getMain().setScene(new Scene(frame));
		} else if (key.getCode() == KeyCode.LEFT || key.getCode() == KeyCode.UP || key.getCode() == KeyCode.RIGHT
				|| key.getCode() == KeyCode.DOWN) {
			pr.getFrank().moveTo(key.getCode(), pr);
		} else if (key.getCode() == KeyCode.E) {
			pr.giveFrank();
		} else if (key.getCode() == KeyCode.U) {
			pr.useEmp();
		}

	}

	public void ChangeLevel() throws FileNotFoundException {

		if ((pr.getCellValue((int) pr.getFrank().getFrankLocation().getX(),
				(int) pr.getFrank().getFrankLocation().getY()) == GridContent.DONE)
				&& pr.getFrank().getHasNuclearCode()) {

			if (pr.getIsSecondLevel()) {
				this.recordTime(false);
				pr.DisplayResult(true);
			} else {
				pr.getEnemies().clear();
				pr.setIsSecondLevel(true);
				initialize();
			}
		}
	}

	public GameResult getgR() {
		return gR;
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

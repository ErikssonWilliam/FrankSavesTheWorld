package myLogics;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import javafx.stage.Stage;
import myGraphics.Pictures;

/**
 * Stores values, states and enables reading of high score
 * Follows the entire program.
 * [KOMP] Removed currentstate and Frame since it didn't actually
 * refer to effective code.
 * @author wiler441
 */
public class Model {

	private Stage main;
	private PlayState playState;
	private Pictures pictures = new Pictures();
	private LinkedList<Long> highScores = new LinkedList<Long>();
	private int amountOfScores = 5;
	private File file;
	private int frameHeight;
	private int frameWidth;
	private int TextButtonHeight = 194;
	private int TextButtonWidth = 400;
	private int textGroundWidth = 1520;
	private int textGroudHeight = 780;

	public Model(Stage arg0) {
		readHighScores();
		this.main = arg0;
		this.frameHeight = (int) arg0.getHeight();
		this.frameWidth = (int) arg0.getWidth();
	}

	/**
	 * Reads the high-score, creates a new file if need be
	 */
	private void readHighScores() {
		try {
			this.file = new File("/home/wiler441/Documents/tdde10_project/Levels/highScores.txt");
			if (file.exists()) {
				try (ObjectInputStream inStream = new ObjectInputStream(new FileInputStream(file))) {
					setHighScores((LinkedList<Long>) inStream.readObject());
				}
			} else {
				file.createNewFile();
				try (ObjectOutputStream outStream = new ObjectOutputStream(new FileOutputStream(file))) {
					outStream.writeObject(this.getHighScores());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Starts the PlayState
	 */
	public void setPlayState(PlayState playState) {
		this.playState = playState;
	}
	
	/**
	 * Getters and setters
	 * @param Getters and setters
	 */	
	public PlayState getPlayState() {
		return playState;
	}
	
	public Stage getMain() {
		return main;
	}

	public Pictures getPictures() {
		return pictures;
	}

	public LinkedList<Long> getHighScores() {
		return highScores;
	}

	public void setHighScores(LinkedList<Long> highScores) {
		this.highScores = highScores;
	}

	public File getFile() {
		return file;
	}

	public int getAmountOfScores() {
		return amountOfScores;
	}

	public int getFrameHeight() {
		return frameHeight;
	}

	public int getFrameWidth() {
		return frameWidth;
	}

	public int getTextButtonHeight() {
		return TextButtonHeight;
	}

	public int getTextButtonWidth() {
		return TextButtonWidth;
	}

	public int getTextGroundWidth() {
		return textGroundWidth;
	}

	public int getTextGroudHeight() {
		return textGroudHeight;
	}

}

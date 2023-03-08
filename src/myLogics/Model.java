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


public class Model {

	private Stage main;
	private stateOfGame currentState;
	private Pictures pictures = new Pictures();
	private LinkedList<Long>highScores = new LinkedList<Long>();
	private int amountOfScores = 5;
	private File file;
	private int frameHeight = 1000;
	private int frameWidth = 1520;
	private int TextButtonHeight = 194;
	private int TextButtonWidth = 400;
	

	


	public Model(Stage arg0) {
		readHighScores();	
		this.currentState = new stateMainMenu(this);
		this.main = arg0;
		
	}
	

	private void readHighScores() {
		try {
		this.file = new File("/home/wiler441/Documents/tdde10_project/Levels/highScores.txt");
		if (file.exists()) {
				ObjectInputStream inStream = new ObjectInputStream(new FileInputStream(file));
			setHighScores((LinkedList<Long>)inStream.readObject());
		} else {
			 file.createNewFile();
	    	 ObjectOutputStream outStream = new ObjectOutputStream(new FileOutputStream(file));
		     outStream.writeObject(this.getHighScores());		
		}
    	} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			
		} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}		
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

}

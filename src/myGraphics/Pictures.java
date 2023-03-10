package myGraphics;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.image.Image;

/**
 * Buffers the pictures of which are used in the different states except the game
 * @author wiler441
 */
public class Pictures {
	private Image playButton;
	private Image storyButton;
	private Image helpButton;
	private Image scoreButton;
	private Image returnButton;
	private Image logo;
	private Image exitButton;

	public Pictures() {

		try {
			playButton = new Image(
					new FileInputStream("/home/wiler441/Documents/tdde10_project/Frank_Pictures/playbutton.png"));
			storyButton = new Image(
					new FileInputStream("/home/wiler441/Documents/tdde10_project/Frank_Pictures/storybutton.png"));
			helpButton = new Image(
					new FileInputStream("/home/wiler441/Documents/tdde10_project/Frank_Pictures/helpbutton.png"));
			scoreButton = new Image(
					new FileInputStream("/home/wiler441/Documents/tdde10_project/Frank_Pictures/scorebutton.png"));
			returnButton = new Image(
					new FileInputStream("/home/wiler441/Documents/tdde10_project/Frank_Pictures/returnbutton.png"));
			logo = new Image(
					new FileInputStream("/home/wiler441/Documents/tdde10_project/Frank_Pictures/logo.png"));
			exitButton = new Image(
					new FileInputStream("/home/wiler441/Documents/tdde10_project/Frank_Pictures/exitbutton.png"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Getters and setters
	 * @return
	 */
	public Image getExitButton() {
		return exitButton;
	}

	public void setExitButton(Image exitButton) {
		this.exitButton = exitButton;
	}

	public Image getPlayButton() {
		return playButton;
	}

	public void setPlayButton(Image playButton) {
		this.playButton = playButton;
	}

	public Image getStoryButton() {
		return storyButton;
	}

	public void setStoryButton(Image storyButton) {
		this.storyButton = storyButton;
	}

	public Image getHelpButton() {
		return helpButton;
	}

	public void setHelpButton(Image helpButton) {
		this.helpButton = helpButton;
	}

	public Image getScoreButton() {
		return scoreButton;
	}

	public void setScoreButton(Image scoreButton) {
		this.scoreButton = scoreButton;
	}

	public Image getReturnButton() {
		return returnButton;
	}

	public void setReturnButton(Image returnButton) {
		this.returnButton = returnButton;
	}

	public Image getLogo() {
		return logo;
	}

	public void setLogo(Image logo) {
		this.logo = logo;
	}
}

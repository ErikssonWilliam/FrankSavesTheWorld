package myGraphics;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import myLogics.PlayRoom;
import myLogics.PlayRoom.GridContent;

/**
 * Updates the view of the game as well as storing the different pictures used
 * in-game. Updates the standard board.
 * @author wiler441
 */
public class GameView extends Group {

	public final static double CELL_SIZE = 40.0;
	private int rowAmount = 25;
	private int columnAmount = 38;
	private Image wall;
	private Image finishline;
	private Image floor;
	private Image flash;
	private ImageView[][] gridViews;

	public GameView(PlayRoom pr) throws FileNotFoundException {
		this.wall = new Image(
				new FileInputStream("/home/wiler441/Documents/tdde10_project/Frank_Pictures/blackbox.png"));
		this.finishline = new Image(
				new FileInputStream("/home/wiler441/Documents/tdde10_project/Frank_Pictures/finish.png"));
		this.floor = new Image(new FileInputStream("/home/wiler441/Documents/tdde10_project/Frank_Pictures/floor.png"));
		this.flash = new Image(
				new FileInputStream("/home/wiler441/Documents/tdde10_project/Frank_Pictures/flashlight.png"));
	}

	/**
	 * Sets the preconditions for the soon to be viewed game
	 */
	public void initializeGrid() {

		gridViews = new ImageView[rowAmount][columnAmount];

		for (int row = 0; row < rowAmount; row++) {
			for (int column = 0; column < columnAmount; column++) {
				ImageView imageView = new ImageView();
				imageView.setX((double) column * CELL_SIZE);
				imageView.setY((double) row * CELL_SIZE);
				imageView.setFitWidth(CELL_SIZE);
				imageView.setFitHeight(CELL_SIZE);
				this.gridViews[row][column] = imageView;
				this.getChildren().add(imageView);
			}
		}
	}

	/**
	 * Updates the gameview for every update in the playroom
	 * @param pr
	 */
	public void update(PlayRoom pr) {

		for (int row = 0; row < rowAmount; row++) {
			for (int column = 0; column < columnAmount; column++) {
				GridContent element = pr.getCellValue(row, column);

				if (element == GridContent.WALL) {
					this.gridViews[row][column].setImage(wall);
				} else if (element == GridContent.DONE) {
					this.gridViews[row][column].setImage(finishline);
				} else if (element == GridContent.FLASH) {
					this.gridViews[row][column].setImage(flash);
				} else if (element == GridContent.EMPTY 
						|| element == GridContent.FRANKSPAWN ) {
					this.gridViews[row][column].setImage(floor);
				} else {

				}
			}
		}
	}
	
	public ImageView[][] getGridViews() {
		return gridViews;
	}
}

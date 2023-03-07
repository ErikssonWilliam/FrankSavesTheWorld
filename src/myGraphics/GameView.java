package myGraphics;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import myLogics.PlayRoom;
import myLogics.PlayRoom.GridContent;

public class GameView extends Group {

	public final static double CELL_SIZE = 40.0;
	private int rowAmount = 25;
	private int columnAmount = 38;
	private Image frank1;
	private Image wall;
	private Image nukekey;
	private Image finishline;
	private Image enemy;
	private Image floor;
	private Image flash;
	private Image camera;
	private Image controlpanel;
	private Image emp;
	private ImageView[][] gridViews;

	public GameView(PlayRoom pr) throws FileNotFoundException {
		this.frank1 = new Image(
				new FileInputStream("/home/wiler441/Documents/tdde10_project/Frank_Pictures/Frank1.png"));
		this.wall = new Image(
				new FileInputStream("/home/wiler441/Documents/tdde10_project/Frank_Pictures/blackbox.png"));
		this.nukekey = new Image(
				new FileInputStream("/home/wiler441/Documents/tdde10_project/Frank_Pictures/nuclearkey.png"));
		this.finishline = new Image(
				new FileInputStream("/home/wiler441/Documents/tdde10_project/Frank_Pictures/finish.png"));
		this.enemy = new Image(
				new FileInputStream("/home/wiler441/Documents/tdde10_project/Frank_Pictures/soldierdiscover1.png"));
		this.floor = new Image(
				new FileInputStream("/home/wiler441/Documents/tdde10_project/Frank_Pictures/floor.png"));
		this.flash = new Image(
				new FileInputStream("/home/wiler441/Documents/tdde10_project/Frank_Pictures/flashlight.png"));
		this.camera = new Image(
				new FileInputStream("/home/wiler441/Documents/tdde10_project/Frank_Pictures/camera.png"));
		this.controlpanel = new Image(
				new FileInputStream("/home/wiler441/Documents/tdde10_project/Frank_Pictures/controlpanel1.png"));
		this.emp = new Image(
				new FileInputStream("/home/wiler441/Documents/tdde10_project/Frank_Pictures/emp.png"));
	}


	public void RenderLevelGraphics() {

	}

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

	public void update(PlayRoom pr) {
		
	
		for (int row = 0; row < rowAmount; row++) {
			for (int column = 0; column < columnAmount; column++) {
				GridContent element = pr.getCellValue(row, column);

				if (row == pr.getFrank().getFrankLocation().getX()
						&& column == pr.getFrank().getFrankLocation().getY()) {
					this.gridViews[row][column].setImage(frank1);
				} else if (element == GridContent.WALL) {
					this.gridViews[row][column].setImage(wall);
				} else if (element == GridContent.DONE) {
					this.gridViews[row][column].setImage(finishline);
				} else if (element == GridContent.CAMERA) {
					this.gridViews[row][column].setImage(camera);
				}else if (element == GridContent.FLASH) {
					this.gridViews[row][column].setImage(flash);
				}else if (element == GridContent.EMP) {
					this.gridViews[row][column].setImage(emp);
				} else if (element == GridContent.NUKEKEY) {
						this.gridViews[row][column].setImage(nukekey);
				} else if (element == GridContent.ENEMY) {
					this.gridViews[row][column].setImage(enemy);
				} else if (element == GridContent.CONTROLPANEL) {
					this.gridViews[row][column].setImage(controlpanel);
				} else {
					this.gridViews[row][column].setImage(floor);
				}
			}
		}
	}
}

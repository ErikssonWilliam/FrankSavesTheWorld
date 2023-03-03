package myGraphics;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import myLogics.PlayRoom;
import myLogics.PlayRoom.GridContent;

public class GameView extends Group{

	public final static double CELL_SIZE = 40.0;
	private int rowAmount = 25;
	private int columnAmount = 38;
	private Image frank1;
	private Image wall;
	private ImageView[][] gridViews;
	
	public GameView(PlayRoom pr) throws FileNotFoundException {
		this.frank1 = new Image(new FileInputStream("/home/wiler441/Documents/tdde10_project/Frank_Pictures/Frank1.png"));
		this.wall = new Image(new FileInputStream("/home/wiler441/Documents/tdde10_project/Frank_Pictures/blackbox.png"));
	}
	public void RenderLevelGraphics() {
		
	}

	public void initializeGrid() {
		
		gridViews = new ImageView[rowAmount][columnAmount];
		
		 for (int row = 0; row < rowAmount; row++) {
             for (int column = 0; column < columnAmount; column++) {
                 ImageView imageView = new ImageView();
                 imageView.setX((double)column * CELL_SIZE);
                 imageView.setY((double)row * CELL_SIZE);
                 imageView.setFitWidth(CELL_SIZE);
                 imageView.setFitHeight(CELL_SIZE);
                 this.gridViews[row][column] = imageView;
                 this.getChildren().add(imageView);
             }
         }
	}
	public void update(PlayRoom pr) {
		System.out.println("Inne i GameView");
	    System.out.println(pr.getFrankLocation());

	
		for (int row = 0; row < rowAmount; row++) {
			for (int column = 0; column < columnAmount; column++) {
				GridContent element = pr.getCellValue(row, column);
				

		if (row == pr.getFrankLocation().getX() && column == pr.getFrankLocation().getY()) {
				  this.gridViews[row][column].setImage(this.frank1);
			} else if (element == GridContent.WALL) {
				  this.gridViews[row][column].setImage(this.wall);
		} else {
			this.gridViews[row][column].setImage(null);
		}
	}
	}
}
}


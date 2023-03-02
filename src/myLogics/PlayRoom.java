package myLogics;

import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import myGraphics.GameView;

import java.io.FileNotFoundException;

import javafx.fxml.FXML;
import javafx.geometry.Point2D;

public class PlayRoom extends VBox {

	public enum GridContent {
		EMPTY, FRANKSPAWN
	};

	public enum Directions {
		NORTH, EAST, SOUTH, WEST, STAY
	};

	private GridContent[][] grid;

	private Point2D frankVelocity;
	private Point2D frankLocation;
	private GameView gameview;
	private int rowCount = 25;
	private int columnCount = 38;

	public PlayRoom(Model model) {
		this.setStyle("-fx-background-color: #f2ca01;");
		try {
			this.gameview = new GameView(this);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		this.getChildren().add(gameview);
	
	}
	
	public void StartNewGame() {
		this.RenderLevel();
		gameview.initializeGrid();
		//Här ska vi sen lägga in Booleans som hjälper gör att vi kan vinna/förlora
	}

	public void RenderLevel() {

		grid = new GridContent[rowCount][columnCount];
	
		int frankRow = 0;
		int frankColumn = 0; // implementeras sen när vi läser av banan från en textfil, genom en if sats i for loopen nedan

		for (int row = 0; row < rowCount; ++row) {
			for (int column = 0; column < columnCount; column++) {
				GridContent thisValue;
				thisValue = GridContent.EMPTY;
				grid[row][column] = thisValue;
			
			}		
		}

		frankVelocity = new Point2D(0, 0);
		frankLocation = new Point2D(frankRow, frankColumn);
		System.out.println("Skapa ny Pointers");
	}

	public void moveTo(KeyCode key) {

		System.out.println("I moveto");
		
		Directions direction = Directions.STAY;
		if (key == KeyCode.UP) {
			direction = Directions.NORTH;
		} else if (key == KeyCode.DOWN) {
			direction = Directions.SOUTH;
		} else if (key == KeyCode.RIGHT) {
			direction = Directions.EAST;
		} else if (key == KeyCode.LEFT) {
			direction = Directions.WEST;
		}
		Point2D frankVelocity = changeVelocity(direction);
		frankLocation = frankLocation.add(frankVelocity);
		gameview.update(this);
		
//			
//		    frankVelocity = changeVelocity(direction);

	}

	public Point2D changeVelocity(Directions direction) {
System.out.println("Inne i changeVelocity");
		if (direction == Directions.WEST) {
			return new Point2D(0, -1);
		} else if (direction == Directions.NORTH) {
			return new Point2D(-1, 0);
		} else if (direction == Directions.EAST) {
			return new Point2D(0, 1);
		} else if (direction == Directions.SOUTH) {
			return new Point2D(1, 0);
		} else
			return new Point2D(0, 0);

	}

	public void update() {
	//	this.gameview.update(this);
	}

	public Point2D getFrankLocation() {
		return frankLocation;
	}

	public void setFrankLocation(Point2D frankLocation) {
		this.frankLocation = frankLocation;
	}

	



// 3. Updatera utanför tryck/updatera innan första tryck
// 3. Väggar
// 4. Nuklearcard
// 5. (vinna)
// 5. säkrehetslampa
// 6. panel
// 7. Vakt
// 8. emp
}

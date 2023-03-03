package myLogics;

import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import myGraphics.GameView;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javafx.fxml.FXML;
import javafx.geometry.Point2D;

public class PlayRoom extends VBox {

	public enum GridContent {
		EMPTY, FRANKSPAWN, WALL, NUKEKEY
	};

	public enum Directions {
		NORTH, EAST, SOUTH, WEST, STAY
	};

	private GridContent[][] grid;

//	private Point2D frankVelocity;
	private Point2D startLocation;
	private GameView gameview;
	private int rowCount = 25;
	private int columnCount = 38;
	private Point2D previousLocation;
	private Frank frank; 

	public PlayRoom(Model model) {
		this.setStyle("-fx-background-color: #b1e6ec;");
		try {
			this.gameview = new GameView(this);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		this.getChildren().add(gameview);
	}

	public void StartNewGame(String LevelName) {
		this.RenderLevel(LevelName);
		this.frank = new Frank(startLocation);
		gameview.initializeGrid();
		// Här ska vi sen lägga in Booleans som hjälper gör att vi kan vinna/förlora
	}

	public void RenderLevel(String LevelName) {

		File myFile = new File(LevelName);
		Scanner scanner1 = null;

		try {
			scanner1 = new Scanner(myFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Scanner scanner2 = null;

		try {
			scanner2 = new Scanner(myFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		grid = new GridContent[rowCount][columnCount];

		int frankRow = 0;
		int frankColumn = 0; // implementeras sen när vi läser av banan från en textfil, genom en if sats i
								// for loopen nedan
		int row = 0;

		while (scanner2.hasNextLine()) {
			int column = 0;
			String readRow = scanner2.nextLine();
			if (row == rowCount) {
				break;
			}

			while (true) {

				if (column == columnCount) {
					break;
				}
				GridContent thisValue;
				if (readRow.charAt(column) == 'W') {
					thisValue = GridContent.WALL;
				} else if (readRow.charAt(column) == 'F') {
					thisValue = GridContent.FRANKSPAWN;
					frankRow = row;
					frankColumn = column;
				} else if (readRow.charAt(column) == 'N') {
					thisValue = GridContent.NUKEKEY;
				} else {
					thisValue = GridContent.EMPTY;
				}
				grid[row][column] = thisValue;
				column++;
			}
			row++;
		}

		//frankVelocity = new Point2D(0, 0);
		startLocation = new Point2D(frankRow, frankColumn);
		//previousLocation = frankLocation;
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
		
		Point2D futurefrankVelocity = changeVelocity(direction);
		Point2D futurefrankLocation = frank.getFrankLocation().add(futurefrankVelocity);
		
		if (grid[(int) futurefrankLocation.getX()][(int) futurefrankLocation.getY()] == GridContent.WALL) {
			futurefrankLocation = previousLocation;//	public Point2D getFrankLocation() {
//			return frankLocation;
//			}
		//
//			public void setFrankLocation(Point2D frankLocation) {
//				this.frankLocation = frankLocation;
//			}
		}
		
		frank.setFrankVelocity(futurefrankVelocity);
//		frankVelocity = futurefrankVelocity;
		frank.setFrankLocation(futurefrankLocation);
		previousLocation = frank.getFrankLocation();
	}

	public Frank getFrank() {
		return frank;
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
		gameview.update(this);
	}

//	public Point2D getFrankLocation() {
//		return frankLocation;
//	}
//
//	public void setFrankLocation(Point2D frankLocation) {
//		this.frankLocation = frankLocation;
//	}
	  public GridContent getCellValue(int row, int column) {
	        if (row >= 0 && row < this.grid.length && column >= 0 && column < this.grid[0].length) {
	        return this.grid[row][column];
	    } else  {
	    	return null;
	    }
	  }

	public void giveFrank() {
		
	}


// 4. Nuklearcard
// 5. (vinna)
// 5. säkrehetslampa
// 6. panel
// 7. Vakt
// 8. emp
}

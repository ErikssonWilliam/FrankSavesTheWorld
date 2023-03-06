package myLogics;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import myGraphics.EventHandler;
import myGraphics.Frame;
import myGraphics.GameView;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.fxml.FXML;
import javafx.geometry.Point2D;

public class PlayRoom extends VBox {

	public enum GridContent {
		EMPTY, FRANKSPAWN, WALL, NUKEKEY, DONE, ENEMY, FLASH
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
	private int level = 0;
	private Frank frank;
	private boolean winGame;
	private boolean gameOver;
	private Boolean isSecondLevel = false;
	private statePlay sP;
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	private int updateCount = 0;
	private EventHandler eH;

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	public ArrayList<Enemy> getEnemies() {
		return enemies;
	}

	public PlayRoom(Model model) {
		this.setStyle("-fx-background-color: #add8e6;");

		try {
			this.gameview = new GameView(this);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
			this.getChildren().add(gameview);
		
	}

	public void StartNewGame(String LevelName) {
		this.winGame = false;
		this.gameOver = false;
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
				} else if (readRow.charAt(column) == 'D') {
					thisValue = GridContent.DONE;
				} else if (readRow.charAt(column) == '>') {
					thisValue = GridContent.ENEMY;
					enemies.add(new Enemy(new Point2D(row, column), Directions.EAST, 3, this, "east"));
				} else if (readRow.charAt(column) == '<') {
					thisValue = GridContent.ENEMY;
					enemies.add(new Enemy(new Point2D(row, column), Directions.WEST, 4, this, "west"));
				} else if (readRow.charAt(column) == 'v') {
					thisValue = GridContent.ENEMY;
					enemies.add(new Enemy(new Point2D(row, column), Directions.SOUTH, 2, this, "south"));
				} else if (readRow.charAt(column) == 'A') {
					thisValue = GridContent.ENEMY;
					enemies.add(new Enemy(new Point2D(row, column), Directions.NORTH, 1, this, "north"));
				} else {
					thisValue = GridContent.EMPTY;
				}
				grid[row][column] = thisValue;
				column++;
			}
			row++;
		}

		startLocation = new Point2D(frankRow, frankColumn);
	}

	public void setGrid(GridContent[][] grid) {
		this.grid = grid;
	}

	public Frank getFrank() {
		return frank;
	}

	public GridContent[][] getGrid() {
		return grid;
	}

	public Point2D changeVelocity(Directions direction) {
		// System.out.println("Inne i changeVelocity");
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

	public void update(statePlay sP) {
		this.sP = sP;
		moveEnemies();
		gameview.update(this);
		try {
			sP.ChangeLevel();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public GridContent getCellValue(int row, int column) {
		if (row >= 0 && row < this.grid.length && column >= 0 && column < this.grid[0].length) {
			return this.grid[row][column];
		} else {
			return null;
		}
	}

	public void giveFrank() {
		if (this.getCellValue((int) frank.getFrankLocation().getX(),
				(int) frank.getFrankLocation().getY()) == GridContent.NUKEKEY) {
			frank.setHasNuclearCode(true);
			update(sP);
		}

	}
//
//	public void ChangeLevel() throws FileNotFoundException {
//
//		if ((this.getCellValue((int) frank.getFrankLocation().getX(),
//				(int) frank.getFrankLocation().getY()) == GridContent.DONE) && frank.getHasNuclearCode()) {
////System.out.println(isSecondLevel);
////System.out.println(winGame);
//			if (isSecondLevel) {
//				setWinGame(true);
//			this.eH = new EventHandler(this);
//			this.getChildren().add(eH);
//		
//
//			} else {
//
//				enemies.clear();
//				setIsSecondLevel(true);
//				// borde ligga i stateplay
//				sP.initialize();
//			}
//		}
//	}

	public boolean isWinGame() {
		return winGame;
	}

	public void setWinGame(boolean winGame) {
		this.winGame = winGame;
	}

	public void moveEnemies() { // Uppdaterar fiendernas rörelser
		updateCount += 1;

		for (int i = 0; i < enemies.size(); i++) {
			if (updateCount % 60 == 0 && enemies.get(i).getType() == "east") {
				enemies.get(i).moveEnemy();
			} else if (updateCount % 30 == 0 && enemies.get(i).getType() == "west") {
				enemies.get(i).moveEnemy();
			} else if (updateCount % 80 == 0 && enemies.get(i).getType() == "south") {
				enemies.get(i).moveEnemy();
			} else if (updateCount % 20 == 0 && enemies.get(i).getType() == "north") {
				enemies.get(i).moveEnemy();
			}
		}
	}

	public Boolean getIsSecondLevel() {
		return isSecondLevel;
	}

	public void setIsSecondLevel(Boolean isSecondLevel) {
		this.isSecondLevel = isSecondLevel;
	}

// 4. Nuklearcard
// 5. (vinna)
// 5. säkrehetslampa
// 6. panel
// 7. Vakt
// 8. emp
}

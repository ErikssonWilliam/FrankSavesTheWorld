package myLogics;

import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import myGraphics.GameView;
import myGraphics.showResult;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.geometry.Point2D;

/**
 * Class that handles an active game that continuously
 * gets updated. also handles certain game-deciding happenings.
 * @author wiler441
 *
 */
public class PlayRoom extends VBox {

	/**
	 * Uses public enums to efficiently transfer 
	 * certain contents and directions within the package
	 * @author wiler441
	 *
	 */
	public enum GridContent {
		EMPTY, FRANKSPAWN, WALL, NUKEKEY, DONE, ENEMY, FLASH, CAMERA, CONTROLPANEL, EMP
	};

	public enum Directions {
		NORTH, EAST, SOUTH, WEST, STAY
	};

	/**
	 * Uses a grid.
	 * Could list the cameras and enemies under a superclass and thereby 
	 * a common arraylist, but in this case we decided not to. The classes
	 * of Enemy and Camera does not have much in common. Would be smart 
	 * when implementing more enemies (can kill Frank).
	 */
	private GridContent[][] grid;
	private Point2D startLocation;
	private GameView gameview;
	private int rowCount = 25;
	private int columnCount = 38;
	private Frank frank;
	private Boolean isSecondLevel = false;
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	private ArrayList<Camera> cameras = new ArrayList<Camera>();
	private int updateCount = 0;
	private Model model;
	private int empCount = 0;
	private boolean activeEMP = false;


	public PlayRoom(Model model) {
		this.setStyle("-fx-background-color: #add8e6;");
		this.model = model;
		try {
			this.gameview = new GameView(this);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		this.getChildren().add(gameview);
	}

	/**
	 * Starts a new game
	 * @param LevelName
	 */
	public void StartNewGame(String LevelName) {
		this.RenderLevel(LevelName);
		this.frank = new Frank(startLocation);
		gameview.initializeGrid();
	}

	/**
	 * Renders a new level and assigns the correct content to correct spot
	 * by reading a properly written game-design map 
	 * @param LevelName
	 */
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
		int frankColumn = 0; 
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
				GridContent thisValue = null;
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
				} else if (readRow.charAt(column) == 'P') {
					thisValue = GridContent.CONTROLPANEL;
				} else if (readRow.charAt(column) == 'e') {
					thisValue = GridContent.EMP;
				} else if (readRow.charAt(column) == 'C') {
					thisValue = GridContent.CAMERA;
					Camera camera = new Camera(new Point2D(row, column), this);
					cameras.add(camera);
					camera.initializeCam();
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
				} else if (readRow.charAt(column) == 'E') {
					thisValue = GridContent.EMPTY;
				}
				grid[row][column] = thisValue;
				column++;
			}
			row++;
		}
		startLocation = new Point2D(frankRow, frankColumn);
	}

	/**
	 * handles movement in the game
	 * @param direction
	 * @return
	 */
	public Point2D changeVelocity(Directions direction) {
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

	/**
	 * updates the game
	 * @param sP
	 */
	public void update(PlayState pS) {
		moveEnemies();
		gameview.update(this);
		statusofFrank();
		try {
			pS.ChangeLevel();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/** 
	 * Checks if frank dies or uses (by default) the control panel
	 */
	private void statusofFrank() {
		if (grid[(int) frank.getFrankLocation().getX()][(int) frank.getFrankLocation().getY()] == GridContent.FLASH ||	
				grid[(int) frank.getFrankLocation().getX()][(int) frank.getFrankLocation().getY()] == GridContent.ENEMY ) {
			try {
				this.DisplayResult(false);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} else if (grid[(int) frank.getFrankLocation().getX()-1 ][(int) frank.getFrankLocation().getY()
				] == GridContent.CONTROLPANEL) {
			for (int i = 0; i < cameras.size(); i++) {
				cameras.get(i).killCam();
			}
		} 
	}

	/**
	 * Handles the pick-up-able items by setting specific booleans in Frank
	 */
	public void giveFrank() {

		if (this.getCellValue((int) frank.getFrankLocation().getX(),
				(int) frank.getFrankLocation().getY()) == GridContent.NUKEKEY) {
			frank.getItems().add(new NukeKey());
			grid[(int) frank.getFrankLocation().getX()][(int) frank.getFrankLocation().getY()] = GridContent.EMPTY;
		} else if (this.getCellValue((int) frank.getFrankLocation().getX(),
				(int) frank.getFrankLocation().getY()) == GridContent.EMP) {
			frank.getItems().add(new EMP(this));
			grid[(int) frank.getFrankLocation().getX()][(int) frank.getFrankLocation().getY()] = GridContent.EMPTY;
		}
	}

	/**
	 * This method handles the potential win/loss of the game
	 * and changes the state
	 * @param win
	 * @throws FileNotFoundException
	 */
	public void DisplayResult(Boolean win) throws FileNotFoundException {
		
		HBox frame = new HBox();
		frame.getChildren().add(new showResult(this, model, win));
		model.getMain().setScene(new Scene(frame));
		model.setPlayState(null);
	}

	/**
	 * Updates the enemies movement dependent on which type of enemy it is
	 * Also handles the emp usage time to relate to the enemies movement 
	 * since the emp only affects the enemies
	 */
	public void moveEnemies() {
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
		if (activeEMP) {
			empCount += 1;
		}
		if (empCount == 400) {
			activeEMP = false;
		}
	}
	
	/**
	 * Getters & Setters
	 */
	public GridContent getCellValue(int row, int column) {
		if (row >= 0 && row < this.grid.length && column >= 0 && column < this.grid[0].length) {
			return this.grid[row][column];
		} else {
			return null;
		}
	}
	
	public boolean isActiveEMP() {
		return activeEMP;
	}

	public void setActiveEMP(boolean activeEMP) {
		this.activeEMP = activeEMP;
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
	public ArrayList<Enemy> getEnemies() {
		return enemies;
	}

	public Boolean getIsSecondLevel() {
		return isSecondLevel;
	}

	public void setIsSecondLevel(Boolean isSecondLevel) {
		this.isSecondLevel = isSecondLevel;
	}
}

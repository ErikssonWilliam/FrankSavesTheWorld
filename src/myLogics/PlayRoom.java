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
	 * when implementing more enemies (can kill Frank). The same thing 
	 * applies to controlpanel. But since Frank can't pick up this powerup
	 * we decided not to include this in an array of objects, since a lot 
	 * of empty classes would be made. But this is certainly an option when 
	 * implementing more objects
	 */
	private GridContent[][] grid;
	private Point2D startLocation;
	private GameView gameview;
	private int rowCount = 25;
	private int columnCount = 38;
	private Frank frank;
	private Boolean isSecondLevel = false;
	private int updateCount = 0;
	private Model model;
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	private ArrayList<Camera> cameras = new ArrayList<Camera>();
	private ArrayList<ControlPanel> cPanels = new ArrayList<ControlPanel>();
	private ArrayList<Item> items = new ArrayList<Item>();

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
	 * @throws FileNotFoundException 
	 */
	public void StartNewGame(String LevelName) throws FileNotFoundException {
		this.RenderLevel(LevelName);
		this.frank = new Frank(startLocation);
		gameview.initializeGrid();
	}

	/**
	 * Renders a new level and assigns the correct content to correct spot
	 * by reading a properly written game-design map 
	 * @param LevelName
	 * @throws FileNotFoundException 
	 */
	public void RenderLevel(String LevelName) throws FileNotFoundException {

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
					items.add(new NukeKey(new Point2D(row, column), this));
				} else if (readRow.charAt(column) == 'D') {
					thisValue = GridContent.DONE;
				} else if (readRow.charAt(column) == 'P') {
					thisValue = GridContent.CONTROLPANEL;
					ControlPanel cPanel = new ControlPanel(new Point2D(row, column), this);
					cPanels.add(cPanel);
				} else if (readRow.charAt(column) == 'e') {
					thisValue = GridContent.EMP;
					items.add(new EMP(this, new Point2D(row, column)));
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
		updateView();
		statusofFrank();
		try {
			pS.ChangeLevel();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/*'
	 * Updateview updates the view of the playing board
	 * as well as the objects in the game.
	 */
	private void updateView() {
		gameview.update(this);
		for (Enemy enemy : enemies) {
			enemy.drawYourself();
		}
		for (Camera camera : cameras) {
			camera.drawYourself();
		}
		for (ControlPanel cpanel : cPanels) {
			cpanel.drawYourself();
		}
		for (Item item : items) {
			item.drawYourself();
		}
		frank.drawYourself(this);
	}

	/** 
	 * Checks if frank dies or uses (by default) the control panel by updating the cPanel
	 */
	private void statusofFrank() {
		if (grid[(int) frank.getFrankLocation().getX()][(int) frank.getFrankLocation().getY()] == GridContent.FLASH ||	
				grid[(int) frank.getFrankLocation().getX()][(int) frank.getFrankLocation().getY()] == GridContent.ENEMY ) {
			try {
				this.DisplayResult(false);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		for (int i = 0; i < cPanels.size(); ++i) {
			cPanels.get(i).update();
		}
	}

	/**
	 * Handles the pick-up-able items for Frank
	 */
	public void giveFrank() {

		if (this.getCellValue((int) frank.getFrankLocation().getX(),
				(int) frank.getFrankLocation().getY()) == GridContent.NUKEKEY
				|| this.getCellValue((int) frank.getFrankLocation().getX(),
						(int) frank.getFrankLocation().getY()) == GridContent.EMP) {
			for (Item item : items) {
				if (frank.getFrankLocation().getX() == item.getLocation().getX()
						&& frank.getFrankLocation().getY() == item.getLocation().getY()) {
					frank.getItems().add(item);
					items.remove(items.indexOf(item));
					grid[(int) frank.getFrankLocation().getX()][(int) frank.getFrankLocation()
							.getY()] = GridContent.EMPTY;
					break;
				}
			}
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

	public ArrayList<Camera> getCameras() {
		return cameras;
	}

	public GameView getGameview() {
		return gameview;
	}
	
}

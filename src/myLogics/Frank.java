package myLogics;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import myLogics.PlayRoom.Directions;
import myLogics.PlayRoom.GridContent;

/**
 * Frank handles the certain aspects of the object Frank.
 * Location, movement, emp-usage & items. Powerup EMP
 * [KOMP]: implemented items.
 * @author wiler441
 */
public class Frank {

	private Boolean usedEMP = false;
	private Point2D frankLocation;
	private Point2D frankVelocity;
	private Point2D previousLocation;
	private Image frank;
	
	/**
	 * Items that Frank can pick up during the game. 
	 */
	private ArrayList<Item> items = new ArrayList<Item>();
    
	public Frank(Point2D startLocation) throws FileNotFoundException {
		this.frankLocation = startLocation;
		this.frank = new Image(
				new FileInputStream("/home/wiler441/Documents/tdde10_project/Frank_Pictures/Frank1.png"));

	}

	/**
	 * Handles movement of Frank
	 * @param key
	 * @param pr
	 */
	public void moveTo(KeyCode key, PlayRoom pR) {
		
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
		
		Point2D futurefrankVelocity = pR.changeVelocity(direction);
		Point2D futurefrankLocation = frankLocation.add(futurefrankVelocity);
		
		if (pR.getGrid()[(int) futurefrankLocation.getX()][(int) futurefrankLocation.getY()] == GridContent.WALL || 
				pR.getGrid()[(int) futurefrankLocation.getX()][(int) futurefrankLocation.getY()] == GridContent.CAMERA ||
				pR.getGrid()[(int) futurefrankLocation.getX()][(int) futurefrankLocation.getY()] == GridContent.CONTROLPANEL ) {
			futurefrankLocation = previousLocation;
		}		
		setFrankVelocity(futurefrankVelocity);
		setFrankLocation(futurefrankLocation);
		previousLocation = getFrankLocation();
	}
	
	/**
	 * Handles the emp-usage
	 */
	public void useEmp() {
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i) instanceof EMP) {
				items.get(i).use();
				items.set(i, null);
				break;
			}
		}	
	}
	
	/**
	 * Checks if Frank has the NuclearKey
	 * @return
	 */
	public boolean hasNuclearKey() {
		for (int i=0; i< items.size(); i++) {
			if (items.get(i) instanceof NukeKey) {
		      return true;
			}
	}
		return false;
	}
	
	public void drawYourself(PlayRoom pR) {
		pR.getGameview().getGridViews()[(int) frankLocation.getX()][(int) frankLocation.getY()].setImage(frank);
	}
		
	/**
	 * Getters and setters
	 * @return
	 */
	public ArrayList<Item> getItems() {
		return items;
	}

	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}

	public Point2D getFrankLocation() {
		return frankLocation;
	}

	public void setFrankLocation(Point2D frankLocation) {
		this.frankLocation = frankLocation;
	}

	public Point2D getFrankVelocity() {
		return frankVelocity;
	}

	public void setFrankVelocity(Point2D frankVelocity) {
		this.frankVelocity = frankVelocity;
	}

	public Boolean getUsedEMP() {
		return usedEMP;
	}

	public void setUsedEMP(Boolean usedEMP) {
		this.usedEMP = usedEMP;
	}
}

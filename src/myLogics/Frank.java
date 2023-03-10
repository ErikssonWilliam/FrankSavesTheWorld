package myLogics;

import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import myLogics.PlayRoom.Directions;
import myLogics.PlayRoom.GridContent;

/**
 * Frank handles the certain aspects of the object Frank.
 * Location, movement, emp-usage & nuclearkey
 * @author wiler441
 */
public class Frank {

	private Boolean hasNuclearCode = false;
	private Boolean hasEMP = false;
	private Boolean usedEMP = false;
	private Point2D frankLocation;
	private Point2D frankVelocity;
	private Point2D previousLocation;
    
	public Frank(Point2D startLocation) {
		this.frankLocation = startLocation;
	}

	/**
	 * Handles movement of Frank
	 * @param key
	 * @param pr
	 */
	public void moveTo(KeyCode key, PlayRoom pr) {
		
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
		
		Point2D futurefrankVelocity = pr.changeVelocity(direction);
		Point2D futurefrankLocation = frankLocation.add(futurefrankVelocity);
		
		if (pr.getGrid()[(int) futurefrankLocation.getX()][(int) futurefrankLocation.getY()] == GridContent.WALL || 
				pr.getGrid()[(int) futurefrankLocation.getX()][(int) futurefrankLocation.getY()] == GridContent.CAMERA ||
				pr.getGrid()[(int) futurefrankLocation.getX()][(int) futurefrankLocation.getY()] == GridContent.CONTROLPANEL ) {
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
		if (getHasEMP()) {
			setUsedEMP(true);
		}
	}
		
	/**
	 * Getters and setters
	 * @return
	 */
	public Boolean getHasEMP() {
		return hasEMP;
	}

	public void setHasEMP(Boolean hasEMP) {
		this.hasEMP = hasEMP;
	}

	public Boolean getHasNuclearCode() {
		return hasNuclearCode;
	}

	public void setHasNuclearCode(Boolean hasNuclearCode) {
		this.hasNuclearCode = hasNuclearCode;
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

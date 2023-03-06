package myLogics;

import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import myLogics.PlayRoom.Directions;
import myLogics.PlayRoom.GridContent;

public class Frank {

	private Boolean hasNuclearCode = false;
	private Boolean hasEMP = false;
	private Point2D frankLocation;
	private Point2D frankVelocity;
	private Point2D previousLocation;
    private PlayRoom pr;
    
	
	public Frank(Point2D startLocation) {
		this.frankLocation = startLocation;
	}

	public void moveTo(KeyCode key, PlayRoom pr) {
		
		this.pr =pr;

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
		
		Point2D futurefrankVelocity = pr.changeVelocity(direction);
		Point2D futurefrankLocation = this.getFrankLocation().add(futurefrankVelocity);
		
		if (pr.getGrid()[(int) futurefrankLocation.getX()][(int) futurefrankLocation.getY()] == GridContent.WALL || 
				pr.getGrid()[(int) futurefrankLocation.getX()][(int) futurefrankLocation.getY()] == GridContent.ENEMY ) {
			futurefrankLocation = previousLocation;//	public Point2D getFrankLocation() {
//
		}
		
		setFrankVelocity(futurefrankVelocity);
//		frankVelocity = futurefrankVelocity;
		setFrankLocation(futurefrankLocation);
		previousLocation = getFrankLocation();
		pr.giveFrank();
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

}

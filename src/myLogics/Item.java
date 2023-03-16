package myLogics;

import javafx.geometry.Point2D;

/**
 * Gathers the respective items in the game
 * and is a good starting ground for implementing
 * new items of which Frank can pick up!
 * Does not have many methods since the items
 * does not do to much at this point.
 * @author wiler441
 *
 */
public abstract class Item {
	
	private Point2D location;
	
	public Item(Point2D location, PlayRoom pR) {
		this.location = location;
	}
	
	public abstract void use();
	
	public abstract void drawYourself();

	public Point2D getLocation() {
		return location;
	}

	public void setLocation(Point2D location) {
		this.location = location;
	}

}

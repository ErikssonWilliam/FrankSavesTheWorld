package myLogics;

import javafx.geometry.Point2D;
import myLogics.PlayRoom.GridContent;

/**
 * Handles Camera and it's belonging flash(detectionArea)
 * @author wiler441
 *
 */
public class Camera {
	private Point2D location;
	private PlayRoom pr;

	public Camera(Point2D location, PlayRoom pr) {
		this.location = location;
		this.pr = pr;
	}

	/**
	 * Two similar methods for initialising and shutting of the camera
	 */
	public void initializeCam() {
		for (int row = -1; row < 1; row++) {
			for (int column = -3; column < 0; column++) {
				pr.getGrid()[(int) location.getX() + row][(int) location.getY() + column] = GridContent.FLASH;
			}
		}
	}

	public void killCam() {
		for (int row = -1; row < 1; row++) {
			for (int column = -3; column < 0; column++) {
				pr.getGrid()[(int) location.getX() + row][(int) location.getY() + column] = GridContent.EMPTY;
			}
		}
	}
}

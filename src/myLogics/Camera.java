package myLogics;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import myLogics.PlayRoom.GridContent;

/**
 * Handles Camera and it's belonging flash(detectionArea)
 * @author wiler441
 *
 */
public class Camera {
	private Point2D location;
	private PlayRoom pR;
	private Image camera;

	public Camera(Point2D location, PlayRoom pR) throws FileNotFoundException {
		this.location = location;
		this.pR = pR;
		this.camera = new Image(
				new FileInputStream("/home/wiler441/Documents/tdde10_project/Frank_Pictures/camera.png"));
	}

	/**
	 * Two similar methods for initialising and shutting of the camera
	 */
	public void initializeCam() {
		for (int row = -1; row < 1; row++) {
			for (int column = -3; column < 0; column++) {
				pR.getGrid()[(int) location.getX() + row][(int) location.getY() + column] = GridContent.FLASH;
			}
		}
	}

	public void killCam() {
		for (int row = -1; row < 1; row++) {
			for (int column = -3; column < 0; column++) {
				pR.getGrid()[(int) location.getX() + row][(int) location.getY() + column] = GridContent.EMPTY;
			}
		}
	}

	public void drawYourself() {
		pR.getGameview().getGridViews()[(int) location.getX()][(int) location.getY()].setImage(camera);
	}

}

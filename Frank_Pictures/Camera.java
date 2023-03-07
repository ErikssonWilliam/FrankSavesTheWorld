package myLogics;

import javafx.geometry.Point2D;

import myLogics.PlayRoom.GridContent;

public class Camera {


	private Point2D location;
	private PlayRoom pr;
	
	public Camera(Point2D location, PlayRoom pr) {
		this.location = location;	
		this.pr = pr;
	}
	
	public void initializeCam() {
		for (int column=-2; column<2; column++) {
			for (int row=-2; row<2; row++) {
				if (pr.getGrid()[(int) location.getX()+row][(int) location.getY()+row] == GridContent.WALL) {
					break;
				}
				pr.getGrid()[(int) location.getX()+row][(int) location.getY()+row] = GridContent.FLASH;
			}
		}
	}
	
	public void killCam() {
		for (int column=-2; column<2; column++) {
			for (int row=-2; row<2; row++) {
				if (pr.getGrid()[(int) location.getX()+row][(int) location.getY()+row] == GridContent.WALL) {
					break;
				}
				pr.getGrid()[(int) location.getX()+row][(int) location.getY()+row] = GridContent.EMPTY;
			}
		}
	}
}

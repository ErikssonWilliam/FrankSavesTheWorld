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
		
		for (int row=-1; row<1; row++) {
			for (int column=-3; column<0; column++) {

					System.out.println("skapa flash");
				pr.getGrid()[(int) location.getX()+row][(int) location.getY()+column] = GridContent.FLASH;
		}
		}
	}

	public void killCam() {
		for (int row=-1; row<1; row++) {
			for (int column=-3; column<0; column++) {
					System.out.println("skapa flash");
				pr.getGrid()[(int) location.getX()+row][(int) location.getY()+column] = GridContent.EMPTY;
		}
		}
	}
}

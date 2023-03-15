package myLogics;

import javafx.geometry.Point2D;

public class ControlPanel {

	private Point2D location;
	private PlayRoom pR;
	
	public ControlPanel(Point2D location, PlayRoom pR) {
	this.location = location;
	this.pR = pR;
}
	public void update() {
		 if (pR.getFrank().getFrankLocation().getX()-1 == location.getX() && 
				 pR.getFrank().getFrankLocation().getY() == location.getY()) {				
			 killCams();
		 }
	}
	
	public void killCams() {
		for (int i = 0; i < pR.getCameras().size(); i++) {
			pR.getCameras().get(i).killCam();
		}
	}
}

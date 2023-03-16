package myLogics;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;

public class ControlPanel {

	private Point2D location;
	private PlayRoom pR;
	private Image controlpanel;
	
	public ControlPanel(Point2D location, PlayRoom pR) throws FileNotFoundException {
	this.location = location;
	this.pR = pR;
	this.controlpanel = new Image(
			new FileInputStream("/home/wiler441/Documents/tdde10_project/Frank_Pictures/controlpanel1.png"));
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
	public void drawYourself() {
		pR.getGameview().getGridViews()[(int) location.getX()][(int) location.getY()].setImage(controlpanel);
	}
}

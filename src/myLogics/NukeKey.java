package myLogics;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;

/**
 * Works as a gateway to win. Does not really contribute to a direct win through
 * methods but could be further built on. 
 * [Komp] denna powerup valde vi att inte inkludera vinstfunktion i
 * 
 * @author wiler441
 *
 */
public class NukeKey extends Item {

	private Point2D location;
	private Image nukeKey;
	private PlayRoom pR;

	public NukeKey(Point2D location, PlayRoom pR) throws FileNotFoundException {
		super(location,pR);
	this.location = location;
		this.pR = pR;
		this.nukeKey = new Image(
				new FileInputStream("/home/wiler441/Documents/tdde10_project/Frank_Pictures/nuclearkey.png"));
	}

	@Override
	public void use() {
		// TODO Auto-generated method stub

	}

	@Override
	public void drawYourself() {
		pR.getGameview().getGridViews()[(int) location.getX()][(int) location.getY()].setImage(nukeKey);
	}
}
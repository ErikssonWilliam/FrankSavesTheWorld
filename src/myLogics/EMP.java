package myLogics;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;

/**
 * Since playroom has the responsibility to check whether emp is 
 * active this simply changes how emp is active in playroom
 * and thereby affecting the enemies, (who shouldn't care about
 * if Frank has one or not)
 * @author wiler441
 *
 */
public class EMP extends Item {

	private PlayRoom pR;
	private Point2D location;
	private Image emp;
	
	public EMP(PlayRoom pR, Point2D location) throws FileNotFoundException {
		super(location, pR);
		this.pR = pR;
		this.location = location;
		this.emp = new Image(new FileInputStream("/home/wiler441/Documents/tdde10_project/Frank_Pictures/emp.png"));
	}
	
	@Override
	public void use() {
		for (int i = 0; i < pR.getEnemies().size(); ++i) {
			pR.getEnemies().get(i).stunEnemy(true, 10);
		}
		
	}
	@Override
	public void drawYourself() {
		pR.getGameview().getGridViews()[(int) location.getX()][(int) location.getY()].setImage(emp);
	}
}
package myLogics;

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
	
	public EMP(PlayRoom pR) {
		this.pR = pR;
	}
	@Override
	public void use() {
	pR.setActiveEMP(true);
	}
}
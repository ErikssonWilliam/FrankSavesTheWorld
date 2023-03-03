package myLogics;

import javafx.geometry.Point2D;

public class Frank {

	private Boolean hasNuclearCode = false;
	private Boolean hasEMP = false;
	private Point2D frankLocation;
	private Point2D frankVelocity;
	
	public Frank(Point2D startLocation) {
		this.frankLocation = startLocation;
	}

	public Boolean getHasNuclearCode() {
		return hasNuclearCode;
	}

	public void setHasNuclearCode(Boolean hasNuclearCode) {
		this.hasNuclearCode = hasNuclearCode;
	}

	public Point2D getFrankLocation() {
		return frankLocation;
	}

	public void setFrankLocation(Point2D frankLocation) {
		this.frankLocation = frankLocation;
	}

	public Point2D getFrankVelocity() {
		return frankVelocity;
	}

	public void setFrankVelocity(Point2D frankVelocity) {
		this.frankVelocity = frankVelocity;
	}

}

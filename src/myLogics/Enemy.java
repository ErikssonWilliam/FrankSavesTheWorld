package myLogics;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

import javafx.geometry.Point2D;
import myGraphics.GameResult;
import myLogics.PlayRoom.Directions;
import myLogics.PlayRoom.GridContent;

public class Enemy {

	private Point2D enemyLocation;
	private Point2D startLocation;

	private Directions enemyDirection;
	private PlayRoom pr;
	private int steps;
	private int stepCount;
	private Point2D previousLocation;
	private String type;
	private ArrayList<Point2D> flashLight;
	private ArrayList<Point2D> oldFlashLight;
	private Boolean firstLight = true;
	private Model model;

	public Enemy(Point2D startLocation, Directions direction, int steps, PlayRoom pr, String type, Model model) {
		this.enemyLocation = startLocation;
		this.startLocation = startLocation;
		this.enemyDirection = direction;
		this.steps = steps;
		this.previousLocation = startLocation;
		this.pr = pr;
		this.type = type;
		this.flashLight = new ArrayList<Point2D>();
		this.oldFlashLight = new ArrayList<Point2D>();
		this.model = model;

	}

	public Point2D getStartLocation() {
		return startLocation;
	}

	public String getType() {
		return type;
	}

	public void moveEnemy() {
		if (stepCount == steps) {
			enemyDirection = opposite(enemyDirection);
			stepCount = 0;
		}

		Point2D futureEnemyVelocity = pr.changeVelocity(enemyDirection); // Här blir det fel får negtiva index
		Point2D futureEnemyLocation = enemyLocation.add(futureEnemyVelocity);

		if (pr.getGrid()[(int) futureEnemyLocation.getX()][(int) futureEnemyLocation.getY()] == GridContent.WALL) {
			futureEnemyLocation = previousLocation;
		}

		enemyLocation = futureEnemyLocation;
		pr.getGrid()[(int) previousLocation.getX()][(int) previousLocation.getY()] = GridContent.EMPTY;
		previousLocation = enemyLocation;

		pr.getGrid()[(int) enemyLocation.getX()][(int) enemyLocation.getY()] = GridContent.ENEMY;
		stepCount += 1;
		
		try {
			flashLight(enemyLocation);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}


	public void setStepCount(int stepCount) {
		this.stepCount = stepCount;
	}

	public void flashLight(Point2D enemyLocation) throws FileNotFoundException {

		removePreviousFlash(oldFlashLight);
		if (!pr.getFrank().getUsedEMP()) {
			
		
		for (int i = 0; i < 3; i++) {
			if (enemyDirection == Directions.WEST) {
				flashLight.add(i, enemyLocation.add(0, -i-1));
			} else if (enemyDirection == Directions.EAST) {
				flashLight.add(i, enemyLocation.add(0, i+1));
			} else if (enemyDirection == Directions.NORTH) {
				flashLight.add(i, enemyLocation.add(-i-1, 0));
			} else if (enemyDirection == Directions.SOUTH) {
				flashLight.add(i, enemyLocation.add(i+1, 0));
			}
			if (pr.getGrid()[(int) flashLight.get(i).getX()][(int) flashLight.get(i).getY()] == GridContent.WALL) {
				break;
			}
			pr.getGrid()[(int) flashLight.get(i).getX()][(int) flashLight.get(i).getY()] = GridContent.FLASH;
		}
		}
		oldFlashLight = flashLight;
	}

	private void removePreviousFlash(ArrayList<Point2D> oldFlashLight) {

		for (int i = 0; i < oldFlashLight.size(); i++) {
			if (pr.getGrid()[(int) oldFlashLight.get(i).getX()][(int) oldFlashLight.get(i).getY()] == GridContent.WALL) {
				break;
			}
			if (pr.getGrid()[(int) oldFlashLight.get(i).getX()][(int) oldFlashLight.get(i).getY()] != GridContent.ENEMY) {
			pr.getGrid()[(int) oldFlashLight.get(i).getX()][(int) oldFlashLight.get(i).getY()] = GridContent.EMPTY;
		}
	}
	}

	public Point2D getEnemyLocation() {
		return enemyLocation;
	}

	public void setEnemyLocation(Point2D enemyLocation) {
		this.enemyLocation = enemyLocation;
	}

	public Directions opposite(Directions direction) {
		Directions oppDirection = null;
		if (direction == Directions.WEST) {
			oppDirection = Directions.EAST;
		} else if (direction == Directions.EAST) {
			oppDirection = Directions.WEST;
		} else if (direction == Directions.NORTH) {
			oppDirection = Directions.SOUTH;
		} else if (direction == Directions.SOUTH) {
			oppDirection = Directions.NORTH;
		}
		return oppDirection;

	}

}

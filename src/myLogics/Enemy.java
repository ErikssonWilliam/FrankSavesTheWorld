package myLogics;

import java.util.ArrayList;
import java.util.Arrays;

import javafx.geometry.Point2D;
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
	private ArrayList <Point2D> flashLight;
	private Boolean firstLight = true;

	public Enemy(Point2D startLocation, Directions direction, int steps, PlayRoom pr, String type) {
		this.enemyLocation = startLocation;
		this.startLocation = startLocation;
		this.enemyDirection = direction;
		this.steps = steps;
		this.previousLocation = startLocation;
		this.pr = pr;
		this.type = type;
		this.flashLight = new ArrayList<Point2D>();
	
	}

	public Point2D getStartLocation() {
		return startLocation;
	}

	public String getType() {
		return type;
	}

	public void moveEnemy() {
		System.out.println(steps);
		System.out.println(stepCount);
		if (stepCount == steps) {
			enemyDirection = opposite(enemyDirection);
			stepCount = 0;
		}


		Point2D futureEnemyVelocity = pr.changeVelocity(enemyDirection); // Här blir det fel får negtiva index
		System.out.println(futureEnemyVelocity);
		Point2D futureEnemyLocation = enemyLocation.add(futureEnemyVelocity);
		System.out.println(futureEnemyLocation);

		if (pr.getGrid()[(int) futureEnemyLocation.getX()][(int) futureEnemyLocation.getY()] == GridContent.WALL) {
			futureEnemyLocation = previousLocation;
		}

		enemyLocation = futureEnemyLocation;
		pr.getGrid()[(int) previousLocation.getX()][(int) previousLocation.getY()] = GridContent.EMPTY;
		previousLocation = enemyLocation;
	
		pr.getGrid()[(int) enemyLocation.getX()][(int) enemyLocation.getY()] = GridContent.ENEMY;
		stepCount += 1;
		System.out.println("EmenyLocation " + enemyLocation);
		flashLight(enemyLocation, futureEnemyVelocity);

	}
	public void flashLight(Point2D enemyLocation, Point2D futureEnemyVelocity) {
	
		Boolean hitWall = false;
		ArrayList <Point2D> oldFlashLight = new ArrayList<Point2D>();
		if (firstLight) {
			//System.out.println("Majs");
			oldFlashLight.add(0, new Point2D(1,1));
			oldFlashLight.add(1, new Point2D(1,2));
			oldFlashLight.add(2, new Point2D(1,3));
		}
		
		for (int i = 0; i < 3; i++) {
			System.out.println(enemyLocation + "ingående location");

		
				if (enemyDirection == Directions.WEST) {
					flashLight.add(i, enemyLocation.add(0, -i));
				} else if (enemyDirection == Directions.EAST) {
					flashLight.add(i, enemyLocation.add(0, i));
				} else if (enemyDirection == Directions.NORTH) {
					flashLight.add(i, enemyLocation.add(-i, 0));
				} else if (enemyDirection == Directions.SOUTH) {
					flashLight.add(i, enemyLocation.add(i, 0));
				}
			
				
				if (pr.getGrid()[(int) flashLight.get(i).getX()][(int) flashLight.get(i).getY()] == GridContent.WALL) {
					hitWall = true;
					break;
				}
				pr.getGrid()[(int) flashLight.get(i).getX()][(int) flashLight.get(i).getY()] = GridContent.FLASH;
				if (flashLight.get(i) == pr.getFrank().getFrankLocation()) {
					pr.setGameOver(true);
				}
				//if (!hitWall) {
				if (i < oldFlashLight.size()) {
					pr.getGrid()[(int) oldFlashLight.get(i).getX()][(int) oldFlashLight.get(i).getY()] = GridContent.EMPTY;
				}
				}
				
		firstLight = false;
		oldFlashLight = flashLight;
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

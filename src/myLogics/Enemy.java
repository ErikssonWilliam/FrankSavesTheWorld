package myLogics;

import javafx.geometry.Point2D;
import myLogics.PlayRoom.Directions;
import myLogics.PlayRoom.GridContent;

public class Enemy {
	

		private Point2D enemyLocation;
		private Point2D startLocation;
		private Point2D[] flashLight;
		private Directions enemyDirection;
		private PlayRoom pr;
		private int steps;
		private int stepCount;
		private Point2D previousLocation;
		private String type;

		public Enemy(Point2D startLocation, Directions direction, int steps, PlayRoom pr, String type) {
			this.enemyLocation = startLocation;
			this.startLocation = startLocation;
			this.enemyDirection = direction;
			this.steps = steps;
			this.previousLocation = startLocation;
			this.pr = pr;
			this.type = type;
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
		//	System.out.println(stepCount);
			Point2D futureEnemyVelocity = pr.changeVelocity(enemyDirection);
			Point2D futureEnemyLocation = enemyLocation.add(futureEnemyVelocity);
			
			if (pr.getGrid()[(int) futureEnemyLocation.getX()][(int) futureEnemyLocation.getY()] == GridContent.WALL) {
				futureEnemyLocation = previousLocation;
			}
			
			enemyLocation = futureEnemyLocation;
			pr.getGrid()[(int) previousLocation.getX()][(int) previousLocation.getY()] = GridContent.EMPTY;
			previousLocation = enemyLocation;
		    pr.getGrid()[(int) enemyLocation.getX()][(int) enemyLocation.getY()] = GridContent.ENEMY;
			stepCount += 1;	
		//	System.out.println(stepCount);
		}

//		public Point2D[] flashLight(Point2D enemyLocation) {
//			if (enemyDirection == Directions.WEST) {
//				flashLight = [enemyLocation.add((0, -1))][enemyLocation.add((0, -2))][enemyLocation.add((0, -3))]
//			}else if (enemyDirection == Directions.EAST) {
//				flashLight = [enemyLocation.add((0, 1))][enemyLocation.add((0, 2))][enemyLocation.add((0, 3))]
//			}else if (enemyDirection == Directions.NORTH) {
//				flashLight = [enemyLocation.add((-1, 0))][enemyLocation.add((-2, 0))][enemyLocation.add((-3, 0))]
//			}else if (enemyDirection == Directions.SOUTH) {
//				flashLight = [enemyLocation.add((1, 0))][enemyLocation.add((2, 0))][enemyLocation.add((3, 0))]
//			}
//
//			for (int i; i<3; i++) {
//			if (pr.getGrid()flashLight(i) == gridContent.WALL) {
//				flashLight(i) = null;
//			}
//			
//			if (flashLight(i) == pr.getFrank().getFrankLocation()) {
//				pr.setGameover(true);
//			}
//			}
//		
//		}

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
			}else if (direction == Directions.EAST) {
				oppDirection = Directions.WEST;
			}else if (direction == Directions.NORTH) {
				oppDirection = Directions.SOUTH;
			}else if (direction == Directions.SOUTH) {
				oppDirection = Directions.NORTH;
			}
			return oppDirection;
		
			}

	}

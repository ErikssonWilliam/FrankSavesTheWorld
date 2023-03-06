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

			Point2D futureEnemyVelocity = pr.changeVelocity(enemyDirection);
			Point2D futureEnemyLocation = enemyLocation.add(futureEnemyVelocity);
			
			if (pr.getGrid()[(int) futureEnemyLocation.getX()][(int) futureEnemyLocation.getY()] == GridContent.WALL) {
				futureEnemyLocation = previousLocation;
			}
			
			enemyLocation = futureEnemyLocation;
			pr.getGrid()[(int) previousLocation.getX()][(int) previousLocation.getY()] = GridContent.EMPTY;
			previousLocation = enemyLocation;
		    pr.getGrid()[(int) enemyLocation.getX()][(int) enemyLocation.getY()] = GridContent.ENEMY;
		    flashLight(enemyLocation);
			stepCount += 1;	
		}

		public void flashLight(Point2D enemyLocation) {
			Boolean wallBreak = false;
			int index1;
			int index2;
			flashLight = new Point2D(index1,index2);
			for (int i=0; i<3; i++ ) {
//				if (pr.getGrid()[(int) flashLight[i].getX()] [(int) flashLight[i].getY()] == GridContent.WALL ||
//						wallBreak) {
//					flashLight[i] = null;
//					wallBreak = true;
					
//
			if (enemyDirection == Directions.WEST) {
				flashLight[i] = enemyLocation.add(0, -i);
			}else if (enemyDirection == Directions.EAST) {
				flashLight[i] = enemyLocation.add(0, i);
			}else if (enemyDirection == Directions.NORTH) {
			flashLight[i] = enemyLocation.add(-i, 0);
			}else if (enemyDirection == Directions.SOUTH) {
				flashLight[i] = enemyLocation.add(i, 0);
			}
			pr.getGrid()[(int) flashLight[i].getX()][(int) flashLight[i].getY()] = GridContent.FLASH;
			if (flashLight[i] == pr.getFrank().getFrankLocation()) {
				pr.setGameOver(true);
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

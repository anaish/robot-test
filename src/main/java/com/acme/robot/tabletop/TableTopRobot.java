package com.acme.robot.tabletop;

import com.acme.robot.Robot;

/**
 * Table-Top implementation of the robot interface
 *  
 */
public class TableTopRobot implements Robot {
	
	private int x;
	private int y;
	private boolean placed = false;
	private BEARING bearing;
	private int angle;
	protected static final int X_MIN = 0;
	protected static final int Y_MIN = 0;
	protected static final int X_MAX = 5;
	protected static final int Y_MAX = 5;
	
	@Override
	public void move() {
		//check the robot is placed
		if(!this.placed)
			throw new IllegalStateException("Incorrect command sequence. Robot has not yet been placed");	
		
		int deltaX = this.toDeltaY(this.bearing);
		int deltaY = this.toDeltaX(this.bearing);
		
		if(!isWithinTable(this.x + deltaX, this.y + deltaY))
			throw new IllegalArgumentException("Invalid move command, robot will be positioned outside table bounds");
		
		this.x += deltaX;
		this.y += deltaY;
		
	}

	
	@Override
	public void rotate(DIRECTION direction) {
		
		if(!this.placed)
			throw new IllegalStateException("Incorrect command sequence. Robot has not yet been placed");	
		
		int delta = direction.equals(DIRECTION.RIGHT) ? 90 : -90;
		
		int newBearing = (angle + delta == 360 ? 0 : angle + delta) == -90 ? 270 : angle + delta;
					
		this.bearing = this.toBearing(newBearing);
		
	}
	
	@Override
	public String report() {
		
		if(!this.placed)
			throw new IllegalStateException("Incorrect command sequence. Robot has not yet been placed");	
		
		return this.x + "," + this.y + "," + this.bearing.name();
	}

	@Override
	public void place(int x, int y, BEARING bearing) {
		
		this.x = x;
		this.y = y;
		this.bearing = bearing;
		this.angle = this.toAngle(bearing);
		this.placed = true;
		
	}

	@Override
	public void reset() {
		
		this.placed = false;
	}
	
	/**
	 * Checks the x y location is inside the table bounds
	 * @param x int x location
	 * @param y int y location
	 * @return true if this x and y are within the table bounds
	 */
	public static boolean isWithinTable(int x, int y) {
		
		return x >= X_MIN && x<=X_MAX && y >= Y_MIN && y<=Y_MAX;
		
	}
	
	/**
	 * Converts the an angle to a bearing
	 * @param angle one of 0,90,180 or 270
	 * @return one of BEARING.NORTH, BEARING.SOUTH, BEARING.EAST, BEARING.WEST
	 * @throws IllegalArgumentException if the angle is not one of 0,90,180 or 270
	 */
	private BEARING toBearing(int angle) {
		
		switch(angle){
			case 0:
				return BEARING.NORTH;
			case 90:
				return BEARING.EAST;
			case 180:
				return BEARING.SOUTH;
			case 270:
				return BEARING.WEST;
			default:
				throw new IllegalArgumentException("Unknown angle");
		}
		
	}
	
	/**
	 * Converts a BEARING to an angle in decimal degrees
	 * @param bearing one of BEARING.NORTH, BEARING.SOUTH, BEARING.EAST, BEARING.WEST
	 * @return int the angle representing the bearing (one of 0,90,180 or 270)
	 */
	private int toAngle(BEARING bearing) {
		
		switch(bearing){
			case NORTH:
				return 0;
			case SOUTH:
				return 180;
			case EAST:
				return 90;
			case WEST:
				return 270;
			default:
				throw new IllegalArgumentException("Unknown bearing type " + bearing.name());
		}
		
	}
	
	/**
	 * Converts a BEARING to a direction vector in the x coordinate plane
	 * @param bearing one of BEARING.NORTH, BEARING.SOUTH, BEARING.EAST, BEARING.WEST
	 * @return 0, 1 or -1 indicating the magnitude of the direction component
	 */
	private int toDeltaX(BEARING bearing) {
		
		switch(bearing){
			case NORTH:
				return 1;
			case SOUTH:
				return -1;
			default:
				return 0;
		}
		
	}

	/**
	 * Converts a BEARING to a direction vector in the y coordinate plane
	 * @param bearing one of BEARING.NORTH, BEARING.SOUTH, BEARING.EAST, BEARING.WEST
	 * @return 0, 1 or -1 indicating the magnitude of the direction component
	 */
	private int toDeltaY(BEARING bearing) {
		
		switch(bearing){
			case EAST:
				return 1;
			case WEST:
				return -1;
			default:
				return 0;
		}
	}

		
	

}

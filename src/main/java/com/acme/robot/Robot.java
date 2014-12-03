package com.acme.robot;

/**
 * Main robot interface
 * All robots must implement this interface
 * in order to be handled by a RobotController
 *
 */
public interface Robot {
	
	public static final String COMMAND_PLACE = "PLACE";
	public static final String COMMAND_MOVE = "MOVE";
	public static final String COMMAND_LEFT = "LEFT";
	public static final String COMMAND_RIGHT = "RIGHT";
	public static final String COMMAND_REPORT = "REPORT";
	
	public static enum BEARING {
		NORTH,
		SOUTH,
		EAST,
		WEST
	}
	
	public static enum DIRECTION {
		LEFT,
		RIGHT
	}
	/**
	 * Resets the robot to an initial state
	 */
	public void reset();
	/**
	 * places the robot at the given coordinates in the specified bearing
	 * @param x location in the x coordinate plane
	 * @param y location in the y coordinate plane
	 * @param bearing one of BEARING.NORTH, BEARING.SOUTH, BEARING.EAST, BEARING.WEST
	 */
	public void place(int x, int y, BEARING bearing);
	/**
	 * Moves the robot one unit based on its current x,y and bearing
	 */
	public void move();
	/**
	 * Rotates the robot in the given direction
	 * @param direction one of DIRECTION.LEFT or DIRECTION.RIGHT
	 */
	public void rotate(DIRECTION direction);
	/**
	 * Reports the current x,y and bearing of the robot
	 * @return string in the format x,y,BEARING
	 */
	public String report();
	
}

package com.acme.robot.tabletop;

import com.acme.robot.Robot.BEARING;

public class Geometry {
	
	/**
	 * Checks the x y location is inside the table bounds
	 * @param x int x location
	 * @param y int y location
	 * @return true if this x and y are within the table bounds
	 */
	public static boolean isWithin(int x, int y, int xMin, int yMin, int xMax, int yMax) {
		
		return x >= xMin && x < xMax && y >= yMin && y< yMax;
		
	}

	
	/**
	 * Converts the an angle to a bearing
	 * @param angle one of 0,90,180 or 270
	 * @return one of BEARING.NORTH, BEARING.SOUTH, BEARING.EAST, BEARING.WEST
	 * @throws IllegalArgumentException if the angle is not one of 0,90,180 or 270
	 */
	public static BEARING toBearing(final int angle) {
		
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
				throw new IllegalArgumentException("Unsupported angle " + angle);
		}
		
	}
	
	/**
	 * Converts a BEARING to an angle in decimal degrees
	 * @param bearing one of BEARING.NORTH, BEARING.SOUTH, BEARING.EAST, BEARING.WEST
	 * @return int the angle representing the bearing (one of 0,90,180 or 270)
	 */
	public static int toAngle(final BEARING bearing) {
		
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
				throw new IllegalArgumentException("Unsupported bearing type " + bearing.name());
		}
		
	}
	
	/**
	 * Converts a BEARING to a direction vector in the x coordinate plane
	 * @param bearing one of BEARING.NORTH, BEARING.SOUTH, BEARING.EAST, BEARING.WEST
	 * @return 0, 1 or -1 indicating the magnitude of the direction component
	 */
	public static int toDeltaX(final BEARING bearing) {
		
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
	public static int toDeltaY(final BEARING bearing) {
		
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

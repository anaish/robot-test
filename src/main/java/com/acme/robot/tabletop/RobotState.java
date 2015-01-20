package com.acme.robot.tabletop;

import com.acme.robot.Robot.BEARING;

/**
 * POJO for storing current robot state
 * @author Andrew
 *
 */
public class RobotState {
	
	//Robot's x position
	private int x;
	//Robot's Y Position
	private int y;
	//Robot has been placed?
	private boolean placed = false;
	//Robot's current Bearing
	private BEARING bearing;
	//Robot's current facing angle
	private int angle;
	
	public int getAngle() {
		return angle;
	}
	public void setAngle(int angle) {
		this.angle = angle;
	}
	public BEARING getBearing() {
		return bearing;
	}
	public void setBearing(BEARING bearing) {
		this.bearing = bearing;
	}
	public boolean isPlaced() {
		return placed;
	}
	public void setPlaced(boolean placed) {
		this.placed = placed;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}

}

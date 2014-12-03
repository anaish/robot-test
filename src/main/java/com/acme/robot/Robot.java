package com.acme.robot;

public interface Robot {
	
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
	
	public void place(int x, int y, BEARING bearing);
	public void move();
	public void rotate(DIRECTION direction);
	public String report();
	
	

}

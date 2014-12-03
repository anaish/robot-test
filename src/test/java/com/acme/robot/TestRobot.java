package com.acme.robot;

import static org.junit.Assert.*;

import org.junit.Test;

import com.acme.robot.Robot.BEARING;
import com.acme.robot.Robot.DIRECTION;
import com.acme.robot.tabletop.TableTopRobot;

/**
 * Test class for robot commands
 * @author Andrew
 *
 */
public class TestRobot {
	
	//Object under test:
	private Robot robot = new TableTopRobot();
	
	/**
	 * Tests example a) PLACE 0,0,NORTH MOVE REPORT
	 */
	@Test
	public void testPlaceMoveReport(){
		
		robot.place(0, 0, BEARING.NORTH);
		assertEquals("0,1,NORTH", robot.report());
	
	}
	
	/**
	 * Tests example b) PLACE 0,0,NORTH LEFT REPORT
	 */
	@Test
	public void testPlaceRotateReport(){
		
		robot.place(0, 0, BEARING.NORTH);
		robot.rotate(DIRECTION.LEFT);
		assertEquals("0,0,WEST", robot.report());
	
	}
	
	/**
	 * Tests example c)  PLACE 1,2,EAST MOVE MOVE LEFT MOVE REPORT
	*/
	@Test
	public void testPlaceMoveRotateReport(){
		
		robot.place(1, 2, BEARING.EAST);
		robot.move();
		robot.move();
		robot.rotate(DIRECTION.LEFT);
		robot.move();
		assertEquals("3,3,NORTH", robot.report());
		
	
	}
	

}

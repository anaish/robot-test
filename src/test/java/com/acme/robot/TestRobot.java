package com.acme.robot;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.acme.robot.Robot.BEARING;
import com.acme.robot.Robot.DIRECTION;
import com.acme.robot.tabletop.TableTopRobot;

/**
 * Test class for robot commands
 *
 */
public class TestRobot {
	
	//Object under test:
	private Robot robot = new TableTopRobot();
	
	@Before
	public void setupTest(){
		//reset the robot before each test
		robot.reset();
		
	}
	
	/**
	 * Tests example a) PLACE 0,0,NORTH MOVE REPORT
	 */
	@Test
	public void testPlaceMoveReport(){
		
		robot.place(0, 0, BEARING.NORTH);
		robot.move();
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
	/**
	 * Tests the ability for the robot to ignore everything until a valid place command
	 */
	@Test
	public void testIgnoreUntilValidPlace(){
		
		robot.place(6, 6, BEARING.NORTH);
		robot.move();
		robot.place(-3, 0, BEARING.NORTH);
		robot.rotate(DIRECTION.LEFT);
		robot.place(0, 0, BEARING.NORTH);
		robot.move();
		assertEquals("0,1,NORTH", robot.report());
		
	}
	
	/**
	 * Tests incorrect place followed by a correct place
	 */
	@Test
	public void testIncorrectPlace(){
		
		robot.place(-1, -1, BEARING.NORTH);
		assertEquals("Incorrect command sequence. Robot has not yet been placed correctly", robot.report());
		
		robot.place(6, 6, BEARING.EAST);
		assertEquals("Incorrect command sequence. Robot has not yet been placed correctly", robot.report());
		
		robot.place(0, 0, BEARING.WEST);
		assertEquals("0,0,WEST", robot.report());
		
	}
	

}

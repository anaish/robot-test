package com.acme.robot;

import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.Test;

import com.acme.robot.tabletop.TableTopRobot;
import com.acme.robot.tabletop.TableTopRobotController;

/**
 * Test class for robot controller
 *
 */
public class TestRobotController {
	
	//Object under test:
	private RobotController robotController = new TableTopRobotController(new TableTopRobot());
	
	private String testFileName = "robot_commands.txt";
			
	/**
	 * Tests the controller
	 */
	@Test
	public void testCommands(){

		try {
			robotController.executeCommands(testFileName);
		} catch (IOException e) {
			fail(e.getMessage());
		}
		
		
	}
	
	

}

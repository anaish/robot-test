package com.acme.robot;

import java.io.IOException;

import com.acme.robot.tabletop.TableTopRobot;
import com.acme.robot.tabletop.TableTopRobotController;

public class Main {

	public static void main(String[] args) {

		//exit early if there are no commands
		if(args.length == 0 || args[0].length() == 0){
			System.out.println("Missing or invalid command(s). Syntax is " + Main.class.getName() + " <robot command file>.");
			return;
		}
				
		final Robot robot = new TableTopRobot();
		final RobotController controller = new TableTopRobotController(robot);
		
		try {
			controller.executeCommands(args[0]);
		} catch (IOException e) {
			System.out.println("Unknown error occured. Check command file and try again.");
			e.printStackTrace();
		}
		

	}

}

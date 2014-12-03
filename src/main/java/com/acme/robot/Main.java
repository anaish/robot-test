package com.acme.robot;

import com.acme.robot.tabletop.TableTopRobotController;

public class Main {

	public static void main(String[] args) {

		/*if we had a DI framework we would inject the members*/
		final RobotController controller = new TableTopRobotController();
		
		try{
			controller.executeCommands(args);
		}catch(Throwable e){
			e.printStackTrace(System.out);
			System.out.println("Error occured: " + e.getMessage());
		}
		

	}

}

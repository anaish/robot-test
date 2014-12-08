package com.acme.robot.tabletop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.acme.robot.Robot;
import com.acme.robot.Robot.BEARING;
import com.acme.robot.Robot.DIRECTION;
import com.acme.robot.RobotController;

/**
 * Main controller for the robot
 * Validates and issues commands to the robot 
 */
public class TableTopRobotController implements RobotController {

	//the robot
	private Robot robot = new TableTopRobot();

	
	/**
	 * Issues individual commands to the robot
	 * @param args String[] of valid robot commands
	 */
	@Override
	public void executeCommands(final String[] args) {

		//log the commands
		System.out.println("Executing commands\n" + Arrays.asList(args).stream().collect(Collectors.joining(" ")));

		//bail early if there are no commands
		if(args.length == 0 || args[0].length() == 0){
			System.out.println("Missing or invalid command(s)");
			return;
		}
		
		//split the command string into individual commands and arguments. Make the list modifiable.
		final List<String> rawCommands = new ArrayList<String>(Arrays.asList(args));
		
		//check we have at least one command
		if(rawCommands.size()==0 && rawCommands.contains(Robot.COMMAND_PLACE)){
			System.out.println("Missing or invalid command(s), PLACE command required");
			return;
		}
		
		//nothing happens before a place command, so strip any commands before that point
		final List<String> commands  = rawCommands.subList(rawCommands.indexOf(Robot.COMMAND_PLACE), rawCommands.size());
		
		//For convenience, join all the place commands with their arguments.
		//We use an index for loop, instead of a for each or 
		//stream because we are removing elements as we go.
		for(int i=0;i<commands.size();i++){
			final String command = commands.get(i);
			if(command.equals(Robot.COMMAND_PLACE)){
				commands.set(i, command + commands.get(i+1));
				commands.remove(i+1);
			}
		}
		
		//strip off any leading or tailing spaces
		//we can use the new java 8 streams api 
		final List<String> processedCommands = commands.stream().map(c -> c.trim()).collect(Collectors.toList());
		
		//reset the robot
		robot.reset();
		
		//issue the commands to the robot
		processedCommands.forEach(command -> this.executeCommand(command));
		
	}

	/**
	 * Issues individual commands to the robot
	 * @param command
	 */
	private void executeCommand(final String command) {

		//bail early if there is a bad command
		if(command==null||command.length()==0){
			System.out.println("Missing or invalid command " + command);
			return;
		}
		
		final String cmd;
		final Optional<String> args;

		//grab the arguments if this is a place command 
		if(command.startsWith(Robot.COMMAND_PLACE)){
			
			cmd = Robot.COMMAND_PLACE;
			args = Optional.of(command.substring(Robot.COMMAND_PLACE.length(), command.length()));
			
		} else {
			cmd = command;
			args = Optional.empty(); 
		}
		//command switch
		switch(cmd){
			case Robot.COMMAND_PLACE:
				if(args.isPresent()){
					this.executePlace(args.get().split(","));
				} else {
					System.out.println("Invalid "+ Robot.COMMAND_PLACE  +" command, requires arguments x,y and bearing");
				}
				break;
				
			case Robot.COMMAND_LEFT:
				robot.rotate(DIRECTION.valueOf(cmd));
				break;
				
			case Robot.COMMAND_RIGHT:
				robot.rotate(DIRECTION.valueOf(cmd));
				break;
				
			case Robot.COMMAND_MOVE:
				robot.move();
				break;
				
			case Robot.COMMAND_REPORT:
				System.out.println(robot.report());
				break;
				
			default:
				throw new IllegalArgumentException("Unknown command " + cmd);
						
		}
		
	}

	/**
	 * Executes a place command for the robot, checks the robot is inside the table
	 * @param args String[]{<x location>,<y location>,<BEARING (NORTH,SOUTH,EAST or WEST)>}
	 */
	private void executePlace(final String[] args){

		if(args.length!=3){
			System.out.println("Invalid place command parameters, requires x,y and bearing");
			return;
		}
		
		//parse the input
		final int x = Integer.parseInt(args[0]);
		final int y = Integer.parseInt(args[1]);
		final BEARING bearing = BEARING.valueOf(args[2]);
		
		robot.place(x, y, bearing);
		

		
	}

	

}

package com.acme.robot.tabletop;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;

import com.acme.robot.Robot;
import com.acme.robot.Robot.BEARING;
import com.acme.robot.Robot.COMMAND_TYPE;
import com.acme.robot.Robot.DIRECTION;
import com.acme.robot.RobotController;

/**
 * Main controller for the robot
 * Validates and issues commands to the robot 
 */
public class TableTopRobotController implements RobotController {

	//the robot
	private Robot robot;
	
	/*
	 * Constructs a new TableTopRobotController
	 */
	public TableTopRobotController(final Robot robot) {
		this.robot = robot;
	}

	/**
	 * Issues individual commands to the robot
	 * @param args String[] of valid robot commands
	 * @throws IOException 
	 */
	@Override
	public void executeCommands(final String fileName) throws IOException {

		//reset the robot
		robot.reset();
		final BufferedReader br = new BufferedReader(new FileReader(fileName));
		String line;
		int lineCount = 1; 
		while ((line = br.readLine()) != null) {
			final String command = line.trim();
			//ignore comments
			if(!command.startsWith("#")){
				final RobotCommand robotCommand = this.parseCommand(command, lineCount);
				if(robotCommand.isValid()){
					this.executeCommand(robotCommand);
				}
			}
			lineCount++;
		}
		br.close();
		
	}

	/**
	 * Issues individual commands to the robot
	 * @param command
	 * @return 
	 */
	private RobotCommand parseCommand(final String command, int lineNumber) {
		
		final RobotCommand cmd = new RobotCommand();
		
		//bail early if there is a bad command
		if(command==null||command.length()==0){
			System.out.println("Missing or invalid command: \"" + command + "\" line " + lineNumber);
			cmd.setValid(false);
			return cmd;
		}
		try{
			//grab the arguments if this is a place command 
			final COMMAND_TYPE placeCommand = Robot.COMMAND_TYPE.PLACE;
			if(command.startsWith(placeCommand.name())){
				cmd.setCmd(placeCommand);
				final String args = command.substring(placeCommand.name().length(), command.length());
				cmd.setArgs(Optional.of(args.trim()));
			} else {
				cmd.setCmd(Robot.COMMAND_TYPE.valueOf(command));
			}
			cmd.setValid(true);	

		}catch(IllegalArgumentException e){
			//thrown when the command (enumeration) could not be looked up by name
			System.out.println("Missing or invalid command: \"" + command + "\" line " + lineNumber);
			cmd.setValid(false);
		}
		
		
		return cmd;
		
	}

	/**
	 * Executes a single Robot Command
	 * @param cmd
	 */
	private void executeCommand(final RobotCommand cmd) {

		//command switch
		final COMMAND_TYPE command = cmd.getCmd();
		final Optional<String> args = cmd.getArgs();
		
		switch(command){
			case PLACE:
				if(args.isPresent()){
					this.executePlace(args.get().split(","));
				} else {
					System.out.println("Invalid place command, requires arguments x,y and bearing");
				}
				break;
				
			case LEFT:
				robot.rotate(DIRECTION.valueOf(command.name()));
				break;
				
			case RIGHT:
				robot.rotate(DIRECTION.valueOf(command.name()));
				break;
				
			case MOVE:
				robot.move();
				break;
				
			case REPORT:
				System.out.println(robot.report());
				break;
				
			default:
				throw new IllegalArgumentException("Unknown command " + command);
						
		}
	}

	/**
	 * Executes a place command for the robot
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

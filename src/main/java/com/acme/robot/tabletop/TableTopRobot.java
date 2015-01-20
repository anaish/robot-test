package com.acme.robot.tabletop;

import com.acme.robot.Robot;

/**
 * Table-Top implementation of the robot interface
 *  
 */
public class TableTopRobot implements Robot {
	
	public static final String ERROR_INVALID_MOVE_COMMAND = "Invalid move command, robot will be positioned outside table bounds. Command ignored";
	public static final String ERROR_INCORRECT_COMMAND_SEQUENCE = "Incorrect command sequence. Robot has not yet been placed";
	
	protected static final int X_MIN = 0;
	protected static final int Y_MIN = 0;
	protected static final int X_MAX = 5;
	protected static final int Y_MAX = 5;
	
	private RobotState robotStateModel = new RobotState();
	
	@Override
	public void move() {
		//check the robot is placed
		
		final RobotState robotModel = this.getRobotStateModel();
		
		if(!robotModel.isPlaced()){
			System.out.println(ERROR_INCORRECT_COMMAND_SEQUENCE);
			return;
		}
				
		final int deltaX = Geometry.toDeltaY(robotModel.getBearing());
		final int deltaY = Geometry.toDeltaX(robotModel.getBearing());
		final int x = robotModel.getX();
		final int y = robotModel.getY();
		
		if(!Geometry.isWithin(x + deltaX, y + deltaY,
								X_MIN, Y_MIN, X_MAX, Y_MAX)){
			
			System.out.println(ERROR_INVALID_MOVE_COMMAND);
			return;
			
		}
		
		robotModel.setX(x + deltaX);
		robotModel.setY(y + deltaY);
		
		
	}

	
	@Override
	public void rotate(DIRECTION direction) {
		
		final RobotState robotModel = this.getRobotStateModel();
		if(!robotModel.isPlaced()){
			System.out.println(ERROR_INCORRECT_COMMAND_SEQUENCE);
			return;
		}
		
		final int delta = direction.equals(DIRECTION.RIGHT) ? 90 : -90;
		final int angle = robotModel.getAngle();
		
		int newBearing = (angle + delta == 360 ? 0 : angle + delta) == -90 ? 270 : angle + delta;
					
		robotModel.setBearing(Geometry.toBearing(newBearing));
		
	}
	
	@Override
	public String report() {
		
		final RobotState robotModel = this.getRobotStateModel();
		if(!robotModel.isPlaced())
			return ERROR_INCORRECT_COMMAND_SEQUENCE;

		return robotModel.getX() + "," + robotModel.getY() + "," + robotModel.getBearing().name();
	}

	@Override
	public void place(final int x, final int y, final BEARING bearing) {
		
		//check this place command is within the table
		if(!Geometry.isWithin(x, y, X_MIN, Y_MIN, X_MAX, Y_MAX)){
			System.out.println("Invalid PLACE command, robot will be positioned outside table bounds. Command ignored");
			return;
		}

		//place the robot
		final RobotState robotModel = this.getRobotStateModel();
		robotModel.setX(x);
		robotModel.setY(y);
		robotModel.setBearing(bearing);
		robotModel.setAngle(Geometry.toAngle(bearing));
		robotModel.setPlaced(true);
		
	
	}

	@Override
	public void reset() {
		
		this.setRobotStateModel(new RobotState());
		
	}

	public RobotState getRobotStateModel() {
		return robotStateModel;
	}


	public void setRobotStateModel(RobotState robotStateModel) {
		this.robotStateModel = robotStateModel;
	}
	
	

		
	

}

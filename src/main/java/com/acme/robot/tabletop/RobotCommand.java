package com.acme.robot.tabletop;

import java.util.Optional;

import com.acme.robot.Robot;
/**
 * Represents a single robot command 
 * @author Andrew
 *
 */
public class RobotCommand {
	
	private Robot.COMMAND_TYPE cmd;
	private Optional<String> args = Optional.empty();
	private boolean valid = false;
	
	public Optional<String> getArgs() {
		return args;
	}
	public void setArgs(Optional<String> args) {
		this.args = args;
	}
	public Robot.COMMAND_TYPE getCmd() {
		return cmd;
	}
	public void setCmd(Robot.COMMAND_TYPE cmd) {
		this.cmd = cmd;
	}
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}

}

package org.team484.doge.commands;

import org.team484.doge.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ToteUpTo0High extends Command {
	boolean isDone = false;

	public ToteUpTo0High() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.totePickup);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		isDone = Robot.totePickup.toteUpTo0High();
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return isDone;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}

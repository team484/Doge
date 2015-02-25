package org.team484.doge.commands;

import org.team484.doge.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ArmOut extends Command {
	boolean isDone = false;
    public ArmOut() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.armLength);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	isDone = Robot.armLength.armOut();
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isDone;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.armLength.armStill();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}

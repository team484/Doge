package org.team484.doge.commands;

import org.team484.doge.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ToteThere extends Command {
	boolean isDone = false;
    public ToteThere() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.totePickup);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	isDone = Robot.totePickup.toteThere();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isDone;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.totePickup.totePickupStill();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}

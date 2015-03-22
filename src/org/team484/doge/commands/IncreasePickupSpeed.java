package org.team484.doge.commands;

import org.team484.doge.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IncreasePickupSpeed extends Command {

    public IncreasePickupSpeed() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	RobotMap.winchSpeedMultiplier = 0.7;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	RobotMap.winchSpeedMultiplier = 0.25;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}

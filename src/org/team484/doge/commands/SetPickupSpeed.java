package org.team484.doge.commands;

import org.team484.doge.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetPickupSpeed extends Command {
	double pickupSpeed;
    public SetPickupSpeed(double pickupSpeed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.pickupSpeed = pickupSpeed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	RobotMap.winchSpeedMultiplier = pickupSpeed;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	RobotMap.winchSpeedMultiplier = pickupSpeed;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	RobotMap.winchSpeedMultiplier = pickupSpeed;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}

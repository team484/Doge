package org.team484.doge.commands;

import org.team484.doge.Robot;
import org.team484.doge.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
/**
 *
 */
public class GoDistance extends Command {
	double setpoint;
	public GoDistance(double setpoint) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		this.setpoint = setpoint;
		requires(Robot.driveTrain);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.driveTrain.setSetpoint(setpoint);
		Robot.driveTrain.enable();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if (Robot.driveTrain.onTarget()) {
			return true;
		} else if (Math.abs(Robot.leftEncoder.getRate() * RobotMap.leftEncoderIncrement + Robot.rightEncoder.getRate() * RobotMap.rightEncoderIncrement)/2.0 < 1 && Math.abs(setpoint - Robot.driveTrain.setCurrentDistance()) < 3) {
			return true;
		} else {
		return false;
		}
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.driveTrain.disable();
		Robot.driveTrain.driveJoysticks();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
package org.team484.doge.commands;

import org.team484.doge.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class JustDrive extends Command {
	double speed = 0.0;
	public JustDrive(double speed) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		this.speed = speed;
		requires(Robot.driveTrain);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.driveTrain.justDrive(speed);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.driveTrain.driveJoysticks();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}

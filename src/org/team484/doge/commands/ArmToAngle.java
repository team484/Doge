package org.team484.doge.commands;

import org.team484.doge.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ArmToAngle extends Command {
	double setpoint;

	public ArmToAngle(double setpoint) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.armHeight);
		this.setpoint = setpoint;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.armHeight.setSetpoint(setpoint);
		Robot.armHeight.enable();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return Robot.armHeight.onTarget();
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.armHeight.disable();
		Robot.armHeight.armHeightJoystick();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.armHeight.disable();
		Robot.armHeight.armHeightJoystick();
	}
}

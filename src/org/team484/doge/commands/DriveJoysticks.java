package org.team484.doge.commands;

import org.team484.doge.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveJoysticks extends Command {
	private PIDController pid;
	public static double output;
	public DriveJoysticks() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.driveTrain);
		pid = new PIDController(0.25, -0.15, 0.55, new PIDSource() {
			public double pidGet() {
				return Robot.driveTrain.gyroAngle();
			}
		}, new PIDOutput() {
			public void pidWrite(double d) {
				Robot.driveTrain.recordOutput(d * 0.25);
			}
		});
		pid.setAbsoluteTolerance(.01);
		pid.setOutputRange(-0.7, 0.7);
		pid.setSetpoint(0);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		pid.reset();
		pid.enable();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.driveTrain.driveJoysticks(Robot.driveTrain.getOutput());
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}

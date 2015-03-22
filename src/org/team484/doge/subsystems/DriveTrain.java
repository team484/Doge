package org.team484.doge.subsystems;

import org.team484.doge.Robot;
import org.team484.doge.RobotMap;
import org.team484.doge.commands.DriveJoysticks;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class DriveTrain extends PIDSubsystem {
	private static double Kp = 0.2; // PID values for GoDistance
	private static double Ki = -0.05;
	private static double Kd = 0.7;

	public DriveTrain() {
		super(Kp, Ki, Kd); // sends PID values above to controller
	}

	protected double returnPIDInput() {
		// Return input value for the PID loop
		return setCurrentDistance();
	}

	protected void usePIDOutput(double output) {
		// Sets PID output to motors
		Robot.driveRobot.arcadeDrive(output, 0);
	}

	double driveDistance = 0;
	double currentDistance = 0;
	double rotateSetRate = 0.3;
	double rotateAngle = 0;

	public void initDefaultCommand() {
		setOutputRange(-0.6, 0.6); // GoDistance max speed
		setAbsoluteTolerance(0.01); // When met GoDistance stops
		setDefaultCommand(new DriveJoysticks());
	}

	public void justDrive(double speed) {
		Robot.driveRobot.arcadeDrive(-speed, 0);
	}

	public void rotateSpeed(double rotate) {
		Robot.driveRobot.arcadeDrive(0, rotate);
	}

	public void driveJoysticks() {
		double multiplier = 0.6;
		if (!Robot.driveStickLeft.getTrigger()) {
			multiplier = multiplier / 1.6;
		}
		if (Robot.driveStickLeft.getY() > 0.005) {
			Robot.driveRobot.arcadeDrive(-Robot.driveStickLeft.getY()
					* multiplier - 0.25, Robot.driveStickLeft.getX());
		} else if (Robot.driveStickLeft.getY() < -0.005) {
			Robot.driveRobot.arcadeDrive(-Robot.driveStickLeft.getY()
					* multiplier + 0.25, Robot.driveStickLeft.getX());
		} else {
			Robot.driveRobot.arcadeDrive(0, Robot.driveStickLeft.getX());
		}
		if (Robot.driveStickLeft.getTrigger()) {
			Robot.leftEncoder.reset();
			Robot.rightEncoder.reset();
		}
	}

	public boolean driveToTote() {
		System.out.println(Robot.toteCenterIR.getAverageVoltage());
		if (Robot.toteCenterIR.getAverageVoltage() < 2.15
				& Robot.toteCenterIR.getAverageVoltage() > 0.2) {
			Robot.driveRobot.arcadeDrive(RobotMap.driveToToteSpeed, 0.0);
			return false;
		} else {
			Robot.driveRobot.arcadeDrive(0.0, 0.0);
			return true;
		}
	}

	// -----Not Robot Commands----
	public double setCurrentDistance() {
		currentDistance = ((Robot.leftEncoder.getDistance() * RobotMap.leftEncoderIncrement) + (Robot.rightEncoder
				.getDistance() * RobotMap.rightEncoderIncrement)) / 2.0;
		return currentDistance;
	}

	public double modifiedInput(double input) { // restrains input value to -1
												// to 1
		double newInput;
		if (input > 1) {
			newInput = 1;
		} else if (input < -1) {
			newInput = -1;
		} else {
			newInput = input;
		}
		return newInput;
	}

	public double currentRate() { // Returns speed in inches per second
		return ((Robot.leftEncoder.getRate() * RobotMap.leftEncoderIncrement) + (Robot.rightEncoder
				.getRate() * RobotMap.rightEncoderIncrement)) / 2.0;

	}

	public double gyroAngle() { // returns rotation in degrees
		return (Robot.gyroUp.getAngle() - Robot.gyroDown.getAngle()) / 2.0;
	}

	public double gyroRate() { // returns rotational velocity in degrees per
								// second
		return (Robot.gyroUp.getRate() - Robot.gyroDown.getRate()) / 2.0;
	}

	public boolean zeroEncoders() { // sets the encoders to 0
		Robot.leftEncoder.reset();
		Robot.rightEncoder.reset();
		return true;
	}

	public boolean zeroGyro() { // sets gyro angle to 0
		Robot.gyroUp.reset();
		Robot.gyroDown.reset();
		return true;
	}
}

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
	boolean wasTrigger = false;
	public double output = 0.0;
	public void recordOutput(double output) {
		this.output = output;
	}
	public double getOutput() {
		return this.output;
	}
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
		slideDrive(0,-speed, 0);
	}

	public void rotateSpeed(double rotate) {
		slideDrive(0,0, rotate);
	}

	public void driveJoysticks(double input) {
		double multiplier = 0.7;
		double Y = Robot.driveStickLeft.getY();
		if (Robot.driveStickLeft.getY() > 0.1) {
			Y = 0.29 + Y;
		} else if (Robot.driveStickLeft.getY() < -0.1) {
			Y = Y - 0.29;
		}
		if (Robot.driveStickLeft.getTrigger()) {
			if (!wasTrigger) {
				Robot.gyroUp.reset();
				Robot.gyroDown.reset();
			}
			wasTrigger = true;
			if (Math.abs(Robot.driveStickLeft.getX()) > Math.abs(Robot.driveStickLeft.getY())) {
				slideDrive(Robot.driveStickLeft.getX(),0, input);
			} else {
				slideDrive(0, Y * multiplier, input);
			}
		} else {
			slideDrive(0, Y * multiplier, Robot.driveStickLeft.getX());
			wasTrigger = false;
		}
	}

	public boolean driveToTote() {
		System.out.println(Robot.toteCenterIR.getAverageVoltage());
		if (Robot.toteCenterIR.getAverageVoltage() < 2.15
				& Robot.toteCenterIR.getAverageVoltage() > 0.2) {
			slideDrive(0,RobotMap.driveToToteSpeed, 0.0);
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
	public void slideDrive(double x,double y,double rot) {
		Robot.driveRobot.arcadeDrive(-y, rot);
		Robot.slideFront.set(x + rot * 2);
		Robot.slideBack.set(-x+rot * 2);
	}
}

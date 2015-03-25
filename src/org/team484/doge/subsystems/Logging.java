package org.team484.doge.subsystems;

import org.team484.doge.Robot;
import org.team484.doge.RobotMap;
import org.team484.doge.commands.NoLogging;
import org.team484.doge.commands.RegularLogging;
import org.team484.doge.commands.VerboseLogging;

import edu.wpi.first.wpilibj.ControllerPower;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Logging extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	public static boolean frontLeftError = false;
	public static boolean rearLeftError = false;
	public static boolean frontRightError = false;
	public static boolean rearRightError = false;
	public static boolean frontLeftCurrent = false;
	public static boolean rearLeftCurrent = false;
	public static boolean frontRightCurrent = false;
	public static boolean rearRightCurrent = false;
	public static boolean totesCurrent = false;
	public static boolean armCurrent = false;
	public static boolean armExtensionCurrent = false;
	public static boolean gyroError = false;

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		if (RobotMap.loggingType == 0) {
			setDefaultCommand(new NoLogging());
		} else if (RobotMap.loggingType == 1) {
			setDefaultCommand(new RegularLogging());
		} else if (RobotMap.loggingType == 2) {
			setDefaultCommand(new VerboseLogging());
		}
	}

	public void regularLogging() {

		pushDashboard();
	}

	public void verboseLogging() {
		pushDashboard();
	}

	public void noLogging() {
		pushDashboard();
	}

	public static void pushDashboard() {
		System.out.println("B: " + Robot.totePickup0High.get() + " T: " + Robot.totePickup1High.get());
		//Robot.driveTrain.getPIDController().setPID(SmartDashboard.getNumber("kP", 0.0), SmartDashboard.getNumber("kI", 0.0), SmartDashboard.getNumber("kD", 0.0));
		SmartDashboard.putNumber("current", Robot.driveTrain.setCurrentDistance());
		SmartDashboard.putNumber("setpoint",Robot.driveTrain.getSetpoint());
		if (Robot.PDP.getCurrent(0) == 0 && Robot.PDP.getCurrent(1) > 5) {
			frontLeftError = true;
		}
		if (Robot.PDP.getCurrent(1) == 0 && Robot.PDP.getCurrent(0) > 5) {
			rearLeftError = true;
		}
		if (Robot.PDP.getCurrent(2) == 0 && Robot.PDP.getCurrent(3) > 5) {
			frontRightError = true;
		}
		if (Robot.PDP.getCurrent(3) == 0 && Robot.PDP.getCurrent(2) > 5) {
			rearRightError = true;
		}
		if (Robot.PDP.getCurrent(0) > 60) {
			frontLeftCurrent = true;
		}
		if (Robot.PDP.getCurrent(1) > 60) {
			rearLeftCurrent = true;
		}
		if (Robot.PDP.getCurrent(2) > 60) {
			frontRightCurrent = true;
		}
		if (Robot.PDP.getCurrent(3) > 60) {
			rearRightCurrent = true;
		}
		if (Robot.PDP.getCurrent(14) > 60) {
			totesCurrent = true;
		}
		if (Robot.PDP.getCurrent(15) > 60) {
			armCurrent = true;
		}
		if (Robot.PDP.getCurrent(4) > 30) {
			armExtensionCurrent = true;
		}
		if (Math.abs(Robot.gyroUp.getRate() + Robot.gyroDown.getRate()) > 220) {
			gyroError = true;
		}
		SmartDashboard.putBoolean("frontLeftError", frontLeftError);
		SmartDashboard.putBoolean("rearLeftError", rearLeftError);
		SmartDashboard.putBoolean("frontRightError", frontRightError);
		SmartDashboard.putBoolean("rearRightError", rearRightError);
		SmartDashboard.putBoolean("frontLeftCurrent", frontLeftCurrent);
		SmartDashboard.putBoolean("rearLeftCurrent", rearLeftCurrent);
		SmartDashboard.putBoolean("frontRightCurrent", frontRightCurrent);
		SmartDashboard.putBoolean("rearRightCurrent", rearRightCurrent);
		SmartDashboard.putBoolean("totesCurrent", totesCurrent);
		SmartDashboard.putBoolean("armCurrent", armCurrent);
		SmartDashboard.putBoolean("armExtensionCurrent", armExtensionCurrent);
		SmartDashboard.putBoolean("gyroError", gyroError);
		if ((Math.abs(RobotMap.getIRDistance(Robot.toteLeftIR.getAverageVoltage()) - 4.2) < 0.35) && (Math.abs(RobotMap.getIRDistance(Robot.toteRightIR.getAverageVoltage()) - 4.2) < 0.35) && (Math.abs(RobotMap.getIRDistance(Robot.toteCenterIR.getAverageVoltage()) - 4) < 0.35)){
			if (Math.abs(RobotMap.getIRDistance(Robot.toteLeftIR.getAverageVoltage()) - RobotMap.getIRDistance(Robot.toteRightIR.getAverageVoltage())) < 0.2) {
				SmartDashboard.putBoolean("pickup", true);
			} else {
				SmartDashboard.putBoolean("pickup", false);
			}
		} else {
			SmartDashboard.putBoolean("pickup", false);
		}
		SmartDashboard.putNumber("left",
				RobotMap.getIRDistance(Robot.toteLeftIR.getAverageVoltage()));
		SmartDashboard.putNumber("center",
				RobotMap.getIRDistance(Robot.toteCenterIR.getAverageVoltage()));
		SmartDashboard.putNumber("right",
				RobotMap.getIRDistance(Robot.toteRightIR.getAverageVoltage()));
		SmartDashboard.putNumber(
				"Distance",
				(Robot.leftEncoder.getDistance()
						* RobotMap.leftEncoderIncrement + Robot.rightEncoder
						.getDistance() * RobotMap.rightEncoderIncrement) / 2.0);
		SmartDashboard.putNumber("gyro",
				(Robot.gyroUp.getAngle() - Robot.gyroDown.getAngle()) / 2.0);
		SmartDashboard.putNumber("robotSpeed", Robot.leftEncoder.getRate()
				* RobotMap.leftEncoderIncrement / 12.0);
		SmartDashboard.putNumber("amperage", Robot.PDP.getTotalCurrent()
				+ ControllerPower.getInputCurrent());
		SmartDashboard.putNumber("armAngle", Robot.armPot.get());

		if (Robot.ds.isDisabled()) {
			SmartDashboard.putBoolean("isDisabled", true);
		} else {
			SmartDashboard.putBoolean("isDisabled", false);
		}
		if (Robot.armRetracted.get()) {
			SmartDashboard.putNumber("armExtend", 0);
		} else if (!Robot.armRetracted.get() && !Robot.armExtended.get()) {
			SmartDashboard.putNumber("armExtend", 1);
		} else {
			SmartDashboard.putNumber("armExtend", 2);
		}
		double[] circle1 = new double[2];
		double[] circle2 = new double[2];
		double[] leftIRCoords = RobotMap.getRightIRCoords();
		double[] rightIRCoords = RobotMap.getRightIRCoords();
		double[] centerIRCoords = new double[2];
		boolean isTote = true;
		centerIRCoords[0] = 2.0;
		centerIRCoords[1] = RobotMap.getIRDistance(Robot.toteCenterIR
				.getAverageVoltage()) - 1;
		if (centerIRCoords[1] < 30) {
			SmartDashboard.putBoolean("objectExist", true);
			circle1 = Robot.calculateCenter(leftIRCoords[0], centerIRCoords[0],
					leftIRCoords[1], centerIRCoords[1], 9.1);
			circle2 = Robot.calculateCenter(centerIRCoords[0],
					rightIRCoords[0], centerIRCoords[1], rightIRCoords[1], 9.1);
			if (Math.abs(circle1[0] - circle2[0]) < 1
					&& Math.abs(circle1[1] - circle2[1]) < 1
					&& circle1[0] != -1 && circle2[0] != -1) {
				isTote = false;
			}
			if (isTote) {
				SmartDashboard.putNumber("objectType", 2);
				if (Math.sqrt(Math.pow(leftIRCoords[0] - centerIRCoords[0], 2)
						+ Math.pow(leftIRCoords[1] - centerIRCoords[1], 2)) > 17) { // if
																					// left-center
																					// >
																					// 17
					if (Math.abs(Math
							.atan((leftIRCoords[1] - centerIRCoords[1])
									/ (leftIRCoords[0] - centerIRCoords[0]))
							- Math.atan((centerIRCoords[1] - rightIRCoords[1])
									/ (centerIRCoords[0] - rightIRCoords[0]))) < 0.1) { // if
																						// all
																						// have
																						// same
																						// slope
						double angle = Math
								.atan((leftIRCoords[1] - rightIRCoords[1])
										/ (leftIRCoords[0] - rightIRCoords[0]));
						SmartDashboard.putNumber("objectRotate", angle);
						SmartDashboard.putNumber("objectX",
								Math.sin(angle) * 8.5);
						SmartDashboard.putNumber("objectY", Math.cos(angle)
								* 8.5 + centerIRCoords[1]);

					}
				} else if (Math.sqrt(Math.pow(rightIRCoords[0]
						- centerIRCoords[0], 2)
						+ Math.pow(rightIRCoords[1] - centerIRCoords[1], 2)) > 17) { // if
																						// right-center
																						// >
																						// 17
					if (Math.abs(Math
							.atan((leftIRCoords[1] - centerIRCoords[1])
									/ (leftIRCoords[0] - centerIRCoords[0]))
							- Math.atan((centerIRCoords[1] - rightIRCoords[1])
									/ (centerIRCoords[0] - rightIRCoords[0]))) < 0.1) { // if
																						// all
																						// have
																						// same
																						// slope
						double angle = Math
								.atan((leftIRCoords[1] - rightIRCoords[1])
										/ (leftIRCoords[0] - rightIRCoords[0]));
						SmartDashboard.putNumber("objectRotate", angle);
						SmartDashboard.putNumber("objectX",
								Math.sin(angle) * 8.5);
						SmartDashboard.putNumber("objectY", Math.cos(angle)
								* 8.5 + centerIRCoords[1]);
					}
				} else {
					if (Math.abs(Math
							.atan((leftIRCoords[1] - centerIRCoords[1])
									/ (leftIRCoords[0] - centerIRCoords[0]))
							- Math.atan((centerIRCoords[1] - rightIRCoords[1])
									/ (centerIRCoords[0] - rightIRCoords[0]))) < 0.1) { // if
																						// all
																						// have
																						// same
																						// slope
						double angle = Math
								.atan((leftIRCoords[1] - rightIRCoords[1])
										/ (leftIRCoords[0] - rightIRCoords[0]));
						SmartDashboard.putNumber("objectRotate", angle);
						SmartDashboard.putNumber("objectX",
								Math.sin(angle) * 8.5);
						SmartDashboard.putNumber("objectY", Math.cos(angle)
								* 8.5 + centerIRCoords[1]);
					}
				}
			} else {
				SmartDashboard.putNumber("objectX", circle1[0]);
				SmartDashboard.putNumber("objectY", circle1[1]);
				SmartDashboard.putNumber("objectType", 1);
			}
		} else {
			SmartDashboard.putBoolean("objectExist", false);
		}
	}
}

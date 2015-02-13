package org.team484.doge.subsystems;

import org.team484.doge.Robot;
import org.team484.doge.RobotMap;
import org.team484.doge.commands.NoLogging;
import org.team484.doge.commands.RegularLogging;
import org.team484.doge.commands.VerboseLogging;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Logging extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		//setDefaultCommand(new MySpecialCommand());
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
	public void pushDashboard() {
		SmartDashboard.putNumber("gyro", (Robot.gyroUp.getAngle() - Robot.gyroDown.getAngle())/2.0);
		SmartDashboard.putNumber("robotSpeed", Robot.leftEncoder.getRate() * RobotMap.leftEncoderIncrement / 12.0);
		SmartDashboard.putNumber("amperage", Robot.PDP.getTotalCurrent());
		SmartDashboard.putNumber("armAngle", Robot.armPot.get());
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
		centerIRCoords[0] = 2;
		centerIRCoords[1] = RobotMap.getIRDistance(Robot.toteCenterIR.getAverageVoltage()) - 1;
		if (centerIRCoords[1] < 30) {
			SmartDashboard.putBoolean("objectExist", true);
			circle1 = Robot.calculateCenter(leftIRCoords[0], centerIRCoords[0], leftIRCoords[1], centerIRCoords[1], 9.1);
			circle2 = Robot.calculateCenter(centerIRCoords[0], rightIRCoords[0],centerIRCoords[1], rightIRCoords[1], 9.1);
			if (Math.abs(circle1[0] - circle2[0]) < 1 && Math.abs(circle1[1] - circle2[1]) < 1 && circle1[0] != -1 && circle2[0] != -1) {
				isTote = false;
			}
			if (isTote) {
				SmartDashboard.putNumber("objectType", 2);
				if (Math.sqrt(Math.pow(leftIRCoords[0] - centerIRCoords[0], 2) + Math.pow(leftIRCoords[1] - centerIRCoords[1], 2)) > 17) { //if left-center > 17
					if (Math.abs(Math.atan((leftIRCoords[1] - centerIRCoords[1])/(leftIRCoords[0] - centerIRCoords[0])) - Math.atan((centerIRCoords[1] - rightIRCoords[1])/(centerIRCoords[0] - rightIRCoords[0]))) < 0.1) { //if all have same slope
						double angle = Math.atan((leftIRCoords[1] - rightIRCoords[1])/(leftIRCoords[0] - rightIRCoords[0]));
						SmartDashboard.putNumber("objectRotate", angle);
						SmartDashboard.putNumber("objectX", Math.sin(angle) * 8.5);
						SmartDashboard.putNumber("objectY", Math.cos(angle) * 8.5 + centerIRCoords[1]);
						
					}
				} else if (Math.sqrt(Math.pow(rightIRCoords[0] - centerIRCoords[0], 2) + Math.pow(rightIRCoords[1] - centerIRCoords[1], 2)) > 17) { //if right-center > 17
					if (Math.abs(Math.atan((leftIRCoords[1] - centerIRCoords[1])/(leftIRCoords[0] - centerIRCoords[0])) - Math.atan((centerIRCoords[1] - rightIRCoords[1])/(centerIRCoords[0] - rightIRCoords[0]))) < 0.1) { //if all have same slope
						double angle = Math.atan((leftIRCoords[1] - rightIRCoords[1])/(leftIRCoords[0] - rightIRCoords[0]));
						SmartDashboard.putNumber("objectRotate", angle);
						SmartDashboard.putNumber("objectX", Math.sin(angle) * 8.5);
						SmartDashboard.putNumber("objectY", Math.cos(angle) * 8.5 + centerIRCoords[1]);		
					}
				} else {
					if (Math.abs(Math.atan((leftIRCoords[1] - centerIRCoords[1])/(leftIRCoords[0] - centerIRCoords[0])) - Math.atan((centerIRCoords[1] - rightIRCoords[1])/(centerIRCoords[0] - rightIRCoords[0]))) < 0.1) { //if all have same slope
						double angle = Math.atan((leftIRCoords[1] - rightIRCoords[1])/(leftIRCoords[0] - rightIRCoords[0]));
						SmartDashboard.putNumber("objectRotate", angle);
						SmartDashboard.putNumber("objectX", Math.sin(angle) * 8.5);
						SmartDashboard.putNumber("objectY", Math.cos(angle) * 8.5 + centerIRCoords[1]);		
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


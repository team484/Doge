package org.team484.doge.subsystems;

import org.team484.doge.Robot;
import org.team484.doge.RobotMap;
import org.team484.doge.commands.DriveJoysticks;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrain extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands
	double driveDistance = 0;
	double currentDistance = 0;
	double rotateSetRate = 0.3;
	double rotateAngle = 0;
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new DriveJoysticks());
    }
    public void justDrive() {
    	Robot.driveRobot.arcadeDrive(0.5,0);
    }
    public void driveJoysticks() {
    		Robot.driveRobot.arcadeDrive(-Robot.driveStickLeft.getY(),Robot.driveStickLeft.getX());
    		//System.out.println("L: " + Robot.leftEncoder.getDistance() * RobotMap.leftEncoderIncrement + " R: " + Robot.rightEncoder.getDistance() * RobotMap.rightEncoderIncrement + " gyro: " + gyroAngle());
    		if (Robot.driveStickLeft.getTrigger()) {
    			Robot.leftEncoder.reset();
    			Robot.rightEncoder.reset();
    		}
    }
    public boolean setDriveDistance(double distance) {
    	setCurrentDistance();
    	driveDistance = distance + currentDistance;
    	rotateSetRate = 0.3;
    	return true;
    }
    public boolean driveDistance() {
    	setCurrentDistance();
    	if (Math.abs(distanceToGo(driveDistance, currentDistance))<0.4) {
    		return true;
    	} else {
    	double moveValue = 0;
    	moveValue = distanceToGo(driveDistance, currentDistance) * RobotMap.moveValueSlope;
    	moveValue = RobotMap.moveValueMultiplier * modifiedInput(moveValue);
    	Robot.driveRobot.arcadeDrive(moveValue, 0);
    	return false;
    	}
    	
    }
    public boolean setDriveRotate(double rotate) {
    	rotateAngle = gyroAngle() + rotate;
    	return true;
    	
    }
    public boolean driveRotate2() {
    	System.out.println("Angle: " + gyroAngle() + " Rate: " + gyroRate());
    	double changeRate = RobotMap.rotateValueMultiplier * modifiedInput((rotateAngle - gyroAngle())/RobotMap.rotateValueSlope);
    	if (Math.abs(rotateAngle-gyroAngle()) < 0) {
    		return true;
    	} else {
    		if (-gyroRate() > changeRate) {
    			rotateSetRate = rotateSetRate - Math.abs(changeRate + gyroRate()/10);
    		} else {
    			rotateSetRate = rotateSetRate + Math.abs(changeRate + gyroRate()/10);
    		}
    		Robot.driveRobot.arcadeDrive(0, rotateSetRate);
    		return false;
    	}
    	
    }
    public boolean driveRotate() {
    	double goToChangeRate = 5 * (rotateAngle - gyroAngle());
    	if (gyroRate() < goToChangeRate) {
    		rotateSetRate = rotateSetRate + 0.009 * Math.abs(gyroRate() - goToChangeRate);    		
    	} else {
    		rotateSetRate = rotateSetRate - 0.009 * Math.abs(gyroRate() - goToChangeRate);
    	}
    	rotateSetRate = modifiedInput(rotateSetRate);
    	Robot.driveRobot.arcadeDrive(0, rotateSetRate);
    	if (Math.abs(rotateAngle - gyroAngle()) < 2) {
    		return true;
    	} else {
    		return false;
    	}
    }
    public boolean driveToTote() {
    	System.out.println(Robot.toteCenterIR.getAverageVoltage());
    	if (Robot.toteCenterIR.getAverageVoltage() < 2.15 & Robot.toteCenterIR.getAverageVoltage() > 0.2) {
    		Robot.driveRobot.arcadeDrive(RobotMap.driveToToteSpeed,0.0);
    		return false;
    	} else {
    		Robot.driveRobot.arcadeDrive(0.0, 0.0);
    		return true;
    	}
    }
    //-----Not Robot Commands----
    public void setCurrentDistance() {
    	currentDistance = ((Robot.leftEncoder.getDistance() * RobotMap.leftEncoderIncrement) + (Robot.rightEncoder.getDistance() * RobotMap.rightEncoderIncrement))/2.0;

    }
    public double modifiedInput(double input) {
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
    public double distanceToGo(double driveDistance, double currentDistance) {
    	return driveDistance - currentDistance;
    }
    public double gyroAngle() {
    	return (Robot.gyroUp.getAngle() - Robot.gyroDown.getAngle())/2.0;
    }
    public double gyroRate() {
    	return (Robot.gyroUp.getRate() - Robot.gyroDown.getRate())/2.0;
    }
}


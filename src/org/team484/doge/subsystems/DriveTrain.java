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
	
	double rotateAngle = 0;
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new DriveJoysticks());
    }
    public void driveJoysticks() {
    	if (RobotMap.tankDrive) {
    		Robot.driveRobot.tankDrive(Robot.driveStickLeft, Robot.driveStickRight);
    	} else {
    		Robot.driveRobot.arcadeDrive(Robot.driveStickLeft);
    	}
    }
    public boolean setDriveDistance(double distance) {
    	setCurrentDistance();
    	driveDistance = distance + currentDistance;
    	return true;
    }
    public boolean driveDistance() {
    	setCurrentDistance();
    	if (Math.abs(distanceToGo(driveDistance, currentDistance))<0.2) {
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
    public boolean driveRotate() {
    	if (Math.abs(rotateAngle-gyroAngle()) < 1) {
    		return true;
    	} else {
    		Robot.driveRobot.arcadeDrive(0, RobotMap.rotateValueMultiplier * modifiedInput((rotateAngle - gyroAngle())/RobotMap.rotateValueSlope));
    		return false;
    	}
    }
    public boolean driveToTote() {
    	if (RobotMap.getIRDistance(Robot.toteCenterIR.getAverageVoltage()) < RobotMap.totePickupDistance) {
    		Robot.driveRobot.arcadeDrive(0.0,0.0);
    		return true;
    	} else {
    		Robot.driveRobot.arcadeDrive(RobotMap.driveToToteSpeed, 0);
    		return false;
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
}


package org.team484.doge.subsystems;

import org.team484.doge.Robot;
import org.team484.doge.RobotMap;
import org.team484.doge.commands.DriveJoysticks;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class DriveTrain extends PIDSubsystem {
    // 0.03, -0.02, 0.02
	
	//20 -2 100
	 private static double Kp = 0.2;
	    private static double Ki = -0.05;
	    private static double Kd = 0.7;
	    public DriveTrain() {
			super(Kp, Ki, Kd);
	        // Use these to get going:
	        // setSetpoint() -  Sets where the PID controller should move the system
	        //                  to
	        // enable() - Enables the PID controller.
			}
	    protected double returnPIDInput() {
	        // Return your input value for the PID loop
	        // e.g. a sensor, like a potentiometer:
	        // return yourPot.getAverageVoltage() / kYourMaxVoltage;
	    	return setCurrentDistance();
	    }
	    
	    protected void usePIDOutput(double output) {
	        // Use output to drive your system, like a motor
	        // e.g. yourMotor.set(output);
	    	Robot.driveRobot.arcadeDrive(output, 0);
	    }
	// Put methods for controlling this subsystem
    // here. Call these from Commands
	double driveDistance = 0;
	double currentDistance = 0;
	double rotateSetRate = 0.3;
	double rotateAngle = 0;
	int atSetpoint = 0;
	double driveSetRate = 0.6;
	double[] gyroValues = new double[5];
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setOutputRange(-0.6, 0.6);
    	setAbsoluteTolerance(0.01);
    	setDefaultCommand(new DriveJoysticks());
    }
    public void justDrive() {
    	Robot.driveRobot.arcadeDrive(-0.5,0);
    }
    public void rotateSpeed(double rotate) {
    	Robot.driveRobot.arcadeDrive(0, rotate);
    }
    public void driveJoysticks() {
    	double multiplier = 0.7;
    	if (Robot.driveStickLeft.getTrigger()) {
    		multiplier = multiplier / 3;
    	}
    	if (Robot.driveStickLeft.getY() > 0.005) {
    		Robot.driveRobot.arcadeDrive(-Robot.driveStickLeft.getY() * multiplier - 0.25,Robot.driveStickLeft.getX());
    	} else  if (Robot.driveStickLeft.getY() < -0.005){
    		Robot.driveRobot.arcadeDrive(-Robot.driveStickLeft.getY() * multiplier + 0.25,Robot.driveStickLeft.getX());
    	} else {
    		Robot.driveRobot.arcadeDrive(0, Robot.driveStickLeft.getX());
    	}
    		
    		//System.out.println("L: " + Robot.leftEncoder.getDistance() * RobotMap.leftEncoderIncrement + " R: " + Robot.rightEncoder.getDistance() * RobotMap.rightEncoderIncrement + " gyro: " + gyroAngle());
    		if (Robot.driveStickLeft.getTrigger()) {
    			Robot.leftEncoder.reset();
    			Robot.rightEncoder.reset();
    		}
    }
    public boolean setDriveDistance(double distance) {
    	driveDistance = 0.0;
    	currentDistance = 0.0;
    	setCurrentDistance();
    	driveDistance = distance + currentDistance;
    	atSetpoint = 0;
    	return true;
    }
    public boolean driveDistance2() {
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
    public boolean driveDistance() {
    	setCurrentDistance();
    	double goToChangeRate = distanceToGo(driveDistance, currentDistance);
    	if (goToChangeRate > 80) {
    		goToChangeRate = 80;
    	}
    	if (currentRate() < goToChangeRate) {
    		driveSetRate = driveSetRate + 0.0005 * Math.abs(currentRate() - goToChangeRate);    		
    	} else {
    		driveSetRate = driveSetRate - 0.0005 * Math.abs(currentRate() - goToChangeRate);
    	}
    	driveSetRate = modifiedInput(driveSetRate);
    	Robot.driveRobot.arcadeDrive(driveSetRate, 0);
    	if (Math.abs(distanceToGo(driveDistance, currentDistance)) < 0.5) {
    		atSetpoint++;
    	}
    	if (atSetpoint > 1) {
    		return true;
    	} else {
    		return false;
    	}
    }
    public boolean setDriveRotate(double rotate) {
    	rotateAngle = gyroAngle() + rotate;
    	atSetpoint = 0;
    	rotateSetRate = 0.2;
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
    	//setGyroValue(gyroRate());
    	double goToChangeRate = 5.5 * (rotateAngle - gyroAngle());
    	if (goToChangeRate > 150) {
    		goToChangeRate = 150;
    	}
    	if (gyroRate() < goToChangeRate) {
    		rotateSetRate = rotateSetRate + 0.007 * Math.abs(gyroRate() - goToChangeRate);    		
    	} else {
    		rotateSetRate = rotateSetRate - 0.007 * Math.abs(gyroRate() - goToChangeRate);
    	}
    	rotateSetRate = modifiedInput(rotateSetRate);
    	Robot.driveRobot.arcadeDrive(0, rotateSetRate);
    	if (Math.abs(rotateAngle - gyroAngle()) < 1) {
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
    public double setCurrentDistance() {
    	currentDistance = ((Robot.leftEncoder.getDistance() * RobotMap.leftEncoderIncrement) + (Robot.rightEncoder.getDistance() * RobotMap.rightEncoderIncrement))/2.0;
    	return currentDistance;
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
    public double currentRate() {
    	return ((Robot.leftEncoder.getRate() * RobotMap.leftEncoderIncrement) + (Robot.rightEncoder.getRate() * RobotMap.rightEncoderIncrement))/2.0;

    }
    public double gyroAngle() {
    	return (Robot.gyroUp.getAngle() - Robot.gyroDown.getAngle())/2.0;
    }
    public double gyroRate() {
    	return (Robot.gyroUp.getRate() - Robot.gyroDown.getRate())/2.0;
    }
    public void setGyroValue(double value) {
    	int i = 0;
    	while (i < 4) {
    		gyroValues[i] = gyroValues[i+1];
    		i++;
    	}
    	gyroValues[4] = value;
    }
    public double getAverageGyroValye() {
    	double average = 0.0;
    	int i = 0;
    	while (i < 5) {
    		if (!Double.isNaN(gyroValues[i])) {
    			average = average + gyroValues[i];
    		}
    		i++;
    	}
    	return average / 5.0;
    }
    public boolean zeroEncoders() {
    	Robot.leftEncoder.reset();
    	Robot.rightEncoder.reset();
    	return true;
    }
    public boolean zeroGyro() {
    	Robot.gyroUp.reset();
    	Robot.gyroDown.reset();
    	return true;
    }
}


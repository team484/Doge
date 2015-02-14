package org.team484.doge.subsystems;

import org.team484.doge.Robot;
import org.team484.doge.RobotMap;
import org.team484.doge.commands.ArmHeightJoystick;

import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class ArmHeight extends PIDSubsystem {
    private static final double Kp = -0.2;
    private static final double Ki = 0.1;
    private static final double Kd = 0.0;
    public ArmHeight() {
		super(Kp, Ki, Kd);
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
		}

	// Initialize your subsystem here
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setOutputRange(-1, 1);
    	setInputRange(0, 130);
    	setAbsoluteTolerance(3);
    	setPercentTolerance(3);
    	
    	setDefaultCommand(new ArmHeightJoystick());
    }
    
    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
    	return Robot.armPot.get();
    }
    
    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    	setHeightMotor(output);
    }
    public void armHeightJoystick() {
    	setHeightMotor(Robot.operatorStick.getY());
    }
    public void setHeightMotor(double speed) {
    	if (Robot.armRetracted.get() == RobotMap.armRetractDefault && Robot.armPot.get() < 47 && speed > 0) {
    			speed = 0;
    	}
    	Robot.armHeightMotor.set(speed);
    }
}

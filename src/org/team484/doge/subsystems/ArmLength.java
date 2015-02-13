package org.team484.doge.subsystems;

import org.team484.doge.Robot;
import org.team484.doge.RobotMap;
import org.team484.doge.commands.ArmStill;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ArmLength extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new ArmStill());
    }
    public boolean armOut() {
    	if (RobotMap.armExtendDefault == Robot.armExtended.get() && Robot.armPot.get() > 47) {
    		Robot.armLengthMotor.set(-RobotMap.armLengthSpeed);
    		return false;
    	} else {
    		Robot.armLengthMotor.set(0);
    		return true;
    	}
    }
    public boolean armIn() {
    	if (RobotMap.armRetractDefault == Robot.armRetracted.get()) {
    		Robot.armLengthMotor.set(RobotMap.armLengthSpeed);
    		return false;
    	} else {
    		Robot.armLengthMotor.set(0);
    		return true;
    	}
    }
    public void armStill() {
    	Robot.armLengthMotor.set(0);
    }
    public boolean doNotExtend() {
    	return Robot.armPot.get() < 47;
    }
}


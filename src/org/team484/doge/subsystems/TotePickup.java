package org.team484.doge.subsystems;

import org.team484.doge.Robot;
import org.team484.doge.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class TotePickup extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public void totePickupStill() {
		Robot.totePickupMotor.set(0);
	}
	public boolean toteThere() {
		if (RobotMap.getIRDistance(Robot.toteTopIR.getAverageVoltage()) < 3) {
			return true;
		} else {
			return false;
		}
	}
	public void totePickupJoystick() {
		if (Robot.totePickupBottom.get() != RobotMap.halleffectDefault
				&& Robot.operatorStick.getY()
						* RobotMap.operatorStickMultiplierY < 0) {
			totePickupStill();
		} else {
			Robot.totePickupMotor.set(Robot.operatorStick.getY()
					* RobotMap.operatorStickMultiplierY);
		}
	}

	public boolean toteToTop() {
		Robot.totePickupMotor.set(1 * RobotMap.winchSpeedMultiplier);
		return false;
	}

	public boolean toteToBottom() {
		boolean goDown = (Robot.totePickupBottom.get() == RobotMap.halleffectDefault);
		if (goDown) {
			Robot.totePickupMotor.set(-1 * RobotMap.winchSpeedMultiplier);
			return false;
		} else {
			Robot.totePickupMotor.set(0);
			return true;
		}
	}

	public boolean toteUpTo0High() {
		if (Robot.totePickup1High.get() == RobotMap.halleffectDefault
				&& Robot.totePickup0High.get() == RobotMap.halleffectDefault) {
			Robot.totePickupMotor.set(1 * RobotMap.winchSpeedMultiplier);
			return false;
		} else {
			Robot.totePickupMotor.set(0);
			return true;
		}
	}

	public boolean toteDownTo0High() {
		if (Robot.totePickup0High.get() != RobotMap.halleffectDefault
				&& Robot.totePickupBottom.get() == RobotMap.halleffectDefault) {
			Robot.totePickupMotor.set(0);
			return true;
		} else {
			Robot.totePickupMotor.set(-1 * RobotMap.winchSpeedMultiplier);
			return false;
		}
	}

	public boolean toteUpTo1High() {
		if (Robot.totePickup1High.get() == RobotMap.halleffectDefault) {
			Robot.totePickupMotor.set(1 * RobotMap.winchSpeedMultiplier);
			return false;
		} else {
			Robot.totePickupMotor.set(0);
			return true;
		}
	}

	public boolean toteDownTo1High() {
		if (Robot.totePickup1High.get() == RobotMap.halleffectDefault
				&& Robot.totePickup0High.get() == RobotMap.halleffectDefault
				&& Robot.totePickupBottom.get() == RobotMap.halleffectDefault) {
			Robot.totePickupMotor.set(-1 * RobotMap.winchSpeedMultiplier);
			return false;
		} else {
			Robot.totePickupMotor.set(0);
			return true;
		}
	}
}

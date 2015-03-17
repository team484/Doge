package org.team484.doge.subsystems;

import org.team484.doge.Robot;
import org.team484.doge.commands.RunLEDs;

import edu.wpi.first.wpilibj.Relay.Direction;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class LEDs extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	int blink = 0;
	int pulse = 0;
	boolean go = false;
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	Robot.LEDs.setDirection(Direction.kForward);
    	setDefaultCommand(new RunLEDs());
    }
    public void run() {
    	if (Robot.ds.isAutonomous()) {
    		solid();
    	} else if (Robot.ds.isOperatorControl()) {
    		if (Robot.ds.getMatchTime() < 20 && Robot.ds.getMatchTime() > 0) {
    			blink();
    			if (Robot.ds.getMatchTime() < 5) {
    				blink++;
    			}
    		} else {
    			solid();
    		}
    	}
    }
    public void solid() {
    	Robot.LEDs.set(Value.kOn);
    }
    public void blink() {
    	if (blink < 25) {
    		Robot.LEDs.set(Value.kOn);
    		blink++;
    	} else if (blink < 40) {
    		Robot.LEDs.set(Value.kOff);
    		blink++;
    	} else {
    		blink = 0;
    	}
    }
    public void off() {
    	Robot.LEDs.set(Value.kOff);
    }
}


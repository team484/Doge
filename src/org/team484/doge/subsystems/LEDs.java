package org.team484.doge.subsystems;

import org.team484.doge.Robot;
import org.team484.doge.commands.RunLEDs;

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
    	setDefaultCommand(new RunLEDs());
    }
    public void run() {
    	if (Robot.ds.isAutonomous()) {
    		pulse();
    	} else if (Robot.ds.isOperatorControl()) {
    		if (Robot.ds.getMatchTime() < 10 && Robot.ds.getMatchTime() > 0) {
    			blink();
    			if (Robot.ds.getMatchTime() < 5) {
    				blink++;
    			}
    		} else {
    			solid();
    		}
    	}
    }
    public void pulse() {
    	if (pulse < 25) {
    		pulse++;
    	} else {
    		pulse = -25;
    	}
    	Robot.LEDs.set(0.04*Math.abs(pulse));
    	
    }
    public void solid() {
    	Robot.LEDs.set(1);
    }
    public void blink() {
    	if (blink < 25) {
    		Robot.LEDs.set(1);
    		blink++;
    	} else if (blink < 40) {
    		Robot.LEDs.set(0);
    		blink++;
    	} else {
    		blink = 0;
    	}
    }
    public void off() {
    	Robot.LEDs.set(0);
    }
}


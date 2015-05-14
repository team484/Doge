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
		setDefaultCommand(new RunLEDs());
	}
	public void run() {
		if (Robot.ds.isFMSAttached()) {
			if (Robot.ds.isAutonomous()) {
				pulse();
			} else if (Robot.ds.isOperatorControl()) {
				if (Robot.ds.getMatchTime() < 30 && Robot.ds.getMatchTime() > 0) {
					blink();
					if (Robot.ds.getMatchTime() < 5) {
						blink++;
					}
				} else {
					solid();
				}
			}
		} else {
			off();
		}
	}
	public void solid() {
		double multiplier = 2.7 - Robot.ds.getBatteryVoltage() / 6;
		Robot.LEDs.set(multiplier);
	}
	public void blink() {
		if (blink < 25) {
			solid();
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
    public void pulse() {
    	if (pulse < 25) {
    		pulse++;
    	} else {
    		pulse = -25;
    	}
    	Robot.LEDs.set(0.04*Math.abs(pulse));
    	
    }
}


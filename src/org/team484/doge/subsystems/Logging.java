package org.team484.doge.subsystems;

import org.team484.doge.RobotMap;
import org.team484.doge.commands.NoLogging;
import org.team484.doge.commands.RegularLogging;
import org.team484.doge.commands.VerboseLogging;

import edu.wpi.first.wpilibj.command.Subsystem;

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
    	
    }
    public void verboseLogging() {
    	
    }
    public void noLogging() {
    	
    }
}


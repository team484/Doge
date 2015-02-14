package org.team484.doge.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class AutonomousToteAndCan extends CommandGroup {
    
    public  AutonomousToteAndCan() {
    	addSequential(new ZeroEncoders(), 0.1);
    	addSequential(new GoDistance(-10));
    	addSequential(new ZeroGyro(), 0.1);
    	addSequential(new GoRotate(-90));
    	addSequential(new ZeroEncoders(), 0.1);
    	addSequential(new GoDistance(81));
    	addSequential(new ZeroGyro(), 0.1);
    	addSequential(new GoRotate(90));
    	addSequential(new ZeroEncoders(), 0.1);
    	addSequential(new GoDistance(10));
    	addSequential(new ZeroEncoders(), 0.1);
    	addSequential(new GoDistance(-10));
    	addSequential(new ZeroGyro(), 0.1);
    	addSequential(new GoRotate(-90));
    	addSequential(new ZeroEncoders(), 0.1);
    	addSequential(new GoDistance(81));
    	addSequential(new ZeroGyro(), 0.1);
    	addSequential(new GoRotate(90));
    	addSequential(new ZeroEncoders(), 0.1);
    	addSequential(new GoDistance(10));
    }
}

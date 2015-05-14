package org.team484.doge.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class Stacker extends CommandGroup {
    
    public  Stacker() {
    	addSequential(new SetPickupSpeed(0.9), 0.1);
    	addSequential(new SetPickupSpeed(1), 0.1);  //sets pickup speed
    	addSequential(new ToteThere());
    	addSequential(new WaitCommand(0.5), 0.5);
    	addSequential(new SetPickupSpeed(1), 0.1);  //sets pickup speed
    	addSequential(new ToteUpTo1High(),2);
    	addSequential(new SetPickupSpeed(1), 0.1);  //sets pickup speed
    	addSequential(new ToteToTop(), 1);
    	addSequential(new TotePickupStill(), 0.1);
    	addSequential(new ToteThere());
    	addSequential(new WaitCommand(0.5),0.5);
    	addSequential(new SetPickupSpeed(0.6), 0.1);  //sets pickup speed
    	addSequential(new ToteDownTo1High(),1.5);
    	addSequential(new TotePickupStill(), 0.1);
    	addSequential(new JustDrive(-0.6), 1);
    	addSequential(new DriveJoysticks(), 0.1);
    	addSequential(new SetPickupSpeed(1), 0.1);  //sets pickup speed
    	addSequential(new ToteDownTo0High(),1);
    	addSequential(new TotePickupStill(), 0.1);
    	addSequential(new JustDrive(0.6), 1.5);
    	addSequential(new DriveJoysticks(), 0.1);
    	
    	addSequential(new WaitCommand(0.5), 0.5);
    	addSequential(new SetPickupSpeed(1), 0.1);  //sets pickup speed
    	addSequential(new ToteUpTo1High(),2);
    	addSequential(new ToteToTop(), 1);
    	addSequential(new TotePickupStill(), 0.1);
    	addSequential(new ToteThere());
    	addSequential(new WaitCommand(0.5),0.5);
    	addSequential(new SetPickupSpeed(0.6), 0.1);  //sets pickup speed
    	addSequential(new ToteDownTo1High(),1.5);
    	addSequential(new TotePickupStill(), 0.1);
    	addSequential(new JustDrive(-0.6), 1);
    	addSequential(new DriveJoysticks(), 0.1);
    	addSequential(new SetPickupSpeed(1), 0.1);  //sets pickup speed
    	addSequential(new ToteDownTo0High(),1);
    	addSequential(new TotePickupStill(), 0.1);
    	addSequential(new JustDrive(0.6), 1.5);
    	addSequential(new DriveJoysticks(), 0.1);
    	
    	addSequential(new WaitCommand(0.5), 0.5);
    	addSequential(new SetPickupSpeed(1), 0.1);  //sets pickup speed
    	addSequential(new ToteUpTo1High(),2);
    	addSequential(new ToteToTop(), 1);
    	addSequential(new TotePickupStill(), 0.1);
    	addSequential(new ToteThere());
    	addSequential(new WaitCommand(0.5),0.5);
    	addSequential(new SetPickupSpeed(0.6), 0.1);  //sets pickup speed
    	addSequential(new ToteDownTo1High(),1.5);
    	addSequential(new TotePickupStill(), 0.1);
    	addSequential(new JustDrive(-0.6), 1);
    	addSequential(new DriveJoysticks(), 0.1);
    	addSequential(new SetPickupSpeed(1), 0.1);  //sets pickup speed
    	addSequential(new ToteDownTo0High(),1);
    	addSequential(new TotePickupStill(), 0.1);
    	addSequential(new JustDrive(0.6), 1.5);
    	addSequential(new DriveJoysticks(), 0.1);
    	
    	addSequential(new SetPickupSpeed(0.25), 0.1); //sets pickup speed
    }
}

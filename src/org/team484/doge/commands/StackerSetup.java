package org.team484.doge.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class StackerSetup extends CommandGroup {
    
    public  StackerSetup() {
    	addSequential(new SetPickupSpeed(1), 0.1);  //sets pickup speed
    	addSequential(new ToteToBottom(),4);
    	addSequential(new SetPickupSpeed(0.3), 0.1);  //sets pickup speed
    	addSequential(new ToteToTop(), 0.72);
    	addSequential(new TotePickupStill(), 0.1);
    	addSequential(new SetPickupSpeed(0.25), 0.1);
    	addSequential(new WaitCommand(215),215);
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}

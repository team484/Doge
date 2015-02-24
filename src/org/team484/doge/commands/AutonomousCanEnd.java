package org.team484.doge.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousCanEnd extends CommandGroup {
    
    public  AutonomousCanEnd() {
    	addSequential(new ToteToTop(), 1);
    	addSequential(new TotePickupStill(), 0.1);
    	addSequential(new ZeroGyro());
    	addSequential(new GoRotate(45),7);
    	addSequential(new GoDistance(-115),7);
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

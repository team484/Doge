package org.team484.doge.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousToteSide extends CommandGroup {
    
    public  AutonomousToteSide() {
    	addSequential(new ZeroEncoders(), 0.1);
    	addSequential(new ToteUpTo1High(), 5);
    	addSequential(new ToteToTop(), 2.65);
    	addSequential(new TotePickupJoystick(), 0.1);
    	addSequential(new GoDistance(25), 5);
    	addSequential(new ZeroGyro(), 0.1);
    	addSequential(new GoRotate(-90), 5);
    	addSequential(new ZeroEncoders(), 0.1);
    	addSequential(new GoDistance(94), 5);
    	addSequential(new ZeroGyro(), 0.1);
    	addSequential(new GoRotate(-90));
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

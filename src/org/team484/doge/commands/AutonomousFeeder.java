package org.team484.doge.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousFeeder extends CommandGroup {
    
    public  AutonomousFeeder() {
    	addSequential(new SetPickupSpeed(0.7), 0.1);
    	addSequential(new ToteUpTo1High(), 2);
    	addSequential(new ToteToTop(), 0.7);
    	addSequential(new TotePickupStill(), 0.1);
    	addSequential(new ZeroEncoders(), 0.1);
    	addSequential(new GoDistance(25), 2);
    	addSequential(new ZeroGyro(), 0.1);
    	addSequential(new GoRotate(40), 2);
    	addSequential(new GoRotate(40), 2);
    	addSequential(new ZeroEncoders(), 0.1);
    	addSequential(new GoDistance(30), 2);
    	addSequential(new ZeroEncoders(), 0.1);
    	addSequential(new GoDistance(-34), 3);
    	addSequential(new ZeroGyro(), 0.1);
    	addSequential(new GoRotate(-130), 3);
    	addSequential(new ZeroEncoders(), 0.1);
    	addSequential(new GoDistance(94), 4);
    	addSequential(new ZeroGyro(), 0.1);
    	addSequential(new GoRotate(90), 4);
    	addSequential(new SetPickupSpeed(0.25), 0.1);
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

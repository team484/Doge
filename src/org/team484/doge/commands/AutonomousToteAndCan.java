package org.team484.doge.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class AutonomousToteAndCan extends CommandGroup {
    
    public  AutonomousToteAndCan() {
    	//addSequential(new ToteToBottom());
    	//addSequential(new DriveToTote());
    	addSequential(new ToteUpTo1High());
    	addSequential(new SetDriveRotate(30));
    	addSequential(new DriveRotate());
    	addSequential(new SetDriveRotate(-30));
    	addSequential(new WaitCommand(1.0), 1.0);
    	addSequential(new JustDrive(), 1);
    	addSequential(new WaitCommand(1.0), 1.0);
    	addSequential(new SetDriveRotate(-30));    	
    	addSequential(new DriveRotate());
    	addSequential(new SetDriveDistance(100));
    	addSequential(new DriveDistance());
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

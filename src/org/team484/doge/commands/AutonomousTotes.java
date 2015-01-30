package org.team484.doge.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousTotes extends CommandGroup {
    
    public  AutonomousTotes() {
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
    	addSequential(new ToteToBottom());
    	addSequential(new SetDriveDistance(3.0));
    	addSequential(new DriveDistance());
    	addSequential(new DriveToTote());
    	addSequential(new ToteUpTo0High());
    	addSequential(new SetDriveDistance(-6.0));
    	addSequential(new DriveDistance());
    	addSequential(new SetDriveRotate(90.0));
    	addSequential(new DriveRotate());
    	addSequential(new SetDriveDistance(24.0));
    	addSequential(new DriveDistance());
    	addSequential(new SetDriveRotate(-90.0));
    	addSequential(new DriveRotate());
    	addSequential(new SetDriveDistance(6.0));
    	addSequential(new DriveDistance());
    	addSequential(new ToteToBottom());
    	addSequential(new DriveToTote());
    	addSequential(new ToteUpTo0High());
    	addSequential(new SetDriveDistance(-6.0));
    	addSequential(new DriveDistance());
    	addSequential(new SetDriveRotate(90.0));
    	addSequential(new DriveRotate());
    	addSequential(new SetDriveDistance(24.0));
    	addSequential(new DriveDistance());
    	addSequential(new SetDriveRotate(-90.0));
    	addSequential(new SetDriveDistance(80.0));
    	addSequential(new DriveDistance());
    	
    	
    }
}

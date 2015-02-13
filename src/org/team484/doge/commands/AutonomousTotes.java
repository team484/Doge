package org.team484.doge.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

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
    	addSequential(new DriveToTote());
    	addSequential(new ToteUpTo1High());
    	addSequential(new JustDrive(), 0.3);
    	addSequential(new WaitCommand(0.3),0.3);
    	addSequential(new SetDriveRotate(-90.0));
    	addSequential(new DriveRotate());
    	addSequential(new WaitCommand(0.5),0.5);
    	addSequential(new SetDriveDistance(75));
    	addSequential(new DriveDistance());
    	addSequential(new SetDriveRotate(90.0));
    	addSequential(new DriveRotate());
    	addSequential(new DriveToTote());
    	addSequential(new ToteDownTo0High(), 0.3);
    	addSequential(new SetDriveDistance(-5.0));
    	addSequential(new DriveDistance());
    	addSequential(new ToteToBottom());
    	addSequential(new DriveToTote());
    	addSequential(new ToteUpTo1High());
    	addSequential(new JustDrive(), 0.3);
    	addSequential(new WaitCommand(0.3),0.3);
    	addSequential(new SetDriveRotate(-90.0));
    	addSequential(new DriveRotate());
    	addSequential(new WaitCommand(0.3),0.3);
    	addSequential(new SetDriveDistance(75));
    	addSequential(new DriveDistance());
    	addSequential(new SetDriveRotate(90.0));
    	addSequential(new DriveRotate());
    	addSequential(new SetDriveDistance(130.0));
    	addSequential(new DriveDistance());
    	addSequential(new ToteDownTo0High());
    	addSequential(new SetDriveDistance(-10.0));
    	
    	
    	
    }
}

package org.team484.doge.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class AutonomousCanCenter extends CommandGroup {

	public AutonomousCanCenter() {
		addSequential(new ArmDown(), 1);
		addSequential(new ArmToAngle(105), 4);
		addSequential(new ArmOut(), 3);
		addSequential(new WaitCommand(0.2),0.2);
		addSequential(new ArmUp(), 0.65);
		addParallel(new ArmIn(), 4);
		addSequential(new ArmToAngle(8), 6);
		addSequential(new WaitCommand(0.5),0.5);
		addSequential(new GoRotate(-90),3);
		addSequential(new WaitCommand(0.5), 0.5);
		addSequential(new GoDistance(50),4);
		/*addSequential(new ArmDown(), 1);
		addSequential(new ArmToAngle(105), 4);
		addSequential(new ArmOut(), 3);
		addSequential(new WaitCommand(0.2),0.2);
		addSequential(new ArmUp(), 0.65);
		addParallel(new ArmIn(), 4);
		addSequential(new ArmToAngle(50), 3.4);
		addSequential(new WaitCommand(0.5), 0.5);
		addSequential(new ArmToAngle(20), 2);
		addSequential(new GoRotate(-90),3);
		addSequential(new WaitCommand(0.3), 0.3);
		addSequential(new GoDistance(35),4);*/
		// Add Commands here:
		// e.g. addSequential(new Command1());
		// addSequential(new Command2());
		// these will run in order.

		// To run multiple commands at the same time,
		// use addParallel()
		// e.g. addParallel(new Command1());
		// addSequential(new Command2());
		// Command1 and Command2 will run in parallel.

		// A command group will require all of the subsystems that each member
		// would require.
		// e.g. if Command1 requires chassis, and Command2 requires arm,
		// a CommandGroup containing them would require both the chassis and the
		// arm.
	}
}

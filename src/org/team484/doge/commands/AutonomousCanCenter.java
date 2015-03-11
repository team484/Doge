package org.team484.doge.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class AutonomousCanCenter extends CommandGroup {

	public AutonomousCanCenter() {
		addSequential(new ArmToAngle(105));
		addSequential(new ArmOut());
		addSequential(new WaitCommand(1),1);
		addSequential(new ArmToAngle(80));
		addParallel(new ArmIn());
		addSequential(new ArmToAngle(60));
		addSequential(new WaitCommand(1.5), 1.5);
		addSequential(new ArmToAngle(20));
		addSequential(new GoRotate(-100));
		addSequential(new GoDistance(80));
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

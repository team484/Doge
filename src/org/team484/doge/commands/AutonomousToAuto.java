package org.team484.doge.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousToAuto extends CommandGroup {

	public AutonomousToAuto() {
		addSequential(new ZeroEncoders());
		addSequential(new GoDistance(-50), 10);
		addSequential(new ZeroGyro(), 0.1);
		addSequential(new GoRotate(90));
	}
}

package org.team484.doge.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousCanEnd extends CommandGroup {

	public AutonomousCanEnd() {
		addSequential(new ToteToTop(), 1);
		addSequential(new TotePickupStill(), 0.1);
		addSequential(new ZeroGyro());
		addSequential(new GoRotate(45), 5);
		addSequential(new GoDistance(-100), 5);
		
	}
}

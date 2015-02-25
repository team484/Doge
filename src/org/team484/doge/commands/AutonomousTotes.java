package org.team484.doge.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
/**
 *
 */
public class AutonomousTotes extends CommandGroup {
    
    public  AutonomousTotes() {
    	addSequential(new ToteToBottom());
    	addSequential(new DriveToTote());
    	addSequential(new ToteUpTo0High());
    	addSequential(new ZeroEncoders());
    	addSequential(new GoDistance(-93), 10);
    }
}

package org.team484.doge;

import org.team484.doge.commands.ArmDown;
import org.team484.doge.commands.ArmIn;
import org.team484.doge.commands.ArmOut;
import org.team484.doge.commands.ArmStill;
import org.team484.doge.commands.ArmUp;
import org.team484.doge.commands.SetPickupSpeed;
import org.team484.doge.commands.Stacker;
import org.team484.doge.commands.StackerSetup;
import org.team484.doge.commands.ToteDownTo0High;
import org.team484.doge.commands.TotePickupJoystick;
import org.team484.doge.commands.TotePickupStill;
import org.team484.doge.commands.ToteToBottom;
import org.team484.doge.commands.ToteToTop;
import org.team484.doge.commands.ToteUpTo1High;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	// ----------Buttons Defined----------
	Button totePickupJoystick = new JoystickButton(Robot.operatorStick,
			RobotMap.totePickupJoystickButton);
	Button toteToTop = new JoystickButton(Robot.operatorStick,
			RobotMap.totePickupUpButton);
	Button toteToBottom = new JoystickButton(Robot.operatorStick,
			RobotMap.totePickupDownButton);
	Button armExtend = new JoystickButton(Robot.operatorStick, 6);
	Button armRetract = new JoystickButton(Robot.operatorStick, 7);
	Button armUp = new JoystickButton(Robot.operatorStick, 11);
	Button armDown = new JoystickButton(Robot.operatorStick, 10);
	
	Button stacker = new JoystickButton(Robot.driveStickLeft, 7);

	public OI() { // Method used for giving buttons functions
		totePickupJoystick.whileHeld(new TotePickupStill()); // When the
																// button is
																// held, the
																// tote pickup
																// is joystick
																// controlled
		totePickupJoystick.whenReleased(new TotePickupJoystick()); // Once the
																// button is
																// released the
																// pickup holds
																// position
		toteToTop.whileHeld(new StackerSetup());
		toteToTop.whenReleased(new SetPickupSpeed(0.25));
		toteToTop.whenReleased(new SetPickupSpeed(0.25));
		toteToTop.whenReleased(new TotePickupStill());
		
		toteToBottom.whenPressed(new SetPickupSpeed(1));
		toteToBottom.whenPressed(new ToteDownTo0High());
		toteToBottom.whenReleased(new SetPickupSpeed(0.25));
		toteToBottom.whenReleased(new TotePickupStill());

		armExtend.whileHeld(new ArmOut());
		armRetract.whileHeld(new ArmIn());
		armUp.whileHeld(new ArmUp());
		armDown.whileHeld(new ArmDown());
		
		stacker.whileHeld(new Stacker());
		stacker.whenReleased(new ArmStill());
		stacker.whenReleased(new SetPickupSpeed(0.25));
	}
}

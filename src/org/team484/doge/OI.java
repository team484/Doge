package org.team484.doge;

import org.team484.doge.commands.ArmIn;
import org.team484.doge.commands.ArmOut;
import org.team484.doge.commands.Rotate90;
import org.team484.doge.commands.TotePickupJoystick;
import org.team484.doge.commands.TotePickupStill;
import org.team484.doge.commands.ToteToBottom;
import org.team484.doge.commands.ToteToTop;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
	
	//----------Buttons Defined----------
	Button totePickupJoystick = new JoystickButton(Robot.operatorStick,RobotMap.totePickupJoystickButton);
	Button toteToTop = new JoystickButton(Robot.operatorStick, RobotMap.totePickupUpButton);
	Button toteToBottom = new JoystickButton(Robot.operatorStick, RobotMap.totePickupDownButton);
	Button armExtend = new JoystickButton(Robot.operatorStick, 8);
	Button armRetract = new JoystickButton(Robot.operatorStick, 7);
	
	public OI() { //Method used for giving buttons functions
		totePickupJoystick.whileHeld(new TotePickupJoystick()); //When the button is held, the tote pickup is joystick controlled
		totePickupJoystick.whenReleased(new TotePickupStill()); //Once the button is released the pickup holds position
		
		toteToTop.whileHeld(new ToteToTop());
		toteToTop.whenReleased(new TotePickupStill());
		
		toteToBottom.whileHeld(new ToteToBottom());
		toteToBottom.whenReleased(new TotePickupStill());
		
		armExtend.whileHeld(new ArmOut());
		armRetract.whileHeld(new ArmIn());
	
	}
}


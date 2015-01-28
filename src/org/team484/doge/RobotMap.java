package org.team484.doge;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static int leftMotor = 1;
    // public static int rightMotor = 2;
    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;
	
	//----------Drive Motors----------
	public static int frontLeftMotor = 0;
	public static int rearLeftMotor = 1;
	public static int frontRightMotor = 2;
	public static int rearRightMotor = 3;
	
	//---------Drive Encoders----------
	public static int leftEncoderA = 0;
	public static int leftEncoderB = 1;
	public static int rightEncoderA = 2;
	public static int rightEncoderB = 3;
	public static boolean leftEncoderReverse = false;
	public static boolean rightEncoderReverse = true;
	public static double leftEncoderIncrement = 1;
	public static double rightEncoderIncrement = 1;
	
	//-----------Drive Gyro------------
	public static int gyroUp = 0;
	public static int gyroDown = 1;
	
	//----------Tote Pickup------------
	public static int totePickupMotor = 4;
	public static int totePickupTop = 4;
	public static int totePickupBottom = 5;
	public static int totePickup1High = 6;
	public static int totePickup0High = 7;
	
	//------------Joystick-------------
	
	public static int driveStickLeft = 0; //Use this for arcade drive
	public static int driveStickRight = 1;
	public static int operatorStick = 2;
	public static boolean tankDrive = true;
}

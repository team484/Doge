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
	public static int frontLeftMotor = 0; //Talon that controls front left motor
	public static int rearLeftMotor = 1; //Talon that controls back left motor
	public static int frontRightMotor = 2; //Talon that controls front right motor
	public static int rearRightMotor = 3; //Talon that controls back right motor
	
	//---------Drive Encoders----------
	public static int leftEncoderA = 0; //Digital IO for left encoder's A channel
	public static int leftEncoderB = 1; //Digital IO for left encoder's B channel
	public static int rightEncoderA = 2; //Digital IO for right encoder's A channel
	public static int rightEncoderB = 3; //Digital IO for right encoder's B channel
	public static boolean leftEncoderReverse = false; //multiplies distance by -1
	public static boolean rightEncoderReverse = true; //multiplies distance by -1
	public static double leftEncoderIncrement = 1; //multiplier for left encoder distance
	public static double rightEncoderIncrement = 1; //multiplier for right encoder distance
	
	//-----------Drive Gyro------------
	public static int gyroUp = 0; //Gyro that is facing up
	public static int gyroDown = 1; //Gyro that is facing down
	
	//----------Tote Pickup------------
	public static int totePickupMotor = 4; //Jaguar for winch motor
	public static int totePickupTop = 4; //Top of pickup rail
	public static int totePickupBottom = 5; //Bottom of pickup rail
	public static int totePickup1High = 6; //Height to put 1 tote on another
	public static int totePickup0High = 7; //Right under first tote
	public static boolean halleffectDefault = true; //Default reading from hall effect sensors
	
	public static double winchSpeedMultiplier = 1; //Use -1 to change direction
	public static double operatorStickMultiplierY = 1; //Use -1 to invert joystick
	
	//------------Joystick-------------
	
	public static int driveStickLeft = 0; //Stick used for arcade drive as well
	public static int driveStickRight = 1;
	public static int operatorStick = 2;
	public static boolean tankDrive = true; //For tank or arcade drive (2 or 1 joysticks)
}

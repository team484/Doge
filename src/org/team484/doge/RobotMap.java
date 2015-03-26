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
	// ----------Logging Type----------
	public static int loggingType = 0; // 0 is no logging 1 is standard logging
										// 2 is verbose logging
	
	//-------------LEDS----------------
	public static int ledVictor = 0;
	// ----------Drive Motors----------
	public static int frontLeftMotor = 0; // Talon that controls front left
											// motor
	public static int rearLeftMotor = 1; // Talon that controls back left motor
	public static int frontRightMotor = 2; // Talon that controls front right
											// motor
	public static int rearRightMotor = 3; // Talon that controls back right
											// motor

	// -------Autonomous-------
	public static double moveValueMultiplier = 0.5; // For autonomous motion
	public static double rotateValueMultiplier = 0.85; // For autonomous
														// rotation
	public static double moveValueSlope = 12.0; // How early the robot slows
												// down in inches
	public static double rotateValueSlope = 12.0; // How early the robot slows
													// down in degrees

	// ---------Drive Encoders----------
	public static int leftEncoderA = 0; // Digital IO for left encoder's A
										// channel
	public static int leftEncoderB = 1; // Digital IO for left encoder's B
										// channel
	public static int rightEncoderA = 2; // Digital IO for right encoder's A
											// channel
	public static int rightEncoderB = 3; // Digital IO for right encoder's B
											// channel
	public static boolean leftEncoderReverse = false; // multiplies distance by
														// -1
	public static boolean rightEncoderReverse = false; // multiplies distance by
														// -1
	public static double leftEncoderIncrement = -0.054287; // multiplier for
															// left encoder
															// distance
	public static double rightEncoderIncrement = 0.14370245; // multiplier for
																// right encoder
																// distance

	// -----------Drive Gyro------------
	public static int gyroUp = 0; // Gyro that is facing up
	public static int gyroDown = 1; // Gyro that is facing down

	// ---------------Arm---------------
	public static int armHeightMotor = 5;
	public static int armLengthMotor = 6;
	public static int armPot = 5;
	public static int armExtended = 8;
	public static int armRetracted = 7;

	public static double armLengthSpeed = 1;
	public static double armPotScale = 136.36;
	public static boolean armExtendDefault = false; // Sensor reading when arm
													// ISNT extended
	public static boolean armRetractDefault = false; // Sensor reading when arm
														// ISNT retracted

	// ----------Tote Pickup------------
	public static int totePickupMotor = 4; // Jaguar for winch motor
	public static int totePickupBottom = 6; // Bottom of pickup rail
	public static int totePickup1High = 4; // Height to put 1 tote on another
	public static int totePickup0High = 5; // Right under first tote
	public static int toteCenterIR = 4; // IR for finding distance to tote
	public static int toteLeftIR = 2; // IR on the left of the bot
	public static int toteRightIR = 3; // IR on the right of the bot
	public static int toteTopIR = 6;
	public static int totePickupTopSwitch = 9;
	public static boolean totePickupTopSwitchDefault = false;

	public static double totePickupDistance = 1.1; // Distance the tote is
													// during pickup (Center IR)
	public static boolean halleffectDefault = true; // Default reading from hall
													// effect sensors
	public static double winchSpeedMultiplier = 0.25; // Use -1 to change
														// directions
	public static double operatorStickMultiplierY = 1; // Use -1 to invert
														// joystick
	public static double driveToToteSpeed = 0.6; // Speed the robot autonomously
													// drive to a tote

	public static double getIRDistance(double sensorVoltage) {
		return Math.pow(sensorVoltage, -1.15) * 10.968; // IR sensor equation
	}

	public static double[] getLeftIRCoords() {
		double[] answer = new double[2];
		answer[0] = getIRDistance(Robot.toteLeftIR.getAverageVoltage()) * 0.38268; // distance
																					// *
																					// sin(22.5)
		answer[1] = getIRDistance(Robot.toteLeftIR.getAverageVoltage()) * 0.92388; // distance
																					// *
																					// cos(22.5)
		answer[0] = -12.5 + answer[0];
		return answer;

	}

	public static double[] getRightIRCoords() {
		double[] answer = new double[2];
		answer[0] = getIRDistance(Robot.toteLeftIR.getAverageVoltage()) * 0.38268; // distance
																					// *
																					// sin(22.5)
		answer[1] = getIRDistance(Robot.toteLeftIR.getAverageVoltage()) * 0.92388; // distance
																					// *
																					// cos(22.5)
		answer[0] = 12.5 - answer[0];
		return answer;

	}

	// ------------Joystick-------------

	public static int driveStickLeft = 0; // Stick used for arcade drive as well
	public static int operatorStick = 1;

	// --------Joystick Buttons---------
	public static int totePickupJoystickButton = 1;
	public static int totePickupUpButton = 3;
	public static int totePickupDownButton = 2;
	public static int binAlginButton = 2;
	public static int autoPickupButton = 3;

}

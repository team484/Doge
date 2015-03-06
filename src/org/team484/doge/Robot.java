package org.team484.doge;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Utility;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.team484.doge.commands.AutonomousCanAndTote;
import org.team484.doge.commands.AutonomousCanCenter;
import org.team484.doge.commands.AutonomousCanEnd;
import org.team484.doge.commands.AutonomousDoNothing;
import org.team484.doge.commands.AutonomousToAuto;
import org.team484.doge.commands.AutonomousTotes;
import org.team484.doge.subsystems.ArmHeight;
import org.team484.doge.subsystems.ArmLength;
import org.team484.doge.subsystems.DriveTrain;
import org.team484.doge.subsystems.Logging;
import org.team484.doge.subsystems.TotePickup;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	public static DriverStation ds;
	
	public static final DriveTrain driveTrain = new DriveTrain();
	public static final TotePickup totePickup = new TotePickup();
	public static final Logging logging = new Logging();
	public static final ArmLength armLength = new ArmLength();
	public static final ArmHeight armHeight = new ArmHeight();
	public static OI oi;
    Command[] autonomousCommand = new Command[6];
    public static int selectedAutonomous = 0;
    //----------THE FOLLOWING SETUP ROBOT COMPONENTS TO BE ACCESSED BY THE CODE----------
    //----------Joysticks----------
    public static final Joystick driveStickLeft = new Joystick(RobotMap.driveStickLeft);
    public static final Joystick operatorStick = new Joystick(RobotMap.operatorStick);
    
    //------------Gyros------------
    public static final Gyro gyroUp = new Gyro(RobotMap.gyroUp);
    public static final Gyro gyroDown = new Gyro(RobotMap.gyroDown);
    //------Speed Controllers------
    public static final Talon frontLeftMotor = new Talon(RobotMap.frontLeftMotor);
    public static final Talon rearLeftMotor =  new Talon(RobotMap.rearLeftMotor);
    public static final Talon frontRightMotor = new Talon(RobotMap.frontRightMotor);
    public static final Talon rearRightMotor = new Talon(RobotMap.rearRightMotor);
    public static final Jaguar totePickupMotor = new Jaguar(RobotMap.totePickupMotor);
    
    //----------RobotDrive---------
    public static final RobotDrive driveRobot = new RobotDrive(frontLeftMotor, rearLeftMotor, frontRightMotor, rearRightMotor);
    
    //-------Drive Encoders--------
    public static final Encoder leftEncoder = new Encoder(RobotMap.leftEncoderA, RobotMap.leftEncoderB,RobotMap.leftEncoderReverse);
    public static final Encoder rightEncoder = new Encoder(RobotMap.rightEncoderA, RobotMap.rightEncoderB,RobotMap.rightEncoderReverse);
    
    //---Tote Pickup Hall Effect---
    public static final DigitalInput totePickupBottom = new DigitalInput(RobotMap.totePickupBottom);
    public static final DigitalInput totePickup1High = new DigitalInput(RobotMap.totePickup1High);
    public static final DigitalInput totePickup0High = new DigitalInput(RobotMap.totePickup0High);

    //---Tote Pickup IR sensors----
    public static final AnalogInput toteLeftIR = new AnalogInput(RobotMap.toteLeftIR);
    public static final AnalogInput toteRightIR = new AnalogInput(RobotMap.toteRightIR);
    public static final AnalogInput toteCenterIR = new AnalogInput(RobotMap.toteCenterIR);
    
    //---Power Distribution Panel---
    public static final PowerDistributionPanel PDP = new PowerDistributionPanel();
    
    //--Arm systems---
    public static final Talon armHeightMotor = new Talon(RobotMap.armHeightMotor);
    public static final VictorSP armLengthMotor = new VictorSP(RobotMap.armLengthMotor);
    public static final AnalogPotentiometer armPot = new AnalogPotentiometer(RobotMap.armPot, RobotMap.armPotScale);
    public static final DigitalInput armExtended = new DigitalInput(RobotMap.armExtended);
    public static final DigitalInput armRetracted = new DigitalInput(RobotMap.armRetracted);
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    CameraServer camera;
    public void robotInit() {
    	ds = DriverStation.getInstance();
		oi = new OI();
		autonomousCommand[0] = new AutonomousDoNothing();
		autonomousCommand[1] = new AutonomousToAuto();
		autonomousCommand[2] = new AutonomousTotes();
        autonomousCommand[3] = new AutonomousCanEnd();
        autonomousCommand[4] = new AutonomousCanAndTote();
        autonomousCommand[5] = new AutonomousCanCenter();
        camera = CameraServer.getInstance();
        camera.startAutomaticCapture("cam0");
        
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		//USER button resets all sticky faults
		if (Utility.getUserButton()) {
			PDP.clearStickyFaults();
		}
		selectedAutonomous = (int) SmartDashboard.getNumber("selectedAutonomous", selectedAutonomous); //Gets the autonomous mode from dashboard
		SmartDashboard.putNumber("setAutonomous", selectedAutonomous);
		Logging.pushDashboard();//updates dashboard
	}

    public void autonomousInit() {
        // schedule the autonomous command (example)
        if (autonomousCommand[selectedAutonomous] != null) autonomousCommand[selectedAutonomous].start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand[selectedAutonomous] != null) autonomousCommand[selectedAutonomous].cancel();
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){

    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
    static double distance(double x1,double x2,double y1,double y2) {
		return Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
	}
	public static double[] calculateCenter(double x1, double x2, double y1, double y2, double radius) {
		double separation = distance(x1,x2,y1,y2),mirrorDistance;
		double[] answer = new double[3];
		answer[0] = 0;
		answer[1] = 0;
		answer[2] = 0;
		if (separation == 0.0) {
			answer[0] = -1;
		} else if (separation == 2 * radius) {
			answer[1] = ((x1 + x2) / 2.0);
			answer[2] = ((y1 + y2) / 2.0);
		} else if (separation > 2 * radius) {
			answer[0] = -1;
		} else {
			mirrorDistance = Math.sqrt(Math.pow(radius, 2)-Math.pow(separation/2, 2));
			double C1X;
			double C1Y;
			double C2X;
			double C2Y;
			C1X = ((x1+x2)/2 + mirrorDistance*(y1-y2)/separation);
			C1Y = ((y1+y2)/2+mirrorDistance*(x2-x1)/separation);
			C2X = ((x1+x2)/2-mirrorDistance*(y1-y2)/separation);
			C2Y = ((y1+y2)/2-mirrorDistance*(x2-x1)/separation);
			if (C1X > C2X) {
				answer[1] = C1X;
				answer[2] = C1Y;
			} else {
				answer[1] = C2X;
				answer[2] = C2Y;
			}
		}
		return answer;
	}
}

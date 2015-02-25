package org.team484.doge.commands;

import org.team484.doge.Robot;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDOutput;

/**
 *
 */
public class GoRotate extends Command {
	private PIDController pid;
    public GoRotate(double setpoint) {
    	
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain); // 0.15, -0.1, 0.15 || 0.25 -0.15, 0.6
    	pid = new PIDController(0.3,-0.15,0.55,
    			new PIDSource() { public double pidGet() {
    				return Robot.driveTrain.gyroAngle();
    			}},
    			new PIDOutput() { public void pidWrite(double d) {
    				Robot.driveTrain.rotateSpeed(d);
    			}});
    	pid.setAbsoluteTolerance(.01);
    	pid.setOutputRange(-0.7, 0.7);
    	pid.setSetpoint(setpoint);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	pid.reset();
    	//pid.setPID(SmartDashboard.getNumber("Kp", 0.0), SmartDashboard.getNumber("Ki", 0.0), SmartDashboard.getNumber("Kd", 0.0));
    	pid.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (Math.abs(Robot.driveTrain.gyroAngle() - pid.getSetpoint()) < 2.5) {
    		return pid.onTarget();
    	} else if (Robot.driveTrain.gyroRate() < 3 && Math.abs(Robot.driveTrain.gyroAngle() - pid.getSetpoint()) < 2.5) {
    		return true;
    	} else if (Robot.frontLeftMotor.get() < 0.01 && Robot.rearLeftMotor.get() < 0.01 && Robot.frontRightMotor.get() < 0.01 && Robot.rearRightMotor.get() < 0.01 && Math.abs(Robot.driveTrain.gyroAngle() - pid.getSetpoint()) < 5) {
    		return true;
    	} else {
    		return false;
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    	pid.disable();
    	Robot.driveTrain.rotateSpeed(0);
    	Robot.driveTrain.driveJoysticks();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}

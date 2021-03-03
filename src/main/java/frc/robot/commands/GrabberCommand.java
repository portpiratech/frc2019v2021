package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.subsystems.GrabberSubsystem;

public class GrabberCommand extends Command {


	private boolean open;
	private boolean aLastPressed;
	
    public void Grab() {
		requires(Robot.grabberSubsystem);
    	this.aLastPressed = true;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
		double speed = 0.75;
	    Robot.grabberSubsystem.close(speed);

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    		if(OI.operatorController.getBumper(Hand.kRight))
    		{
    			//ungrab
    	    	Robot.grabberSubsystem.close(-RobotMap.ungrabSpeed);
    	    	aLastPressed = true;
    		}
    		else if(OI.operatorController.getBumper(Hand.kLeft))
    		{
    			//grab
    			Robot.grabberSubsystem.close(RobotMap.grabSpeed);
    			aLastPressed = false;
    		}
    		/*else
    		{
    			if(!aLastPressed && !OI.operatorController.getBumper(Hand.kRight))
    			{
    				//keep trying to grab
    				Robot.grabberSubsystem.close(RobotMap.holdSpeed);
    			}
    			else if(aLastPressed && !OI.operatorController.getBumper(Hand.kLeft))
    			{
    				Robot.grabberSubsystem.close(0);
    			}
    		}*/
    }
    

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        //return !isTurning || Robot.grabberSubsystem.getCount() > MAX_COUNT || Robot.grabberSubsystem.getCount() < 0;
		return false;
		//(OI.operatorController.getBumper(Hand.kRight) || OI.operatorController.getBumper(Hand.kLeft));
    			
    }

    // Called once after isFinished returns true
    protected void end() {
    	if(open) {
    		Robot.grabberSubsystem.close(0);
    	}
    	else{
    		Robot.grabberSubsystem.close(0.45);
    	}
    	
    }
	
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	//Robot.grabberSubsystem.close(0);
    }
} 

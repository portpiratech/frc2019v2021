
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;



import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Subsystem;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.OI;
import frc.robot.RobotMap;
//import frc.robot.commands.LifterCommand;
import frc.robot.commands.LifterCommand;


public class LifterSubsystem extends Subsystem {
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	private TalonSRX motor = new TalonSRX(RobotMap.lifterDriveId);
	//private Encoder leftEncoder;
	//private Encoder rightEncoder;
	//currently no encoders on motors, but there might be later
	public void Lifter(){
		//leftEncoder = new Encoder(RobotMap.leftDriveEncoderId);
		//rightEncoder = new Encoder(RobotMap.rightDriveEncoderId);
	}
	@Override
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new LifterCommand());
    }
    
    public void liftDrive() {
    	
    	double liftSpeed = OI.operatorController.getY(Hand.kRight);
    	liftSpeed *= RobotMap.lifterSpeedMultiplier;
    	
    	motor.set(ControlMode.PercentOutput, liftSpeed);
    }
}
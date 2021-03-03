package frc.robot.subsystems;




import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;


//import frc.robot.commands.DriveCommand;
//import frc.robot.commands.GrabberCommand;
import edu.wpi.first.wpilibj.AnalogTrigger;
//import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.commands.GrabberCommand;

public class GrabberSubsystem extends Subsystem {
	//This is a very basic version of grabber's program
	
	private TalonSRX grabberMotor;
	private AnalogTrigger grabberAnalogTrigger;
	private int count;
	private boolean encState;
	
	
	//Encoder is 174.9:1 gear ratio (174.9 counts per revolution)
	//(50ish reads per second)/(174.9 counts) = 0.285 speed necessary for full encoder resolution
	
	
	public GrabberSubsystem(){
		grabberMotor = new TalonSRX(RobotMap.grabberId);
		grabberAnalogTrigger = new AnalogTrigger(RobotMap.grabberEncoderId);
		grabberAnalogTrigger.setLimitsVoltage(3.0, 3.75);		
		count = 0;
		encState = getEncoderState();
		grabberMotor.enableCurrentLimit(true);
		grabberMotor.configPeakCurrentLimit(0, 0);
		grabberMotor.configContinuousCurrentLimit(RobotMap.grabberMaxCurrent, 500);
		
	}
	@Override
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		//setDefaultCommand(new GrabberCommand());
    }
	
	public void close(double setSpeed) {
		grabberMotor.set(ControlMode.PercentOutput, -setSpeed);
	}

	public void smartDashboardOutput() {
		SmartDashboard.putNumber("Seat motor encoder index", grabberAnalogTrigger.getIndex());
    	SmartDashboard.putBoolean("Seat motor encoder bool", getEncoderState());
    	SmartDashboard.putNumber("Seat motor encoder count test", count);
    
	}
	
	public double getMotorCurrent() {
		return grabberMotor.getOutputCurrent();
	}
	
	public boolean getEncoderState(){
		 return grabberAnalogTrigger.getTriggerState();
	}
	
	public double getMotorOutputPercent() {
		return grabberMotor.getMotorOutputPercent();
	}
	
	public double getMotorOutputPercentSignum() {
		return Math.signum(grabberMotor.getMotorOutputPercent());
	}
	
	public void count() {
		System.out.println(count);
		//Catches the rising edges
		if(encState != getEncoderState() && getEncoderState()) { 
			count += (int) getMotorOutputPercentSignum();
		}
		encState = getEncoderState();
		
	}

	public void resetCount() {
		count = 0;
	}

	public int getCount() {
		return count;
	}
	

}


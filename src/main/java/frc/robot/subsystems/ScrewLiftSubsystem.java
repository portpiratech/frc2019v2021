/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.GenericHID.Hand;
//import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.OI;
import frc.robot.RobotMap;


/**
 * Add your docs here.
 */
public class ScrewLiftSubsystem extends Subsystem {
  private TalonSRX liftMotor1;
  private TalonSRX liftMotor2;
  private TalonSRX liftDriveMotor1;
  private TalonSRX liftDriveMotor2;
  //private DigitalInput switch1;

public ScrewLiftSubsystem() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    liftMotor1 = new TalonSRX(RobotMap.liftMotor1ID);
    liftMotor2 = new TalonSRX(RobotMap.liftMotor2ID);
    liftDriveMotor1 =  new TalonSRX(RobotMap.liftDriveMotor1ID);
    liftDriveMotor2 =  new TalonSRX(RobotMap.liftDriveMotor2ID);
    //switch1 = new DigitalInput(RobotMap.topSwitch);
  }
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    //setDefaultCommand(new UnLiftCommand());
  }
  public void lift(){
    if(OI.operatorController.getBackButton()){
      liftMotor2.set(ControlMode.PercentOutput, -RobotMap.liftSpeed*RobotMap.backLiftMotorMulti);
    }else{
      liftMotor2.set(ControlMode.PercentOutput, 0.0);
    }
    if(OI.operatorController.getStartButton()){
      liftMotor1.set(ControlMode.PercentOutput, -RobotMap.liftSpeed);
    }else{
      liftMotor1.set(ControlMode.PercentOutput, 0.0);
    }
  

    double leftY = OI.operatorController.getY(Hand.kLeft);
    double liftDriveSpeed = (leftY * RobotMap.liftDriveSpeedMultiplier);
    liftDriveMotor1.set(ControlMode.PercentOutput, -liftDriveSpeed);
    liftDriveMotor2.set(ControlMode.PercentOutput, -liftDriveSpeed); 
  }

  public void unLift(){
    if(OI.operatorController.getAButton()){
      liftMotor1.set(ControlMode.PercentOutput, RobotMap.liftSpeed);
    }else{
      liftMotor1.set(ControlMode.PercentOutput, 0.0);
    }

    if(OI.operatorController.getBButton()){
      liftMotor2.set(ControlMode.PercentOutput, RobotMap.liftSpeed);
    }else{
      liftMotor2.set(ControlMode.PercentOutput, 0.0);
    }
    double leftY = OI.operatorController.getY(Hand.kLeft);
    double liftDriveSpeed = (leftY * RobotMap.liftDriveSpeedMultiplier);
    liftDriveMotor1.set(ControlMode.PercentOutput, -liftDriveSpeed);
    liftDriveMotor2.set(ControlMode.PercentOutput, -liftDriveSpeed); 
  }
}

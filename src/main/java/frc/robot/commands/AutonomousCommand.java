/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.OI;
import frc.robot.RobotMap;
public class AutonomousCommand extends CommandGroup {
  
  public AutonomousCommand() {
    TalonSRX liftMotor1;
    TalonSRX liftMotor2;
    TalonSRX rightMotor;
    TalonSRX leftMotor;
    

    liftMotor1 = new TalonSRX(RobotMap.liftMotor1ID);
    liftMotor2 = new TalonSRX(RobotMap.liftMotor2ID);
    rightMotor = new TalonSRX(RobotMap.rightMotorID);
    leftMotor = new TalonSRX(RobotMap.leftMotorID);
    if(OI.driverController.getYButton())
    {
      liftMotor1.set(ControlMode.PercentOutput, -RobotMap.liftSpeed);
      Timer.delay(1);
      liftMotor1.set(ControlMode.PercentOutput, RobotMap.liftStopper);
      rightMotor.set(ControlMode.PercentOutput, RobotMap.driveSpeedMultiplier/4);
      leftMotor.set(ControlMode.PercentOutput, RobotMap.driveSpeedMultiplier/4);
      Timer.delay(2);
      liftMotor2.set(ControlMode.PercentOutput, RobotMap.liftSpeed);
      Timer.delay(1);
      liftMotor1.set(ControlMode.PercentOutput, RobotMap.liftStopper);
    }
    // Add Commands here:
    // e.g. addSequential(new Command1());
    // addSequential(new Command2());
    // these will run in order.

    // To run multiple commands at the same time,
    // use addParallel()
    // e.g. addParallel(new Command1());
    // addSequential(new Command2());
    // Command1 and Command2 will run in parallel.

    // A command group will require all of the subsystems that each member
    // would require.
    // e.g. if Command1 requires chassis, and Command2 requires arm,
    // a CommandGroup containing them would require both the chassis and the
    // arm.
  }
}

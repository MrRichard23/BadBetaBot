// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Teleop;
import frc.robot.Operator;
import frc.robot.subsystems.Drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;
public class DrivetrainCommand extends CommandBase{
  /** Creates a new Easy. */
  Drivetrain drivetrain;

  private boolean toggleTrigger = false;
  private boolean oldTrigger = true;
  private boolean newTrigger = false;
  public DrivetrainCommand() {
    // Use addRequirements() here to declare subsystem dependencies.
    drivetrain = Drivetrain.getInstance();   
    addRequirements(drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    newTrigger = Operator.getLeftTrigger();
    if(newTrigger == true && oldTrigger == false && toggleTrigger == false){
      toggleTrigger = true;
    }
    else if(newTrigger == true && oldTrigger == false && toggleTrigger == true){
      toggleTrigger = false;
    }
    if(Operator.getRightTrigger() == true){
      drivetrain.setSenLeftDrive(0);
      drivetrain.setSenRightDrive(0);
    }
    else{
      if(toggleTrigger == false){
        drivetrain.setSenLeftDrive(Operator.getLeftJoystick());
        drivetrain.setSenRightDrive(Operator.getRightJoystick());
      }
      else{
        drivetrain.setSenLeftDrive(-Operator.getRightJoystick());
        drivetrain.setSenRightDrive(-Operator.getLeftJoystick());
      }
    }

    oldTrigger = newTrigger;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}

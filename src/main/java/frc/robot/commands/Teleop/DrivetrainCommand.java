// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Teleop;
import frc.robot.Operator;
import frc.robot.subsystems.Drivetrain;
import frc.robot.Logic;

import edu.wpi.first.wpilibj2.command.CommandBase;
public class DrivetrainCommand extends CommandBase{
  /** Creates a new Easy. */
  Drivetrain drivetrain;

  private boolean toggleLTrigger = false;
  private boolean oldLTrigger = true;
  private boolean newLTrigger = false;
  private boolean alternateLTrigger = false;
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
    newLTrigger = Operator.getLeftTrigger();
    
    alternateLTrigger = Logic.justPressed2ToggleLogic(newLTrigger, oldLTrigger, alternateLTrigger);

    // toggleLTrigger = Logic.justPressedLogic(newLTrigger, oldLTrigger);

    // if(toggleLTrigger == true){
    //   if(alternateLTrigger == false){
    //     alternateLTrigger = true;
    //   }
    //   else{
    //     alternateLTrigger = false;
    //   }
    // }
    if(Operator.getRightTrigger() == true){
      drivetrain.setSenLeftDrive(0);
      drivetrain.setSenRightDrive(0);
    }
    else{
      if(alternateLTrigger == false){
        drivetrain.setSenLeftDrive(Operator.getLeftJoystick());
        drivetrain.setSenRightDrive(Operator.getRightJoystick());
      }
      else{
        drivetrain.setSenLeftDrive(-Operator.getRightJoystick());
        drivetrain.setSenRightDrive(-Operator.getLeftJoystick());
      }
    }

    oldLTrigger = newLTrigger;
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

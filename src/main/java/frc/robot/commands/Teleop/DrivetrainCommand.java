package frc.robot.commands.Teleop;
import frc.robot.Operator;
import frc.robot.subsystems.Drivetrain;
import frc.robot.Logic;

import edu.wpi.first.wpilibj2.command.CommandBase;
public class DrivetrainCommand extends CommandBase{
  /** Creates a new Easy. */
  Drivetrain drivetrain;

  private boolean oldButton1 = true;  //Right trigger
  private boolean newButton1 = false;
  private boolean pressedButton1 = false;
  private boolean alternateButton1 = false;

  private boolean oldButton2 = true;  //Left trigger
  private boolean newButton2 = false;
  private boolean pressedButton2 = false;
  private boolean alternateButton2 = false;
  
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
    newButton1 = Operator.getRightTrigger();

    pressedButton1 = Operator.getRightJoystick().getTriggerPressed();

    alternateButton1 = Logic.pressed2ToggleLogic(pressedButton1, alternateButton1);

    newButton2 = Operator.getLeftTrigger();

    pressedButton2 = Operator.getLeftJoystick().getTriggerPressed();
    
    alternateButton2 = Logic.pressed2ToggleLogic(pressedButton2, alternateButton2);

    if(Operator.getRightTrigger() == true){
      drivetrain.setLeftDrive(0);
      drivetrain.setRightDrive(0);
      alternateButton1 = false;
    }
    else if(alternateButton1 == true){
      
    }
    else{
      if(alternateButton2 == false){
        drivetrain.setLeftDrive(Operator.getLeftJoystickY());
        drivetrain.setRightDrive(Operator.getRightJoystickY());
      }
      else{
        drivetrain.setLeftDrive(-Operator.getRightJoystickY());
        drivetrain.setRightDrive(-Operator.getLeftJoystickY());
      }
    }

    oldButton2 = newButton2;
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

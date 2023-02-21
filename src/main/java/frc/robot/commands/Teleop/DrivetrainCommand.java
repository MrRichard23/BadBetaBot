package frc.robot.commands.Teleop;
import frc.robot.Operator;
import frc.robot.subsystems.Drivetrain;
import frc.robot.Logic;

import edu.wpi.first.wpilibj2.command.CommandBase;
public class DrivetrainCommand extends CommandBase{
  /** Creates a new Easy. */
  Drivetrain drivetrain;
  
  private boolean newButton1 = false;
  private boolean oldButton1 = true; //Right trigger
  private boolean pressedButton1 = false;
  private boolean alternateButton1 = false;

  private boolean newButton2 = false; //Left trigger
  private boolean oldButton2 = true;
  private boolean pressedButton2 = false;
  private boolean alternateButton2 = false;

  private boolean newButton3 = false; //Right side button
  private boolean oldButton3 = true;
  private boolean pressedButton3 = false;
  private boolean alternateButton3 = false;

  private boolean newButton4 = false; //Right button 3
  private boolean oldButton4 = true;
  private boolean pressedButton4= false;
  private boolean alternateButton4 = false;

  private int littlePitch;

  private double autoDriveTrainSpeed = 0.015;
  private double autoDriveTrainSlowSpeed = 0.08;
  
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
    Operator.SmartDashboard1(Operator.getLeftThrottle(), "Sensitivity");
    Operator.SmartDashboard2(Operator.getPitch(), "Pitch");
    Operator.SmartDashboard3(Operator.getRoll(), "Roll");
    Operator.SmartDashboard4(Operator.getYaw(), "Yaw");
    Operator.SmartDashboard5(Operator.getXVelocity(), "X Velocity");
    Operator.SmartDashboard6(Operator.getYVelocity(), "Y Velocity");
    Operator.SmartDashboard7(Operator.getZVelocity(), "Z Velocity");

    oldButton1 = newButton1;
    newButton1 = Operator.getRightTrigger();
    pressedButton1 = Operator.getRightJoystick().getTriggerPressed();
    alternateButton1 = Logic.pressed2ToggleLogic(pressedButton1, alternateButton1);

    oldButton2 = newButton2;
    newButton2 = Operator.getLeftTrigger();
    pressedButton2 = Operator.getLeftJoystick().getTriggerPressed();
    alternateButton2 = Logic.pressed2ToggleLogic(pressedButton2, alternateButton2);

    oldButton3 = newButton3;
    newButton3 = Operator.getRightButton2();
    pressedButton3 = Logic.pressedLogic(newButton3, oldButton3);
    alternateButton3 = Logic.pressed2ToggleLogic(pressedButton3, alternateButton3);

    oldButton4 = newButton4;
    newButton4 = Operator.getRightButton3();
    pressedButton4 = Logic.pressedLogic(newButton4, oldButton4);
    alternateButton4 = Logic.pressed2ToggleLogic(pressedButton4, alternateButton4);

    if(Logic.lessGreater(-3, Operator.getPitch(), 3)){
      littlePitch++;
    }
    else{
      littlePitch = 0;
    }

    if(alternateButton3 == true){
      drivetrain.setStopPID();
      alternateButton1 = false;
    }
    else if(newButton4 == true){
      if(Operator.getRoll() < 0){
        drivetrain.setFullLeftDrive(-0.1);
        drivetrain.setFullRightDrive(0.1);
      }
      else{
        drivetrain.setFullLeftDrive(0.1);
        drivetrain.setFullRightDrive(-0.1);
      }
    }
    else if(alternateButton1 == true){
      if(littlePitch > 25){
        drivetrain.setStopPID();
      }
      else if(Logic.lessGreater(-15, Operator.getPitch(), 15)){
        drivetrain.setBalanceDrivetrain();
      }
      else{
        drivetrain.setFullAllDrive(-0.05 * Logic.plusNeg(Operator.getPitch()));
      }
    }
    else{
      if(alternateButton2 == true){
        drivetrain.setLeftDrive(Operator.getLeftJoystickY());
        drivetrain.setRightDrive(Operator.getRightJoystickY());
      }
      else{
        drivetrain.setLeftDrive(-Operator.getRightJoystickY());
        drivetrain.setRightDrive(-Operator.getLeftJoystickY());
      }
    }
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

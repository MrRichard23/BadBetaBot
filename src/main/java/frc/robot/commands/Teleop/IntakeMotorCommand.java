package frc.robot.commands.Teleop;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Operator;
import frc.robot.subsystems.IntakeMotor;
import frc.robot.Logic;

public class IntakeMotorCommand extends CommandBase{
    IntakeMotor intakeMotor;
  
    private boolean newButton1 = false; //X button
    private boolean oldButton1 = true;
    private boolean pressedButton1 = false;
    private boolean alternateButton1 = false;

    private boolean newStartButton = false;
    // private boolean oldStartButton = false;
    private boolean unPressedStartButton = false;

    public IntakeMotorCommand() {
        intakeMotor = IntakeMotor.getInstance();
        addRequirements(intakeMotor);
    }

    public void execute() {
        oldButton1 = newButton1;
        newButton1 = Operator.getXButton();
        pressedButton1 = Operator.getXboxController().getXButtonPressed();
        alternateButton1 = Logic.pressed2ToggleLogic(pressedButton1, alternateButton1);

        newStartButton = Operator.getStartButton();
        unPressedStartButton = Operator.getXboxController().getStartButtonReleased();

        if(newStartButton == true){
            intakeMotor.setIntakeMotor(-1);
        }
        else if(unPressedStartButton == true){
            intakeMotor.setIntakeMotor(0);
        }
        else{
            if(pressedButton1 == true){
                if(alternateButton1 == true){
                    intakeMotor.setIntakeMotor(0);
                }
                else{
                    intakeMotor.setIntakeMotor(1);
                }
            }
        }
    }
}
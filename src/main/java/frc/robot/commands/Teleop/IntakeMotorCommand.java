package frc.robot.commands.Teleop;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Operator;
import frc.robot.subsystems.IntakeMotor;
import frc.robot.Logic;

public class IntakeMotorCommand extends CommandBase{
    IntakeMotor intakeMotor;

    private boolean oldYButton = true;
    private boolean newYButton = false;
    private boolean toggleYButton = false;
    private boolean alternateYButton = false;

    private boolean newStartButton = false;
    private boolean oldStartButton = false;
    private boolean toggleStartButton = false;

    public IntakeMotorCommand() {
        intakeMotor = IntakeMotor.getInstance();
        addRequirements(intakeMotor);
    }

    public void execute() {
        newYButton = Operator.getXButton();

        toggleYButton = Logic.justPressedLogic(newYButton, oldYButton);

        alternateYButton = Logic.justPressed2ToggleLogic(newYButton, oldYButton, alternateYButton);

        newStartButton = Operator.getStartButton();

        toggleStartButton = Logic.justUnPressedLogic(newStartButton, oldStartButton);

        if(newStartButton == true){
            intakeMotor.setIntakeMotor(-1);
        }
        else if(toggleStartButton == true){
            intakeMotor.setIntakeMotor(0);
        }
        else{
            if(toggleYButton == true){
                if(alternateYButton == true){
                    intakeMotor.setIntakeMotor(0);
                }
                else{
                    intakeMotor.setIntakeMotor(1);
                }
            }
        }
        oldYButton = newYButton;

        oldStartButton = newStartButton;
    }
}
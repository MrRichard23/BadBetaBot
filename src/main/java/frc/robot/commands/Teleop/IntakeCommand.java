package frc.robot.commands.Teleop;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Operator;
import frc.robot.subsystems.Intake;
import frc.robot.Logic;

public class IntakeCommand extends CommandBase{
    Intake intake;

    private boolean oldAButton = true;
    private boolean newAButton = false;
    private boolean toggleAButton = false;
    private boolean alternateAButton = false;

    public IntakeCommand() {
        intake = Intake.getInstance();
        addRequirements(intake);
    }

    public void execute() {
        newAButton = Operator.getAButton();

        toggleAButton = Logic.justPressedLogic(newAButton, oldAButton);

        alternateAButton = Logic.justPressed2ToggleLogic(newAButton, oldAButton, alternateAButton);

        if(toggleAButton == true){
            intake.setIntake(alternateAButton);
        }
        oldAButton = newAButton;
    }
}

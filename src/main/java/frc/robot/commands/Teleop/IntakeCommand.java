package frc.robot.commands.Teleop;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Operator;
import frc.robot.subsystems.Intake;
import frc.robot.Logic;

public class IntakeCommand extends CommandBase{
    Intake intake;

    private boolean newButton1 = false; //A button
    private boolean oldButton1 = true;
    private boolean pressedButton1 = false;
    private boolean alternateButton1 = false;

    public IntakeCommand() {
        intake = Intake.getInstance();
        addRequirements(intake);
    }

    public void execute() {
        oldButton1 = newButton1;
        newButton1 = Operator.getAButton();
        pressedButton1 = Operator.getXboxController().getAButtonPressed();
        alternateButton1 = Logic.pressed2ToggleLogic(pressedButton1, alternateButton1);

        if(pressedButton1 == true){
            intake.setIntake(alternateButton1);
        }
    }
}

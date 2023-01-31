package frc.robot.commands.Teleop;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Operator;
import frc.robot.subsystems.Climber;
import frc.robot.Logic;

public class ClimberCommand extends CommandBase{
    Climber climber;

    private boolean oldButton1 = true;    //B button
    private boolean newButton1 = false;
    private boolean pressedButton1 = false;
    private boolean alternateButton1 = false;

    public ClimberCommand() {
        climber = Climber.getInstance();
        addRequirements(climber);
    }

    public void execute() {
        newButton1 = Operator.getBButton();

        pressedButton1 = Operator.getXboxController().getBButtonPressed();

        alternateButton1 = Logic.pressed2ToggleLogic(pressedButton1, alternateButton1);

        if(pressedButton1 == true){
            climber.setClimber(alternateButton1);
        }
        oldButton1 = newButton1;
    }
}

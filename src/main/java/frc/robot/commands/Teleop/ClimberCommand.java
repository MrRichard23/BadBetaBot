package frc.robot.commands.Teleop;
import edu.wpi.first.wpilibj.PneumaticHub;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Operator;
import frc.robot.subsystems.Climber;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import frc.robot.Logic;

public class ClimberCommand extends CommandBase{
    Climber climber;

    private boolean oldBButton = true;
    private boolean newBButton = false;
    private boolean toggleBButton = false;
    private boolean alternateBButton = false;

    public ClimberCommand() {
        climber = Climber.getInstance();
        addRequirements(climber);
    }

    public void execute() {
        newBButton = Operator.getBButton();

        toggleBButton = Logic.justPressedLogic(newBButton, oldBButton);

        alternateBButton = Logic.justPressed2ToggleLogic(newBButton, oldBButton, alternateBButton);

        if(toggleBButton == true){
          climber.setClimber(alternateBButton);
        }
        oldBButton = newBButton;
    }
}

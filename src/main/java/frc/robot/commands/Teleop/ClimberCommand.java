package frc.robot.commands.Teleop;
import edu.wpi.first.wpilibj.PneumaticHub;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Operator;
import frc.robot.subsystems.Climber;
import edu.wpi.first.wpilibj.DoubleSolenoid;

public class ClimberCommand extends CommandBase{
    Climber climber;

    private boolean oldBButton = true;
    private boolean newBButton = false;
    private boolean toggleBButton = false;

    public ClimberCommand() {
        climber = Climber.getInstance();
        addRequirements(climber);
    }

    public void execute() {
        newBButton = Operator.getBButton();

        

        if(oldBButton == false && newBButton == true){
             toggleBButton = true;
        }
        else{
            toggleBButton = false;
        }

        if(toggleBButton == true){
          if(climber.getPiston() == DoubleSolenoid.Value.kForward){
             climber.setBackwardClimber();
          }
          else{
            climber.setForwardClimber();
          }
        }
        oldBButton = newBButton;
    }
}

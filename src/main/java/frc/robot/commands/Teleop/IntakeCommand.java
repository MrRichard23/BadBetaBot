package frc.robot.commands.Teleop;
import edu.wpi.first.wpilibj.PneumaticHub;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Operator;
import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj.DoubleSolenoid;

public class IntakeCommand extends CommandBase{
    Intake intake;

    private boolean oldAButton = true;
    private boolean newAButton = false;
    private boolean toggleAButton = false;

    public IntakeCommand() {
        intake = Intake.getInstance();
        addRequirements(intake);
    }

    public void execute() {
        newAButton = Operator.getAButton();

        

        if(oldAButton == false && newAButton == true){
             toggleAButton = false;
        }
        else{
            toggleAButton = false;
        }

        if(toggleAButton == true){
          if(intake.getPiston() == DoubleSolenoid.Value.kForward){
             intake.setBackwardIntake();
          }
          else{
            intake.setForwardIntake();
          }
        }
        if(Operator.getAButton() == true){
          intake.setForwardIntake();
        }
        else if(Operator.getBButton() == true){
            intake.setBackwardIntake();
        }

        oldAButton = newAButton;
    }
}

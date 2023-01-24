package frc.robot.commands.Teleop;
import edu.wpi.first.wpilibj.PneumaticHub;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Operator;
import frc.robot.subsystems.Feeder;
import frc.robot.Logic;

public class FeederCommand extends CommandBase{
    Feeder feeder;

    private boolean oldLeftBumper = true;
    private boolean newLeftBumper = false;
    private boolean toggleLeftBumper = false;
    private boolean alternateLeftBumper = false;

    public FeederCommand() {
        feeder = Feeder.getInstance();
        addRequirements(feeder);
    }

    public void execute() {
        newLeftBumper = Operator.getLeftBumper();

        toggleLeftBumper = Logic.justPressedLogic(newLeftBumper, oldLeftBumper);

        alternateLeftBumper = Logic.justPressed2ToggleLogic(newLeftBumper, oldLeftBumper, alternateLeftBumper);

        if(toggleLeftBumper == true){
            if(alternateLeftBumper == true){
                feeder.setFeeder(1);
            }
            else{
                feeder.setFeeder(0);
            }
        }
        oldLeftBumper = newLeftBumper;
    
     }


}

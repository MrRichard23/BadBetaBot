package frc.robot.commands.Teleop;
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

    private boolean oldRightBumper = true;
    private boolean newRightBumper = false;
    private boolean toggleRightBumper = false;
    private boolean alternateRightBumper = false;

    private boolean newStartButton = false;
    private boolean oldStartButton = false;
    private boolean toggleStartButton = false;

    public FeederCommand() {
        feeder = Feeder.getInstance();
        addRequirements(feeder);
    }

    public void execute() {
        newLeftBumper = Operator.getLeftBumper();

        toggleLeftBumper = Logic.justPressedLogic(newLeftBumper, oldLeftBumper);

        alternateLeftBumper = Logic.justPressed2ToggleLogic(newLeftBumper, oldLeftBumper, alternateLeftBumper);

        newRightBumper = Operator.getRightBumper();

        toggleRightBumper = Logic.justPressedLogic(newRightBumper, oldRightBumper);

        alternateRightBumper = Logic.justPressed2ToggleLogic(newRightBumper, oldRightBumper, alternateRightBumper);

        newStartButton = Operator.getStartButton();

        toggleStartButton = Logic.justUnPressedLogic(newStartButton, oldStartButton);

        if(newStartButton == true){
            feeder.setUpperFeeder(-1);
            feeder.setLowerFeeder(-1);
        }
        else if(toggleStartButton == true){
            feeder.setUpperFeeder(0);
            feeder.setLowerFeeder(0);
        }
        else{
            if(toggleRightBumper == true){
                if(alternateRightBumper == true){
                    feeder.setUpperFeeder(1);
                }
                else{
                    feeder.setUpperFeeder(0);
                }
            }
            if(toggleLeftBumper == true){
                if(alternateLeftBumper == true){
                    feeder.setLowerFeeder(1);
                }
                else{
                    feeder.setLowerFeeder(0);
                }
            }
        }
        oldLeftBumper = newLeftBumper;

        oldRightBumper = newRightBumper;
        
        oldStartButton = newStartButton;
    }
}
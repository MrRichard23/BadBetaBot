package frc.robot.commands.Teleop;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Operator;
import frc.robot.subsystems.Feeder;
import frc.robot.Logic;

public class FeederCommand extends CommandBase{
    Feeder feeder;

    private boolean oldButton1 = true;    //Left bumper
    private boolean newButton1 = false;
    private boolean pressedButton1 = false;
    private boolean alternateButton1 = false;

    private boolean oldButton2 = true;    //Right bumper
    private boolean newButton2 = false;
    private boolean pressedButton2 = false;
    private boolean alternateButton2 = false;

    private boolean newStartButton = false;
    private boolean oldStartButton = false;
    private boolean unPressedStartButton = false;

    public FeederCommand() {
        feeder = Feeder.getInstance();
        addRequirements(feeder);
    }

    public void execute() {
        newButton1 = Operator.getLeftBumper();

        pressedButton1 = Operator.getXboxController().getLeftBumperPressed();

        alternateButton1 = Logic.pressed2ToggleLogic(pressedButton1, alternateButton1);

        newButton2 = Operator.getRightBumper();

        pressedButton2 = Operator.getXboxController().getRightBumperPressed();

        alternateButton2 = Logic.pressed2ToggleLogic(pressedButton2, alternateButton2);

        newStartButton = Operator.getStartButton();

        // unPressedStartButton = Operator.getXboxController().getStartButtonReleased();

        unPressedStartButton = Logic.unPressedLogic(newStartButton, oldStartButton);

        if(newStartButton == true){
            feeder.setUpperFeeder(-1);
            feeder.setLowerFeeder(-1);
        }
        else if(unPressedStartButton == true){
            feeder.setUpperFeeder(0);
            feeder.setLowerFeeder(0);
            alternateButton1 = false;
            alternateButton2 = false;
        }
        else{
            if(pressedButton1 == true){
                if(alternateButton1 == true){
                    feeder.setLowerFeeder(1);
                }
                else{
                    feeder.setLowerFeeder(0);
                }
            }
            if(pressedButton2 == true){
                if(alternateButton2 == true){
                    feeder.setUpperFeeder(1);
                }
                else{
                    feeder.setUpperFeeder(0);
                }
            }
        }
        oldButton1 = newButton1;

        oldButton2 = newButton2;
        
        oldStartButton = newStartButton;
    }
}
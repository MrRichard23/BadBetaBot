package frc.robot.commands.Teleop;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Logic;
import frc.robot.Operator;
import frc.robot.subsystems.Lights;

public class LightsCommand extends CommandBase{
    Lights lights;

    private boolean newButton1 = false;
    private boolean oldButton1 = true;
    private boolean pressedButton1 = false;
    private boolean alternateButton1 = false;

    public LightsCommand(){
        lights = Lights.getInstance();

        addRequirements(lights);
    }

    public void execute(){
        if(Operator.getPOV() == 0){
            lights.setLights(1, false);
        }
        else if(Operator.getPOV() == 90){
            lights.setLights(1, true);
        }
        else if(Operator.getPOV() == 180){
            lights.setLights(2, false);
        }
        else if(Operator.getPOV() == 270){
            lights.setLights(2, true);
        }
    }
}

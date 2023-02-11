package frc.robot.commands.Called;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;

public class ClimberControl extends CommandBase{
    Climber climber;
    boolean setting;

    public ClimberControl(boolean setting){
        this.setting = setting;

        climber = Climber.getInstance();

        addRequirements(climber);
    }

    public void execute(){
        climber.setClimber(setting);
    }
}

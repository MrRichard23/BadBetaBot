package frc.robot.commands.Called;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Feeder;

public class LowerFeederControl extends CommandBase{
    Feeder feeder;
    private double setting;

    public LowerFeederControl(double setting){
        this.setting = setting;

        feeder = Feeder.getInstance();

        addRequirements(feeder);
    }

    public void execute(){
        feeder.setLowerFeeder(setting);
    }
}

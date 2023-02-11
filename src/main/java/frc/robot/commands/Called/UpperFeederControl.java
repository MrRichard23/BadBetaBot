package frc.robot.commands.Called;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Feeder;

public class UpperFeederControl extends CommandBase{
    Feeder feeder;
    private double setting;

    public UpperFeederControl(double setting){
        this.setting = setting;

        feeder = Feeder.getInstance();

        addRequirements(feeder);
    }

    public void initialize(){
        feeder.setUpperFeeder(setting);
    }

    public boolean isFinished() {
        return true;
    }
}

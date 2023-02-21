package frc.robot.commands.Control;

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

    public void initialize(){
        feeder.setLowerFeeder(setting);
    }

    public boolean isFinished() {
        return true;
    }
}

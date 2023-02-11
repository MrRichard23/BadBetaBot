package frc.robot.commands.Called;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

public class ShooterControl extends CommandBase{
    Shooter shooter;
    private double setting;

    public ShooterControl(double setting){
        this.setting = setting;

        shooter = Shooter.getInstance();

        addRequirements(shooter);
    }

    public void initialize(){
        shooter.setShooterPID(setting);
    }

    public boolean isFinished() {
        return true;
    }
}

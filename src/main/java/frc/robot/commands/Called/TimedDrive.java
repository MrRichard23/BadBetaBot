package frc.robot.commands.Called;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Operator;
import frc.robot.subsystems.Drivetrain;

public class TimedDrive extends CommandBase{
    Drivetrain drivetrain;
    private double speed;
    private double time;

    private Timer timer;

    public TimedDrive(double speed, double time) {
        drivetrain = Drivetrain.getInstance();
        this.speed = speed;
        this.time = time;
        timer = new Timer();

        addRequirements(drivetrain);
    }

    public void initialize() {
        timer.reset();
        timer.start();
    }

    public void execute() {
        drivetrain.setUnlimitedAllDrive(speed);
    }

    public boolean isFinished() {
        return timer.get() > time;
    }

    public void end(boolean interupted){
        drivetrain.setStop();
    }
}

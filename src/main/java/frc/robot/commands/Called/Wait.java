package frc.robot.commands.Called;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class Wait extends CommandBase{
    Timer timer;
    double time;

    public Wait(double time){
        this.time = time;

        timer = new Timer();
    }

    public void initialize() {
        timer.reset();
        timer.start();
    }

    public boolean isFinished() {
        return timer.get() > time;
    }
}

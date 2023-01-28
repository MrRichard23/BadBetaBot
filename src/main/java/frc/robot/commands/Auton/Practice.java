package frc.robot.commands.Auton;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public final class Practice extends CommandBase{
    Drivetrain drivetrain = Drivetrain.getInstance();
    private Timer timer = new Timer();
    private double time;

    private double drivetrainspeed = 0.1;

    public Practice(double time){
        timer = new Timer();
        this.time = time;
        drivetrain = Drivetrain.getInstance();
        addRequirements(drivetrain);
    }

    public void initialize() {
        timer.reset();
        timer.start();
    }

    public void execute() {
        if(timer.get() < 1){
            drivetrain.setLeftDrive(drivetrainspeed);
            drivetrain.setRightDrive(drivetrainspeed);
        }
        else if((timer.get()) < 2 && (timer.get() > 1)){
            drivetrain.setStop();
        }
        else if(timer.get() > 2){
            drivetrain.setUnlimitedLeftDrive(0);
            drivetrain.setRightDrive(0);
        }
        else{
            drivetrain.setUnlimitedLeftDrive(0);
            drivetrain.setRightDrive(0);
        }
    }

    public void end(boolean interrupted) {
        drivetrain.setLeftDrive(0);
        drivetrain.setRightDrive(0);
        timer.stop();
    }
}
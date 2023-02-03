package frc.robot.commands.Auton;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.Logic;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.IntakeMotor;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Feeder;

public final class Practice extends CommandBase{
    Drivetrain drivetrain;
    Intake intake;
    IntakeMotor intakeMotor;
    Feeder feeder;
    Shooter shooter;

    private Timer timer = new Timer();
    private double time;
    private double currentTime;

    private double drivetrainspeed = 0.15;
    private double time0 = 0;
    private double time1 = time0 + 1;
    private double time2 = time1 + 0.25;
    private double time3 = time2 + 1;
    private double time4 = time3 + 4.4;
    private double time5 = time4 + 1;
    private double time6 = time5 + 1;

    public Practice(double time){
        timer = new Timer();
        this.time = time;

        drivetrain = Drivetrain.getInstance();
        intake = Intake.getInstance();
        intakeMotor = IntakeMotor.getInstance();
        feeder = Feeder.getInstance();
        shooter = Shooter.getInstance();

        addRequirements(drivetrain, intake, intakeMotor, feeder, shooter);
    }

    public void initialize() {
        timer.reset();
        timer.start();
    }

    public void execute() {
        currentTime = timer.get();
        if(Logic.lessGreater(time0, currentTime, time1)){
            drivetrain.setUnlimitedAllDrive(drivetrainspeed);
            intake.setIntake(true);
            intakeMotor.setIntakeMotor(1);
            feeder.setLowerFeeder(1);
        }
        else if(Logic.lessGreater(time1, currentTime, time2)){
            drivetrain.setStop();
        }
        else if(Logic.lessGreater(time2, currentTime, time3)){
            drivetrain.setUnlimitedAllDrive(-drivetrainspeed);
            intake.setIntake(false);
            intakeMotor.setIntakeMotor(0);
        }
        else if(Logic.lessGreater(time3, currentTime, time4)){
            drivetrain.setUnlimitedLeftDrive(-drivetrainspeed);
            drivetrain.setUnlimitedRightDrive(drivetrainspeed);
            feeder.setLowerFeeder(0);
            shooter.setShooter(0.75);
        }
        else if(Logic.lessGreater(time4, currentTime, time5)){
            drivetrain.setStop();
            feeder.setUpperFeeder(1);
        }
        else if(Logic.lessGreater(time5, currentTime, time6)){
            feeder.setUpperFeeder(0);
            shooter.setShooter(0);
        }
    }

    public void end(boolean interrupted) {
        drivetrain.setStop();
        shooter.setShooter(0);
        feeder.setUpperFeeder(0);
        feeder.setLowerFeeder(0);
        intakeMotor.setIntakeMotor(0);
        timer.stop();
    }
}
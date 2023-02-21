package frc.robot.commands.Auton;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.Logic;
import frc.robot.Operator;
import frc.robot.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.Timer;

public class Square extends CommandBase{
    Drivetrain drivetrain;
    
    private Timer timer;
    private double time;
    private double currentTime;

    private double drivetrainspeed = 0.15;
    private double forewardTime = 1;
    private double turn90 = 2.5;
    private double time0 = 0;
    private double time1 = time0 + forewardTime;
    private double time2 = time1 + turn90;
    private double time3 = time2 + forewardTime;
    private double time4 = time3 + turn90;
    private double time5 = time4 + forewardTime;
    private double time6 = time5 + turn90;
    private double time7 = time6 + forewardTime;
    private double time8 = time7 + turn90;
    private double time9 = time8 + 1;

    private double oldYaw = 0;
    private double newYaw;

    private double oldTime;

    public Square(double time){
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
        newYaw = Operator.getYaw();

        currentTime = timer.get();

        Operator.SmartDashboard1(currentTime, "Time");

        if(Logic.lessGreater(time0, currentTime, time1)){
            drivetrain.setFullAllDrive(drivetrainspeed);
            oldYaw = newYaw;
        }
        else if(Logic.lessGreater(time1, currentTime, time2)){
            drivetrain.setTurnPID(-90, oldYaw);
        }
        else if(Logic.lessGreater(time2, currentTime, time3)){
            drivetrain.setFullAllDrive(drivetrainspeed);
            oldYaw = newYaw;
        }
        else if(Logic.lessGreater(time3, currentTime, time4)){
            drivetrain.setTurnPID(-90, oldYaw);
        }
        else if(Logic.lessGreater(time4, currentTime, time5)){
            drivetrain.setFullAllDrive(drivetrainspeed);
            oldYaw = newYaw;
        }
        else if(Logic.lessGreater(time5, currentTime, time6)){
            drivetrain.setTurnPID(-90, oldYaw);
        }
        else if(Logic.lessGreater(time6, currentTime, time7)){
            drivetrain.setFullAllDrive(drivetrainspeed);
            oldYaw = newYaw;
        }
        else if(Logic.lessGreater(time7, currentTime, time8)){
            drivetrain.setTurnPID(-90, oldYaw);
        }
        else if(Logic.lessGreater(time8, currentTime, time9)){
            drivetrain.setStop();
        }
    }

    public void end(boolean interrupted) {
        drivetrain.setStop();
        timer.stop();
    }

}

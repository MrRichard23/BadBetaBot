package frc.robot.subsystems;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import edu.wpi.first.math.controller.PIDController;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Operator;


public class Drivetrain extends SubsystemBase{
    private static Drivetrain drivetrain;

    private TalonFX masterLeftTalon;
    private TalonFX masterRightTalon;
    private TalonFX followLeftTalon;
    private TalonFX followRightTalon;

    private double proportional = 0.175;
    private double integration = 0;
    private double derivative = 0;
    
    private Drivetrain() {
        masterLeftTalon = new TalonFX(Constants.MASTER_LEFT_TALON_PORT);
        masterRightTalon = new TalonFX(Constants.MASTER_RIGHT_TALON_PORT);
        followLeftTalon = new TalonFX(Constants.FOLLOW_LEFT_TALON_PORT);
        followRightTalon = new TalonFX(Constants.FOLLOW_RIGHT_TALON_PORT);

        followLeftTalon.follow(masterLeftTalon);
        followRightTalon.follow(masterRightTalon);

        masterLeftTalon.config_kP(0, proportional);
        masterLeftTalon.config_kI(0, integration);
        masterLeftTalon.config_kD(0, derivative);

        masterRightTalon.config_kP(0, proportional);
        masterRightTalon.config_kI(0, integration);
        masterRightTalon.config_kD(0, derivative);
    }

    public static Drivetrain getInstance() {
        if(drivetrain == null){
            drivetrain = new Drivetrain();
        }
        return drivetrain;
    }
    public void setLeftDrive(double speed) {
        System.out.println(Operator.getLeftThrottle());
        masterLeftTalon.set(ControlMode.PercentOutput, -speed * Operator.getLeftThrottle());
    }
    public void setRightDrive(double speed) {
        masterRightTalon.set(ControlMode.PercentOutput, speed * Operator.getLeftThrottle());
    }
    public void setAllDrive(double speed) {
        masterLeftTalon.set(ControlMode.PercentOutput, speed * Operator.getLeftThrottle());
        masterRightTalon.set(ControlMode.PercentOutput, speed * Operator.getLeftThrottle());
    }
    public void setUnlimitedLeftDrive(double speed) {
        masterLeftTalon.set(ControlMode.PercentOutput, -speed);
    }
    public void setUnlimitedRightDrive(double speed) {
        masterRightTalon.set(ControlMode.PercentOutput, speed);
    }
    public void setUnlimitedAllDrive(double speed) {
        masterLeftTalon.set(ControlMode.PercentOutput, speed);
        masterRightTalon.set(ControlMode.PercentOutput, speed);
    }
    public void setStop() {
        masterRightTalon.set(ControlMode.PercentOutput, 0);
        masterLeftTalon.set(ControlMode.PercentOutput, 0);
    }
    public void setStopPIDDrivetrain(){
        masterLeftTalon.set(ControlMode.Velocity, 0);
        masterRightTalon.set(ControlMode.Velocity, 0);
    }
}

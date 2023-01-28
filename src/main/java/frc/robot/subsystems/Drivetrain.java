package frc.robot.subsystems;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Operator;


public class Drivetrain extends SubsystemBase{
    private static Drivetrain drivetrain;

    private TalonFX masterLeftTalon;
    private TalonFX masterRightTalon;
    private TalonFX followLeftTalon;
    private TalonFX followRightTalon;
    
    private Drivetrain() {
        masterLeftTalon = new TalonFX(Constants.MASTER_LEFT_TALON_PORT);
        masterRightTalon = new TalonFX(Constants.MASTER_RIGHT_TALON_PORT);
        followLeftTalon = new TalonFX(Constants.FOLLOW_LEFT_TALON_PORT);
        followRightTalon = new TalonFX(Constants.FOLLOW_RIGHT_TALON_PORT);
    }

    public static Drivetrain getInstance() {
        if(drivetrain == null){
            drivetrain = new Drivetrain();
        }
        return drivetrain;
    }
    public void setLeftDrive(double speed) {
        masterLeftTalon.set(ControlMode.PercentOutput, -speed * Operator.getLeftThrottle());
        followLeftTalon.set(ControlMode.PercentOutput, -speed * Operator.getLeftThrottle());
    }
    public void setRightDrive(double speed) {
        masterRightTalon.set(ControlMode.PercentOutput, speed * Operator.getLeftThrottle());
        followRightTalon.set(ControlMode.PercentOutput, speed * Operator.getLeftThrottle());
    }
    public void setUnlimitedLeftDrive(double speed) {
        masterLeftTalon.set(ControlMode.PercentOutput, -speed);
        followLeftTalon.set(ControlMode.PercentOutput, -speed);
    }
    public void setUnlimitedRightDrive(double speed) {
        masterRightTalon.set(ControlMode.PercentOutput, speed);
        followRightTalon.set(ControlMode.PercentOutput, speed);
    }
    public void setStop() {
        masterRightTalon.set(ControlMode.PercentOutput, 0);
        followRightTalon.set(ControlMode.PercentOutput, 0);
        masterLeftTalon.set(ControlMode.PercentOutput, 0);
        followLeftTalon.set(ControlMode.PercentOutput, 0);
    }
}

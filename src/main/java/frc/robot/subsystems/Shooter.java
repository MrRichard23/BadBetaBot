package frc.robot.subsystems;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Operator;

public class Shooter extends SubsystemBase{
    private static Shooter shooter;

    private CANSparkMax shooterMotor = new CANSparkMax(Constants.SHOOTER_MOTOR, MotorType.kBrushless);

    public static Shooter getInstance() {
        if(shooter == null){
            shooter = new Shooter();
        }
        return shooter;
    }

    public void setShooter(double speed){
        shooterMotor.set(speed);
    }
}
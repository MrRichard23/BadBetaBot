package frc.robot.subsystems;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Operator;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Feeder extends SubsystemBase{
    private static Feeder feeder;

    private CANSparkMax lowerFeeder = new CANSparkMax(Constants.LOWER_FEEDER, MotorType.kBrushless);
    private CANSparkMax upperFeeder = new CANSparkMax(Constants.UPPER_FEEDER, MotorType.kBrushless);

    public static Feeder getInstance() {
        if(feeder == null){
            feeder = new Feeder();
        }
        return feeder;
    }

    public void setFeeder(double speed){
        lowerFeeder.set(speed);
        upperFeeder.set(speed);
    }
}

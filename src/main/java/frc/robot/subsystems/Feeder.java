package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
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

    public void setUpperFeeder(double speed){
        upperFeeder.set(-speed);
    }
    public void setLowerFeeder(double speed){
        lowerFeeder.set(speed);
    }
}

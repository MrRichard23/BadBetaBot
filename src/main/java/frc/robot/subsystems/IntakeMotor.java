package frc.robot.subsystems;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IntakeMotor extends SubsystemBase{
    private static IntakeMotor intakeMotor;

    private CANSparkMax motorIntake = new CANSparkMax(Constants.INTAKE_MOTOR, MotorType.kBrushless);

    public static IntakeMotor getInstance() {
        if(intakeMotor == null){
            intakeMotor = new IntakeMotor();
        }
        return intakeMotor;
    }

    public void setIntakeMotor(double speed){
        motorIntake.set(speed);
    }
}
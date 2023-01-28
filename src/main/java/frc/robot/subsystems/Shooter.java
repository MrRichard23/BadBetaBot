package frc.robot.subsystems;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase{
    
    private static Shooter shooter;
    private CANSparkMax shooterMotor;
    private PIDController shooterController;
    private double kf = (5676.0*42.0)/60.0; // (RPM * Ticks per revolution)/ minutes to second

    
    private Shooter(){
        shooterMotor = new CANSparkMax(Constants.SHOOTER_MOTOR, MotorType.kBrushless);
        shooterController =  new PIDController(0.0125, 0, 0); //good kp value for desired velocity
    


    }

    public static Shooter getInstance() {
        if(shooter == null){
            shooter = new Shooter();
        }
        return shooter;
    }

    public void setShooter(double speed){
        shooterMotor.set(-speed);
    }

    public void setShooterPID(double speed){
        shooterMotor.setVoltage(
            (speed*kf)
            //shooterController.calculate(shooterMotor.getEncoder().getVelocity(), speed )
        );
       

    }

    public double displayShooterVal(){
        return shooterMotor.getEncoder().getVelocity();
    }
}
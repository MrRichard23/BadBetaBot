package frc.robot.subsystems;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Operator;


public class Drivetrain extends SubsystemBase{
    private static Drivetrain drivetrain;
    Timer timer;

    private TalonFX masterLeftTalon;
    private TalonFX masterRightTalon;
    private TalonFX followLeftTalon;
    private TalonFX followRightTalon;

    private double proportional;
    private double integration;
    private double derivative;

    PIDController turnPositionPIDController;
    PIDController turnVelocityPIDController;
    PIDController balancePIDController;
    
    private Drivetrain() {
        masterLeftTalon = new TalonFX(Constants.MASTER_LEFT_TALON_PORT);
        masterRightTalon = new TalonFX(Constants.MASTER_RIGHT_TALON_PORT);
        followLeftTalon = new TalonFX(Constants.FOLLOW_LEFT_TALON_PORT);
        followRightTalon = new TalonFX(Constants.FOLLOW_RIGHT_TALON_PORT);

        followLeftTalon.follow(masterLeftTalon);
        followRightTalon.follow(masterRightTalon);

        turnPositionPIDController = new PIDController(0.017, 0, 0.0015);
        turnPositionPIDController.setTolerance(1);
        turnPositionPIDController.enableContinuousInput(-180.0f, 180.0f);

        turnVelocityPIDController = new PIDController(0.0001, 0, 0);

        balancePIDController = new PIDController(0.008, 0.1, 0);


    }

    public static Drivetrain getInstance() {
        if(drivetrain == null){
            drivetrain = new Drivetrain();
        }
        return drivetrain;
    }
    public void setLeftDrive(double speed) {
        //System.out.println(Operator.getLeftThrottle());
        masterLeftTalon.set(ControlMode.PercentOutput, -speed * Operator.getLeftThrottle());
    }
    public void setRightDrive(double speed) {
        masterRightTalon.set(ControlMode.PercentOutput, speed * Operator.getLeftThrottle());
    }
    public void setAllDrive(double speed) {
        masterLeftTalon.set(ControlMode.PercentOutput, -speed * Operator.getLeftThrottle());
        masterRightTalon.set(ControlMode.PercentOutput, speed * Operator.getLeftThrottle());
    }
    public void setUnlimitedLeftDrive(double speed) {
        masterLeftTalon.set(ControlMode.PercentOutput, -speed);
    }
    public void setUnlimitedRightDrive(double speed) {
        masterRightTalon.set(ControlMode.PercentOutput, speed);
    }
    public void setUnlimitedAllDrive(double speed) {
        masterLeftTalon.set(ControlMode.PercentOutput, -speed);
        masterRightTalon.set(ControlMode.PercentOutput, speed);
    }
    public void setStop() {
        masterRightTalon.set(ControlMode.PercentOutput, 0);
        masterLeftTalon.set(ControlMode.PercentOutput, 0);
    }
    public void setStopPID(){
        proportional = 0.03;
        integration = 0.001;
        derivative = 0.;

        masterLeftTalon.config_kP(0, proportional);
        masterLeftTalon.config_kI(0, integration);
        masterLeftTalon.config_kD(0, derivative);

        masterRightTalon.config_kP(0, proportional);
        masterRightTalon.config_kI(0, integration);
        masterRightTalon.config_kD(0, derivative);

        masterLeftTalon.set(ControlMode.Velocity, 0);
        masterRightTalon.set(ControlMode.Velocity, 0);
    }
    public void setBalanceDrivetrain(){
        double current = Operator.getPitch();
         masterLeftTalon.set(ControlMode.PercentOutput, 
                balancePIDController.calculate(-current,0));
        masterRightTalon.set(ControlMode.PercentOutput, 
                balancePIDController.calculate(current,0));
    }
    public void setTurnPID(double degrees, double oldYaw){

        double current =  Operator.getYaw();
        double setpoint = degrees + oldYaw;
        // double setpoint =((degrees + oldYaw + 180) % 360) - 180;
        // if(setpoint - current > 180){
        //     setpoint -= 360;
        // }
        // else if(setpoint - current < -180){
        //     setpoint += 360;
        // }
        // setpoint %= 360;
        double output = turnPositionPIDController.calculate(current, setpoint) + turnVelocityPIDController.calculate(masterLeftTalon.getSelectedSensorVelocity(), 0);
        System.out.println(current + " current  " + setpoint + " setpoint");
        
        masterLeftTalon.set(ControlMode.PercentOutput, output);
        masterRightTalon.set(ControlMode.PercentOutput, output);
    }

     public boolean turnSetpoint(){
        if(turnPositionPIDController.atSetpoint() && turnVelocityPIDController.atSetpoint()){
            return true;
        }
        else{
            return false;
        }
    }
}

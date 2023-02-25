package frc.robot.subsystems;
import java.util.function.BiConsumer;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Operator;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SerialPort;


public class Drivetrain extends SubsystemBase{
    private static Drivetrain drivetrain;
    private static AHRS ahrs;
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
    PIDController cameraCenterPIDController;

    private DifferentialDriveOdometry odometry;
    private Pose2d pose2d;
    public DifferentialDriveKinematics driveKinematics;
    
    private Drivetrain() {
        ahrs = new AHRS(SerialPort.Port.kMXP);

        masterLeftTalon = new TalonFX(Constants.MASTER_LEFT_TALON_PORT);
        masterRightTalon = new TalonFX(Constants.MASTER_RIGHT_TALON_PORT);
        followLeftTalon = new TalonFX(Constants.FOLLOW_LEFT_TALON_PORT);
        followRightTalon = new TalonFX(Constants.FOLLOW_RIGHT_TALON_PORT);

        followLeftTalon.follow(masterLeftTalon);
        followRightTalon.follow(masterRightTalon);

        driveKinematics = new DifferentialDriveKinematics(0.8);

        pose2d = new Pose2d();

        odometry = new DifferentialDriveOdometry(
                ahrs.getRotation2d(),
                masterLeftTalon.getSelectedSensorPosition(),
                masterRightTalon.getSelectedSensorPosition(),
                pose2d
        );

        turnPositionPIDController = new PIDController(0.017, 0, 0.0015);
        turnPositionPIDController.setTolerance(1,5);
        turnPositionPIDController.enableContinuousInput(-180.0f, 180.0f);

        // turnVelocityPIDController = new PIDController(0.0001, 0, 0);

        balancePIDController = new PIDController(0.008, 0.1, 0);

        cameraCenterPIDController = new PIDController(0.018,0,0.004); //0.012, 0, 0.003
        cameraCenterPIDController.setTolerance(0.5, 2);
    }

    public static Drivetrain getInstance() {
        if(drivetrain == null){
            drivetrain = new Drivetrain();
        }
        return drivetrain;
    }

    public void periodic() {
        pose2d = odometry.update(ahrs.getRotation2d(),
                masterLeftTalon.getSelectedSensorPosition(),
                masterRightTalon.getSelectedSensorPosition()
        );
    }



    public static float getRoll(){
        return (ahrs.getRoll() + 2.5f);
    }
    public static float getPitch(){
        return (ahrs.getPitch() - 4.8f);
    }
    public static double getYaw(){
        return ahrs.getYaw();
    }
    public static double getXVelocity(){
        return ahrs.getVelocityX();
    }
    public static double getYVelocity(){
        return ahrs.getVelocityY();
    }
    public static double getZVelocity(){
        return ahrs.getVelocityZ();
    }


    public Pose2d getPose() {
		return odometry.getPoseMeters();
	}

    public void resetPose(Pose2d pose) {
		odometry.resetPosition(ahrs.getRotation2d(), masterLeftTalon.getSelectedSensorPosition(), masterRightTalon.getSelectedSensorPosition(), pose);
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
    public void setFullLeftDrive(double speed) {
        masterLeftTalon.set(ControlMode.PercentOutput, -speed);
    }
    public void setFullRightDrive(double speed) {
        masterRightTalon.set(ControlMode.PercentOutput, speed);
    }
    public void setFullAllDrive(double speed) {
        masterLeftTalon.set(ControlMode.PercentOutput, -speed);
        masterRightTalon.set(ControlMode.PercentOutput, speed);
    }
    public void setDrive(double leftInput, double rightInput) {
        
        masterLeftTalon.set(ControlMode.PercentOutput, -leftInput * Operator.getLeftThrottle());
        masterRightTalon.set(ControlMode.PercentOutput, rightInput * Operator.getLeftThrottle());
    }
    public void setFullDrive(double leftInput, double rightInput){
        masterLeftTalon.set(ControlMode.PercentOutput, -leftInput);
        masterRightTalon.set(ControlMode.PercentOutput, rightInput);
    }
    public void setStop() {
        masterRightTalon.set(ControlMode.PercentOutput, 0);
        masterLeftTalon.set(ControlMode.PercentOutput, 0);
    }
    public void setStopPID(){
        proportional = 0.03;
        integration = 0.001;
        derivative = 0;

        masterLeftTalon.config_kP(0, proportional);
        masterLeftTalon.config_kI(0, integration);
        masterLeftTalon.config_kD(0, derivative);

        masterRightTalon.config_kP(0, proportional);
        masterRightTalon.config_kI(0, integration);
        masterRightTalon.config_kD(0, derivative);

        masterLeftTalon.set(ControlMode.Velocity, 0);
        masterRightTalon.set(ControlMode.Velocity, 0);
    }
    public void setCameraCenter(){
        double output = cameraCenterPIDController.calculate(Operator.cameraX(), 0);
        masterLeftTalon.set(ControlMode.PercentOutput, -output);
        masterRightTalon.set(ControlMode.PercentOutput, -output);
    }
    public boolean cameraCenterSetpoint(){
        if(cameraCenterPIDController.atSetpoint()){
            return true;
        }
        else{
            return false;
        }
    }
    public void setBalanceDrivetrain(){
        double current = Drivetrain.getPitch();
         masterLeftTalon.set(ControlMode.PercentOutput, 
                balancePIDController.calculate(-current,0));
        masterRightTalon.set(ControlMode.PercentOutput, 
                balancePIDController.calculate(current,0));
    }
    public void setTurnPID(double degrees, double oldYaw){

        double current =  Drivetrain.getYaw();
        double setpoint = degrees + oldYaw;
        // double setpoint =((degrees + oldYaw + 180) % 360) - 180;
        // if(setpoint - current > 180){
        //     setpoint -= 360;
        // }
        // else if(setpoint - current < -180){
        //     setpoint += 360;
        // }
        // setpoint %= 360;
        double output = turnPositionPIDController.calculate(current, setpoint); //+ turnVelocityPIDController.calculate(masterLeftTalon.getSelectedSensorVelocity(), 0);
        System.out.println(current + " current  " + setpoint + " setpoint");
        
        masterLeftTalon.set(ControlMode.PercentOutput, output);
        masterRightTalon.set(ControlMode.PercentOutput, output);
    }

     public boolean turnSetpoint(){
        if(turnPositionPIDController.atSetpoint()){ //&& turnVelocityPIDController.atSetpoint()){
            return true;
        }
        else{
            return false;
        }
    }
}

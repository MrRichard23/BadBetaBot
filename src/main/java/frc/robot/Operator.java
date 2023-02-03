package frc.robot;
import edu.wpi.first.wpilibj.XboxController;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SerialPort;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Operator{
    private static Joystick leftJoystick;
    private static Joystick rightJoystick;
    private static XboxController xboxController;
    private static AHRS ahrs;

    public static void init() {
        leftJoystick = new Joystick(Constants.JOYSTICK_LEFT_TALON_PORT);
        rightJoystick = new Joystick(Constants.JOYSTICK_RIGHT_TALON_PORT);
        xboxController = new XboxController(Constants.XBOX_CONTROLLER_PORT);
        ahrs = new AHRS(SerialPort.Port.kMXP);
    }

    public static Joystick getLeftJoystick(){
        return leftJoystick;
    }
    public static Joystick getRightJoystick(){
        return rightJoystick;
    }
    public static XboxController getXboxController(){
        return xboxController;
    }

    public static void SmartDashboard1(double input, String message){
        SmartDashboard.putNumber(message, input);
    }
    public static void SmartDashboard2(double input, String message){
        SmartDashboard.putNumber(message, input);
    }
    public static void SmartDashboard3(double input, String message){
        SmartDashboard.putNumber(message, input);
    }
    public static void SmartDashboard4(double input, String message){
        SmartDashboard.putNumber(message, input);
    }
    public static void SmartDashboard5(double input, String message){
        SmartDashboard.putNumber(message, input);
    }
    public static void SmartDashboard6(double input, String message){
        SmartDashboard.putNumber(message, input);
    }
    public static void SmartDashboard7(double input, String message){
        SmartDashboard.putNumber(message, input);
    }

    public static double getLeftJoystickY() {
        return leftJoystick.getY();
    }
    public static double getLeftThrottle() {
        return (0.5 * (1 - leftJoystick.getThrottle()));
    }
    public static double getRightJoystickY() {
        return rightJoystick.getY();
    }
    public static double getRightThrottle() {
        return (0.5 * (1 - rightJoystick.getThrottle()));
    }
    public static boolean getLeftTrigger() {
        return leftJoystick.getTrigger();
    }
    public static boolean getRightTrigger() {
        return rightJoystick.getTrigger();
    }

    public static boolean getAButton() {
        return xboxController.getAButton();
    }
    public static boolean getBButton() {
        return xboxController.getBButton();
    }
    public static boolean getXButton() {
        return xboxController.getXButton();
    }
    public static boolean getYButton() {
        return xboxController.getYButton();
    }
    public static boolean getLeftBumper(){
        return xboxController.getLeftBumper();
    }
    public static boolean getRightBumper(){
        return xboxController.getRightBumper();
    }
    public static boolean getStartButton(){
        return xboxController.getStartButton();
    }

    public static float getRoll(){
        return ahrs.getRoll();
    }
    public static float getPitch(){
        return ahrs.getPitch();
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
}

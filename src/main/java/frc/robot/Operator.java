package frc.robot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.Joystick;

public class Operator{
    private static Joystick leftJoystick;
    private static Joystick rightJoystick;
    private static XboxController xboxController;

    public static void init() {
        leftJoystick = new Joystick(Constants.JOYSTICK_LEFT_TALON_PORT);
        rightJoystick = new Joystick(Constants.JOYSTICK_RIGHT_TALON_PORT);
        xboxController = new XboxController(Constants.XBOX_CONTROLLER_PORT);
    }

    public static double getLeftJoystick() {
        return leftJoystick.getY();
    }
    public static double getLeftThrottle() {
        return (1 - leftJoystick.getThrottle());
    }
    public static double getRightJoystick() {
        return rightJoystick.getY();
    }
    public static double getRightThrottle() {
        return (1 - rightJoystick.getThrottle());
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
}

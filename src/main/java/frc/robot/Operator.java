package frc.robot;
import frc.robot.Constants;
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
  
}

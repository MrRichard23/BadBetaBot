package frc.robot;

public class Logic {
    public static boolean pressedLogic(boolean newInput, boolean oldInput){
        if(newInput == true && oldInput == false){
            return true;
        }
        else{
            return false;
        }
    }
    public static boolean unPressedLogic(boolean newInput, boolean oldInput){
        if(newInput == false && oldInput == true){
            return true;
        }
        else{
            return false;
        }
    }
    public static boolean pressed2ToggleLogic(boolean pressed, boolean oldAlternate){
        if(pressed == true){
            if(oldAlternate == true){
                return false;
            }
            else{
                return true;
            }
        }
        else{
            return oldAlternate;
        }
    }
    public static int pressedMultiToggleLogic(boolean pressed, int oldAlternate, int numPressed){
        if(pressed == true){
            if(oldAlternate < numPressed){
                return (oldAlternate + 1);
            }
            else{
                return 1;
            }
        }
        else{
            return oldAlternate;
        }
    }

}

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
    public static boolean lessGreater(double beginTime, double currentTime, double endTime){
       if(currentTime > beginTime && currentTime < endTime){
        return true;
       }
       else{
        return false;
       }
    }
    public static int plusNeg(double input){
        if(input < 0){
            return -1;
        }
        else if(input == 0){
            return 0;
        }
        else{
            return 1;
        }
    }
}

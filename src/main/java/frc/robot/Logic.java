package frc.robot;

public class Logic {
    public static boolean justPressedLogic(boolean newInput, boolean oldInput){
        if(newInput == true && oldInput == false){
            return true;
        }
        else{
            return false;
        }
    }
    public static boolean justPressed2ToggleLogic(boolean newInput, boolean oldInput, boolean oldAlternate){
        if(newInput == true && oldInput == false){
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

}

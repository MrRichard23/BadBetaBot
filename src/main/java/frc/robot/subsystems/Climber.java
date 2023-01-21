package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.PneumaticHub;
import edu.wpi.first.wpilibj.PneumaticsBase;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import frc.robot.Constants;

public class Climber extends SubsystemBase{
    private static Climber climber;

    private static DoubleSolenoid climberPiston = new DoubleSolenoid(Constants.PNEUMATIC_PORT, PneumaticsModuleType.REVPH, Constants.OPEN_CLIMBER_PISTON, Constants.CLOSE_CLIMBER_PISTON);
    
    public static Climber  getInstance() {
        if(climber == null){
            climber = new Climber();
        }
        return climber;
    }
    public void setForwardClimber(){
        climberPiston.set(DoubleSolenoid.Value.kForward);
    }
    public void setBackwardClimber(){
        climberPiston.set(DoubleSolenoid.Value.kReverse);
    }
    public DoubleSolenoid.Value getPiston() {
        return climberPiston.get();
    }
}

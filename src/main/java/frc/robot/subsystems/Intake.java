package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import frc.robot.Constants;

public class Intake extends SubsystemBase{
    private static Intake intake;

    private static DoubleSolenoid intakePiston = new DoubleSolenoid(Constants.PNEUMATIC_PORT, PneumaticsModuleType.REVPH, Constants.OPEN_INTAKE_PISTON_PORT, Constants.CLOSE_INTAKE_PISTON_PORT);
    
    public static Intake getInstance() {
        if(intake == null){
            intake = new Intake();
        }
        return intake;
    }

    public void setIntake(boolean setIntake){
       if(setIntake == true){
         intakePiston.set(DoubleSolenoid.Value.kForward);
       }
       else{
         intakePiston.set(DoubleSolenoid.Value.kReverse);
       }
    }
    public DoubleSolenoid.Value getPiston() {
        return intakePiston.get();
    }
}

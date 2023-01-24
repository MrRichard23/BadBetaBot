package frc.robot.commands.Teleop;
import edu.wpi.first.wpilibj.PneumaticHub;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.Button;
import frc.robot.Operator;
import frc.robot.subsystems.IntakeMotor;
import frc.robot.Logic;

public class IntakeMotorCommand extends CommandBase{
    IntakeMotor intakeMotor;

    private boolean oldleftBumper = true;
    private boolean newleftBumper = false;
    private boolean toggleleftBumper = false;
    private boolean alternateleftBumper = false;

    public IntakeMotorCommand() {
        intakeMotor = IntakeMotor.getInstance();
        addRequirements(intakeMotor);
    }

    public void execute() {
        newleftBumper = Operator.getLeftBumper();

        toggleleftBumper = Logic.justPressedLogic(newleftBumper, oldleftBumper);

        alternateleftBumper = Logic.justPressed2ToggleLogic(newleftBumper, oldleftBumper, alternateleftBumper);

        if(toggleleftBumper == true){
            if(alternateleftBumper == true){
                intakeMotor.setIntakeMotor(1);
            }
            else{
                intakeMotor.setIntakeMotor(0);
            }
        }
        oldleftBumper = newleftBumper;
    
     }


}

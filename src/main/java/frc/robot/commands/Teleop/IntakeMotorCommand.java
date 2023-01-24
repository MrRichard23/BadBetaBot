package frc.robot.commands.Teleop;
import edu.wpi.first.wpilibj.PneumaticHub;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.Button;
import frc.robot.Operator;
import frc.robot.subsystems.IntakeMotor;
import frc.robot.Logic;

public class IntakeMotorCommand extends CommandBase{
    IntakeMotor intakeMotor;

    private boolean oldXButton = true;
    private boolean newXButton = false;
    private boolean toggleXButton = false;
    private boolean alternateXButton = false;

    public IntakeMotorCommand() {
        intakeMotor = IntakeMotor.getInstance();
        addRequirements(intakeMotor);
    }

    public void execute() {
        newXButton = Operator.getXButton();

        toggleXButton = Logic.justPressedLogic(newXButton, oldXButton);

        alternateXButton = Logic.justPressed2ToggleLogic(newXButton, oldXButton, alternateXButton);

        if(toggleXButton == true){
            if(alternateXButton == true){
                intakeMotor.setIntakeMotor(1);
            }
            else{
                intakeMotor.setIntakeMotor(0);
            }
        }
        oldXButton = newXButton;
    
     }


}

package frc.robot.commands.Control;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeMotor;

public class IntakeMotorControl extends CommandBase{
    IntakeMotor intakeMotor;
    double setting;

    public IntakeMotorControl(double setting){
        this.setting = setting;

        intakeMotor = IntakeMotor.getInstance();

        addRequirements(intakeMotor);
    }

    public void initialize(){
        intakeMotor.setIntakeMotor(setting);
    }

    public boolean isFinished() {
        return true;
    }
}

package frc.robot.commands.Called;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class IntakeControl extends CommandBase{
    Intake intake;
    boolean setting;

    public IntakeControl(boolean setting){
        this.setting = setting;

        intake = Intake.getInstance();

        addRequirements(intake);
    }

    public void initialize(){
        intake.setIntake(setting);
    }

    public boolean isFinished() {
        return true;
    }
}

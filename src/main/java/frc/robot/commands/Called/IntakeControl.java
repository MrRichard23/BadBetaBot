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

    public void execute(){
        intake.setIntake(setting);
    }
}

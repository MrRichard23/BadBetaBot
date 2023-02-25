package frc.robot.commands.Auton;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.Called.TimedDrive;

public class Forwarding extends SequentialCommandGroup{
    public Forwarding(){
        addCommands(
            new SequentialCommandGroup(
                new TimedDrive(0.15, 3)
            )
        );
    }
}

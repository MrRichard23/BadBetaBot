package frc.robot.commands.Auton;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Operator;
import frc.robot.commands.Called.TagCenter;

public class TagChase extends SequentialCommandGroup{
    
    public TagChase(){
        addCommands(
            new SequentialCommandGroup(
                new TagCenter()
            )
        );
    }
}

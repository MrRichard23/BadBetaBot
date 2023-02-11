package frc.robot.commands.Auton;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.Called.TimedDrive;
import frc.robot.commands.Called.Turn;

public class TestSquare extends SequentialCommandGroup {
    private double angle;
    private double speed;
    private double time;

    public TestSquare(){
        addCommands(

<<<<<<< HEAD
        new SequentialCommandGroup(
            new TimedDrive(0.25, 1),
            new Turn(-90),
            new TimedDrive(0.15, 1),
            new Turn(-90),
            new TimedDrive(0.15, 1),
            new Turn(-90),
            new TimedDrive(0.15, 1),
            new Turn(-90)
=======
            new SequentialCommandGroup(
                new TimedDrive(0.15, 1),
                new Turn(-90),
                new TimedDrive(0.15, 1),
                new Turn(-90),
                new TimedDrive(0.15, 1),
                new Turn(-90),
                new TimedDrive(0.15, 1),
                new Turn(-90)
            )
>>>>>>> 8eb24243592bcb7ecaf1fd4e179d7a21e0c833ca
        );
    }
}
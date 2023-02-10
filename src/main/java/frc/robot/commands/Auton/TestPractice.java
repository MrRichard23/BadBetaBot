package frc.robot.commands.Auton;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.Called.IntakeControl;
import frc.robot.commands.Called.IntakeMotorControl;
import frc.robot.commands.Called.TimedDrive;
import frc.robot.commands.Called.Turn;
import frc.robot.commands.Called.Wait;

public class TestPractice extends SequentialCommandGroup{
    private double angle;
    private double speed;
    private double time;
    private boolean intake;
    private double intakeMotor;
    private double wait;

    public TestPractice(){
        addCommands(new Turn(angle), new TimedDrive(speed, time), new IntakeControl(intake), new IntakeMotorControl(intakeMotor), new Wait(wait));

        new SequentialCommandGroup(
                new ParallelCommandGroup(
                        new TimedDrive(0.15, 1),
                        new IntakeControl(true),
                        new IntakeMotorControl(1)
                ),
                new Wait(0.25),
                new ParallelCommandGroup(
                        new TimedDrive(-0.15, 1)
                )
        );
    }
}

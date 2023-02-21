package frc.robot.commands.Auton;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.Called.TimedDrive;
import frc.robot.commands.Called.Turn;
import frc.robot.commands.Called.Wait;
import frc.robot.commands.Control.IntakeControl;
import frc.robot.commands.Control.IntakeMotorControl;
import frc.robot.commands.Control.LowerFeederControl;
import frc.robot.commands.Control.ShooterControl;
import frc.robot.subsystems.Intake;

public class TestPractice extends SequentialCommandGroup{
    public TestPractice(){
        addCommands(
                new SequentialCommandGroup(
                        new ParallelCommandGroup(
                                new TimedDrive(0.15, 1),
                                new InstantCommand(() -> Intake.getInstance().setIntake(true)),
                                new IntakeControl(true),
                                new IntakeMotorControl(1),
                                new LowerFeederControl(1)
                        ),
                        new Wait(0.25),
                        new ParallelCommandGroup(
                                new TimedDrive(-0.15, 1),
                                new IntakeControl(false),
                                new IntakeMotorControl(0)
                        ),
                        new ParallelCommandGroup(
                                new Turn(180),
                                new LowerFeederControl(0),
                                new ShooterControl(-550)
                        )

                )
        );
    }
}

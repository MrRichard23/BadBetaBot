package frc.robot.commands.Auton;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.Called.IntakeControl;
import frc.robot.commands.Called.IntakeMotorControl;
import frc.robot.commands.Called.TimedDrive;
import frc.robot.commands.Called.Turn;
import frc.robot.commands.Called.Wait;
import frc.robot.subsystems.Intake;

public class TestPractice extends SequentialCommandGroup{
    private double angle;
    private double speed;
    private double time;
    private boolean intake;
    private double intakeMotor;
    private double wait;

    public TestPractice(){
        addCommands(
                new SequentialCommandGroup(
                        // new ParallelCommandGroup(
                        //         new TimedDrive(0.15, 1),
                        //         new InstantCommand(() -> Intake.getInstance().setIntake(true)),
                        //         new IntakeControl(true),
                        //         new IntakeMotorControl(1)
                        // ),
                        // new Wait(0.25),
                        // new ParallelCommandGroup(
                        //         new TimedDrive(-0.15, 1)
                        // )
                        new Turn(-90)

                )
        );
    }
}

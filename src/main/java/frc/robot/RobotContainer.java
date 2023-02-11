// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Climber;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.Teleop.DrivetrainCommand;
import frc.robot.commands.Teleop.IntakeCommand;
import frc.robot.commands.Teleop.IntakeMotorCommand;
import frc.robot.subsystems.IntakeMotor;
import frc.robot.commands.Teleop.ClimberCommand;
import frc.robot.commands.Teleop.ShooterCommand;
import frc.robot.subsystems.Shooter;
import frc.robot.commands.Teleop.FeederCommand;
import frc.robot.subsystems.Feeder;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import frc.robot.commands.Auton.Practice;
import frc.robot.commands.Auton.Square;
import frc.robot.commands.Auton.TestPractice;
import frc.robot.commands.Auton.TestSquare;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final Drivetrain drivetrain = Drivetrain.getInstance();
  private final Intake intake = Intake.getInstance();
  private final IntakeMotor intakeMotor = IntakeMotor.getInstance();
  private final Climber climber = Climber.getInstance();
  private final Feeder feeder = Feeder.getInstance();
  private final Shooter shooter = Shooter.getInstance();
  public static SendableChooser<Command> autonCommandChooser = new SendableChooser<>();

  // private final Command DrivetrainCommand = new DrivetrainCommand();
  // private final Command IntakeCommand = new IntakeCommand();
  // private final Command ClimberCommand = new ClimberCommand();
  // private final Command FeederCommand = new FeederCommand();
  // private final Command ShooterCommand = new ShooterCommand();

  private final Compressor compressor = new Compressor(Constants.PNEUMATIC_PORT, PneumaticsModuleType.REVPH);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    Operator.init();
    compressor.enableAnalog(Constants.COMPRESSER_MIN_PRESSURE, Constants.COMPRESSER_MAX_PRESSURE);

    // Configure the button bindings
    drivetrain.setDefaultCommand(new DrivetrainCommand());
    intake.setDefaultCommand(new IntakeCommand());
    climber.setDefaultCommand(new ClimberCommand());
    intakeMotor.setDefaultCommand(new IntakeMotorCommand());
    feeder.setDefaultCommand(new FeederCommand());
    shooter.setDefaultCommand(new ShooterCommand());

    configureButtonBindings();
    displayChoices();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public void displayChoices(){
    autonCommandChooser.addOption("nothing", null);
    autonCommandChooser.addOption("Practice", new Practice(9));
    autonCommandChooser.addOption("Square", new Square(22));
    autonCommandChooser.addOption("TestSquare", new TestSquare());
    autonCommandChooser.setDefaultOption("TestPractice", new TestPractice());

    SmartDashboard.putData("auto choooser", autonCommandChooser);
  }
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return autonCommandChooser.getSelected();
  }
}

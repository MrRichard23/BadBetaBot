package frc.robot;

import java.util.HashMap;

import com.pathplanner.lib.auto.PIDConstants;
import com.pathplanner.lib.auto.RamseteAutoBuilder;

import com.pathplanner.lib.PathConstraints;
import com.pathplanner.lib.PathPlanner;

import frc.robot.subsystems.Drivetrain;
import frc.robot.commands.Auton.Practice;
import frc.robot.commands.Auton.Square;
import frc.robot.commands.Auton.TagChase;
import frc.robot.commands.Auton.TestPractice;
import frc.robot.commands.Auton.TestSquare;
import edu.wpi.first.math.controller.RamseteController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.commands.Control.ClimberControl;

public class Auto {
	private static RamseteAutoBuilder autoBuilder;

	private static Drivetrain drivetrain;

	private static HashMap<String, Command> eventMap;

	private static SendableChooser<Command> autoChooser;

	public static void init() {
		eventMap = new HashMap<String, Command>();
		addEvents();

		drivetrain = Drivetrain.getInstance();

		autoBuilder = new RamseteAutoBuilder(
			drivetrain::getPose, 
			drivetrain::resetPose, 
			new RamseteController(), 
			drivetrain.driveKinematics, 
			drivetrain::setFullDrive, 
			eventMap, 
			drivetrain
		);

		autoChooser = new SendableChooser<Command>();
		addAutoOptions();

		// put the auto chooser onto SmartDashboard
		SmartDashboard.putData(autoChooser);
	}

	public static void addAutoOptions() {
		// By default, the nothing option is selected
		autoChooser.addOption("nothing", null);
        autoChooser.addOption("Practice", new Practice(9));
        autoChooser.addOption("Square", new Square(22));
        autoChooser.addOption("TestSquare", new TestSquare());
        autoChooser.setDefaultOption("Limelight", new TagChase());
        autoChooser.addOption("TestPractice", new TestPractice());
		autoChooser.addOption("Test", autoFromPathGroup("Test"));
	}

	public static CommandBase autoFromPathGroup(String name) {
		return autoBuilder.fullAuto(PathPlanner.loadPathGroup(name, new PathConstraints(3, 3)));
	}

	/**
	 * @return selected auto from auto chooser
	 */
	public static Command getSelected() {
		return autoChooser.getSelected();
	}

	public static void addEvents() {
		eventMap.put("event1", new ClimberControl(true));
		eventMap.put("event2", new ClimberControl(false));
		eventMap.put("event3", new ClimberControl(true));
		eventMap.put("event4", new ClimberControl(false));
    }
}

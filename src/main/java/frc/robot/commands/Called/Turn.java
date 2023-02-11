package frc.robot.commands.Called;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Operator;
import frc.robot.subsystems.Drivetrain;

public class Turn extends CommandBase{
    Drivetrain drivetrain;
    private double angle;
    private double oldAngle;

    public Turn(double angle) {
        this.angle = angle;
        drivetrain = Drivetrain.getInstance();

        addRequirements(drivetrain);
    }

    public void initialize() {
        oldAngle = Operator.getYaw();
    }

    public void execute() {
        drivetrain.setTurnPID(angle, oldAngle);
    }

    @Override
    public boolean isFinished() {
        if(drivetrain.turnSetpoint()){
            System.out.println("finished");
            return true;
        }
        else{
            return false;
        }
    }

    public void end(boolean interupted){
        drivetrain.setStop();
    }

    
}

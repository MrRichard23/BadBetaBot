package frc.robot.commands.Called;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Operator;
import frc.robot.subsystems.Drivetrain;

public class TagCenter extends CommandBase{
    Drivetrain drivetrain;

    public TagCenter() {
        drivetrain = Drivetrain.getInstance();

        addRequirements(drivetrain);
    }

    public void initialize(){

    }

    public void execute(){
        if(Operator.cameraSee() == 1){
            drivetrain.setCameraCenter();
        }
        else if(Operator.cameraSee() == 0){
            drivetrain.setFullDrive(0.15, -0.15);
        }
    }

    public boolean isFinished() {
        if(drivetrain.cameraCenterSetpoint()){
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

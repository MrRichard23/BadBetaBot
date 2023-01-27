package frc.robot.commands.Teleop;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Operator;
import frc.robot.subsystems.Shooter;
import frc.robot.Logic;

public class ShooterCommand extends CommandBase{
    Shooter shooter;

    private boolean oldleftBumper = false;
    private boolean newleftBumper = false;
    private boolean toggleleftBumper = false;
    private int alternateleftBumper = 2;
    
    private boolean newStartButton = false;
    private boolean oldStartButton = false;
    private boolean toggleStartButton = false;

    public ShooterCommand() {
        shooter = Shooter.getInstance();
        addRequirements(shooter);
    }

    public void execute() {
        newleftBumper = Operator.getLeftBumper();

        toggleleftBumper = Logic.justPressedLogic(newleftBumper, oldleftBumper);

        alternateleftBumper = Logic.justPressedMultiToggleLogic(newleftBumper, oldleftBumper, alternateleftBumper, 4);

        newStartButton = Operator.getStartButton();

        toggleStartButton = Logic.justUnPressedLogic(newStartButton, oldStartButton);

        if(newStartButton == true){
            shooter.setShooter(1);
        }
        else if(toggleStartButton == true){
            shooter.setShooter(0);
        }
        else{
            System.out.println(alternateleftBumper);
            if(toggleleftBumper == true){
                if(alternateleftBumper == 1){
                    shooter.setShooter(0);
                }
                else if(alternateleftBumper == 2){
                    shooter.setShooter(0.25);
                }
                else if(alternateleftBumper == 3){
                    shooter.setShooter(0.50);
                }
                else if(alternateleftBumper == 4){
                    shooter.setShooter(0.75);
                }
            }
        }
        oldleftBumper = newleftBumper;

        oldStartButton = newStartButton;
    }
}
package frc.robot.commands.Teleop;
import edu.wpi.first.wpilibj.PneumaticHub;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Operator;
import frc.robot.subsystems.Shooter;
import frc.robot.Logic;

public class ShooterCommand extends CommandBase{
    Shooter shooter;

    private boolean oldYButton = true;
    private boolean newYButton = false;
    private boolean toggleYButton = false;
    private boolean alternateYButton = false;

    public ShooterCommand() {
        shooter = Shooter.getInstance();
        addRequirements(shooter);
    }

    public void execute() {
        newYButton = Operator.getYButton();

        toggleYButton = Logic.justPressedLogic(newYButton, oldYButton);

        alternateYButton = Logic.justPressed2ToggleLogic(newYButton, oldYButton, alternateYButton);

        if(toggleYButton == true){
            if(alternateYButton == true){
                shooter.setShooter(1);
            }
            else{
                shooter.setShooter(0);
            }
        }
        oldYButton = newYButton;
    
     }


}

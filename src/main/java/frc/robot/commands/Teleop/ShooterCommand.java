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
    private int alternateleftBumper = 1;
    
    private boolean newStartButton = false;
    private boolean oldStartButton = false;
    private boolean toggleStartButton = false;

    public ShooterCommand() {
        shooter = Shooter.getInstance();
        addRequirements(shooter);
    }

    public void execute() {

        System.out.println(shooter.displayShooterVal());

       
        newleftBumper = Operator.getYButton();

        toggleleftBumper = Logic.justPressedLogic(newleftBumper, oldleftBumper);

        alternateleftBumper = Logic.justPressedMultiToggleLogic(newleftBumper, oldleftBumper, alternateleftBumper, 2);

        newStartButton = Operator.getStartButton();

        toggleStartButton = Logic.justUnPressedLogic(newStartButton, oldStartButton);

        if(newStartButton == true){
            shooter.setShooter(-1);
        }
        else if(toggleStartButton == true){
            shooter.setShooter(0);
            alternateleftBumper = 1;
        }
       
        else{
            if(toggleleftBumper == true){
                if(alternateleftBumper == 1){
                    shooter.setShooter(0);
                }
                else if(alternateleftBumper == 2){
                    shooter.setShooterPID(-550); //keep this value!! -550 is best suited for the velocity of the shooter
                }
            }
            // System.out.println(alternateleftBumper);
            // if(toggleleftBumper == true){
            //     if(alternateleftBumper == 1){
            //         shooter.setShooterPID(0);
            //     }
            //     else if(alternateleftBumper == 2){
            //         shooter.setShooterPID(0.5);
            //     }
            //     else if(alternateleftBumper == 3){
            //         shooter.setShooterPID(0.60);
            //     }
            //     else if(alternateleftBumper == 4){
            //         shooter.setShooterPID(0.70);
            //     }
            //     else if(alternateleftBumper == 5){
            //         shooter.setShooterPID(0.780);
            //     }
            // }
        }
        oldleftBumper = newleftBumper;

        oldStartButton = newStartButton;
    }
}



package frc.robot.commands.Teleop;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Operator;
import frc.robot.subsystems.Shooter;
import frc.robot.Logic;

public class ShooterCommand extends CommandBase{
    Shooter shooter;
  
    private boolean newButton1 = false; //Y button
    private boolean oldButton1 = false;
    private boolean pressedButton1 = false;
    private int alternateButton1 = 1;
    
    private boolean newStartButton = false;
    private boolean oldStartButton = false;
    private boolean unPressedStartButton = false;

    public ShooterCommand() {
        shooter = Shooter.getInstance();
        addRequirements(shooter);
    }

    public void execute() {

        // System.out.println(shooter.displayShooterVal());

       
        oldButton1 = newButton1;
        newButton1 = Operator.getYButton();
        pressedButton1 = Operator.getXboxController().getYButtonPressed();
        alternateButton1 = Logic.pressedMultiToggleLogic(pressedButton1, alternateButton1, 2);

        oldStartButton = newStartButton;
        newStartButton = Operator.getStartButton();
        // unPressedStartButton = Operator.getXboxController().getStartButtonReleased();
        unPressedStartButton = Logic.unPressedLogic(newStartButton, oldStartButton);

        if(newStartButton == true){
            shooter.setShooter(-1);
        }
        else if(unPressedStartButton == true){
            shooter.setShooter(0);
            alternateButton1 = 1;
        }
       
        else{
            if(pressedButton1 == true){
                if(alternateButton1 == 1){
                    shooter.setShooter(0);
                }
                else if(alternateButton1 == 2){
                    shooter.setShooterPID(-550); //keep this value!! -550 is best suited for the velocity of the shooter
                }
            }
            // System.out.println(alternateButton1);
            // if(toggleButton1 == true){
            //     if(alternateButton1 == 1){
            //         shooter.setShooter(0);
            //     }
            //     else if(alternateButton1 == 2){
            //         shooter.setShooter(0.50);
            //     }
            //     else if(alternateButton1 == 3){
            //         shooter.setShooter(0.60);
            //     }
            //     else if(alternateButton1 == 4){
            //         shooter.setShooter(0.70);
            //     }
            //     else if(alternateButton1 == 5){
            //         shooter.setShooter(0.80);
            //     }
            // }
        }
    }
}



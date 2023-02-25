package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Lights extends SubsystemBase{
    private static Lights lights;
    private static DigitalOutput light1;
    private static DigitalOutput light2;

    public Lights() {
        light1 = new DigitalOutput(1);
        light2 = new DigitalOutput(2);
    }

    public static Lights getInstance() {
        if(lights == null){
        lights = new Lights();
        }
        return lights;
    }

    public void setLights(int lightNum, boolean input) {
        if(lightNum == 1) {
            light1.set(input);
        }
        else {
            light2.set(input);
        }
    }
}

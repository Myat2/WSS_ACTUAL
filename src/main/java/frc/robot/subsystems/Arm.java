package frc.robot.subsystems;
import com.studica.frc.Servo;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class Arm extends SubsystemBase{
    private Servo servo0;
    private double servoAngle0;
    private Servo servo1;
    private double servoAngle1;
    private final ShuffleboardTab tab = Shuffleboard.getTab("Arm");
    private final NetworkTableEntry D_armValue0 = tab.add("Servo0", 0).getEntry();
    private final NetworkTableEntry D_armValue1 = tab.add("Servo1", 0).getEntry();
    public Arm(){

        servo0 = new Servo(0);
        servo1 = new Servo(1);
        servo0.setAngle(0);
        servo1.setAngle(0);
        servoAngle0 = 0;
        servoAngle1 = 0;
    }
    /**
     * Set Servo Angle for Servo0 in degrees
     * <p>
     * 
     * @param value between 0 - 100 (valid data range is 10cm - 80cm)
     */
    public void setServoAngle0(final double degrees){
        servoAngle0 = degrees;
        servo0.setAngle(degrees);
    }
    public void setServoAngle1(final double degrees){
        servoAngle1 = degrees;
        servo1.setAngle(degrees);
    }
    public double getServoAngle0(){
        return servoAngle0;
    }

    @Override
    // Runs every 20ms
    public void periodic()
    {
       
        D_armValue0.setNumber(servoAngle0);
        D_armValue1.setNumber(servoAngle1);
    }
}

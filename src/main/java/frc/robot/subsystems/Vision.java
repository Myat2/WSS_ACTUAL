package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Globals;
import frc.robot.RobotContainer;

public class Vision extends SubsystemBase{
    private final ShuffleboardTab tab = Shuffleboard.getTab("Vision");
    private final String[] items = {
        "Dettol",
        "Jagabee",
        "Coke"
    };
    private final NetworkTableEntry D_currentItem = tab.add("CurrentItem", 0).getEntry();
    private final NetworkTableEntry D_currentItemX = tab.add("CurrentItemX", 0).getEntry();
    private final NetworkTableEntry D_currentItemY = tab.add("CurrentItemY", 0).getEntry();

    private final NetworkTableEntry D_AddedArmX = tab.add("AddedArmX", 0).getEntry();
    private final NetworkTableEntry D_AddedRobotX = tab.add("AddedRobotX", 0).getEntry();
    private final NetworkTableEntry D_useTF = tab.add("useTF", 0).getEntry();
    public final NetworkTableEntry D_targetXArm = tab.add("targetXArm", 0).getEntry();

    private double[] defaultValue = new double[] {0};

    private final static Arm m_arm = RobotContainer.m_arm;
    public Vision(){

        m_arm.setCameraAngle(290); // Look down
    }
    public double[] getLine(){

        // Changed not tested
        double[] line = (SmartDashboard.getEntry("line").getDoubleArray(defaultValue));

      return line;
    }
    
    
    
    public void setUseTF(){
        SmartDashboard.putBoolean("UseTF", Globals.useTF);
    }

    public double[] getObjects(){
        // Jagabee, Dettol, Coke
       
        double[] objects = (SmartDashboard.getEntry("objects").getDoubleArray(defaultValue));
        
        return objects;
    }
    public double[] getWOB(){
        // Red (Jagabee, Dettol, Coke), Green (Jagabee, Dettol, Coke), Blue (Jagabee, Dettol, Coke)
       
        double[] wob = (SmartDashboard.getEntry("wob").getDoubleArray(defaultValue));
        
        return wob;
    }
    
    @Override
    public void periodic()
    {
        D_currentItem.setNumber(Globals.curItemType);
        D_currentItemX.setNumber(Globals.curItemX);
        D_currentItemY.setNumber(Globals.curItemY);
        
        D_AddedRobotX.setNumber(((Globals.curItemX -400 - 24) * Globals.convertPxToM));
        D_AddedArmX.setNumber((Globals.curItemY - 300) * Globals.convertPxToM);
        D_useTF.setBoolean(Globals.useTF);
        
    }
}

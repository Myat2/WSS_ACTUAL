package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Globals;

import frc.robot.RobotContainer;

public class Vision extends SubsystemBase{
    private final ShuffleboardTab tab = Shuffleboard.getTab("Vision");
    private NetworkTableInstance inst = NetworkTableInstance.getDefault();
    private NetworkTable visionTable = inst.getTable("Shuffleboard/Vision");
    private final NetworkTableEntry D_currentItem = tab.add("CurrentItem", 0).getEntry();
    private final NetworkTableEntry D_currentItemX = tab.add("CurrentItemX", 0).getEntry();
    private final NetworkTableEntry D_currentItemY = tab.add("CurrentItemY", 0).getEntry();
    private final NetworkTableEntry D_cvMode = tab.add("cvMode", -1).getEntry();
    private final NetworkTableEntry D_colorMode = tab.add("ColorMode", 0).getEntry();
    public final NetworkTableEntry D_targetXArm = tab.add("targetXArm", 0).getEntry();

    private double[] defaultValue = new double[12];

    private final static Arm m_arm = RobotContainer.m_arm;


    public Vision(){

        m_arm.setCameraAngle(Globals.defaultCameraAngle); // Look down
    }
    public double[] getLine(){

        double[] line = (visionTable.getEntry("line").getDoubleArray(defaultValue));

      return line;
    }
    
    
    public void setCVMode(int mode){
        /*
         * Video : -1
         * Color Detection : 0
         * Object Detection: 1
         * WOB Detection : 2
         * Perspective Transformation : 3
         * Trolley Detection with Transformation: 4
         */
        D_cvMode.setNumber(mode);
      
    }

    public double[] getObjects(){
        // CokeU, Coke, Dettol, Jagabee
       
        double[] objects = (visionTable.getEntry("objects").getDoubleArray(defaultValue));
        
        return objects;
    }

    // Get Distance of colored target from camera

    public double[] getDistanceTarget(String targetName){
        double[] distance = (visionTable.getEntry(targetName).getDoubleArray(defaultValue));
        return distance;
    }
    public void setColor(String color){
        /*
         * Black : 0
         * Red : 1
         * Green : 2
         * Blue: 3
         */
        int mode = 0;
        if (color == "Black"){
            mode = 0;
        }
        else if(color == "Red"){
            mode = 1;
        }
        else if(color == "Green"){
            mode = 2 ;
        }
        else if(color == "Blue"){
            mode = 3;
        }
        D_colorMode.setNumber(mode);
        
    }
    
    public void updatePoint(String targetName){
        double x, y;
        int w = (int)Globals.curPose.getRotation().getDegrees();
        if (w != -90){
            y = Globals.curPose.getTranslation().getY() + getDistanceTarget(targetName)[1] + RobotContainer.m_points.camOffset.getTranslation().getY();
            x = Globals.curPose.getTranslation().getX() + getDistanceTarget(targetName)[0] + RobotContainer.m_points.camOffset.getTranslation().getX();
        }
        else{
            x = Globals.curPose.getTranslation().getX() + getDistanceTarget(targetName)[1] + RobotContainer.m_points.camOffset.getTranslation().getY();
            y = Globals.curPose.getTranslation().getY() - getDistanceTarget(targetName)[0] + RobotContainer.m_points.camOffset.getTranslation().getX(); 
        }
        RobotContainer.m_points.updatePoint(targetName, new Pose2d(new Translation2d(x, y), Globals.curPose.getRotation()));  
    }
    
    public void updateAllPoints(){
        String[] targetAreas = {"RedTarget", "GreenTarget", "BlueTarget", "Trolley"};
        for (String targetName: targetAreas){
            if(getDistanceTarget(targetName)[0] != 0 && getDistanceTarget(targetName)[1] != 0 ){
                updatePoint(targetName);
            }
        }
    }

    @Override
    public void periodic()
    {
        D_currentItem.setNumber(Globals.curItemType);
        D_currentItemX.setNumber(Globals.curItemX);
        D_currentItemY.setNumber(Globals.curItemY);
        
       
    }
}

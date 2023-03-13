package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Globals;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import java.util.Map;
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

    private final NetworkTableEntry D_CameraMountOffsetX = tab.addPersistent("CameraMountOffsetX", 0.01).withWidget(BuiltInWidgets.kNumberSlider)
    .withProperties(Map.of("min", 0, "max", 0.1)).getEntry();
    private final NetworkTableEntry D_ConvertPxToM = tab.addPersistent("ConvertPxToM", 0.000625).withWidget(BuiltInWidgets.kNumberSlider)
    .withProperties(Map.of("min", 0, "max", 0.0007)).getEntry();
    private final NetworkTableEntry D_ArmOffsetZ = tab.addPersistent("ArmOffsetZ", 0.25).withWidget(BuiltInWidgets.kNumberSlider)
            .withProperties(Map.of("min", 0, "max", 0.5)).getEntry();
    private final NetworkTableEntry D_GripperOffsetZ = tab.addPersistent("GripperOffsetZ", 0.16).withWidget(BuiltInWidgets.kNumberSlider)
            .withProperties(Map.of("min", 0, "max", 0.25)).getEntry();
    private final NetworkTableEntry D_CokeRatio = tab.addPersistent("CokeRatio", 0.85).withWidget(BuiltInWidgets.kNumberSlider)
            .withProperties(Map.of("min", 0, "max", 1)).getEntry();

    private double[] defaultValue = new double[13];

    private final static Arm m_arm = RobotContainer.m_arm;


    public Vision(){

        m_arm.setCameraAngle(300); // Look down
    }
    public double[] getLine(){

        double[] line = (visionTable.getEntry("line").getDoubleArray(defaultValue));

      return line;
    }
    public void getWOBItems(){
        // reads the array passed to the networktable
        double[] WOB = visionTable.getEntry("WOB").getDoubleArray(defaultValue);
        // stores the data in Globals in a 2d array
        int[][] Targets = new int[3][3];
        int index = 0;
            for (int ROW = 0; ROW < 3; ROW++){
              for (int COL = 0; COL < 3; COL++) {
              Targets[ROW][COL] = (int) WOB[index];
              index++;
              }
            }
        Globals.Targets = Targets;
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
    
    // public void updatePoint(String targetName){
    //     double x, y;
    //     int w = (int)Globals.curPose.getRotation().getDegrees();
    //     if (w != -90){
    //         y = Globals.curPose.getTranslation().getY() + getDistanceTarget(targetName)[1] + RobotContainer.m_points.camOffset.getTranslation().getY();
    //         x = Globals.curPose.getTranslation().getX() + getDistanceTarget(targetName)[0] + RobotContainer.m_points.camOffset.getTranslation().getX();
    //     }
    //     else{
    //         x = Globals.curPose.getTranslation().getX() + getDistanceTarget(targetName)[1] + RobotContainer.m_points.camOffset.getTranslation().getY();
    //         y = Globals.curPose.getTranslation().getY() - getDistanceTarget(targetName)[0] + RobotContainer.m_points.camOffset.getTranslation().getX(); 
    //     }
    //     RobotContainer.m_points.updatePoint(targetName, new Pose2d(new Translation2d(x, y), Globals.curPose.getRotation()));  
    // }
    
    // public void updateAllPoints(){
    //     String[] targetAreas = {"RedTarget", "GreenTarget", "BlueTarget", "Trolley"};
    //     for (String targetName: targetAreas){
    //         if(getDistanceTarget(targetName)[0] != 0 && getDistanceTarget(targetName)[1] != 0 ){
    //             updatePoint(targetName);
    //         }
    //     }
    // }

    @Override
    public void periodic()
    {
        Globals.convertPxToM = D_ConvertPxToM.getDouble(0.000625);
        Globals.camera_mount_offset_x = D_CameraMountOffsetX.getDouble(0.01);
        Globals.arm_offset_z = D_ArmOffsetZ.getDouble(0.25);
        Globals.gripper_offset = D_GripperOffsetZ.getDouble(0.16);
        Globals.CokeRatio = D_CokeRatio.getDouble(0.85);
        D_currentItem.setNumber(Globals.curItemType);
        D_currentItemX.setNumber(Globals.curItemX);
        D_currentItemY.setNumber(Globals.curItemY);
        /*
         * Video : -1
         * Color Detection : 0
         * Object Detection: 1
         * WOB Detection : 2
         * Perspective Transformation : 3
         * Trolley Detection with Transformation: 4
         * Trolley Alignment: 5
         */
        D_cvMode.setNumber(Globals.cvMode);
        // SmartDashboard.putString("TrolleyPose", RobotContainer.m_points.pointMap.get("Trolley").toString());
        // SmartDashboard.putString("GreenTargetPose", RobotContainer.m_points.pointMap.get("GreenTarget").toString());
        // SmartDashboard.putString("TrolleyObs", RobotContainer.m_points.obstacleMap.get("Trolley").toString());
        // SmartDashboard.putString("ObsMap", RobotContainer.m_points.obstacleMap.toString());
        // SmartDashboard.putString("CurPose", Globals.curPose.toString());
        // SmartDashboard.putNumber("LineY", RobotContainer.m_vision.getLine()[1]);
    }
}

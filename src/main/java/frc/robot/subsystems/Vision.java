package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Globals;

import frc.robot.RobotContainer;

public class Vision extends SubsystemBase{
    private final ShuffleboardTab tab = Shuffleboard.getTab("Vision");
    
    private final NetworkTableEntry D_currentItem = tab.add("CurrentItem", 0).getEntry();
    private final NetworkTableEntry D_currentItemX = tab.add("CurrentItemX", 0).getEntry();
    private final NetworkTableEntry D_currentItemY = tab.add("CurrentItemY", 0).getEntry();

    private final NetworkTableEntry D_AddedArmX = tab.add("AddedArmX", 0).getEntry();
    private final NetworkTableEntry D_AddedRobotX = tab.add("AddedRobotX", 0).getEntry();
    private final NetworkTableEntry D_useTF = tab.add("useTF", 0).getEntry();
    public final NetworkTableEntry D_targetXArm = tab.add("targetXArm", 0).getEntry();

    private double[] defaultValue = new double[] {0};
    private double[] RedTargetPointPos = new double[2];
    private double[] GreenTargetPointPos = new double[2];
    private double[] BlueTargetPointPos = new double[2];
    private final static Arm m_arm = RobotContainer.m_arm;


    public Vision(){

        m_arm.setCameraAngle(290); // Look down
    }
    public double[] getLine(){

        double[] line = (SmartDashboard.getEntry("line").getDoubleArray(defaultValue));

      return line;
    }
    
    
    
    public void setCVMode(int mode){

        SmartDashboard.putNumber("cvMode", mode);
    }

    public double[] getObjects(){
        // Jagabee, Dettol, Coke
       
        double[] objects = (SmartDashboard.getEntry("objects").getDoubleArray(defaultValue));
        
        return objects;
    }

    // Get Distance of colored target from camera
    // Changes
    public double[] getDistanceTarget(String targetName){
        double[] distance = (SmartDashboard.getEntry(targetName).getDoubleArray(defaultValue));
        return distance;
    }
    public void setFlag(int mode){
        SmartDashboard.putNumber("flag", mode);
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
        String[] targetAreas = {"RedTarget", "GreenTarget", "BlueTarget"};
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
        
        D_AddedRobotX.setNumber(((Globals.curItemX -400 - 24) * Globals.convertPxToM));
        D_AddedArmX.setNumber((Globals.curItemY - 300) * Globals.convertPxToM);
        D_useTF.setBoolean(Globals.useTF);
        SmartDashboard.putNumber("LoopCount", Globals.loopCount);
        SmartDashboard.putString("RedTargetPose", RobotContainer.m_points.getPoint("RedTarget").toString());
        SmartDashboard.putString("GreenTargetPose", RobotContainer.m_points.getPoint("GreenTarget").toString());
        SmartDashboard.putString("BlueTargetPose", RobotContainer.m_points.getPoint("BlueTarget").toString());
        
        RedTargetPointPos[0] = RobotContainer.m_points.getPoint("RedTarget").getTranslation().getX();
        RedTargetPointPos[1] = RobotContainer.m_points.getPoint("RedTarget").getTranslation().getY();
        SmartDashboard.putNumberArray("RedTargetPointPos", RedTargetPointPos);

        GreenTargetPointPos[0] = RobotContainer.m_points.getPoint("GreenTarget").getTranslation().getX();
        GreenTargetPointPos[1] = RobotContainer.m_points.getPoint("GreenTarget").getTranslation().getY();
        SmartDashboard.putNumberArray("GreenTargetPointPos", GreenTargetPointPos);

        BlueTargetPointPos[0] = RobotContainer.m_points.getPoint("BlueTarget").getTranslation().getX();
        BlueTargetPointPos[1] = RobotContainer.m_points.getPoint("BlueTarget").getTranslation().getY();
        SmartDashboard.putNumberArray("BlueTargetPointPos", BlueTargetPointPos);  
    }
}

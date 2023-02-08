package frc.robot;

import edu.wpi.first.wpilibj.geometry.Pose2d;

//Put all global variables here
public class Globals
{
    static public int menuItem;

    static public final int DNUM = 4;
    static public int debug[] = new int[DNUM];
    static public String[] debugNames = new String[] {"debug0", "debug1", "debug2", "debug3"};
    public static double baseOffsetX = -0.21;
    public static double baseOffsetY = 0.415;
    public static double desOffsetX = 0.2;
    public static double desOffsetY = 0.2;

    public static double convertPxToM = 0.000625; // 0.56/800 , 0.00058 good
    public static double defaultCameraAngle = 300;
    public static double armDefaultX = 0.20;
    public static double armDefaultY = 0.09;

    public static double curDir; 
    public static double targetXArmPick;
    public static double[] yellowBinDimension = {0.415,0.295};


    public static double camera_offset = 0.12;
    public static double camera_mount_offset_x = 0.01;
    public static double arm_offset_y = 0.13; 
    public static double arm_offset_z = 0.25;
    public static double gripper_offset = 0.16;
    public static double CokeRatio = 0.85;
    
    public static int curItemType = 0;
    public static double curItemX;
    public static double curItemY;
    public static int curTarget;
    public static boolean useTF;
    

    public static int imH = 600;
    public static int imW = 800;
    public static int totalItems;
    public static Pose2d curPose;
    public static double WOB[];
    /*
	 *                                               J|D|C              
	 *                                            T1|x|x|x|
	 *                                            T2|x|x|X|
	 *                                            T3|x|x|x|
	 */
    


    public static int loopCount = 0;
    public static boolean endConditionCP5(String targetArea){
        loopCount++;
        if(loopCount<19 && RobotContainer.m_vision.getDistanceTarget(targetArea)[0] == 0){
            return false;
        }
        else{
            loopCount = 0;
            return true;
        }
    } 

    public static boolean endConditionCP7(){
        loopCount++;
        // Count 19
        if(loopCount<19 && (RobotContainer.m_vision.getDistanceTarget("Trolley")[0] == 0)){
            return false;
        }
        else{
            loopCount = 0;
            return true;
        }
    } 

    public static double[][] moveCommands = {
        {2,Math.PI/2,0,0, Math.PI/2},
        {2,-Math.PI/2,0,0, Math.PI/2},
        {0, -0.43, 0, 0, 5},
        {0, -0.43, 0, 0, 5}
    };
    

}
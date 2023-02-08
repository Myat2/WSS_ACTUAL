package frc.robot;

import java.util.HashMap;
import java.util.Map;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Astar.AStarAlgorithm;
import frc.robot.Astar.Grid;
import frc.robot.Astar.Layout;

public class Points{
  private final ShuffleboardTab tab = Shuffleboard.getTab("Points");
  private NetworkTableInstance inst = NetworkTableInstance.getDefault();

  private NetworkTable pointsTable = inst.getTable("Shuffleboard/Points");

  public Map<String, Pose2d> pointMap = new HashMap<>();
  public Map<String, Pose2d> obstacleMap = new HashMap<>();
    // Target Areas
  public Pose2d redTarget = new Pose2d();
  public Pose2d greenTarget = new Pose2d();
  public Pose2d blueTarget = new Pose2d();
  public Pose2d trolley = new Pose2d();
  private double[] defaultValue = new double[12];
  public Pose2d camOffset = new Pose2d(0.015, 0.6, new Rotation2d(0)); // Update this

  // public Pose2d camOffset = new Pose2d(0.015, 0.67, new Rotation2d(0)); // Update this
  // public Pose2d camOffset = new Pose2d(0.015, 1.02, new Rotation2d(0)); // Update this

  public Points() {
    pointMap.put("RedTarget", redTarget);
    pointMap.put("GreenTarget", greenTarget);
    pointMap.put("BlueTarget", blueTarget);
    obstacleMap.put("Trolley", trolley);
  }
  

  public void resetMap() {
    pointMap.clear();
    
  }

  public void updatePoint(String pointname, Pose2d newpose) {
    pointMap.put(pointname, newpose);
    
  }
  public void updateObsPoint(String pointname, Pose2d newpose) {
    obstacleMap.put(pointname, newpose);
    
  }
  

  public void addPoint(String pointname, Pose2d newpose) {
    pointMap.put(pointname, newpose);
    
  }
  public void addObstaclePoint(String pointname, Pose2d newpose) {
    obstacleMap.put(pointname, newpose);
  }

  

  public Pose2d getPoint(String pointname) {
    if (pointMap.containsKey(pointname))
      return pointMap.get(pointname);
    else
      return Globals.curPose;
  }

  public Pose2d getObstacle(String pointname) {
    if (obstacleMap.containsKey(pointname))
      return obstacleMap.get(pointname);
    else
      return Globals.curPose;
    
  }
  
  // Changes
  public void updateLayout(){
    for (String obsName: obstacleMap.keySet()){
      if(obsName == "Trolley"){
        int[] arr = new int[]{(int)(getObstacle(obsName).getTranslation().getX()*1000),(int)(getObstacle(obsName).getTranslation().getY()*1000), 295,  295, 0};
        RobotContainer.m_layout.vision_obs_mm.add(arr);
      }
    }
    // for (Map.Entry<String, Pose2d> set : obstacleMap.entrySet()) {
    //   if (set.getKey() == "Bin"){
    //     int[] arr = new int[]{(int)set.getValue().getTranslation().getX()*1000,(int)set.getValue().getTranslation().getY()*1000, 300,  420, 0};
    //     RobotContainer.m_layout.vision_obs_mm.add(arr);
    //   }
    //   else if(set.getKey() == "Trolley"){
    //     int[] arr = new int[]{(int)set.getValue().getTranslation().getX()*1000,(int)set.getValue().getTranslation().getY()*1000, 295,  295, 0};
    //     RobotContainer.m_layout.vision_obs_mm.add(arr);
    //   }
    // }
  }
  public double[] getDistanceTarget(String targetName){
    double[] distance = (pointsTable.getEntry(targetName).getDoubleArray(defaultValue));
    return distance;
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
public void updateObsPoint(String obsName){
  double x, y;
  int w = (int)Globals.curPose.getRotation().getDegrees();
  if (w != -90){
      y = Globals.curPose.getTranslation().getY() + getDistanceTarget(obsName)[1] + RobotContainer.m_points.camOffset.getTranslation().getY();
      x = Globals.curPose.getTranslation().getX() + getDistanceTarget(obsName)[0] + RobotContainer.m_points.camOffset.getTranslation().getX();
  }
  else{
      x = Globals.curPose.getTranslation().getX() + getDistanceTarget(obsName)[1] + RobotContainer.m_points.camOffset.getTranslation().getY();
      y = Globals.curPose.getTranslation().getY() - getDistanceTarget(obsName)[0] + RobotContainer.m_points.camOffset.getTranslation().getX(); 
  }
  RobotContainer.m_points.updateObsPoint(obsName, new Pose2d(new Translation2d(x, y), Globals.curPose.getRotation()));  
}
public void updateAllTarget(){
  String[] targetAreas = {"RedTarget", "GreenTarget", "BlueTarget", "Trolley"};
  for (String targetName: targetAreas){
      if(getDistanceTarget(targetName)[0] != 0 && getDistanceTarget(targetName)[1] != 0 ){
          updatePoint(targetName);
      }
  }
}
public void updateAllObs(){
  String[] targetAreas = {"Trolley", "Bin"};
  for (String targetName: targetAreas){
      if(getDistanceTarget(targetName)[0] != 0 && getDistanceTarget(targetName)[1] != 0 ){
          updateObsPoint(targetName);
      }
  }
}

  public void updateGrid(){
    for (int i = 0; i< RobotContainer.m_layout.vision_obs_mm.size(); i++){
      int[] obs = RobotContainer.m_layout.vision_obs_mm.get(i);
      SmartDashboard.putNumber("obs", (double)obs[0]);
      RobotContainer.m_Grid.AddObstacle(obs[0], obs[1], obs[2], obs[3], obs[4]);
    }
  }
  public void updateAstar(){
    
    
    
    RobotContainer.m_layout = new Layout();
    updateLayout();
    RobotContainer.m_Grid = new Grid(RobotContainer.m_layout);
    updateGrid();
    RobotContainer.m_Grid.ExpandObstacles(300);
    RobotContainer.m_Astar = new AStarAlgorithm(RobotContainer.m_Grid);
  }

}

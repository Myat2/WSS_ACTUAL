package frc.robot;

import java.util.HashMap;
import java.util.Map;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import frc.robot.Astar.AStarAlgorithm;
import frc.robot.Astar.Grid;
import frc.robot.Astar.Layout;

public class Points{

  public Map<String, Pose2d> pointMap = new HashMap<>();
  public Map<String, Pose2d> obstacleMap = new HashMap<>();
    // Target Areas
  public Pose2d redTarget = new Pose2d();
  public Pose2d greenTarget = new Pose2d();
  public Pose2d blueTarget = new Pose2d();
  public Pose2d trolley = new Pose2d();

  public Pose2d camOffset = new Pose2d(0.015, 0.67, new Rotation2d(0)); // Update this
 
  public Points() {
    pointMap.put("RedTarget", redTarget);
    pointMap.put("GreenTarget", greenTarget);
    pointMap.put("BlueTarget", blueTarget);
    pointMap.put("Trolley", trolley);
  }
  

  public void resetMap() {
    pointMap.clear();
    
  }

  public void updatePoint(String pointname, Pose2d newpose) {
    pointMap.put(pointname, newpose);
    obstacleMap.put(pointname, newpose);
  }

  public void addPoint(String pointname, Pose2d newpose) {
    pointMap.put(pointname, newpose);
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

}

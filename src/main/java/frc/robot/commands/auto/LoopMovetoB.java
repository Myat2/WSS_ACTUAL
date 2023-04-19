package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.geometry.Transform2d;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.WaitUntilCommand;
import frc.robot.Globals;
import frc.robot.RobotContainer;

public class LoopMovetoB extends SequentialCommandGroup{

   /**
     * Moves to a waypoint, scans area, after scanning updates point map
     * stops scanning, checks if trolleys are in the way
     */
  public LoopMovetoB(String targetArea){
    super(
      
      new MovetoBWaypoint(),                                  
      new InstantCommand(() -> Globals.cvMode = 3), 
      // new PassVariable(),
      new WaitCommand(8),
      new InstantCommand(() -> RobotContainer.m_points.updateAllPoints()),
      new InstantCommand(() -> Globals.cvMode = -1),
      new CheckTrolleyinWaypoint()
    );
  }   //the reason we have two is because one is using model and other
      //the other uses HSV CVmode 4 uses Model, 3 uses HSV, both perspective transform
  public LoopMovetoB(){
    super(
      
      new MovetoBWaypoint(),
      new InstantCommand(() -> Globals.cvMode = 4), 
     // new PassVariable(),
      new WaitCommand(5), // Changes      
      new InstantCommand(() -> RobotContainer.m_points.updateAllPoints()),
      new InstantCommand(() -> Globals.cvMode = -1),
      new CheckTrolleyinWaypoint()
    );
  }
}

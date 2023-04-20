package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Globals;
import frc.robot.RobotContainer;
import frc.robot.Astar.Layout;

public class Task_A_pick1st extends SequentialCommandGroup{
  /*
     * Sequence for Competition Task A
     */
    public Task_A_pick1st(){
      super(
          // Start Orientation Correction
          new InstantCommand(()-> System.out.println("inside Task_A_pick1st")),
          new StartOrientation(),
          new InstantCommand(() -> RobotContainer.m_omnidrive.UpdatePosition(Layout.startPos)),
          new InstantCommand(()-> System.out.println("Before setting obstacles")),
          new InstantCommand(()->RobotContainer.m_points.SetTrolleysAsObstacles()),
          new InstantCommand(()-> System.out.println("After setting trolleys as obstacles")),
          new InstantCommand(()->RobotContainer.m_points.SetBinsAsObstacles()),
          new InstantCommand(()-> System.out.println("After setting obstacles")),
          //## Calibrate Robot Position ##//
          new InitialCalibration(),
          //## Read WOB ##// 
          new MovetoB(Layout.workOrderPos),
          new ReadWOB(),
          //## Sort Items ##//
          //## pick up bin 1 ##//
          new MovetoB(Layout.PickUpBinPos),
          new Rotate2Orientation(Layout.PickUpBinPos.getRotation().getDegrees()),
          new Align2Line(),
          new ViewItem(),
          new InstantCommand(()->System.out.println("Before SortItems" )),
          new LoopCmd(new SortItems(), ()->Globals.WOBLoopCondition()),
          // new InstantCommand(()->Globals.curBin = 1), // Change to second bin
          new MoveArm(new Translation2d(0.33,0.24), 0.5), // Line detection position
          new WaitCommand(2), 
          //## Transport Trolleys ##//
          new SortTrolleys()
           
          
      );
  }
}

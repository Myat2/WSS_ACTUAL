package frc.robot.commands.auto;

import frc.robot.RobotContainer;
import frc.robot.Astar.Layout;
// import the commands
import frc.robot.commands.auto.MoveRobot;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.Arm;

public class ProcessSeq extends SequentialCommandGroup {

  private final static Arm m_arm = RobotContainer.m_arm;
  public ProcessSeq(){
    super(  
      
        new PickItem(),
        new MoveCamera(240),   
        new MoveRobot(1, -0.10, 0, 0, 5),     
        new CheckRotationPose(Layout.Convert_mm_Pose2d(Layout.GreenPos)),
        new MovetoB(Layout.Convert_mm_Pose2d(Layout.GreenPos)), // Added
        // new MovetoB(new Pose2d(0.96, 1.5, new Rotation2d(0))),
        
        new PlaceDown(),
        new MoveArm(new Translation2d(0.33,0.24), 0.5),
        // new MovetoB(new Pose2d(0.96, 1.1, new Rotation2d(0))),
        new MovetoB(Layout.Convert_mm_Pose2d(Layout.PickUpBinPos)),
        new CheckRotationPose(Layout.Convert_mm_Pose2d(Layout.PickUpBinPos)),
        new Align2Line(),
        new ViewPickUpBin()
    );
  }
}
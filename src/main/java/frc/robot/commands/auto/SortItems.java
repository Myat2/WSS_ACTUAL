package frc.robot.commands.auto;

import java.util.Map;
import frc.robot.Globals;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SelectCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotContainer;
import frc.robot.Astar.Layout;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Vision;

public class SortItems extends SequentialCommandGroup{
  private final static Arm m_arm = RobotContainer.m_arm;
  private final static Vision m_vision = RobotContainer.m_vision;
  private enum CommandSelector {
    ONE, TWO, THREE
  }

  static public CommandSelector selectTarget() {
    if (Globals.curTarget == 0)
        return CommandSelector.ONE;
    else if (Globals.curTarget == 1)
        return CommandSelector.TWO;
    else if (Globals.curTarget == 2)
        return CommandSelector.THREE;
    else 
        return null;
    
  }
  static public CommandSelector selectBin() {
    
    if (Globals.curBin == 0)
        return CommandSelector.ONE;
    else 
        return CommandSelector.TWO;
    
  }
  public SortItems() 
    {
        super(   
            new PickItem(),
            new MoveCamera(300),
            new MoveRobot(1, -0.1, 0, 0, 5),
            new SelectCommand(
                    Map.ofEntries(
                        Map.entry(CommandSelector.ONE,new CheckAndMoveTarget("RedTarget")),
                        Map.entry(CommandSelector.TWO, new CheckAndMoveTarget("GreenTarget")),
                        Map.entry(CommandSelector.THREE, new CheckAndMoveTarget("BlueTarget"))
                        ), 
                    SortItems::selectTarget
                ),
            new PlaceDown(),
            new MoveRobot(1, -0.07, 0, 0, 5),
            new MoveArm(new Translation2d(0.33,0.24), 0.5),
            new CheckRotationPose(Layout.Convert_mm_Pose2d(Layout.PickUpBinPos)),
            new MovetoB(Layout.Convert_mm_Pose2d(Layout.PickUpBinPos)),
            new InstantCommand(() -> m_vision.setCVMode(0)),
            new Align2Line(),
            new InstantCommand(() -> m_vision.setCVMode(-1)),
            new ViewPickUpBin()
        );
    }  
}
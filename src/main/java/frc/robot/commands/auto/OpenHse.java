package frc.robot.commands.auto;

import frc.robot.Globals;
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

public class OpenHse extends SequentialCommandGroup {

  private final static Arm m_arm = RobotContainer.m_arm;
  public OpenHse(){
    super(  
        
        new PickItemfromBin(),
        new MoveCamera(Globals.NormalCameraAngle),
        // new MovetoB(new Pose2d(0.96, 1.5, new Rotation2d(-Math.PI/2))),
        new MoveRobot(0,-0.4,0,0,0.4),
        new PlaceDown(),
        new MoveArm(new Translation2d(0.33,0.3), 0.5), // Line detection position
        new MoveArm(new Translation2d(0.33,0.24), 0.5), // Line detection position
        new MoveRobot(0,0.4,0,0,0.4),
        new ViewItem()
    );
  }
}
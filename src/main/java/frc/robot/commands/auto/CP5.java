package frc.robot.commands.auto;

import frc.robot.Globals;
import frc.robot.RobotContainer;
// import the commands
import frc.robot.commands.auto.MoveRobot;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.Arm;

public class CP5 extends SequentialCommandGroup {

  private final static Arm m_arm = RobotContainer.m_arm;
  public CP5(){
    super(  
      

        new MoveArm(new Translation2d(0.3,0.4), 2),
        new InstantCommand(()-> m_arm.setCameraAngle(280)),
        new InstantCommand(() -> RobotContainer.m_vision.setFlag(-1)),

        // Move out of the way
        new MoveRobot(0, -0.05, 0, 0, 5),
        new MoveRobot(1, 0.25, 0, 0, 5),


        new loopMoveRobotWaypoint(),
        new LoopCmd(new loopMoveRobotWaypoint(), () -> Globals.endConditionCP5()),

        new InstantCommand(()-> RobotContainer.m_vision.setFlag(0)), // 0 Red, 1 Green, 2 Blue

        // new MovetoPoint("RedTarget"),
        // new CheckRotation("RedTarget"),
       

        // new MoveArm(new Translation2d(0.335,0.24), 2),
        // new InstantCommand(()-> m_arm.setCameraAngle(295)),
        // new WaitCommand(5),
        // new AlignRobot(320,240),
        // new MoveRobot(1, 0.4, 0, 0, 5),
        // new WaitCommand(1)
        
        new InstantCommand(()-> RobotContainer.m_vision.setFlag(1)), // 0 Red, 1 Green, 2 Blue

        new MovetoPoint("GreenTarget"),
        new CheckRotation("GreenTarget"),
        new MoveRobot(1, 0.4, 0, 0, 5)
        // new MoveArm(new Translation2d(0.335,0.24), 2),
        // new InstantCommand(()-> m_arm.setCameraAngle(295)),
        // new WaitCommand(5),
        // new AlignRobot(320,240),
        // new MoveRobot(1, 0.4, 0, 0, 5),
        // new WaitCommand(1)

        // new InstantCommand(()-> RobotContainer.m_vision.setFlag(2)), // 0 Red, 1 Green, 2 Blue
        // new MovetoPoint("BlueTarget"),
        // new CheckRotation("BlueTarget"),
        // new WaitCommand(1),

        // new MoveArm(new Translation2d(0.335,0.24), 2),
        // new InstantCommand(()-> m_arm.setCameraAngle(295)),
        // new WaitCommand(3),
        // new AlignRobot(320,240),
        // new MoveRobot(1, 0.4, 0, 0, 5)
        
        
      
    );
  }
}
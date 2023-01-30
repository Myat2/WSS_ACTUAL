package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Globals;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Arm;

public class CP7 extends SequentialCommandGroup {
    private final static Arm m_arm = RobotContainer.m_arm;
    public CP7(){

        super(
            new MoveArm(new Translation2d(0.3,0.4), 2),
            new InstantCommand(()-> m_arm.setCameraAngle(285)),
            new InstantCommand(() -> RobotContainer.m_vision.setFlag(-1)),
            new loopMoveRobotWaypoint(),
            // new LoopCmd(new loopMoveRobotWaypoint(), () -> Globals.endConditionCP7()),


            new MovetoPoint("Trolley"),
            // new CheckRotation("Trolley"), Idk if you need this 
            
            new InstantCommand(()-> RobotContainer.m_vision.setFlag(3)), // 0 Red, 1 Green, 2 Blue, 3 Black
            new MoveArm(new Translation2d(0.335,0.24), 2),
            new InstantCommand(()-> m_arm.setCameraAngle(295)),
            new WaitCommand(3),
            new AlignRobot(320,240),
            new MoveArm(new Translation2d(0.335, 0.1), 0.5)
            );
    }
}

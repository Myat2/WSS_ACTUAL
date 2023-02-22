package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.RobotContainer;

public class Align2TargetArea extends SequentialCommandGroup{
    public Align2TargetArea(){
        super(
            new MoveArm(new Translation2d(0.335,0.4), 0.5), // Go up
            new MoveArm(new Translation2d(0.335,0.24), 0.5), // Line detection position
            new MoveCamera(265),
            new InstantCommand(()-> RobotContainer.m_vision.setColor("Green")),
            new InstantCommand(() -> RobotContainer.m_vision.setCVMode(0)),
            new WaitCommand(2),     
           new AlignRobot(100,115),
           new MoveRobot(1, 0.4, 0, 0, 5)
        );
    }
}

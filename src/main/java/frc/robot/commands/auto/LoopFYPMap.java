package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.RobotContainer;

public class LoopFYPMap extends SequentialCommandGroup{
    

    public LoopFYPMap(){
        super(
            new MoveRobot(0, 0.38, 0, 0, 5),
            new WaitCommand(4),
            // new InstantCommand(()->RobotContainer.m_points.updateAllTarget())
            new InstantCommand(() -> RobotContainer.m_points.updateAllPoints())
        );
    }
}

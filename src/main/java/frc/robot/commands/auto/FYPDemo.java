package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.*;
import frc.robot.Points;
import frc.robot.RobotContainer;

public class FYPDemo extends SequentialCommandGroup{

    private final static Arm m_arm = RobotContainer.m_arm;
    private  final static Points m_points = RobotContainer.m_points;

    public FYPDemo(){
        super(
            new MoveArm(new Translation2d(0.3,0.4), 2),
            new InstantCommand(()-> m_arm.setCameraAngle(295)),
            // Sets the python script to perspective transformation with HSV mode
            new InstantCommand(() -> RobotContainer.m_vision.setCVMode(4)),

           new WaitCommand(5),
        
           new InstantCommand(()->m_points.updateAllTarget()),
           new MovetoPoint("Trolley"),
           new InstantCommand(() -> RobotContainer.m_vision.setCVMode(0)), 
           new InstantCommand(()-> m_arm.setCameraAngle(300)),
           new MoveArm(new Translation2d(0.335,0.24), 2),
           new WaitCommand(2),
           new AlignRobot("trolley"),
           new MoveRobot(1, 0.08, 0, 0, 0.1),
           
           new TrolleyHolder(1),
           new MovetoPoint("GreenTarget"),
           new TrolleyHolder(0)

        );
    }
}

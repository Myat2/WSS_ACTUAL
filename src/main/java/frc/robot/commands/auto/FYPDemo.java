package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Transform2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.*;
import frc.robot.Globals;
import frc.robot.Points;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.Astar.Layout;

public class FYPDemo extends SequentialCommandGroup{

    private final static Arm m_arm = RobotContainer.m_arm;
    private  final static Points m_points = RobotContainer.m_points;

    public FYPDemo(){
        super(
            new MoveArm(new Translation2d(0.3,0.4), 2),
            new MoveCamera(295),

            // Sets the python script to perspective transformation tensorflow
            new InstantCommand(() -> Globals.cvMode = 4),
            // Move out of the way
            new MoveRobot(0, -0.08, 0, 0, 5),
            new MoveRobot(1, 0.25, 0, 0, 5),
            // Rotate to Map
            new MoveRobot(2,Math.PI/2,0,0, Math.PI/2),
            new WaitCommand(4),
            new InstantCommand(()->RobotContainer.m_points.updateAllPoints()),
            // Mapping Seq
            new LoopFYPMap(),
            // new LoopCmd(new LoopFYPMap(), () -> Globals.endConditionDemo()), // Havent Test
            
            

            new InstantCommand(() -> Globals.cvMode = -1),
            // Read WOB
            // new MovetoB(Layout.Convert_mm_Pose2d(Layout.demoWorkOrderPos)),
            // new CheckRotationPose(Layout.Convert_mm_Pose2d(Layout.demoWorkOrderPos)),
            new ReadWOB(), 

            // Go to Trolley
            new CheckRotationPose(RobotContainer.m_points.getPoint("Trolley")),
            new MovetoPoint("Trolley"),
            new Align2Trolley(),
            // new MoveRobot(1, 0.05, 0, 0, 2),
            new TrolleyHolder(1),


           // Go to Green Target
           new CheckRotationPose(RobotContainer.m_points.getPoint("GreenTarget")),
           new MovetoPoint("GreenTarget"),
           new Align2Color(),
           new TrolleyHolder(0)
           
          
        //    // Update Obstacles
        //    new InstantCommand(() -> RobotContainer.m_points.AddObsGrid()),
        //     //Update Green Target to offset
        //     new InstantCommand(() -> RobotContainer.m_points.pointMap.put("GreenTarget", RobotContainer.m_points.getPoint("GreenTarget").plus(new Transform2d(new Translation2d(0.00, -0.02),new Rotation2d())))),


        // //    Go to Pickup Bin
            
        //     new MovetoB(Layout.Convert_mm_Pose2d(Layout.PickUpBinPos)),
        //     new CheckRotationPose(Layout.Convert_mm_Pose2d(Layout.PickUpBinPos)),
        //     new Align2Line(),

        //    // Pick and place at target area
        //    new WaitCommand(2),
        //    new ViewPickUpBin(),
        //    new InstantCommand(()-> Globals.curTarget = 1), // curTarget = Green
        //    new LoopCmd(new SortItems(), ()->Globals.DemoWOBLoopCondition())



        );
    }
}

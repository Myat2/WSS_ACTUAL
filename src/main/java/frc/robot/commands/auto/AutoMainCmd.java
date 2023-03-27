package frc.robot.commands.auto;



import edu.wpi.first.wpilibj2.command.ProxyScheduleCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.OmniDrive;
import frc.robot.subsystems.Sensor;
import frc.robot.subsystems.Vision;
import frc.robot.Astar.Layout;

/**
 * DriveMotor class
 * <p>
 * This class creates the inline auto command to drive the motor
 */
public class AutoMainCmd extends SequentialCommandGroup
{   
    int count = 0;
    double temp;


	public AutoMainCmd() 
    {
        
        
        super
        (
            // new GotoColor(Layout.RedPos)
            new Task_B()
            // Lifts arm up and close gripper
            // new Task_A_pick1st()
            // new ViewingPosition()
            // new StartOrientation()
            // new MoveGripper(new Translation2d(0.4, 0), 0.5)
            // new MovetoB(new Pose2d(new Translation2d(0.46, 0.26), new Rotation2d())),
            // new Rotate2Orientation(0),
            // new LoopMovetoB(),
            // new LoopCmd(new LoopMovetoB(), ()-> Globals.endConditionTaskBMapping())  
            // new InitialCalibration()
            // new Task_B()
            // Move out of starting position
            // new MovetoB(RobotContainer.m_Grid.findGotoPos(Layout.Convert_mm_Pose2d(Layout.T1Pos).getTranslation(), 0.5)),
            // new Rotate2Orientation(RobotContainer.m_Grid.findGotoPos(Layout.Convert_mm_Pose2d(Layout.T1Pos).getTranslation(), 0.5)),
            // new MovetoB(RobotContainer.m_Grid.findGotoPos(Layout.Convert_mm_Pose2d(Layout.T2Pos).getTranslation(), 0.5)),
            // new Rotate2Orientation(RobotContainer.m_Grid.findGotoPos(Layout.Convert_mm_Pose2d(Layout.T2Pos).getTranslation(), 0.5)),
            // new MovetoB(RobotContainer.m_Grid.findGotoPos(Layout.Convert_mm_Pose2d(Layout.T3Pos).getTranslation(), 0.5)),
            // new Rotate2Orientation(RobotContainer.m_Grid.findGotoPos(Layout.Convert_mm_Pose2d(Layout.T3Pos).getTranslation(), 0.5)),
            // new MovetoB(RobotContainer.m_Grid.findGotoPos(new Translation2d(1.5,4.35), 0.5)),
            // new Rotate2Orientation(RobotContainer.m_Grid.findGotoPos(new Translation2d(1.5,4.35), 0.5)),
            // new MovetoB(RobotContainer.m_Grid.findGotoPos(new Translation2d(1.9,1.9), 0.5)),
            // new Rotate2Orientation(RobotContainer.m_Grid.findGotoPos(new Translation2d(1.9,1.9), 0.5)),
            // new MovetoB(RobotContainer.m_Grid.findGotoPos(Layout.Convert_mm_Pose2d(Layout.GreenPos).getTranslation(), 0.5)),
            // new Rotate2Orientation(RobotContainer.m_Grid.findGotoPos(Layout.Convert_mm_Pose2d(Layout.GreenPos).getTranslation(), 0.5))
            
            // new InstantCommand(()-> Globals.curAngle = RobotContainer.m_Grid.findGotoPos(Layout.Convert_mm_Pose2d(Layout.T1Pos).getTranslation(), 0.5).getRotation().getDegrees())
            // new Rotate2Orientation(-135)
            // new MovetoB(Layout.Convert_mm_Pose2d(Layout.PickUpBinPos))
        // new StartOrientation()
        // new StartPosition()
        // new Task_A_trolley1st()
    //   new MovetoB(new Pose2d(new Translation2d(1.8, 1.7),new Rotation2d(0))),
    
    //    new Task_A_pick1st()
    // new GotoTrolley(Layout.T1Pos)
        // new InstantCommand(()-> System.out.println(RobotContainer.m_Grid.findGotoPos(Layout.T1Pos.getTranslation(), 0.5)))
        // new MovetoB(RobotContainer.m_Grid.findGotoPos(Layout.T1Pos.getTranslation(), 0.5))
        // new GotoTrolley(Layout.T1Pos)
        // new testing(Layout.T1Pos)
        // new InstantCommand(()->RobotContainer.m_points.SetTrolleysAsObstacles()),
        // new MovetoB(Layout.Convert_mm_Pose2d(Layout.testPickUpBinPos)),
        // // new MovetoB(Layout.Convert_mm_Pose2d(Layout.T2Pos)),
        // new GotoTrolley(Layout.Convert_mm_Pose2d(Layout.T2Pos)),
        // new MovetoB(Layout.Convert_mm_Pose2d(Layout.PickUpBinPos))
        
        // new Align2Line(),
        // ###################################################################################### // 
        //          FOR TESTING, IF ROBOT MOVEMENT HAS ISSUES           //
            //new LoopCmd(new TestMotion(), ()->(++Globals.LoopCnt)>5 ) /// loop cmd
            // new MoveRobot(0,-1.5,0,0,0.4)
                // new TestMotionX(),
                // new TestMotionX(),
                // new TestMotionX(),
                // new TestMotionX(),
                // new TestMotionX()
                // new MoveRobot(0, 0.0875, 0, 0, 0.4),
                // // new WaitCommand(2),
                // new MovetoB(new Pose2d(0.96, 1.6, new Rotation2d(0))),
                // new MovetoB(new Pose2d(0.96, 1.1, new Rotation2d(0))),
                // new MoveRobot(0, 0.0875, 0, 0, 0.4),
                // // new WaitCommand(2),
                // new MovetoB(new Pose2d(0.96, 1.6, new Rotation2d(0))),
                // new MovetoB(new Pose2d(0.96, 1.1, new Rotation2d(0))),
                // new MoveRobot(0, 0.0875, 0, 0, 0.4),
                // // new WaitCommand(2),
                // new MovetoB(new Pose2d(0.96, 1.6, new Rotation2d(0))),
                // new MovetoB(new Pose2d(0.96, 1.1, new Rotation2d(0))),
                // new MoveRobot(0, 0.0875, 0, 0, 0.4),
                // // new WaitCommand(2),
                // new MovetoB(new Pose2d(0.96, 1.6, new Rotation2d(0))),
                // new MovetoB(new Pose2d(0.96, 1.1, new Rotation2d(0))),
                // new MoveRobot(0, 0.0875, 0, 0, 0.4),
                // new WaitCommand(2),
                // new MovetoB(new Pose2d(0.96, 1.6, new Rotation2d(0))),
                // new MovetoB(new Pose2d(0.96, 1.1, new Rotation2d(0)))
              );
            
    }
    
}
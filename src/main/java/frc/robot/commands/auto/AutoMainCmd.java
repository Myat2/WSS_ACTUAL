package frc.robot.commands.auto;

import java.util.List;
import java.util.function.BooleanSupplier;

import org.opencv.core.Mat;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.ProfiledPIDController;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.trajectory.TrapezoidProfile.Constraints;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ProxyScheduleCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.RobotContainer;
import frc.robot.Astar.AStarAlgorithm;
import frc.robot.Astar.Grid;
import frc.robot.Astar.Layout;
import frc.robot.Globals;
import frc.robot.Points;
import frc.robot.Robot;
import frc.robot.subsystems.Sensor;
import frc.robot.subsystems.Vision;
import frc.robot.subsystems.Arm;


/**
 * DriveMotor class
 * <p>
 * This class creates the inline auto command to drive the motor
 */
public class AutoMainCmd extends SequentialCommandGroup {

    private final static Sensor m_sensor = RobotContainer.m_sensor;
    private final static Vision m_vision = RobotContainer.m_vision;
    private final static Arm m_arm = RobotContainer.m_arm;
    private  final static Points m_points = RobotContainer.m_points;
    private final static int[] obs = {930,280,300,300,0};
    public AutoMainCmd() {

        super(
           
            // new CheckRotationPose(Layout.Convert_mm_Pose2d(Layout.T0Pos)),
            // new MovetoB(Layout.Convert_mm_Pose2d(Layout.T0Pos)),
            // new CheckRotationPose(Layout.Convert_mm_Pose2d(Layout.PickUpBinPos)),
            // new MovetoB(Layout.Convert_mm_Pose2d(Layout.PickUpBinPos))

            // new MoveArm(new Translation2d(0.335,0.24), 0.5), // Line detection position
            // new MoveCamera(300),
            // new InstantCommand(() -> RobotContainer.m_vision.setCVMode(0)),
            // new WaitCommand(3),
            // new AlignRobot(),
            // new WaitCommand(2),
            // new InstantCommand(()-> RobotContainer.m_omnidrive.UpdatePosition(Layout.Convert_mm_Pose2d(Layout.PickUpBinPos))),

            // new MovetoB(Layout.Convert_mm_Pose2d(Layout.PickUpBinPos))
            //new ReadWOB()
            // new MoveArm(new Translation2d(0.335,0.4), 0.5), // Line detection position

            // // new WaitCommand(2),
            // new MoveArm(new Translation2d(0.335,0.24), 0.5), // Line detection position
            // new MoveCamera(300),
            // new InstantCommand(() -> RobotContainer.m_vision.setCVMode(0)),
            // new MovetoB(Layout.Convert_mm_Pose2d(Layout.PickUpBinPos)),
            // new WaitCommand(2),
            // new AlignRobot(),
            // new WaitCommand(3),
            // new InstantCommand(()-> RobotContainer.m_omnidrive.UpdatePosition(Layout.Convert_mm_Pose2d(Layout.PickUpBinPos)))


            // new FYPDemo()
            new CP1()

            //  new Align2Trolley()
            // new CP5("RedTarget")
            //new CP1()
            
        );


    }

}

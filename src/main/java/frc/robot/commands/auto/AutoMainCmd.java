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
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.RobotContainer;
import frc.robot.Astar.Layout;
import frc.robot.Globals;
// import the commands
import frc.robot.commands.auto.MoveRobot;
import frc.robot.commands.auto.MoveTest;
//import frc.robot.commands.auto.MoveIRSensor;
import frc.robot.commands.auto.RotateTest;
import frc.robot.commands.auto.MoveRobotSense.end_func;
import frc.robot.subsystems.Sensor;
import frc.robot.subsystems.Vision;
import frc.robot.subsystems.Arm;
import edu.wpi.first.wpilibj.AnalogInput;

/**
 * DriveMotor class
 * <p>
 * This class creates the inline auto command to drive the motor
 */
public class AutoMainCmd extends SequentialCommandGroup {

    public static int testPos0[] = { 210, 1210, 0 };
    public static int testPos1[] = { 900, 900, 0 };
    public static int depoPos1[] = { 600, 3400, 0 };
    public static int homeBase[] = { 300, 300, 0 };
    public static int depoPos2[] = { 1500, 3300, 0 };
    private final static Sensor m_sensor = RobotContainer.m_sensor;
    private final static Vision m_vision = RobotContainer.m_vision;
    private final static Arm m_arm = RobotContainer.m_arm;
    // private static double pickUpX = -1.1;
    // private static double pickUpY = 1.2;
    // private static double pickUpW = 0;

    // private static double base1X = -3.25;
    // private static double base1Y = 0.285;
    // private static double base1W = Math.PI;

    // private static double base2X = -4.2;
    // private static double base2Y = 1.135;
    // private static double base2W = Math.PI / 2;

    public AutoMainCmd() {
        /*
         * 0 - Jagabee
         * 1 - Dettol
         * 2 - Coke
         */

        super(
                       
            new ViewItem(), 
            new LoopCommands(new ProcessSeq())
        );


    }

    @Override
    public void initialize() {
        // Initialize done before base initialization

        super.initialize();
        RobotContainer.m_arm.initialize();
        RobotContainer.m_omnidrive.initialise();
        Globals.useTF = false;
    }

}

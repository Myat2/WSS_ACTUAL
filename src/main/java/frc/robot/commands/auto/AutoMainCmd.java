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
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.RobotContainer;
// import the commands
import frc.robot.commands.auto.MoveRobot;
import frc.robot.commands.auto.MoveTest;
//import frc.robot.commands.auto.MoveIRSensor;
import frc.robot.commands.auto.RotateTest;
import frc.robot.commands.auto.MoveRobotSense.end_func;
import frc.robot.subsystems.Sensor;
import edu.wpi.first.wpilibj.AnalogInput;
/**
 * DriveMotor class
 * <p>
 * This class creates the inline auto command to drive the motor
 */
public class AutoMainCmd extends SequentialCommandGroup
{   

    
    
	public AutoMainCmd()
    {
       
        
        super( 
            /* 
            new MoveArm(new Translation2d(0.2, 0),25),
            
            new MoveArm(new Translation2d(0.2, 0.2), 25),
            new MoveArm(new Translation2d(0.05, 0), 25)
            //new MoveArm(new Translation2d(0, 0), 25)
            
             */
            new MoveArm(new Translation2d(0.2,0),25),
            new MoveArm(new Translation2d(0.3,0),25),
            new MoveArm(new Translation2d(0.3,0.3),25),
            new MoveArm(new Translation2d(0.2,0.3),25),
            new MoveArm(new Translation2d(0.2,0),25)
            );
       
    }
    @Override
    public void initialize(){
        // Initialize done before base initialization
        RobotContainer.m_arm.initialize();
        super.initialize();
        
    }
    
    
    
    
}

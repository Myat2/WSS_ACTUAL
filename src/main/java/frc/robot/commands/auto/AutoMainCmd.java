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

    private double maxSpeed = 0.5;
    private final static Sensor m_sensor = RobotContainer.m_sensor;
    
	public AutoMainCmd()
    {
       
        
        super( 
                // Base to PickUp
                // new BaseToPickUp(), 
                // //new LoopCmd(new SequentialCommandGroup(new PickUpToDeposit(), new DepositToPickUp())),
                // //Pickup to Top
                // new PickUpToDeposit(), 

                // //Top to Pickup
                // new DepositToPickUp(), 
                // //Pickup to Top
                // new PickUpToDeposit(), 

                // //Top to Pickup
                // new DepositToPickUp(),

                // //PickUp Rotate
                // new RotatePickUpToDeposit(),
                // Rotate, DepositToBin
               new RotateDepositToPickUp(),
                // new MoveRobot(0, -2.5, 0, 0, 5),
                
                // new MoveRobotSense(0, 10, 0, 0, 0.25, () -> m_sensor.getCobraTotal() > 3500),
                // new Pick()

                // Pickup to base
                new PickUpToBase()

                
            );
       
    }
    @Override
    public void initialize(){
        // Initialize done before base initialization
        RobotContainer.m_arm.initialize();
        super.initialize();
        
    }
    
    
    
    
}

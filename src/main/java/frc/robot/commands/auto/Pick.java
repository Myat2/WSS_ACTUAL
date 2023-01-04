package frc.robot.commands.auto;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Globals;
import frc.robot.RobotContainer;
import frc.robot.commands.auto.MoveArm;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Sensor;
import frc.robot.subsystems.Vision;

public class Pick extends SequentialCommandGroup {
    private final static Sensor m_sensor = RobotContainer.m_sensor;
    private final static Vision m_vision = RobotContainer.m_vision;
    private final static Arm m_arm = RobotContainer.m_arm;
    private final static int maxSpeed = 1;
    private final static int maxSpeedGripper = 20;
    public Pick() {
        super(
                new Gripper(1, maxSpeedGripper),
                new ArmPickX(5),
                //new MoveGripper(new Translation2d(m_arm.getArmPos().getX() - Globals.arm_offset_y,0.02), maxSpeed),
                new Gripper(0, maxSpeedGripper),
                new MoveGripper(new Translation2d(0.4,0.3),maxSpeed)
                // new WaitCommand(2),
                
                // new ArmPickXSense(50, () -> m_sensor.getGripperIRDistance() <=5),
                // new Grip(0, maxSpeedGripper),
                // new MoveGripper(new Translation2d(m_arm.getArmPos().getX(),0.2), maxSpeed)
                

        );
    }
    
}

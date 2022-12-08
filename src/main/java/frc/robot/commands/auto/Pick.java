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
   
    public Pick() {
        super(
                new MoveGripper(0, 100),
                new MoveArm(new Translation2d(0.335,0.25), 100),
                new MoveGripper(1, 100),
                new WaitCommand(2),
                new ArmPickX(25),
                // new ArmPickXSense(50, () -> m_sensor.getGripperIRDistance() <=5),
                new MoveGripper(0, 100),
                new MoveArm(new Translation2d(0.335,0.25), 100)

        );
    }
    
}

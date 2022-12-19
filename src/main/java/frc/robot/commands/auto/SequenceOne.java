package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Globals;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Sensor;
import frc.robot.subsystems.Vision;
import frc.robot.subsystems.Arm;

public class SequenceOne extends SequentialCommandGroup{
    private final static Sensor m_sensor = RobotContainer.m_sensor;
    private final static Vision m_vision = RobotContainer.m_vision;
    private final static Arm m_arm = RobotContainer.m_arm;
    public SequenceOne(){
        super(
            new MoveArm(new Translation2d(0.33,0.24), 10),
            new InstantCommand(()-> m_arm.setServoAngle3(295)),
            new InstantCommand(() -> Globals.useTF = false),
            new InstantCommand(m_vision::setUseTF),
            new WaitCommand(3),
            new AlignRobot(),
            new MoveRobotSense(1,1,0,0,.5,() -> m_sensor.getFrontIRDistance() <= 15),
            new ArmToMidBin(5),
            new InstantCommand(()-> m_arm.setServoAngle3(280)),
            new WaitCommand(3),
            new InstantCommand(() -> Globals.useTF = true),
            new InstantCommand(m_vision::setUseTF),
            new InstantCommand(() -> Globals.curItem = 0),
            new WaitCommand(5),
            new AlignPicker(50),
            new Pick(),

            new MoveArm(new Translation2d(0.33,0.24), 10),
            new InstantCommand(()-> m_arm.setServoAngle3(295)),
            new MoveRobotSense(1,-1,0,0,.5,() -> m_sensor.getFrontIRDistance() >= 30),
            new MoveRobot(0,1.35,0,0,5),
            new AlignRobot(),
            new MoveRobotSense(1,1,0,0,.5,() -> m_sensor.getFrontIRDistance() <= 15),
            new Place()
        );
    }
}

package frc.robot.commands.auto;


import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Globals;
import frc.robot.RobotContainer;
import frc.robot.Astar.Layout;
import frc.robot.subsystems.*;

public class CP1 extends SequentialCommandGroup{

    private static final Sensor m_sensor = RobotContainer.m_sensor;
    private static final Vision m_vision = RobotContainer.m_vision;
    private static final Arm m_arm = RobotContainer.m_arm;
  public CP1(){
    super(
        // set arm to vertical position
        new MoveArm(new Translation2d(0.33,0.24), 10),
        new MovetoB(new Pose2d(Layout.PickUpBinPos[0][0],Layout.PickUpBinPos[0][1],new Rotation2d(Layout.PickUpBinPos[0][2]))),
        
        new InstantCommand(()-> m_arm.setServoAngle3(295)),
        new InstantCommand(() -> Globals.useTF = false),
        new InstantCommand(m_vision::setUseTF),
        new WaitCommand(3),
        new AlignRobot(),
        // Move Forward
        new MoveRobotSense(1,1,0,0,.5,() -> m_sensor.getFrontIRDistance() <= 15),
        // Lower arm camera
        new MoveArm(new Translation2d(Globals.armDefaultX,Globals.armDefaultY), 1),
        new InstantCommand(()-> m_arm.setServoAngle3(270)),
        new WaitCommand(3),

        // Tell python to useTF
        new InstantCommand(() -> Globals.useTF = true),
        new InstantCommand(m_vision::setUseTF),
        new InstantCommand(() -> Globals.curItem = 0),
        new WaitCommand(5),

        // Align X-axis to object
        new AlignPicker(50),
        new Pick()
    );
  }
  
}

package frc.robot.commands.auto;


import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Globals;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Vision;

public class Align2Trolley extends SequentialCommandGroup{
  private final static Vision m_vision = RobotContainer.m_vision;
  public Align2Trolley(){
    super(
      new MoveArm(new Translation2d(0.335,0.24), 2),
      new WaitCommand(2),
      new InstantCommand(() -> RobotContainer.m_vision.setCVMode(0)),
      new AlignRobot("trolley"),
      new InstantCommand(() -> RobotContainer.m_vision.setCVMode(1))
      // new MoveRobot(1, 0.07, 0, 0, 0.1)
      
    );
  }
}

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.PerpetualCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.WaitUntilCommand;
import frc.robot.Globals;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Vision;

public class ReadWOB extends SequentialCommandGroup{
  private final static Arm m_arm = RobotContainer.m_arm;
  private final static Vision m_vision = RobotContainer.m_vision;
  //This command is used to read the Work Order Board
  public ReadWOB(){
    super(
      
      
      // Moves arm to work order board position
      new WOBPosition(),

      //waits for camera to settle
      new WaitCommand(0.5),

      // sets cvMOde to work order board detection
      new InstantCommand(()-> Globals.cvMode = 2),

      //checks if scan has finished, then continues if yes
      new PassVariable(),
      
      // resets cvMode to idle
      new InstantCommand(()-> Globals.cvMode=-1),
      // gets the array from networktables and saves it
      new InstantCommand(()-> m_vision.getWOBItems()),
     
      // Lifts arm up
      new DetectionPosition()
    );
  }
}
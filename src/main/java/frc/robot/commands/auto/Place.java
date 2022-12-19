package frc.robot.commands.auto;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.auto.MoveArm;
import frc.robot.Globals;
import frc.robot.Robot;
import frc.robot.RobotContainer;
public class Place extends SequentialCommandGroup
{

    public Place(){
        super(
            new ArmToMidBin(5),
            new MoveGripper(new Translation2d(RobotContainer.m_arm.getArmPos().getX() + Globals.arm_offset_y,0.00), 100),
           new Grip(1,100),
           new WaitCommand(1),
            new MoveGripper(new Translation2d(RobotContainer.m_arm.getArmPos().getX() + Globals.arm_offset_y,0.24), 100),
            new Grip(0,100)

        );
    }
}

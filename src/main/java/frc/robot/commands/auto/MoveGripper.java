package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.CommandBase;

import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Globals;
//RobotContainer import
import frc.robot.RobotContainer;
//Subsystem imports
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Sensor;
import frc.robot.subsystems.Vision;
public class MoveGripper extends MoveArm{

    public MoveGripper(Translation2d pos, double maxSpeed){
        super(pos,maxSpeed);
        
        super.tgt_pos = new Translation2d(pos.getX()-Globals.arm_offset_y, pos.getY()-Globals.arm_offset_z+Globals.gripper_offset);
        
    }
}

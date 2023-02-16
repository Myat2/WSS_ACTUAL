package frc.robot.commands.auto;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Globals;
//RobotContainer import
import frc.robot.RobotContainer;

import org.opencv.core.Mat;

import edu.wpi.first.wpilibj.geometry.Pose2d;

//Subsystem imports
import frc.robot.subsystems.OmniDrive;

public class CheckRotationPose extends MoveRobot{
    private final static OmniDrive m_drive = RobotContainer.m_omnidrive;
    private boolean m_endFlag = false;
    private double speedW;
    private double target_angle_rad;
    private Pose2d m_point;
    private double m_angle;
    public CheckRotationPose(Pose2d point){
        super(2, 0, 0, 0, 0.4);
        m_point = point;
        
    }
    @Override
    public void initialize()
    {   
        m_angle = m_point.getRotation().getDegrees() - m_drive.getDir();
        if(m_angle>180)
            m_angle = m_angle- 360;
        else if(m_angle < -180)
            m_angle = m_angle + 360;
        else
            m_angle = m_angle + 0;
        super.m_dist = Math.toRadians(m_angle);
        
        super.initialize();
        
    }
    
}
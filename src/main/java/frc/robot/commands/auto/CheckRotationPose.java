package frc.robot.commands.auto;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Globals;
//RobotContainer import
import frc.robot.RobotContainer;
import edu.wpi.first.wpilibj.geometry.Pose2d;

//Subsystem imports
import frc.robot.subsystems.OmniDrive;

public class CheckRotationPose extends CommandBase{
    private final static OmniDrive m_drive = RobotContainer.m_omnidrive;
    private boolean m_endFlag = false;
    private double speedW;
    private double target_angle_rad;
    private Pose2d m_point;
    public CheckRotationPose(Pose2d point){
        
        m_point = point;
        
    }
    @Override
    public void initialize()
    {   
        target_angle_rad = m_point.getRotation().getRadians();
        m_endFlag = false;
        
    }
    /**
     * Condition to end speed profile
     * Used by derived class to terminate the profile early
     */
    public boolean endCondition()
    {
        return false;
    }
    /**
     * Called continously until command is ended
     */
    @Override
    public void execute()
    {
        speedW = target_angle_rad - m_drive.getDirRad();
        m_drive.setRobotSpeedType(2, speedW);

        if (Math.abs(speedW) <= 0.002){
            m_drive.setRobotSpeedType(2, 0);
            m_endFlag = true;
        }
 
    }
     /**
     * Called when the command is told to end or is interrupted
     */
    @Override
    public void end(boolean interrupted)
    {

    }

    /**
     * Creates an isFinished condition if needed
     */
    @Override
    public boolean isFinished()
    {
        return m_endFlag;
    }
}
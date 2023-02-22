package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.OmniDrive;

public class MoveRobotIR extends CommandBase{
    private final static OmniDrive m_drive = RobotContainer.m_omnidrive;
    private int dist;
    private double speedY;
    private boolean m_endFlag = false;
    public MoveRobotIR(int distance){
        dist = distance;
    }
    @Override
    public void initialize(){
        m_endFlag = false; 
    }
    @Override
    public void execute()
    {
        speedY = (RobotContainer.m_sensor.getFrontIRDistance() - dist ) * 0.25;
        m_drive.setRobotSpeedType(1, speedY); 
        // Infinite loop 
        // if (RobotContainer.m_sensor.getFrontIRDistance() == dist){
        //     m_endFlag = true;
        // }
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

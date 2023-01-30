package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.trajectory.TrapezoidProfile;
//WPI imports
import edu.wpi.first.wpilibj2.command.CommandBase;
//RobotContainer import
import frc.robot.RobotContainer;

//Subsystem imports
import frc.robot.subsystems.OmniDrive;

/**
 * SimpleDrive class
 * <p>
 * This class drives a motor 
 */
public class MoveRobot extends CommandBase
{
    //Grab the subsystem instance from RobotContainer
    private final static OmniDrive m_drive = RobotContainer.m_omnidrive;
    private double dT = 0.02;
    private double time = 0;
    private boolean m_endFlag = false;
    protected int m_profType;
    protected TrapezoidProfile.Constraints m_constraints;
    protected TrapezoidProfile.State m_goal;
    protected TrapezoidProfile.State m_setpoint;
    protected TrapezoidProfile m_profile;

    protected double m_startSpeed, m_endSpeed, m_maxSpeed;
    protected double m_dist;
    private int m_dir;

    /**
     * This command moves the robot a certain distance following a trapezoidal speed profile.
     * <p>
     * 
     * @param type - 0, 1 or 2 for x, y, or w speed
     * @param dist - distance to move (m/s or rad/s)
     * @param startSpeed -  starting speed of robot
     * @param endSpeed - ending speed of robot
     * @param maxSpeed - max speed of robot
     */
    //This move the robot a certain distance following a trapezoidal speed profile.
    public MoveRobot(int type, double dist, double startSpeed, double endSpeed, double maxSpeed)
    {
        m_startSpeed = startSpeed;
        m_endSpeed = endSpeed;
        m_profType = type;
        m_dist = dist;
        m_maxSpeed = maxSpeed;
 
        
        m_goal = new TrapezoidProfile.State(m_dist, m_endSpeed);

        //addRequirements(m_drive); // Adds the subsystem to the command
     
    }

    /**
     * Runs before execute
     */
    @Override
    public void initialize()
    {   
        if (m_profType==2){
            m_constraints = new TrapezoidProfile.Constraints(m_maxSpeed, 2.0*Math.PI);
        }
        else{
            m_constraints = new TrapezoidProfile.Constraints(m_maxSpeed, 0.3);
        }

        m_setpoint = new TrapezoidProfile.State(0, m_startSpeed);
        m_endFlag = false;        
        
        m_goal = new TrapezoidProfile.State(m_dist, m_endSpeed);
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
        time += dT;

        m_profile = new TrapezoidProfile(m_constraints, m_goal, m_setpoint);
        m_setpoint = m_profile.calculate(dT);

        m_drive.setRobotSpeedType(m_profType, m_setpoint.velocity);

        if ( m_profile.isFinished(dT) || endCondition() ) {
            //distance reached or end condition met. End the command
            //This class should be modified so that the profile can end on other conditions like
            //sensor value etc.
            m_drive.setRobotSpeedType(m_profType, m_goal.velocity);
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
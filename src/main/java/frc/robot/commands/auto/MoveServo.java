package frc.robot.commands.auto;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.trajectory.TrapezoidProfile;
//WPI imports
import edu.wpi.first.wpilibj2.command.CommandBase;
//RobotContainer import
import frc.robot.RobotContainer;
import frc.robot.subsystems.Arm;
//Subsystem imports
import frc.robot.subsystems.OmniDrive;

/**
 * SimpleDrive class
 * <p>
 * This class drives a motor 
 */
public class MoveServo extends CommandBase
{
    //Grab the subsystem instance from RobotContainer
    private final static Arm m_arm = RobotContainer.m_arm;
    private double dT = 0.02;
    private boolean m_endFlag = false;
    private int m_profType;
    private TrapezoidProfile.Constraints m_constraints;
    private TrapezoidProfile.State m_goal = new TrapezoidProfile.State();
    private TrapezoidProfile.State m_setpoint = new TrapezoidProfile.State();
    
    /* 
    private final ShuffleboardTab tab = Shuffleboard.getTab("MoveServo");
    private final NetworkTableEntry D_position = tab.add("m_dir", 0).getEntry();
    private final NetworkTableEntry D_setpoint_position = tab.add("Setpoint Position", 0).getEntry();
    private final NetworkTableEntry D_goal_position = tab.add("Goal Position", 0).getEntry();
    */
    private int m_dir;

    private final double _startSpeed;

    /**
     * This command moves the servo a certain angke (degrees) following a trapezoidal speed profile.
     * <p>
     * 
     * @param position - position in degrees
     * @param startSpeed -  starting speed of robot
     * @param endSpeed - ending speed of robot
     * @param maxSpeed - max speed of robot
     */
    //This move the robot a certain distance following a trapezoidal speed profile.
    public MoveServo(double position, double startSpeed, double endSpeed, double maxSpeed)
    {
        _startSpeed = startSpeed;
        
        
        m_constraints = new TrapezoidProfile.Constraints(maxSpeed, 50);
        
        m_setpoint = new TrapezoidProfile.State(m_arm.getServoAngle0(), _startSpeed);
        
        //Negative distance don't seem to work with the library function????
        //Easier to make distance positive and use m_dir to keep track of negative speed.
        
        m_dir = (position>0)?1:-1;
        position *= m_dir;          
        
        // m_goal = new TrapezoidProfile.State(position, endSpeed);
        m_goal = new TrapezoidProfile.State(position, endSpeed);
        //addRequirements(m_drive); // Adds the subsystem to the command
     
    }

    /**
     * Runs before execute
     */
    @Override
    public void initialize()
    {   
        m_setpoint = new TrapezoidProfile.State(m_arm.getServoAngle0(), _startSpeed);
        m_endFlag = false;
    }
    /**
     * Condition to end speed profile
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
        //Create a new profile to calculate the next setpoint(speed) for the profile
        var profile = new TrapezoidProfile(m_constraints, m_goal, m_setpoint);
        m_setpoint = profile.calculate(dT);
        m_arm.setServoAngle0(m_setpoint.position*m_dir);

        if ((m_setpoint.position>=m_goal.position) || endCondition()) {
            //distance reached or end condition met. End the command
            //This class should be modified so that the profile can end on other conditions like
            //sensor value etc.
            m_arm.setServoAngle0(m_goal.position*m_dir);
            m_endFlag = true;
        }
        /* 
        D_position.setNumber(m_dir);
        D_setpoint_position.setNumber(m_setpoint.position*m_dir);
        D_goal_position.setNumber(m_goal.position*m_dir);
*/
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
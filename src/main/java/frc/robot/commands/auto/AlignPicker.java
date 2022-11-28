package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.trajectory.TrapezoidProfile;
//RobotContainer import
import frc.robot.RobotContainer;

//Subsystem imports
import frc.robot.subsystems.OmniDrive;
import frc.robot.subsystems.Vision;

public class AlignPicker extends CommandBase {
    //Grab the subsystem instance from RobotContainer
    private final static OmniDrive m_drive = RobotContainer.m_omnidrive;
    private final static Vision m_vision = RobotContainer.m_vision;

    private static double convertPxToMM = 0.1/50;
    private double dT = 0.02;
    private double time=0;
    private double targetXDistance;
    private TrapezoidProfile.Constraints m_constraints;
    private TrapezoidProfile.State m_goal;
    private TrapezoidProfile.State m_setpoint;
    private TrapezoidProfile m_profile;
    private boolean m_endFlag = false;
    private final double _startSpeed;
    public AlignPicker(int maxSpeed){
        targetXDistance = ((m_vision.getDettol(0) -160) * convertPxToMM);
        _startSpeed = 0;        
        m_constraints = new TrapezoidProfile.Constraints(maxSpeed, 0.3);
        m_goal = new TrapezoidProfile.State(targetXDistance, 0);
        
    }
     /**
     * Runs before execute
     */
    @Override
    public void initialize()
    {   
        targetXDistance = ((m_vision.getDettol(0) -160) * convertPxToMM);
        m_goal = new TrapezoidProfile.State(targetXDistance, 0);
        m_setpoint = new TrapezoidProfile.State(0, _startSpeed);
        m_profile = new TrapezoidProfile(m_constraints, m_goal, m_setpoint);
        m_endFlag = false;
    }
    /**
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
        targetXDistance = ((m_vision.getDettol(0) -160) * convertPxToMM);

        m_goal = new TrapezoidProfile.State(targetXDistance, 0);

        m_profile = new TrapezoidProfile(m_constraints, m_goal, m_setpoint);
        m_setpoint = m_profile.calculate(dT);

        m_drive.setRobotSpeedType(0, m_setpoint.velocity);

        if ( m_profile.isFinished(dT) || endCondition() ) {
            //distance reached or end condition met. End the command
            //This class should be modified so that the profile can end on other conditions like
            //sensor value etc.
            m_drive.setRobotSpeedType(0, m_goal.velocity);
            m_endFlag = true;
        }
 
    }
}

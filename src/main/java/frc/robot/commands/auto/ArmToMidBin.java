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
public class ArmToMidBin extends CommandBase{
    private final static Vision m_vision = RobotContainer.m_vision;
    private final static Arm m_arm = RobotContainer.m_arm;
    private final static Sensor m_sensor = RobotContainer.m_sensor;

    private double dT = 0.02;
    private boolean m_endFlag = false;
    private TrapezoidProfile.Constraints m_constraints;
    private TrapezoidProfile.State m_goal = new TrapezoidProfile.State();
    private TrapezoidProfile.State m_setpoint = new TrapezoidProfile.State();
    private TrapezoidProfile m_profile;
    private double xgoal, ygoal;
    private Translation2d tgt_pos, cur_pos, start_pos;
    
    private double _maxSpeed, tgt_dist, m_dx, m_dy;

    private double arm_offset_y = 0.125;
    private double arm_offset_z = 0.27;
    private double gripper_offset = 0.18;
   
    private double targetX, targetY, pickUpHeight = 0.2; 
    public ArmToMidBin(double maxSpeed){
        _maxSpeed = maxSpeed;
        
        m_constraints = new TrapezoidProfile.Constraints(_maxSpeed, 1);
    }
     /**
     * Runs before execute
     */
    @Override
    public void initialize() {
        
        start_pos = m_arm.getArmPos();
        targetX = Globals.frontIROffset+m_sensor.getFrontIRDistance()/100+Globals.yellowBinDimension[1]/2;
        targetY = pickUpHeight;
        tgt_pos = new Translation2d(targetX-Globals.arm_offset_y-Globals.camera_offset, targetY-Globals.arm_offset_z+Globals.gripper_offset);
        tgt_dist = start_pos.getDistance(tgt_pos);
        m_dx = tgt_pos.getX() - start_pos.getX();
        m_dy = tgt_pos.getY() - start_pos.getY();
        
        m_goal = new TrapezoidProfile.State(tgt_dist, 0);
        m_setpoint = new TrapezoidProfile.State(0,0);
        m_endFlag = false;
    }
     /**
     * Condition to end speed profile
     */
    public boolean endCondition()
    {
        return false;
    }
    @Override
    public void execute()
    {
         
        
        m_profile = new TrapezoidProfile(m_constraints, m_goal, m_setpoint);

        m_setpoint = m_profile.calculate(dT);
        cur_pos = start_pos.plus(new Translation2d((m_setpoint.position*m_dx/tgt_dist),(m_setpoint.position*m_dy/tgt_dist)));

        m_arm.setArmPos(cur_pos);
        
        if (m_profile.isFinished(dT) || endCondition()) {
            //distance reached End the command
            
            m_arm.setArmPos(tgt_pos);
            m_endFlag = true;
        }
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
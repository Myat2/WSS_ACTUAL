package frc.robot.commands.auto;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import frc.robot.Globals;
//RobotContainer import
import frc.robot.RobotContainer;
//Subsystem imports
import frc.robot.subsystems.Arm;


public class ArmPick extends MoveArm {
    private final static Arm m_arm = RobotContainer.m_arm;

    
    private double pickUpHeight = 0.00;
    private int m_type;
    public ArmPick(int type){
        super(new Translation2d(0,0), 0.2);
        m_type = type;
    }
     /**
     * Runs before execute
     */
    @Override
    public void initialize() {
        Translation2d pos;
        if (m_type==0) {
            double x = m_arm.getArmPos().getX() + Globals.camera_offset - (Globals.curItemY - Globals.imH/2) * Globals.convertPxToM;
            pos = new Translation2d(x, m_arm.getArmPos().getY());
        }
        else {
            double y = pickUpHeight - Globals.arm_offset_z+ Globals.gripper_offset;
            pos = new Translation2d(m_arm.getArmPos().getX(), y);   
        }
            
        super.tgt_pos = pos;
        super.initialize();
    }
}
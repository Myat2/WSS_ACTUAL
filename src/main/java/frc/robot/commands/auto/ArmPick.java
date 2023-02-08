package frc.robot.commands.auto;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import frc.robot.Globals;
//RobotContainer import
import frc.robot.RobotContainer;
//Subsystem imports
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Vision;


public class ArmPick extends MoveArm {
    private final static Vision m_vision = RobotContainer.m_vision;
    private final static Arm m_arm = RobotContainer.m_arm;

    private double pickUpHeight = 0.03;//default pick up height in m
    private double ratio = 0;
    private int m_type;
    /**
     * This command extends the arm towards the object to be picked up
     * @param type - 0 = X movement, 1 = Y movement(picking height), 2 = Y movement(just above object)
     */
    public ArmPick(int type){
        super((new Translation2d(0,0)), 0.2);
        m_type = type;
    }
     /**
     * Runs before execute
     */
    @Override
    public void initialize() {
        // xgoal = 0.335 - (getItemY(Globals.curItem) - 120) * Globals.convertPxToMM + 0.012;
        Translation2d pos;
        if (m_type==0) {
                if (Globals.curItemType==0)// for cokeU
                ratio = Globals.CokeRatio;
            else
                ratio = 1;
            double x = m_arm.getArmPos().getX() + Globals.camera_offset - (Globals.curItemY - Globals.imH/2) * Globals.convertPxToM*ratio;
            pos = new Translation2d(x, m_arm.getArmPos().getY());
        }
        else if (m_type==1){ // PICKING HEIGHT 
            if (Globals.curItemType==0){// for cokeU
                double y = (pickUpHeight + 0.04) - Globals.arm_offset_z+ Globals.gripper_offset;
                pos = new Translation2d(m_arm.getArmPos().getX(), y);   
            }
            else{
                double y = (pickUpHeight) - Globals.arm_offset_z+ Globals.gripper_offset;
                pos = new Translation2d(m_arm.getArmPos().getX(), y);
            }
        }
        else { // CLEARANCE HEIGHT FOR OPENING GRIPPER
            if (Globals.curItemType==0){// for cokeU
                double y = (pickUpHeight + 0.12) - Globals.arm_offset_z+ Globals.gripper_offset;
                pos = new Translation2d(m_arm.getArmPos().getX(), y);   
            }
            else{
                double y = (pickUpHeight + 0.06) - Globals.arm_offset_z+ Globals.gripper_offset;
                pos = new Translation2d(m_arm.getArmPos().getX(), y);
            }
        }
            
        super.tgt_pos = pos;
        super.initialize();
    }
    
}
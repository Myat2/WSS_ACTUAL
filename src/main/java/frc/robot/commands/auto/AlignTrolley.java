package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Globals;
import frc.robot.RobotContainer;

public class AlignTrolley extends MoveRobot{

    private int m_type;
    private double convertPxToM = 0.00095625;
    public AlignTrolley(int type){
        super(type,0,0,0,0.5);
        m_type = type;
    }
    @Override
    public void initialize(){
        if (m_type == 0){
            super.m_dist = ((RobotContainer.m_vision.getLine()[0] - Globals.imW/2 ) * convertPxToM)-Globals.camera_mount_offset_x;
        }
        else if (m_type == 1){
            super.m_dist = ((450 - RobotContainer.m_vision.getLine()[1] ) * convertPxToM);

        }
        
        super.initialize();
    }
    @Override
    public boolean endCondition(){
        if (m_type == 0){
            if (Math.abs(RobotContainer.m_vision.getLine()[0] - Globals.imW/2 ) <3) {
                return true;
            }
        }
        else if (m_type == 1){
            if (Math.abs(RobotContainer.m_vision.getLine()[1] - 450) <3) {
                return true;
            }
        }
        return false;
    }
}
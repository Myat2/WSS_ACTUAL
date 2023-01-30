package frc.robot.commands.auto;

import frc.robot.Globals;
import frc.robot.RobotContainer;

public class CheckRotation extends MoveRobot{
    private static String m_targetName;
    public CheckRotation(String targetName){
        super(2, 0, 0, 0, Math.PI/2);
        m_targetName = targetName;
    }
    @Override
    public void initialize(){

        if(Math.abs((int)Globals.curPose.getRotation().getDegrees()) != Math.abs((int)RobotContainer.m_points.getPoint(m_targetName).getRotation().getDegrees())){
            super.m_dist = RobotContainer.m_points.getPoint(m_targetName).getRotation().getRadians() - Globals.curPose.getRotation().getRadians();
        }

        super.initialize();
    }
}

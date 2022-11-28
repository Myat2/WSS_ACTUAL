package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Globals;
import frc.robot.RobotContainer;

public class Vision extends SubsystemBase{
    private final ShuffleboardTab tab = Shuffleboard.getTab("Vision");
    private final NetworkTableEntry D_cW = tab.add("cW", 0).getEntry();
    private final NetworkTableEntry D_targetX = tab.add("TargetX", 0).getEntry();
    private final NetworkTableEntry D_alignArm = tab.add("DistanceMove", 0).getEntry();
    private final NetworkTableEntry D_dettolX = tab.add("DettolX", 0).getEntry();
    private final static Arm m_arm = RobotContainer.m_arm;
    public Vision(){

        m_arm.setServoAngle3(300); // Look down
    }
    public double getLine(int xyw){
        double[] line = new double[3];

      line[0] = (SmartDashboard.getNumber("BlackLineX",0));
      line[1] = (SmartDashboard.getNumber("BlackLineY",0));
      line[2] = (SmartDashboard.getNumber("BlackLineW",0));
      return line[xyw];
    }
    public double getJagabee(int xy){
        double[] position = new double[2];

        position[0] = (SmartDashboard.getNumber("JagabeeX",0));
        position[1] = (SmartDashboard.getNumber("JagabeeY",0));
        return position[xy];
    }
    public double getDettol(int xy){
        double[] position = new double[2];

        position[0] = (SmartDashboard.getNumber("DettolX",0));
        position[1] = (SmartDashboard.getNumber("DettolY",0));
        return position[xy];
    }
    public void getOmega(){
        Globals.cW = getLine(2);
    }
    @Override
    public void periodic()
    {
        Globals.cW = getLine(2);
        
        D_cW.setNumber(Globals.cW);
        D_targetX.setNumber(getLine(0) - 345);
        D_alignArm.setNumber((getDettol(0)-160)*0.1/100);
        D_dettolX.setNumber(getDettol(0));

    }
}

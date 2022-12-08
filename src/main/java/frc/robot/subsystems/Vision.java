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
    private final String[] items = {
        "Dettol",
        "Jagabee",
        "Coke"
    };
    private final NetworkTableEntry D_currentItem = tab.add("CurrentItem", 0).getEntry();
    private final NetworkTableEntry D_AddedArmX = tab.add("AddedArmX", 0).getEntry();
    private final NetworkTableEntry D_AddedRobotX = tab.add("AddedRobotX", 0).getEntry();
    private final NetworkTableEntry D_useTF = tab.add("useTF", 0).getEntry();
    
    private final static Arm m_arm = RobotContainer.m_arm;
    public Vision(){

        m_arm.setServoAngle3(290); // Look down
    }
    public double getLine(int xyw){
        double[] line = new double[3];

      line[0] = (SmartDashboard.getNumber("Bl_X",0));
      line[1] = (SmartDashboard.getNumber("Bl_Y",0));
      line[2] = (SmartDashboard.getNumber("Bl_W",0));
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
    public double getCoke(int xy){
        double[] position = new double[2];

        position[0] = (SmartDashboard.getNumber("CokeX",0));
        position[1] = (SmartDashboard.getNumber("CokeY",0));
        return position[xy];
    }
    public void getOmega(){
        Globals.cW = getLine(2);
    }
    
    
    public void setUseTF(){
        SmartDashboard.putBoolean("UseTF", Globals.useTF);
    }
    public double getItemX(int item) {
        /*
         * 1 - Dettol
         * 2 - Jagabee
         * 3 - Coke
         */
        //gets item type to pick and returns item coordinate
        double[] itemCo = new double[3];

        itemCo[0] = getDettol(0);
        itemCo[1] = getJagabee(0);
        itemCo[2] = getCoke(0);
        
        return itemCo[item];
    }
    public double getItemY(int item) {
        /*
         * 0 - Dettol
         * 1 - Jagabee
         * 2 - Coke
         */
        //gets item type to pick and returns item coordinate
        double[] itemCo = new double[3];

        itemCo[0] = getDettol(1);
        itemCo[1] = getJagabee(1);
        itemCo[2] = getCoke(1);
        
        return itemCo[item];
    }
    
    @Override
    public void periodic()
    {

        D_currentItem.setString(items[Globals.curItem]);
        D_AddedRobotX.setNumber(((getItemX(Globals.curItem) -160) * Globals.convertPxToMM));
        D_AddedArmX.setNumber(-(getItemY(Globals.curItem) - 120) * Globals.convertPxToMM + 0.012);
        D_useTF.setBoolean(Globals.useTF);
    }
}

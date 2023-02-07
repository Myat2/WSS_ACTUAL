package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Globals;
//RobotContainer import
import frc.robot.RobotContainer;

//Subsystem imports
import frc.robot.subsystems.OmniDrive;
import frc.robot.subsystems.Vision;

public class AlignRobot extends CommandBase{
    //Grab the subsystem instance from RobotContainer
    private final static OmniDrive m_drive = RobotContainer.m_omnidrive;
    private final static Vision m_vision = RobotContainer.m_vision;
    private double targetW, targetX, targetY;
    private double speedX, speedY, speedW;
    private double centerX;
    private double centerY; 
   
    private boolean m_endFlag = false;
    private boolean useW = false;
    
    /**
     * This command aligns the robot to desired black objects (T Junction or Trolley)
     * <p>
     * 
     * @param x - target X position
     * @param y - target Y position
     */
    

    public AlignRobot(String object){
        useW = object == "trolley"? false:true;
        centerX = 100;
        centerY = 110;
        double[] line = m_vision.getLine();
        // targetW = -m_vision.getLine(2);
        targetX = (line[0] - centerX);
        targetY = -(line[1] - centerY);
        
    }
    
    public AlignRobot(){
        // Width = 200
        centerX = 105;
        centerY= 110; 
        useW = true;
        double[] line = m_vision.getLine();
        targetW = -line[2];
        targetX = (line[0] - centerX);
        targetY = -(line[1] - centerY);
        
    }
    /**
     * Runs before execute
     */
    @Override
    public void initialize()
    {   
        double[] line = m_vision.getLine();
        targetW = -line[2];
        targetX = (line[0] - centerX);
        targetY = -(line[1] - centerY);
        m_endFlag = false;
        Globals.useTF = false;
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
        double[] line = m_vision.getLine();
        targetW = -line[2];
        targetX = (line[0] - centerX);
        targetY = -(line[1] - centerY);

        // When width = 200
        speedX = 0.002 * targetX;
        speedY = 0.002 * targetY;
        speedW = useW? 0.5 * targetW: 0;
        m_drive.setRobotSpeedType(0, speedX);
        m_drive.setRobotSpeedType(1, speedY); 
        m_drive.setRobotSpeedType(2, speedW);
        
        if (Math.abs(line[0] - centerX) <2 && - Math.abs(line[1] - centerY) < 2 && Math.abs(line[2]) < 0.05){
            m_endFlag = true;
            m_drive.setRobotSpeedType(0, 0);
            m_drive.setRobotSpeedType(1, 0); 
            m_drive.setRobotSpeedType(2, 0);
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

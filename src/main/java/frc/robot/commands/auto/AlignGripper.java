package frc.robot.commands.auto;
import frc.robot.Globals;


public class AlignGripper extends MoveRobot {
    private boolean m_endFlag = false;
    private int camera_offset_pixels = 25;
    private final double _startSpeed;
    public AlignGripper(){
        super(0, 0, 0, 0, 0.4 );
        _startSpeed= 0;  
    }
     /**
     * Runs before execute
     */
    @Override
    public void initialize()
    {   
        super.m_dist = ((Globals.curItemX - Globals.imW/2 - camera_offset_pixels) * Globals.convertPxToM);
       
        super.initialize();
    }
}
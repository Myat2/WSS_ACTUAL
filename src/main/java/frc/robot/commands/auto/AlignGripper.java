package frc.robot.commands.auto;
import frc.robot.Globals;


public class AlignGripper extends MoveRobot {
    private boolean m_endFlag = false;
    private int camera_offset_pixels = 25;
    private final double _startSpeed;
    private double ratio = 0;
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
        if(Globals.curItemType==0)// for standing coke
            ratio = Globals.CokeRatio;
        else
            ratio = 1;
        super.m_dist = ((Globals.curItemX - Globals.imW/2 ) * Globals.convertPxToM)*ratio-Globals.camera_mount_offset_x;
        super.initialize();
    }
}
package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ProxyScheduleCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Globals;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.OmniDrive;
import frc.robot.subsystems.Sensor;
import frc.robot.subsystems.Vision;
import frc.robot.Astar.Layout;

/**
 * DriveMotor class
 * <p>
 * This class creates the inline auto command to drive the motor
 */
public class AutoMainCmd extends SequentialCommandGroup
{   
    int count = 0;
    double temp;


	public AutoMainCmd() 
    {
        
        
        super
        (

         new InstantCommand(()-> Globals.cvMode=-1),
         new MovetoB(Layout.workOrderPos),
         new ReadWOB(),
         new MovetoB(Layout.startPos)
        //  new SortItems()
    //     new MovetoB(Layout.startPos_Test),
    //    new TestPicking()
                    
            
            
        );
            
    }
    
}
package frc.robot.commands.auto;

import java.util.Map;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SelectCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotContainer;
// import the commands
import frc.robot.commands.auto.MoveRobotSense;

/**
 * DriveMotor class
 * <p>
 * This class creates the inline auto command to drive the motor
 */
public class SelectCP extends SequentialCommandGroup
{
    private enum CommandSelector {
        ONE, TWO
    }

    static public CommandSelector selectCmd12() {
        if (RobotContainer.m_sensor.getFrontIRDistance()<=20)
            return CommandSelector.ONE;
        else 
            return CommandSelector.TWO;
      
    }


    
    // use IR to select
    static public boolean selectCmd12_IR() {
        return RobotContainer.m_sensor.getFrontIRDistance()>30?true:false;
    }
	public SelectCP()
    {

        super(

            //Select one of two commands
            //new ConditionalCommand( new MoveLeft(), new MoveRight(),  MoveTest::selectCmdA ) 
            
            //Select one of many commands
            //Selection command in selectCmd123
            new SelectCommand(
                Map.ofEntries(
                    Map.entry(CommandSelector.ONE, new MoveRight()),
                    Map.entry(CommandSelector.TWO, new MoveLeft())
                    //Map.entry(CommandSelector.THREE, new MoveRobot(1, 0.5, 0, 0.0, 0.5)) 
                    ),
                MoveTest::selectCmd12
            )
             
 
        );
    }
}

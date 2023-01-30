package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class CP1 extends SequentialCommandGroup{
    public CP1(){
        super(
            new ViewPickUpBin(),
            new LoopCommands(new ProcessSeq())
        );
    }
}

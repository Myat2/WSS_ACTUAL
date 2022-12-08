package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.RobotContainer;
import frc.robot.commands.auto.MoveRobot;
import frc.robot.commands.auto.MoveRobotSense;

public class TopToYellow extends SequentialCommandGroup {
    public TopToYellow() {
        super(
                new MoveRobot(1, -0.63, 0, 0, 5),
                new MoveRobot(0, 2, 0, 0, 5),
                new MoveRobotSense(0, 10, 0, 0, 0.25, () -> RobotContainer.m_sensor.getCobraTotal() > 3500),
                new MoveRobotSense(1, 1, 0, 0, 5, () -> RobotContainer.m_sensor.getFrontIRDistance() < 10),
                new Pick());
    }
}
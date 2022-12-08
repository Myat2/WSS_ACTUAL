package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.auto.MoveRobot;

/**
 * SimpleDrive class
 * <p>
 * This class drives a motor
 */
public class ArmPickXSense extends ArmPickX {
    private final end_func f_ptr;

    interface end_func {
        public boolean endCondition();
    }

    /**
     * This command moves the robot a certain distance following a trapezoidal speed
     * profile
     * or terminate early when condition is met.
     * <p>
     * 
     * @param maxSpeed   - max speed of robot
     * @param f          - function that defines early end condition
     */
    public ArmPickXSense(double maxSpeed, end_func f) {
        super(maxSpeed);
        f_ptr = f;
    }

    

    @Override
    public boolean endCondition() {
        return f_ptr.endCondition();
    }
}
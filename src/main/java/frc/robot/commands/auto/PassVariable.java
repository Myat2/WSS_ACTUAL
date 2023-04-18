/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.CommandBase;


import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;


public class PassVariable extends CommandBase {

  private NetworkTable table;
  private NetworkTableEntry myVariableEntry;
  private double myVariable;
  
 /**
     * Passes a variable from wssvision to VSCode using network table
     * this is done to have an endflag so after it finishing scanning myVariable will be changed
     * from 0 to 1
     */
   
  public PassVariable() {
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
       // Connect to the NetworkTables server running on the VNC Viewer
       NetworkTableInstance inst = NetworkTableInstance.getDefault();
       //inst.startClient("10.69.42.2"); ;

       // Get the "MyTable" table from the NetworkTables server
       table = inst.getTable("MyTable");

       // Get the "myVariable" entry from the table
       myVariableEntry = table.getEntry("myVariable");

       // Initialize the variable to a default value
       myVariable = 0.0;

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
   
       myVariable = myVariableEntry.getDouble(0.0);
       System.out.println("myVariable = " + myVariable);
       
   
  }
  

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (myVariable==1.0)
    {
      return true;
    }
    return false;
  }
}

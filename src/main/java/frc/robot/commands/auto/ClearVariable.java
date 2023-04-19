/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.CommandBase;

import javax.lang.model.util.ElementScanner6;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Timer;


public class ClearVariable extends CommandBase {

  private NetworkTable table;
  private NetworkTableEntry myVariableEntry;
  private double myVariable;
  private Timer timer;

  
 /**
     * checks the value of MyVariable
     */
   
  public ClearVariable() {
    
    timer = new Timer();

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

       timer.start();

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
       


       
        myVariable = myVariableEntry.getDouble(0.0);
        System.out.println("myVariable +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("What is myVariable = " + myVariable);
        System.out.println("myVariable########################################################");
       
       
   
  }
  

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    
  }


  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    
       return true;
  }
}

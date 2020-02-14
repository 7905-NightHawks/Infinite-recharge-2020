/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.Constants.OIConstants;

public class DriveManually extends CommandBase {
  /**
   * Creates a new DriveManually.
   */
  public DriveManually() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.DriveSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    XboxController m_driverController = new XboxController(OIConstants.kDriverControllerPort);
    
    new JoystickButton(m_driverController, Button.kBumperRight.value)
        .whenPressed(() -> RobotContainer.m_robotDrive.setMaxOutput(0.5))
        .whenReleased(() -> RobotContainer.m_robotDrive.setMaxOutput(1));

        double move = -m_driverController.getRawAxis(OIConstants.kDriverControllerPortY);
        double turn = m_driverController.getRawAxis(OIConstants.kDriverControllerPortX); 


        if ((Math.abs(move) < OIConstants.Deadzone_Value)) {
          move = 0; 
        }
        if ((Math.abs(turn) < OIConstants.Deadzone_Value)) {
          turn = 0; 
        }


        Robot.DriveSubsystem.manualDrive(move, turn,  Math.abs(move) < .20);



        
        


  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}

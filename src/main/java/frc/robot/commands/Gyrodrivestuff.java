/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.RobotContainer;
import frc.robot.Constants.DriveConstants;
import frc.robot.Constants.OIConstants;

public class Gyrodrivestuff extends CommandBase {
  /**
   * Creates a new Gyrodrivestuff.
   */
  public Gyrodrivestuff() {
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    XboxController m_driverController = new XboxController(OIConstants.kDriverControllerPort);
    //Stabilize robot to drive straight with gyro when left bumper is held
    new JoystickButton(m_driverController, Button.kBumperLeft.value).whenHeld(new PIDCommand(
      new PIDController(DriveConstants.kStabilizationP, DriveConstants.kStabilizationI,
                          DriveConstants.kStabilizationD),
        // Close the loop on the turn rate
        RobotContainer.m_robotDrive::getTurnRate,
        // Setpoint is 0
        0,
        // Pipe the output to the turning controls 
        output -> RobotContainer.m_robotDrive.manualDrive(-m_driverController.getRawAxis(OIConstants.kDriverControllerPortY), output, true),
        // Require the robot drive
        RobotContainer.m_robotDrive));

    // Turn to 90 degrees when the 'B' button is pressed
    new JoystickButton(m_driverController, Button.kB.value)
        .whenPressed(new TurnToAngle(90, RobotContainer.m_robotDrive));

    // Turn to -90 degrees with a profile when the 'X' button is pressed
    new JoystickButton(m_driverController, Button.kX.value)
        .whenPressed(new TurnToAngleProfiled(-90, RobotContainer.m_robotDrive));
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

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.DriveConstants;
import frc.robot.Constants.OIConstants;

import frc.robot.commands.TurnToAngle;
import frc.robot.commands.TurnToAngleProfiled;
import frc.robot.subsystems.DriveSubsystem;


import static edu.wpi.first.wpilibj.XboxController.Button;



/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  
  
  private final DriveSubsystem m_robotDrive = new DriveSubsystem();

  // The driver's controller
  XboxController m_driverController = new XboxController(OIConstants.kDriverControllerPort);



  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
    

    // Configure default commands
    
    //m_robotDrive.setDefaultCommand(
      //  new RunCommand(() -> m_robotDrive
      //  .curvatureDrive(m_driverController.getY(GenericHID.Hand.kLeft),
      //  m_driverController.getX(GenericHID.Hand.kRight), m_driverController.getY(GenericHID.Hand.kLeft) < .05),
        //    m_robotDrive));
   // ;
   m_robotDrive.setDefaultCommand(
        new RunCommand(() -> m_robotDrive
        .curvatureDrive(m_driverController.getRawAxis(OIConstants.kDriverControllerPortY),
       m_driverController.getRawAxis(OIConstants.kDriverControllerPortX), m_driverController.getRawAxis(OIConstants.kDriverControllerPortY) < .05),
            m_robotDrive));
    ;
            
  }
  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    new JoystickButton(m_driverController, Button.kBumperRight.value)
        .whenPressed(() -> m_robotDrive.setMaxOutput(0.5))
        .whenReleased(() -> m_robotDrive.setMaxOutput(1));

    // Stabilize robot to drive straight with gyro when left bumper is held
    new JoystickButton(m_driverController, Button.kBumperLeft.value).whenHeld(new PIDCommand(
      new PIDController(DriveConstants.kStabilizationP, DriveConstants.kStabilizationI,
                          DriveConstants.kStabilizationD),
        // Close the loop on the turn rate
        m_robotDrive::getTurnRate,
        // Setpoint is 0
        0,
        // Pipe the output to the turning controls
        output -> m_robotDrive.curvatureDrive(m_driverController.getY(GenericHID.Hand.kLeft), output, true),
        // Require the robot drive
        m_robotDrive));

    // Turn to 90 degrees when the 'X' button is pressed, with a 5 second timeout
    new JoystickButton(m_driverController, Button.kB.value)
        .whenPressed(new TurnToAngle(90, m_robotDrive));

    // Turn to -90 degrees with a profile when the 'A' button is pressed, with a 5 second timeout
    new JoystickButton(m_driverController, Button.kX.value)
        .whenPressed(new TurnToAngleProfiled(-90, m_robotDrive));
  }
  


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return new InstantCommand();
  }
}

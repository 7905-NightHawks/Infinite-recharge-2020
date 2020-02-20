/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.OIConstants;
import frc.robot.commands.TurntoAngleNOPID;
import frc.robot.subsystems.DriveSubsystem;






/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  
  

  public static DriveSubsystem m_robotDrive = new DriveSubsystem();

  // The driver's controller
  XboxController m_driverController = new XboxController(OIConstants.kDriverControllerPort);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    XBoxTrigger RightTrigger;

    RightTrigger = new XBoxTrigger(m_driverController, 3);



    // Configure default commands

    m_robotDrive.setDefaultCommand(new RunCommand(
        () -> m_robotDrive.curvatureDrive(m_robotDrive.getSpeed(), m_driverController.getX(GenericHID.Hand.kRight),
      Math.abs(m_robotDrive.getSpeed()) < 0.15 ||  RightTrigger.get()), // quick turn if below set speed or if trigger is pressed
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
    new JoystickButton(m_driverController, Button.kBumperLeft.value)
        .whenPressed(() -> m_robotDrive.setMaxOutput(0.5))
        .whenReleased(() -> m_robotDrive.setMaxOutput(0.7));


    new JoystickButton(m_driverController, Button.kBumperRight.value)
        .whenPressed(() -> m_robotDrive.setMaxOutput(1.0))
        .whenReleased(() -> m_robotDrive.setMaxOutput(0.7));
    
  

    // Turn to 90 degrees when the 'B' button is pressed
    new JoystickButton(m_driverController, Button.kA.value)
    .whenPressed(new TurntoAngleNOPID(.3, 90));

    // Turn to -90 degrees with a profile when the 'X' button is pressed
    new JoystickButton(m_driverController, Button.kA.value)
    .whenPressed(new TurntoAngleNOPID(.3, 270));

    new JoystickButton(m_driverController, Button.kA.value)
    .whenPressed(new TurntoAngleNOPID(.3, 180));
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

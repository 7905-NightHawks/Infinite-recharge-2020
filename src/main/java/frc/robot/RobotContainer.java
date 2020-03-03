/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.AutoConstants;
import frc.robot.Constants.ClimbConstants;
import frc.robot.Constants.IntakeConstants;
import frc.robot.Constants.OIConstants;
import frc.robot.Constants.RampConstants;
import frc.robot.commands.Auto_ForwardScore;
import frc.robot.commands.DriveForwardXForY;
import frc.robot.commands.TurntoAngleNOPID;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Rampsubsystem;
import frc.robot.subsystems.Winch;






/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  public static Elevator Elevator = new Elevator();
  public static Intake Intake = new Intake();
  public static Rampsubsystem Ramp = new Rampsubsystem();
  public static Winch Winch = new Winch();

  private final Command OffInitiationLine = new DriveForwardXForY(AutoConstants.simpleDriveForwardPower, AutoConstants.simpleDriveForwardtime);
  private final Command Auto_ForwardScore = new Auto_ForwardScore(m_robotDrive, Ramp);
 
  SendableChooser<Command> m_chooser = new SendableChooser<>();
 
  public static DriveSubsystem m_robotDrive = new DriveSubsystem();

  // The Driver's controller

  XboxController m_driverController = new XboxController(OIConstants.kDriverControllerPort);

  // The Operator's controller

  Joystick m_operatorController = new Joystick(OIConstants.kOperatorControllerPort);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    XBoxTrigger RightTrigger;

    RightTrigger = new XBoxTrigger(m_driverController, OIConstants.TriggerAxis);



    // Configure default commands

    m_robotDrive.setDefaultCommand(new RunCommand(
        () -> m_robotDrive.curvatureDrive(m_robotDrive.getSpeed(), m_driverController.getX(GenericHID.Hand.kRight),
      Math.abs(m_robotDrive.getSpeed()) < 0.15 ||  RightTrigger.get()), // quick turn if below set speed or if trigger is held
        m_robotDrive));
   ;    
   
   if (Math.abs(m_robotDrive.getSpeed()) < 0.07 && Math.abs(m_robotDrive.getCurvature()) < 0.07){
    m_robotDrive.setm_leftMotors(0);
    m_robotDrive.setm_rightMotors(0);
   }


   // add auto options to chooser
   m_chooser.setDefaultOption("OffInitiationLine", OffInitiationLine);
   m_chooser.addOption("ForwardScore", Auto_ForwardScore);

    //put the chooser on the dashboard
   Shuffleboard.getTab("Autonomous").add(m_chooser);

  }
  
  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {

    // Driver
    
    new JoystickButton(m_driverController, Button.kBumperLeft.value)
        .whenPressed(() -> m_robotDrive.setMaxOutput(0.5))
        .whenReleased(() -> m_robotDrive.setMaxOutput(1));
        
    // Turn to 90 degrees when the 'B' button is pressed
    new JoystickButton(m_driverController, Button.kA.value)
    .whenPressed(new TurntoAngleNOPID(.3, 90));

    // Turn to -90 degrees when the 'X' button is pressed
    new JoystickButton(m_driverController, Button.kA.value)
    .whenPressed(new TurntoAngleNOPID(.3, -90));

    // Turn to 180 degrees when the 'A' button is pressed
    new JoystickButton(m_driverController, Button.kA.value)
    .whenPressed(new TurntoAngleNOPID(.3, 180));


    // Operator
    
    // Power ramp forward when 'rightbumper' is pressed 
    new JoystickButton(m_operatorController, OIConstants.kOperatorControllerRightBumper)
    .whenPressed(() -> Ramp.setOutput(RampConstants.RampSpeedUp))
    .whenReleased(() -> Ramp.setOutput(0));

    // Power Intake forward when 'Leftbumper' is pressed
    new JoystickButton(m_operatorController, OIConstants.kOperatorControllerLeftBumper)
    .whenPressed(() -> Intake.setOutput(IntakeConstants.IntakeSpeed))
    .whenReleased(() -> Intake.setOutput(0));

    // Winch up when 'A' button is pressed
    new JoystickButton(m_operatorController, OIConstants.kOperatorControllerA)
    .whenPressed(() -> Winch.setOutput(ClimbConstants.WinchSpeed))
    .whenReleased(() -> Winch.setOutput(0));

    //Elevator up when 'Y' button is pressed
    new JoystickButton(m_operatorController, OIConstants.kOperatorControllerY)
    .whenPressed(() -> Elevator.setOutput(ClimbConstants.ElevatorSpeed))
    .whenReleased(() -> Elevator.setOutput(0));









  }



  


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_chooser.getSelected();
  }
}

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Encoder;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;

public class DriveSubsystem extends SubsystemBase {
  private final SpeedControllerGroup m_leftMotors = new SpeedControllerGroup(
    new WPI_VictorSPX(DriveConstants.kLeftMotor1Port),
                               new WPI_VictorSPX(DriveConstants.kLeftMotor2Port));

  // The motors on the right side of the drive.
  private final SpeedControllerGroup m_rightMotors =
      new SpeedControllerGroup(new WPI_VictorSPX(DriveConstants.kRightMotor1Port),
                               new WPI_VictorSPX(DriveConstants.kRightMotor2Port));

  // The robot's drive
  private final DifferentialDrive m_drive = new DifferentialDrive(m_leftMotors, m_rightMotors);

  // The left-side drive encoder
  private final Encoder m_leftEncoder =
      new Encoder(DriveConstants.kLeftEncoderPorts[0], DriveConstants.kLeftEncoderPorts[1],
                  DriveConstants.kLeftEncoderReversed);

  // The right-side drive encoder
  private final Encoder m_rightEncoder =
      new Encoder(DriveConstants.kRightEncoderPorts[0], DriveConstants.kRightEncoderPorts[1],
                  DriveConstants.kRightEncoderReversed);

  // The gyro sensor
  private final AHRS m_gyro = new AHRS();

  /**
   * Creates a new DriveSubsystem.
   */
  public DriveSubsystem() {
     // Sets the distance per pulse for the encoders
     m_leftEncoder.setDistancePerPulse(DriveConstants.kEncoderDistancePerPulse);
     m_rightEncoder.setDistancePerPulse(DriveConstants.kEncoderDistancePerPulse);
   }
   public void curvatureDrive(double xSpeed, double zRotation, boolean isQuickTurn) {
    m_drive.curvatureDrive(xSpeed, zRotation, isQuickTurn);
   }
  
   
   /**
    * Resets the drive encoders to currently read a position of 0.
    */
   public void resetEncoders() {
     m_leftEncoder.reset();
     m_rightEncoder.reset();
   }
 
   /**
    * Gets the average distance of the two encoders.
    *
    * @return the average of the two encoder readings
    */
   public double getAverageEncoderDistance() {
     return (m_leftEncoder.getDistance() + m_rightEncoder.getDistance()) / 2.0;
   }
 
   /**
    * Gets the left drive encoder.
    *
    * @return the left drive encoder
    */
   public Encoder getLeftEncoder() {
     return m_leftEncoder;
   }
 
   /**
    * Gets the right drive encoder.
    *
    * @return the right drive encoder
    */
   public Encoder getRightEncoder() {
     return m_rightEncoder;
   }
 
   /**
    * Sets the max output of the drive.  Useful for scaling the drive to drive more slowly.
    *
    * @param maxOutput the maximum output to which the drive will be constrained
    */
   public void setMaxOutput(double maxOutput) {
     m_drive.setMaxOutput(maxOutput);
   }
 
   /**
    * Zeroes the heading of the robot.
    */
   public void zeroHeading() {
     m_gyro.reset();
   }
 
   /**
    * Returns the heading of the robot.
    *
    * @return the robot's heading in degrees, from 180 to 180
    */
   public double getHeading() {
     return Math.IEEEremainder(m_gyro.getAngle(), 360) * (DriveConstants.kGyroReversed ? -1.0 : 1.0);
   }
 
   /**
    * Returns the turn rate of the robot.
    *
    * @return The turn rate of the robot, in degrees per second
    */
   public double getTurnRate() {
     return m_gyro.getRate() * (DriveConstants.kGyroReversed ? -1.0 : 1.0);
   }
 

  

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.GenericHID;
//import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;
import frc.robot.Constants.OIConstants;


public class DriveSubsystem extends SubsystemBase {
  private final SpeedControllerGroup m_leftMotors = 
  new SpeedControllerGroup(new WPI_VictorSPX(DriveConstants.kLeftMotor1Port),
                           new WPI_VictorSPX(DriveConstants.kLeftMotor2Port));

  // The motors on the right side of the drive.
  private final SpeedControllerGroup m_rightMotors =
      new SpeedControllerGroup(new WPI_VictorSPX(DriveConstants.kRightMotor1Port),
                               new WPI_VictorSPX(DriveConstants.kRightMotor2Port));

  // The robot's drive
  private final DifferentialDrive m_drive = new DifferentialDrive(m_leftMotors, m_rightMotors);



  // The gyro sensor
   
  private final AHRS m_gyro = new AHRS(SPI.Port.kMXP);

  /**
   * Creates a new DriveSubsystem.
   */
  public DriveSubsystem() {
     
   }
   
   
   public void curvatureDrive(double move, double turn, boolean isQuickTurn) {

    m_drive.curvatureDrive( move, turn, isQuickTurn );

   
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

   XboxController m_driverController = new XboxController(OIConstants.kDriverControllerPort);

   public double getSpeed(){
     return -m_driverController.getY(GenericHID.Hand.kLeft);
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

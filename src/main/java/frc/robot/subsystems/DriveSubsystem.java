/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DriverStation;
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
  private AHRS ahrs;
   
  

  /**
   * Creates a new DriveSubsystem.
   */
  public DriveSubsystem() {
     
   }
   
   
   public void curvatureDrive(double move, double turn, boolean isQuickTurn) {

    m_drive.curvatureDrive( move, turn, isQuickTurn );
   }
   public void setDeadband​(double deadband){
   deadband = 0.1;
   }
   
   
   {


    try {
      /* Communicate w/navX-MXP via the MXP SPI Bus. */
      /*
       * Alternatively: I2C.Port.kMXP, SerialPort.Port.kMXP or SerialPort.Port.kUSB
       */
      /*
       * See http://navx-mxp.kauailabs.com/guidance/selecting-an-interface/ for
       * details.
       */
      ahrs = new AHRS(SPI.Port.kMXP);
    } catch (RuntimeException ex) {
      DriverStation.reportError("Error instantiating navX-MXP: " + ex.getMessage(), true);
    }

    ahrs.reset();
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
    ahrs.reset();
   }
 
   /**
    * Returns the heading of the robot.
    *
    * @return the robot's heading in degrees, from 180 to 180
    */
   public double getHeading() {
     return Math.IEEEremainder(ahrs.getAngle(), 360) * (DriveConstants.kGyroReversed ? -1.0 : 1.0);
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
     return ahrs.getRate() * (DriveConstants.kGyroReversed ? -1.0 : 1.0);
   }


   public void setAHRSAdjustment(double adj) {
		ahrs.setAngleAdjustment(adj);
  }
  
  public double getAHRSGyroAngle() {
		return ahrs.getAngle();
	}

  

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}

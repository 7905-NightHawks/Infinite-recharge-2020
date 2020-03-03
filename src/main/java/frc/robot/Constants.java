/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {


    //Drivebase

    public static final class DriveConstants {
        public static final int kLeftMotor1Port = 0;
        public static final int kLeftMotor2Port = 2;
        public static final int kRightMotor1Port = 3;
        public static final int kRightMotor2Port = 12;
  
        public static final boolean kGyroReversed = false;
    
        public static final double kStabilizationP = .03;
        public static final double kStabilizationI = 0;
        public static final double kStabilizationD = 0;
        public static final double kTurnFriction = 0.2;
    
        public static final double kTurnP = .03;
        public static final double kTurnI = 0;
        public static final double kTurnD = 0;
    
        public static final double kMaxTurnRateDegPerS = 100;
        public static final double kMaxTurnAccelerationDegPerSSquared = 300;
    
        public static final double kTurnToleranceDeg = 2.0f;
        public static final double kTurnRateToleranceDegPerS = 10; // degrees per second
      }
    
      public static final class OIConstants {
        public static final int kDriverControllerPort = 1;
        public static final int TriggerAxis = 3;
        public static final int kDriverControllerPortY = 1;
        public static final int kDriverControllerPortX = 4;
        public static final double kDeadzone_Value = 0.1;
        public static final int kOperatorControllerPort = 0;
        public static final int kOperatorControllerRightBumper = 6;
        public static final int kOperatorControllerLeftBumper = 5;
        public static final int kOperatorControllerY = 4;
        public static final int kOperatorControllerX = 3;
        public static final int kOperatorControllerB = 2;
        public static final int kOperatorControllerA = 1;
        public static final int kOperatorControllerStop = 7;
        public static final int kOperatorControllerStart = 8;
        public static final int kOperatorControllerRightTrigger = 3;
        public static final int kOperatorControllerLeftTrigger = 2;
      
      }
      public static final class ClimbConstants {
        public static final int ElevatorMotor = 13;
        public static final int WinchMotor = 6;
        public static final double ElevatorSpeed = 0.5;
        public static final double WinchSpeed = 0.5;
      
      }

      public static final class IntakeConstants {
        public static final int IntakeMotor = 14;
        public static final double IntakeSpeed = 0.6;

      }
      public static final class RampConstants {
        public static final int TopRampMotor = 15;
        public static final int BottomRampMotor = 1;
        public static final double RampSpeedUp = 0.9;

      }
    public static final class AutoConstants {
      public static final double simpleDriveForwardPower = 0.7;
      public static final double simpleDriveForwardtime = 3000;
    }


    }

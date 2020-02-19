/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class TurntoAngleNOPID extends CommandBase {
  /**
   * Creates a new TurntoAngleNOPID.
   */
  
    private double goalAngle = 0.0;
    private boolean isDone = false;
    private double speed;
    private double tolerance = 3;
    // private double currentAngle;
  
    public TurntoAngleNOPID(double speed, double givenAngle) {
      // Use requires() here to declare subsystem dependencies
      // eg. requires(chassis);
      addRequirements(Robot.DriveSubsystem);
      goalAngle = givenAngle;
      this.speed = speed;
      isDone = false;
    }
  

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    isDone = false;

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double currentAngle = Robot.DriveSubsystem.getAHRSGyroAngle();
    // SmartDashboard.putNumber("Gyro: ", currentAngle);
    if (Math.abs(goalAngle - currentAngle) < tolerance) { // if within tolerance
      Robot.DriveSubsystem.curvatureDrive(0, 0, true);
      isDone = true;
    } else if (currentAngle < goalAngle) { // If left of target angle
      Robot.DriveSubsystem.curvatureDrive(0, speed, true); // turn clockwise
    } else if (currentAngle > goalAngle) { // If right of target angle
      Robot.DriveSubsystem.curvatureDrive(0, -speed, true); // turn counterclockwise
    }
  }



  

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Robot.DriveSubsystem.curvatureDrive(0, 0, true);
    isDone = true;
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return isDone;
  }
}

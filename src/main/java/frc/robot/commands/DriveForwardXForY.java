/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class DriveForwardXForY extends CommandBase {

  protected double power;
    protected double time;
    protected long endTime;

  public DriveForwardXForY(double power, double timeInMillis) {
    addRequirements(RobotContainer.m_robotDrive);
    this.power = power;
    this.time = timeInMillis;
  }

  // Called when the command is initially scheduled.
  

@Override
  public void initialize() {
    long startTime = System.currentTimeMillis();
        endTime = (long) (startTime + this.time);




        
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    RobotContainer.m_robotDrive.curvatureDrive(power, 0, false);
  }


  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.m_robotDrive.curvatureDrive(0, 0, false);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return System.currentTimeMillis() >= endTime;
  }
}

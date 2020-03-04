/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.Constants.AutoConstants;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Rampsubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class SlowMoveIntakeAndRampIn extends ParallelCommandGroup {
  /**
   * Creates a new SlowMoveIntakeAndRampIn.
   */
  public SlowMoveIntakeAndRampIn(DriveSubsystem Drive, Rampsubsystem Ramp, Intake Intake) {

    addCommands(

    new DriveForwardXForY(AutoConstants.GetCellPower, AutoConstants.GetCellTime),

    new IntakeIn(AutoConstants.IntakeTimeGetCell),

    new RampPowerForward(AutoConstants.RampTimeGetCell));



  }
}

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotContainer;
import frc.robot.Constants.AutoConstants;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.Rampsubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class Auto_TrenchandScore extends SequentialCommandGroup {
  /**
   * Creates a new Auto_TrenchandScore.
   */
  public Auto_TrenchandScore(DriveSubsystem Drive, Rampsubsystem Ramp) {
    
    addCommands(

    new DriveForwardXForY(AutoConstants.InitlinetofirstcellPower, AutoConstants.InitLineToFirstCellTime),

    new SlowMoveIntakeAndRampIn(RobotContainer.m_robotDrive, RobotContainer.Ramp, RobotContainer.Intake),

    new DriveForwardXForY(AutoConstants.FirstCelltoSecondCellPower, AutoConstants.FirstCellToSecondCellTime),

    new SlowMoveIntakeAndRampIn(RobotContainer.m_robotDrive, RobotContainer.Ramp, RobotContainer.Intake),

    new DriveForwardXForY(AutoConstants.SecondCelltoTurnPointPower, AutoConstants.SecondCellToTurnPointTime),

    new AutoTurn(AutoConstants.TurnfromTurnPointPower, AutoConstants.TurnFromTurnPointTime),

    new DriveForwardXForY(AutoConstants.TurnPointToGoalTurnPointPower, AutoConstants.TurnPointToGoalTurnPointTime),

    new AutoTurn(AutoConstants.GoalTurnPower, AutoConstants.GoalTurnTime),

    new DriveForwardXForY(AutoConstants.ToGoalPower, AutoConstants.ToGoalTime),

    new RampPowerForward(AutoConstants.RampScoreTime));
  }
}

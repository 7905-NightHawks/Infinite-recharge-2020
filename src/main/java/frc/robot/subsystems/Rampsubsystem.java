/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.RampConstants;

public class Rampsubsystem extends SubsystemBase {

  private final WPI_VictorSPX TopRampMotor = new WPI_VictorSPX(RampConstants.TopRampMotor);
  private final WPI_VictorSPX BottomRampMotor = new WPI_VictorSPX(RampConstants.BottomRampMotor);

  private final SpeedControllerGroup m_RampMotors = new SpeedControllerGroup(TopRampMotor, BottomRampMotor);

  public Rampsubsystem() {

  }

public void setOutput(double output) {
  m_RampMotors.set(output);
}


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}

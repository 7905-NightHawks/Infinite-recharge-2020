/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeConstants;

public class Intake extends SubsystemBase {

  //private final WPI_VictorSPX IntakeMotor = new WPI_VictorSPX(IntakeConstants.IntakeMotor);

  private final  CANSparkMax IntakeMotor = new CANSparkMax(IntakeConstants.IntakeMotor, MotorType.kBrushed);

  public Intake() {

  }
  public void setOutput(double output) {
    IntakeMotor.set(output);
  }





  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}

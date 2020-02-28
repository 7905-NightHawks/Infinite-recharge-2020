/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ClimbConstants;

public class Winch extends SubsystemBase {
  
  private final WPI_VictorSPX WinchMotor = new WPI_VictorSPX(ClimbConstants.WinchMotor);

  public Winch() {

  }

  public void setOutput(double output){
    WinchMotor.set(output);
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}

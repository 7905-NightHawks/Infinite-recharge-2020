/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot;


import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import frc.robot.Constants.OIConstants;

/**
 * Add your docs here.
 */
public class XBoxTrigger extends Button {
    XboxController m_drivercontroller;
    int axis;
    
    

    public XBoxTrigger(XboxController m_driverController, int axis) {
        this.m_drivercontroller = m_driverController;
        this.axis = OIConstants.TriggerAxis;
    }

    public double getTriggerValue() {
        return m_drivercontroller.getRawAxis(OIConstants.TriggerAxis);
    }

    @Override
    public boolean get() {
        return m_drivercontroller.getRawAxis(OIConstants.TriggerAxis) > 0.15;
    }

}


package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class IntakeSubsystem extends SubsystemBase {
    private final DcMotorEx motor;

    public IntakeSubsystem(HardwareMap map) {
        motor = map.get(DcMotorEx.class, "intake");
        motor.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void setPower(double power) {
        motor.setPower(power);
    }
}

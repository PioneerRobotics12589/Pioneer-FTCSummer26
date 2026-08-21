package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.util.types.DriveSpeed;

import java.util.function.DoubleSupplier;

public class MecanumDriveSubsystem extends SubsystemBase {
    private final DcMotorEx frontLeft, frontRight, backLeft, backRight;
    private DriveSpeed mode;
    public MecanumDriveSubsystem(HardwareMap map) {
        frontLeft = map.get(DcMotorEx.class, "frontLeft");
        frontRight = map.get(DcMotorEx.class, "frontRight");
        backLeft = map.get(DcMotorEx.class, "backLeft");
        backRight = map.get(DcMotorEx.class, "backRight");

//        frontRight.setDirection(DcMotorEx.Direction.REVERSE);
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
//        backRight.setDirection(DcMotorEx.Direction.REVERSE);
        mode = DriveSpeed.NORMAL;
    }

    public void setMode(DriveSpeed mode) {
        this.mode = mode;
    }

    public void drive(double move, double turn, double strafe, double multiplier) {
        // Robot-Centric Driving
        frontLeft.setPower( (move+turn+strafe)*multiplier);
        frontRight.setPower((move-turn-strafe)*multiplier);
        backLeft.setPower(  (move+turn-strafe)*multiplier);
        backRight.setPower( (move-turn+strafe)*multiplier);
    }

    public void driveMotors(double frontLeftPower, double frontRightPower, double backLeftPower, double backRightPower) {
        // Set Drive Motor Powers
        frontLeft.setPower( frontLeftPower);
        frontRight.setPower(frontRightPower);
        backLeft.setPower(  backLeftPower);
        backRight.setPower( backRightPower);
    }
}
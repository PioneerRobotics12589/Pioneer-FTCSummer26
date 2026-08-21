package org.firstinspires.ftc.teamcode.teleops;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.RobotContainer;
import org.firstinspires.ftc.teamcode.subsystems.TelemetrySubsystem;

@TeleOp(name = "Jaedyn's teleop")
public class TestTeleOp extends OpMode {
    private RobotContainer robot;

    @Override
    public void init() {
        TelemetrySubsystem.init(telemetry);
        robot = new RobotContainer(hardwareMap);
    }

    @Override
    public void loop() {
//        robot.driveTrain.drive(gamepad1.left_stick_y, -gamepad1.right_stick_x, -gamepad1.left_stick_x, 1.0);
//        robot.intake.setPower(gamepad1.right_trigger);
        robot.odometry.update();
    }
}

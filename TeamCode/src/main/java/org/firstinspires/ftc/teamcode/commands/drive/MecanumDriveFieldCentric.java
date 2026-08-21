package org.firstinspires.ftc.teamcode.commands.drive;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.drivebase.RobotDrive;
import com.arcrobotics.ftclib.geometry.Vector2d;

import org.firstinspires.ftc.teamcode.subsystems.MecanumDriveSubsystem;

import java.util.function.DoubleSupplier;

public class MecanumDriveFieldCentric extends CommandBase {

    private final MecanumDriveSubsystem  driveSystem;
    private final DoubleSupplier moveSupplier, turnSupplier, strafeSupplier;

    public MecanumDriveFieldCentric(
            MecanumDriveSubsystem driveSubsystem,
            DoubleSupplier moveSupplier,
            DoubleSupplier turnSupplier,
            DoubleSupplier strafeSupplier
    ) {
        this.driveSystem = driveSubsystem;
        this.moveSupplier = moveSupplier;
        this.turnSupplier = turnSupplier;
        this.strafeSupplier = strafeSupplier;

    }

    @Override
    public void execute() {
        double move = moveSupplier.getAsDouble(), turn = turnSupplier.getAsDouble(), strafe = strafeSupplier.getAsDouble();

        Vector2d input = new Vector2d(strafe, move);
        input = input.rotateBy(-0.0); // Robot angle

        double theta = input.angle();

        double[] wheelSpeeds = new double[4];
        double frontLeftPower, frontRightPower, backLeftPower, backRightPower;
        frontLeftPower = Math.sin(theta + Math.PI / 4.0);
        frontRightPower = Math.sin(theta - Math.PI / 4.0);
        backLeftPower = Math.sin(theta - Math.PI / 4.0);
        backRightPower = Math.sin(theta + Math.PI / 4.0);

        double maxMagnitude = Math.max(frontLeftPower, Math.max(frontRightPower, Math.max(backLeftPower, backRightPower)));
        frontLeftPower *= input.magnitude() / maxMagnitude;
        frontRightPower *= input.magnitude() / maxMagnitude;
        backLeftPower *= input.magnitude() / maxMagnitude;
        backRightPower *= input.magnitude() / maxMagnitude;

        wheelSpeeds[RobotDrive.MotorType.kFrontLeft.value] += turn;
        wheelSpeeds[RobotDrive.MotorType.kFrontRight.value] -= turn;
        wheelSpeeds[RobotDrive.MotorType.kBackLeft.value] += turn;
        wheelSpeeds[RobotDrive.MotorType.kBackRight.value] -= turn;

        maxMagnitude = Math.max(frontLeftPower, Math.max(frontRightPower, Math.max(backLeftPower, backRightPower)));
        frontLeftPower /= maxMagnitude;
        frontRightPower /= maxMagnitude;
        backLeftPower /= maxMagnitude;
        backRightPower /= maxMagnitude;


        driveSystem.driveMotors(frontLeftPower, frontRightPower, backLeftPower, backRightPower);
    }
}

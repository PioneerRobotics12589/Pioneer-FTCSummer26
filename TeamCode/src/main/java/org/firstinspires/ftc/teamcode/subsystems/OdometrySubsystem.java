package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcontroller.external.samples.SensorGoBildaPinpoint;
import org.firstinspires.ftc.teamcode.commands.odometry.UpdateOdometryCommand;
import org.firstinspires.ftc.teamcode.subsystems.odometry.OpticalFlowSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.odometry.PinpointSubsystem;
import org.firstinspires.ftc.teamcode.util.Constants;
import org.firstinspires.ftc.teamcode.util.types.Pose;

public class OdometrySubsystem extends SubsystemBase {

    private final PinpointSubsystem pinpoint;
    private final OpticalFlowSubsystem otos;
//    private final Pose position, velocity, acceleration;

    public OdometrySubsystem(HardwareMap map) {
        pinpoint = new PinpointSubsystem(map);
        otos = new OpticalFlowSubsystem(map);

        this.setDefaultCommand(new UpdateOdometryCommand(this));

        pinpoint.setOffsets(Constants.Odometry.pinpointX, Constants.Odometry.pinpointY);
        otos.setOffsets(Constants.Odometry.opticalX, Constants.Odometry.opticalY, Constants.Odometry.opticalH);

//        position = new Pose(); velocity = new Pose(); acceleration = new Pose();
    }

    public void update() {
        otos.update();
        TelemetrySubsystem.addLine("*** Optical Flow Data ***");
        TelemetrySubsystem.addData("Position:", otos.getPose());
        TelemetrySubsystem.addData("Velocity:", otos.getVelocity());
        TelemetrySubsystem.addData("Acceleration:", otos.getAcceleration());

        pinpoint.updatePose();
        pinpoint.updateVelocity();
        pinpoint.updateAcceleration();
        TelemetrySubsystem.addLine("*** Pinpoint Data ***");
        TelemetrySubsystem.addData("Position:", pinpoint.getPose());
        TelemetrySubsystem.addData("Velocity:", pinpoint.getVelocity());
        TelemetrySubsystem.addData("Acceleration:", pinpoint.getAcceleration());

        TelemetrySubsystem.updateTelemetry();
    }
}

package org.firstinspires.ftc.teamcode.subsystems.odometry;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.hardware.sparkfun.SparkFunOTOS;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.util.types.Pose;

public class OpticalFlowSubsystem extends SubsystemBase {

    private final SparkFunOTOS otos;
    private Pose pose, velocity, acceleration;

    public OpticalFlowSubsystem(HardwareMap map) {
        otos = map.get(SparkFunOTOS.class, "otos");

        pose = new Pose();
        velocity = new Pose();
        acceleration = new Pose();
    }

    public void init(double xOffset, double yOffset, double hOffset) {
        otos.setLinearUnit(DistanceUnit.INCH);
        otos.setAngularUnit(AngleUnit.RADIANS);

        otos.setLinearScalar(1.0d);
        otos.setAngularScalar(1.0d);

        otos.setOffset(new SparkFunOTOS.Pose2D(xOffset, yOffset, hOffset));

        otos.calibrateImu();

        otos.resetTracking();

        otos.setPosition(pose.toPose2D());
    }

    public void setOffsets(double xOffset, double yOffset, double hOffset) {
        otos.setOffset(new SparkFunOTOS.Pose2D(xOffset, yOffset, hOffset));
    }

    public void resetPose() {
        otos.resetTracking();
        otos.calibrateImu();
    }

    public void setPose(Pose newPose) {
        pose = new Pose(newPose);
        otos.setPosition(pose.toPose2D());
    }

    public void update() {
        SparkFunOTOS.Pose2D pose2D, vel2D, acc2D;
        pose2D = new SparkFunOTOS.Pose2D(); vel2D = new SparkFunOTOS.Pose2D(); acc2D = new SparkFunOTOS.Pose2D();

        otos.getPosVelAcc(pose2D, vel2D, acc2D);

        pose = Pose.toPose(pose2D);
        velocity = Pose.toPose(vel2D);
        acceleration = Pose.toPose(acc2D);
    }

    public Pose getPose() {
        return pose;
    }

    public Pose getVelocity() {
        return velocity;
    }

    public Pose getAcceleration() {
        return acceleration;
    }
}

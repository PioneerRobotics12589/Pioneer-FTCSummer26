package org.firstinspires.ftc.teamcode.subsystems.odometry;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.robotcore.external.navigation.UnnormalizedAngleUnit;
import org.firstinspires.ftc.teamcode.util.types.Pose;

public class PinpointSubsystem extends SubsystemBase {

    private final GoBildaPinpointDriver pinpoint;
    private static Pose currentPose;
    private static Pose lastPose;
    private static Pose velocityPose;
    private static Pose accelerationPose;
    private static long lastTime;

    public PinpointSubsystem(HardwareMap map) {
        pinpoint = map.get(GoBildaPinpointDriver.class, "pinpoint");
    }

    public void init(double xOffset, double yOffset) {
        pinpoint.setOffsets(xOffset, yOffset, DistanceUnit.INCH);
        pinpoint.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
        pinpoint.setEncoderDirections(GoBildaPinpointDriver.EncoderDirection.FORWARD, GoBildaPinpointDriver.EncoderDirection.REVERSED);
        // ^ Directions or offsets can be a reason why position accumulates
        pinpoint.resetPosAndIMU();
        pinpoint.recalibrateIMU();

        currentPose = new Pose(0, 0, 0);
        lastPose = new Pose(0, 0, 0);
        velocityPose = new Pose(0, 0, 0);
        accelerationPose = new Pose(0, 0, 0);

        lastTime = System.nanoTime();
    }

    public void setOffsets(double xOffset, double yOffset) {
        pinpoint.setOffsets(xOffset, yOffset, DistanceUnit.INCH);
    }

    public void resetPose() {
        pinpoint.resetPosAndIMU();
    }

    public void setPose(Pose newPose) {
        updatePose();
        pinpoint.setPosition(new Pose2D(DistanceUnit.INCH, newPose.x, newPose.y, AngleUnit.RADIANS, newPose.heading));
        pinpoint.update();
        lastPose = new Pose(newPose);
    }

    public void updatePose() {
        pinpoint.update();
        currentPose.x = pinpoint.getPosX(DistanceUnit.INCH);
        currentPose.y = pinpoint.getPosY(DistanceUnit.INCH);
        lastPose = new Pose(currentPose);
        currentPose.heading = AngleUnit.normalizeRadians(pinpoint.getHeading(AngleUnit.RADIANS));
    }

    public Pose getPose() {
        return currentPose;
    }

    public void updateVelocity() {
        pinpoint.update();
        velocityPose.x = pinpoint.getVelX(DistanceUnit.INCH);
        velocityPose.y = pinpoint.getVelY(DistanceUnit.INCH);
        velocityPose.heading = pinpoint.getHeadingVelocity(UnnormalizedAngleUnit.RADIANS);
    }

    public Pose getVelocity() {
        return velocityPose;
    }

    public void updateAcceleration() {
        Pose prevVel = new Pose(velocityPose);
        updateVelocity();
        double dt = (System.nanoTime() - lastTime) / (1e9);

        accelerationPose.x = (velocityPose.x - prevVel.x) / dt;
        accelerationPose.y = (velocityPose.y - prevVel.y) / dt;
        accelerationPose.heading = (velocityPose.heading - prevVel.heading) / dt;
    }

    public Pose getAcceleration() {
        return accelerationPose;
    }

}

package org.firstinspires.ftc.teamcode.util.control;

import java.util.function.DoubleSupplier;

// Dynamic Complementary Filter Implementation
public class OdoFilter {
    private double alpha;
    private double maxAcceleration;
    private DoubleSupplier pinVel, pinAccel, optoVel;
    private double position;
    private long lastTime;

    public OdoFilter(double alpha, double maxAccel, DoubleSupplier pinpointVel, DoubleSupplier pinpointAccel, DoubleSupplier opticalVel) {
        this.alpha = alpha;
        this.maxAcceleration = maxAccel;
        this.pinVel = pinpointVel;
        this.pinAccel = pinpointAccel;
        this.optoVel = opticalVel;
        this.lastTime = System.nanoTime();
    }

    public double run() {
        double thisTime = System.nanoTime();
        double dt = (thisTime - lastTime) / (10e9);

        alpha = Math.min(1.0, Math.max(0.1, pinAccel.getAsDouble()/maxAcceleration));

        double filterVel = alpha * pinVel.getAsDouble() + (1 - alpha) * optoVel.getAsDouble();

        position += filterVel * dt;

        return position;
    }
}

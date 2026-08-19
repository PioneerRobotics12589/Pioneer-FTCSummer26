package org.firstinspires.ftc.teamcode.util.control;

public class Feedforward {
    private final double ks, kv, ka;
    private final double maxVoltage = 14.0;

    public Feedforward(double ks, double kv, double ka) {
        this.ks = ks;
        this.kv = kv;
        this.ka = ka;

    }

    public Feedforward(double ks, double kv) {
        this.ks = ks;
        this.kv = kv;
        this.ka = 0.0;
    }

    public double run(double velocity, double acceleration) {
        return ks * Math.signum(velocity) + kv * velocity + ka * acceleration;
    }

    public double run(double velocity) {
        return run(velocity, 0.0);
    }

    public double maxVelocity(double acceleration) {
        return (maxVoltage - ks - acceleration * ka) / kv;
    }

    public double maxAcceleration(double velocity) {
        return (maxVoltage - ks  - Math.abs(velocity) * kv) / ka;
    }
}

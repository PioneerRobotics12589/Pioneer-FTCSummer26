package org.firstinspires.ftc.teamcode.util.control;

public class PID {
    private final double kp, ki, kd;
    private final double max_value;
    private double lastError;
    private double integral;
    private long lastTime;
    
    public PID(double kp, double ki, double kd) {
        this.kp = kp;
        this.ki = ki;
        this.kd = kd;
        this.max_value = Double.MAX_VALUE;
        this.integral = 0.0;
        this.lastError = 0.0;
        lastTime = System.nanoTime();
    }

    public PID(double kp, double ki, double kd, double max) {
        this.kp = kp;
        this.ki = ki;
        this.kd = kd;
        this.max_value = max;
        this.integral = 0.0;
        this.lastError = 0.0;
        lastTime = System.nanoTime();
    }
    
    public double run(double measured, double target) {
        long thisTime = System.nanoTime();
        double dt = ((thisTime - lastTime) / (10e9));
        
        double error = target - measured;
        
        double proportional = kp * error;
        integral += ki * error * dt;
        double derivative = kd * (error - lastError) / dt;

        double pid = proportional + integral + derivative;

        if (Math.abs(pid) > max_value) {
            pid = Math.signum(pid) * max_value;
        }

        lastError = error;
        lastTime = thisTime;

        return pid;
    }
}

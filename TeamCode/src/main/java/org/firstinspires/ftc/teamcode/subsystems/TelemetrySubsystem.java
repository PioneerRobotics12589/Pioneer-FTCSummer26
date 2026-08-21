package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.DashboardCore;
import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class TelemetrySubsystem {

    private static Telemetry telemetry;

    private static FtcDashboard dashboard;

    private static TelemetryPacket packet;

    public static void init(Telemetry tele) {
        telemetry = tele;
        dashboard = FtcDashboard.getInstance();
        packet = new TelemetryPacket();
    }

    public static void addLine(String line) {
        packet.addLine(line);
        telemetry.addLine(line);
    }

    public static void addData(String caption, Object value) {
        packet.put(caption, value);
        telemetry.addData(caption, value);
    }

    public static void clear() {
        packet.clearLines();
        telemetry.clear();
    }

    public static void updateTelemetry() {
        dashboard.sendTelemetryPacket(packet);
        packet = new TelemetryPacket();
        telemetry.update();
    }
}

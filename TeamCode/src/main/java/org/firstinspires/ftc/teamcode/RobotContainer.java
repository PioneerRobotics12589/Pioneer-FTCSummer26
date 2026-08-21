package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.MecanumDriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.OdometrySubsystem;

public class RobotContainer {

    public IntakeSubsystem intake;
    public MecanumDriveSubsystem driveTrain;

    public OdometrySubsystem odometry;

    public RobotContainer(HardwareMap map) {
        intake = new IntakeSubsystem(map);
        driveTrain = new MecanumDriveSubsystem(map);
        odometry = new OdometrySubsystem(map);
    }
}

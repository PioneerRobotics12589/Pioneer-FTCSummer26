package org.firstinspires.ftc.teamcode.commands.drive;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.MecanumDriveSubsystem;

import java.util.function.DoubleSupplier;

public class DefaultMecanumDriveCommand extends CommandBase {

    private final MecanumDriveSubsystem  driveSystem;
    private final DoubleSupplier moveSupplier, turnSupplier, strafeSupplier;

    public DefaultMecanumDriveCommand(
            MecanumDriveSubsystem driveSubsystem,
            DoubleSupplier moveSupplier,
            DoubleSupplier turnSupplier,
            DoubleSupplier strafeSupplier
    ) {
        this.driveSystem = driveSubsystem;
        this.moveSupplier = moveSupplier;
        this.turnSupplier = turnSupplier;
        this.strafeSupplier = strafeSupplier;

        addRequirements(driveSubsystem);
    }

    @Override
    public void execute() {
        driveSystem.drive(moveSupplier.getAsDouble(),
                          turnSupplier.getAsDouble(),
                          strafeSupplier.getAsDouble(),
                 1.0);
    }
}

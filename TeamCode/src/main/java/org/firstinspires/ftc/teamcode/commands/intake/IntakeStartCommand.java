package org.firstinspires.ftc.teamcode.commands.intake;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;

import java.util.function.DoubleSupplier;

public class IntakeStartCommand extends CommandBase {
    private final IntakeSubsystem intake;
    private final DoubleSupplier intakePower;

    public IntakeStartCommand(IntakeSubsystem intakeSystem, DoubleSupplier powerSupplier) {
        this.intake = intakeSystem;
        this.intakePower = powerSupplier;

        addRequirements(intakeSystem);
    }

    @Override
    public void execute() {
        intake.setPower(intakePower.getAsDouble());
    }

    @Override
    public void end(boolean interrupted) {
        intake.setPower(0.0);
    }
}

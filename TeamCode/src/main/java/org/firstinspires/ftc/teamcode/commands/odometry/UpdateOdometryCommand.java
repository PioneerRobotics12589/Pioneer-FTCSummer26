package org.firstinspires.ftc.teamcode.commands.odometry;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.OdometrySubsystem;

public class UpdateOdometryCommand extends CommandBase {

    private final OdometrySubsystem odoSubsystem;

    public UpdateOdometryCommand(OdometrySubsystem odoSubsystem) {
        this.odoSubsystem = odoSubsystem;

        addRequirements(odoSubsystem);
    }

    @Override
    public void execute() {
        odoSubsystem.update();
    }

}

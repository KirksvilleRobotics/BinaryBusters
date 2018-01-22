package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by yearbook on 1/5/18.
 */

@Autonomous(name = "BBSimple")
public class BBSimple extends BinaryBustersAutonomus {
    @Override
    public void runOpMode() {
        setup();

        waitForStart();

        encoderDrive(29, 29, 1);
    }
}

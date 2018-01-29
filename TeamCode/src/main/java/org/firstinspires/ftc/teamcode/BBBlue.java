package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

/**
 * Created by BinaryBusters on 12/4/17.
 */

@Autonomous(name = "BBBlue")
public class BBBlue extends BinaryBustersAutonomus {

    @Override
    public void runOpMode() {
        setup();

        waitForStart();

        telemetry.addData("Status:", "initiated");
        telemetry.update();

        dropJewel();
        if(checkColor()) {
            //color red
            //drive forwards
            encoderDrive(4.0, 4.0, 0.5);
            telemetry.addData("color: ", "red");
            telemetry.update();
            sleep(1000);

            liftJewel();

            //drive to safe zone
            encoderDrive(28.0 ,28.0, 0.5);
        } else {
            //color blue
            //drive backwards
            encoderDrive(-4.0, -4.0, 0.5);
            telemetry.addData("color: ", "blue");
            telemetry.update();
            sleep(1000);

            liftJewel();

            //drive to safe zone
            encoderDrive(36.0, 36.0, 0.5);
        }

    }
}

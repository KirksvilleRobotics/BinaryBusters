package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

/**
 * Created by yearbook on 12/4/17.
 */
@Autonomous(name = "BBBlue")
public class BBBlue extends BinaryBustersAutonomus {
    double color;

    @Override
    public void runOpMode() {
        setup();

        waitForStart();

        telemetry.addData("Status:", "initiated");
        telemetry.update();

        dropJewel();

        color = checkColor();
        if(color == 1) {
            //color red
            //drive forwards
            encoderDrive(4.0, 4.0, 1.0);
            telemetry.addData("color: ", "red");
            telemetry.update();

            liftJewel();

            //drive to safe zone
            encoderDrive(28.0 ,28.0 , 1.0);
        } else if(color == 0) {
            //color blue
            //drive backwards
            encoderDrive(4.0, 4.0, -1.0);
            telemetry.addData("color: ", "blue");
            telemetry.update();

            liftJewel();

            //drive to safe zone
            encoderDrive(36.0, 36.0 , 1.0);
        } else {
            liftJewel();

            encoderDrive(32, 32, 1.0);
        }

    }
}

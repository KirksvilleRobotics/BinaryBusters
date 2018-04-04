package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

/**
 * Created by BinaryBusters on 12/4/17.
 */

@Autonomous(name = "BBRed")
public class BBRed extends BinaryBustersAutonomus {

    @Override
    public void runOpMode() {


        setup();
        telemetry.addData("Status:", "initiated");
        telemetry.update();
        waitForStart();

        glyphLeft.setPosition(0.3611);
        glyphRight.setPosition(0.4);

        sleep(500);

        dropJewel();
        if(checkColor()) {
            //color red
            //drive backwards
            encoderDrive(-3.0, 3.0, 0.5);
            telemetry.addData("color: ", "red");
            telemetry.update();
            sleep(1000);

            liftJewel();
            sleep(1000);
            encoderDrive(3.0, -3.0, 0.5);

            //drive to safe zone
            encoderDrive(-28.0 ,-28.0, 0.25);

            colorSensor.passiveMode();
        } else {
            //color blue
            //drive forwards
            encoderDrive(3.0, -3.0, 0.5);
            telemetry.addData("color: ", "blue");
            telemetry.update();
            sleep(1000);

            liftJewel();
            sleep(1000);
            encoderDrive(-3.0, 3.0, 0.5);

            //drive to safe zone
            encoderDrive(-36.0, -36.0, 0.25);

            colorSensor.passiveMode();
        }
    }
}

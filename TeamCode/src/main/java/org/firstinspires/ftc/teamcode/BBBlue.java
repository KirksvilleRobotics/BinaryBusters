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

        glyphLeft.setPosition(0.3611);
        glyphRight.setPosition(0.4);

        telemetry.addData("Status:", "initiated");
        telemetry.update();

        waitForStart();

        dropJewel();
        if(checkColor()) {
            //color red
            //drive forwards
            encoderDrive(3.0, -3.0, 0.5);
            telemetry.addData("color: ", "red");
            telemetry.update();
            sleep(1000);

            liftJewel();
            sleep(1000);
            encoderDrive(-3.0, 3.0, 0.5);

            //drive to safe zone
            encoderDrive(28.0 ,28.0, 0.25);

            glyphLeft.setPosition(0.1111);
            glyphRight.setPosition(0.65);

            colorSensor.passiveMode();
            encoderDrive(-1, -1, 0.5);
        } else {
            //color blue
            //drive backwards
            encoderDrive(-3.0, 3.0, 0.5);
            telemetry.addData("color: ", "blue");
            telemetry.update();
            sleep(1000);

            liftJewel();
            sleep(1000);
            encoderDrive(3.0, -3.0, 0.5);


            //drive to safe zone
            encoderDrive(36.0, 36.0, 0.25);

            glyphLeft.setPosition(0.1111);
            glyphRight.setPosition(0.65);

            colorSensor.passiveMode();
            encoderDrive(-1, -1, 0.5);
        }

    }
}

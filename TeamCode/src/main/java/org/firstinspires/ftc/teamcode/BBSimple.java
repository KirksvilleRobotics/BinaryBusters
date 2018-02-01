package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

/**
 * Created by BinaryBusters on 1/5/18.
 */

@Autonomous(name = "BBSimple")
public class BBSimple extends BinaryBustersAutonomus {
    @Override
    public void runOpMode() {
        setup();

        waitForStart();


        glyphLeft.setPosition(0.3611);
        glyphRight.setPosition(0.4);

        sleep(500);

        encoderDrive(29, 29, 0.5);

        glyphLeft.setPosition(0.1111);
        glyphRight.setPosition(0.65);
    }
}

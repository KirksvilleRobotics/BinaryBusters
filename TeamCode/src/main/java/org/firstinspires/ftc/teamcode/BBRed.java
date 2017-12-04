package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
 * Created by yearbook on 12/4/17.
 */

public class BBRed extends BinaryBustersAutonomus {
    public int direction;

    @Override
    public void runOpMode() {
        setUp();

        jewel.setPosition(0);
        direction = checkColor();
        if(direction == -1) {
            backRightMotor.setPower(0.8);
            backLeftMotor.setPower(-0.8);
        } else if(direction == 1) {
            backRightMotor.setPower(-0.8);
            backLeftMotor.setPower(0.8);
        }
        jewel.setPosition(90);

        backRightMotor.setPower(-0.8);
        backLeftMotor.setPower(0.8);
        /*while(true) {
            if(encoderValue is where we want it) {
                backRightMotor.setPower(0);
                backLeftMotor.setPower(0);
                break;
            }
        }*/
    }
}

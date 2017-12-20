package org.firstinspires.ftc.teamcode;

/**
 * Created by yearbook on 12/4/17.
 */

public class BBBlue extends BinaryBustersAutonomus {
    double color;

    @Override
    public void runOpMode() {
        setUp();

        //jewel.setPosition(0);
        color = checkColor();
        if(color > 200) {
            //color blue
            //ddrive backwards
            backRightMotor.setPower(0.8);
            backLeftMotor.setPower(-0.8);
        } else if(color > 1 && color < 10) {
            backRightMotor.setPower(-0.8);
            backLeftMotor.setPower(0.8);
        }
        //jewel.setPosition(90);

        backRightMotor.setPower(0.8);
        backLeftMotor.setPower(-0.8);
       /*while(true) {
            if(encoderValue is where we want) {
                backRightMotor.setPower(0);
                backLeftMotor.setPower(0);
                break;
            }
        }*/
    }
}

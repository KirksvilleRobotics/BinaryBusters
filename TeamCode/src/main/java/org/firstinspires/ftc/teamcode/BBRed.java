package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
 * Created by yearbook on 12/4/17.
 */

@Autonomous(name = "BBRed")
public class BBRed extends BinaryBustersAutonomus {
    double color;

    @Override
    public void runOpMode() {


        setUp();
        telemetry.addData("Status:", "initiated");
        telemetry.update();
        sleep(1000);
        encoderDrive(36, 36);
        /*
        //jewel.setPosition(0);
        //direction = checkColor(teamColor);
        telemetry.addData("Ready to test", 1);
        telemetry.update();
        color = checkColor();
        sleep(1000);
        if(color > 1 && color < 10) {
            //color red
            //drive backwards
            backRightMotor.setPower(0.8);
            backLeftMotor.setPower(-0.8);
            telemetry.addData("color: ", "red");
            telemetry.update();
        } else if(color > 200) {
            //color blue
            //drive forwards
            backRightMotor.setPower(-0.8);
            backLeftMotor.setPower(0.8);
            telemetry.addData("color: ", "blue");
            telemetry.update();
        }
        sleep(3000);
        //jewel.setPosition(90);

        //drive backwards
        //backRightMotor.setPower(-0.8);
        //backLeftMotor.setPower(0.8);
        /*while(true) {
            if(encoderValue is where we want it) {
                backRightMotor.setPower(0);
                backLeftMotor.setPower(0);
                break;
            }
        }*/
    }
}

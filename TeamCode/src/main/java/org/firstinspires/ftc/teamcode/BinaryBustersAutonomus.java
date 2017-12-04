package org.firstinspires.ftc.teamcode;

/**
 * Created by yearbook on 10/11/17.
 */

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@Autonomous(name = "BinaryBustersAutonomus")
public class BinaryBustersAutonomus extends LinearOpMode{
    public DcMotor backRightMotor;
    public DcMotor backLeftMotor;

    public DcMotor glyphVertical;

    public Servo jewel;

    public int direction;

    public void runOpMode() throws InterruptedException {
        backRightMotor.setPower(-0.8);
        backLeftMotor.setPower(0.8);
        sleep(800);
        backRightMotor.setPower(0);
        backLeftMotor.setPower(0);
        /*
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
        while(true) {
            if(ultrasonicSensor < 3) {
                backRightMotor.setPower(0);
                backLeftMotor.setPower(0);
                break;
            }
        }
        */
    }

    public int checkColor() {
        //check color in front of the robot
        /*
        read sensor
        color = readSensor
        if(color  == teamColor) {
            return -1;
        } else {
            return 1;
        }
        */
        return 0;
    }

    public void setUp() {
        backRightMotor = hardwareMap.get(DcMotor.class, "backRightMotor");
        backLeftMotor = hardwareMap.get(DcMotor.class, "backLeftMotor");

        backRightMotor.setDirection(DcMotor.Direction.FORWARD);
        backLeftMotor.setDirection(DcMotor.Direction.FORWARD);

        glyphVertical = hardwareMap.get(DcMotor.class, "glyphVertical");

        glyphVertical.setDirection(DcMotor.Direction.FORWARD);

        jewel = hardwareMap.get(Servo.class, "jewel");
        jewel.setPosition(0);
    }
}

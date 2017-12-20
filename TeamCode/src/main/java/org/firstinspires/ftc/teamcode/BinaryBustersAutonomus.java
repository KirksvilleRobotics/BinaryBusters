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
import com.qualcomm.robotcore.hardware.ColorSensor;
import android.graphics.Color;

@Autonomous(name = "BinaryBustersAutonomus")
public class BinaryBustersAutonomus extends LinearOpMode{
    //Hardware variables
    public DcMotor backRightMotor;
    public DcMotor backLeftMotor;

    //public Servo jewel;

    public ColorSensor colorSensor;

    //Math Variables
    static final double     COUNTS_PER_MOTOR_REV    = 1440 ;    // eg: TETRIX Motor Encoder
    static final double     DRIVE_GEAR_REDUCTION    = 2.0 ;     // This is < 1.0 if geared UP
    static final double     WHEEL_DIAMETER_INCHES   = 4.0 ;     // For figuring circumference
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_INCHES * 3.1415);

    public ElapsedTime currentTime = new ElapsedTime();

    public void runOpMode() throws InterruptedException {}

    public void encoderDrive(double leftInches, double rightInches) {
        int leftTarget;
        int rightTarget;


            telemetry.addData("Status:", "encoder drive");
            telemetry.update();
            sleep(1000);
            leftTarget = backLeftMotor.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
            rightTarget = backRightMotor.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);

            telemetry.addData("leftTarget:", leftTarget);
            telemetry.update();
            sleep(1000);
            telemetry.addData("rightTarget", rightTarget);
            telemetry.update();
            sleep(1000);

            backLeftMotor.setPower(1.0);
            backRightMotor.setPower(-1.0);

            telemetry.addData("Status", "motor started");
            telemetry.update();
            sleep(1000);

            while(backLeftMotor.getCurrentPosition() < leftTarget && backRightMotor.getCurrentPosition() < rightTarget) {
                telemetry.addData("Status:", backRightMotor.getCurrentPosition());
                telemetry.update();
            }

            backLeftMotor.setPower(0.0);
            backRightMotor.setPower(0.0);

            /*backLeftMotor.setTargetPosition(leftTarget);
            backRightMotor.setTargetPosition(rightTarget);

            backLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            backLeftMotor.setPower(1.0);
            backRightMotor.setPower(1.0);

            while(backLeftMotor.isBusy() && backRightMotor.isBusy()) {
                telemetry.addData("Status:", "running");
                telemetry.update();
            }

            backLeftMotor.setPower(0);
            backRightMotor.setPower(0);

            telemetry.addData("Status:", "done");
            telemetry.update();

            backLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            backRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);*/

    }

    /*public void encoderDrive(double speed, double leftInches, double rightInches, double time) {
        int leftTarget;
        int rightTarget;

        if(opModeIsActive()) {
            leftTarget = backLeftMotor.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
            rightTarget = backRightMotor.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);
            backLeftMotor.setTargetPosition(leftTarget);
            backRightMotor.setTargetPosition(rightTarget);

            backLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            currentTime.reset();
            currentTime.startTime();

            backLeftMotor.setPower(speed);
            backRightMotor.setPower(-speed);

            while(opModeIsActive() && currentTime.milliseconds() < time && backLeftMotor.isBusy() && backRightMotor.isBusy()) {
                telemetry.addData("Time Completed:", currentTime.milliseconds());
                telemetry.update();
            }

            backLeftMotor.setPower(0);
            backRightMotor.setPower(0);

            backLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            backRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }*/

    public float checkColor() {
        //check color in front of the robot
        float hsvValues[] = {0F,0F,0F};
        Color.RGBToHSV(colorSensor.red() * 8, colorSensor.green() * 8, colorSensor.blue() * 8, hsvValues);
        return hsvValues[0];
    }

    public void setUp() {
        backRightMotor = hardwareMap.get(DcMotor.class, "backRightMotor");
        backLeftMotor = hardwareMap.get(DcMotor.class, "backLeftMotor");

        backRightMotor.setDirection(DcMotor.Direction.FORWARD);
        backLeftMotor.setDirection(DcMotor.Direction.FORWARD);

        backRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //jewel = hardwareMap.get(Servo.class, "jewel");
        //jewel.setPosition(0);

        colorSensor = hardwareMap.get(ColorSensor.class, "colorSensor");
    }
}

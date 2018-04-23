package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by yearbook on 3/28/18.
 */

public class BBAuto extends LinearOpMode {
    public DcMotor frontLeft;
    public DcMotor frontRight;
    public DcMotor backLeft;
    public DcMotor backRight;

    public DcMotor lift;

    public Servo flag;
    public Servo pipeDrop;

    final double counts = 425;
    final double wheelDiameter = 4;
    final double countsPerInch = counts / (wheelDiameter * 3.14);

    double armPosition = 0.0;

    @Override
    public void runOpMode() {
        //Set up
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");

        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        frontLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        frontRight.setDirection(DcMotorSimple.Direction.FORWARD);
        backLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        backRight.setDirection(DcMotorSimple.Direction.FORWARD);

        lift = hardwareMap.get(DcMotor.class, "lift");

        lift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        lift.setDirection(DcMotorSimple.Direction.FORWARD);

        flag = hardwareMap.get(Servo.class, "flag");

        pipeDrop = hardwareMap.get(Servo.class, "pipeDrop");

        //Drive left and line up with flag
        encoderDrive(-38, 38, 38, -38, 1);
        raiseFlag();

        encoderDrive(38, -38, -38, 38, 1);

        encoderDrive(30, 30, 30, 30, 1);
    }

    public void encoderDrive(double frontLeftInches, double frontRightInches, double backLeftInches, double backRightInches, double speed) {
        int frontLeftTarget;
        int frontRightTarget;
        int backLeftTarget;
        int backRightTarget;

        frontLeftTarget = frontLeft.getCurrentPosition() + (int)(frontLeftInches * countsPerInch);
        frontRightTarget = frontRight.getCurrentPosition() + (int)(frontRightInches * countsPerInch);
        backLeftTarget = backLeft.getCurrentPosition() + (int)(backLeftInches * countsPerInch);
        backRightTarget = backRight.getCurrentPosition() + (int)(backRightInches * countsPerInch);

        frontLeft.setTargetPosition(frontLeftTarget);
        frontRight.setTargetPosition(frontRightTarget);
        backLeft.setTargetPosition(backLeftTarget);
        backRight.setTargetPosition(backRightTarget);

        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        frontLeft.setPower(speed);
        frontRight.setPower(speed);
        backLeft.setPower(speed);
        backRight.setPower(speed);

        while(frontLeft.isBusy() && frontRight.isBusy() && backLeft.isBusy() && backRight.isBusy()) {
            telemetry.addData("Front Left:", frontLeft.getCurrentPosition() + " " + frontLeftTarget);
            telemetry.addData("Front Right:", frontRight.getCurrentPosition() + " " + frontRightTarget);
            telemetry.addData("Back Left:", backLeft.getCurrentPosition() + " " + backLeftTarget);
            telemetry.addData("Back Right:", backRight.getCurrentPosition() + " " + backRightTarget);

            if(!opModeIsActive()) {
                frontLeft.setPower(0);
                frontRight.setPower(0);
                backLeft.setPower(0);
                backRight.setPower(0);
            }
        }

        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);

        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void raiseFlag() {
        double startTime = getRuntime();

        while(getRuntime() - startTime > 20.0) {
            armPosition += 0.5;
            flag.setPosition(armPosition);
        }

        flag.setPosition(0.5);
    }
}

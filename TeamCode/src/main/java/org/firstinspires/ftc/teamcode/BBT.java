package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by yearbook on 3/26/18.
 */

@TeleOp(name = "BBT")
public class BBT  extends OpMode {
    public DcMotor frontLeft;
    public DcMotor frontRight;
    public DcMotor backLeft;
    public DcMotor backRight;


    public DcMotor lift;

    public Servo flag;
    public Servo pipeDrop;

    public double armPosition = 0.0;

    @Override
    public void init() {
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");

        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        frontLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        frontRight.setDirection(DcMotorSimple.Direction.FORWARD);
        backLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        backRight.setDirection(DcMotorSimple.Direction.FORWARD);

        lift = hardwareMap.get(DcMotor.class, "lift");

        lift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        lift.setDirection(DcMotorSimple.Direction.FORWARD);

        flag = hardwareMap.get(Servo.class, "flag");

        pipeDrop = hardwareMap.get(Servo.class, "pipeDrop");
    }

    @Override
    public void loop() {
        //Mecanum wheel code
        double r = Math.hypot(gamepad1.left_stick_x, gamepad1.left_stick_y);
        double angle = Math.atan2(gamepad1.left_stick_y, gamepad1.left_stick_x) - Math.PI /4;
        double rightX = gamepad1.right_stick_x;

        final double v1 = r * Math.cos(angle) + rightX;
        final double v2 = r * Math.sin(angle) - rightX;
        final double v3 = r * Math.sin(angle) + rightX;
        final double v4 = r * Math.cos(angle) - rightX;

        frontLeft.setPower(-v1);
        frontRight.setPower(v2);
        backLeft.setPower(v3);
        backRight.setPower(v4);

        //Lift Code
        lift.setPower(gamepad2.left_stick_y);

        //Pipe Dropper
        if(gamepad2.x) pipeDrop.setPosition(0);
        if(gamepad2.y) pipeDrop.setPosition(180);

        //Flag Code
        if(gamepad2.a) {
            //raise flag with 360 servo
            armPosition += 0.5;
            flag.setPosition(armPosition);
        }
        if(gamepad2.b) flag.setPosition(0.5); //stop raising flag
    }
}

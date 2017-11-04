package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by BinaryBusters on 9/25/17.
 */
@TeleOp(name = "Septeleop")
public class Septeleop extends OpMode {

    public DcMotor backRightMotor;
    public DcMotor backLeftMotor;
    //public DcMotor frontRightMotor;
    //public DcMotor frontLeftMotor;

    public DcMotor glyphVertical;
    public Servo glyphHorizontal;

    //public DcMotor relicHorizontal;

    public ElapsedTime currentTime = new ElapsedTime();

    @Override
    public void init() {
        backRightMotor = hardwareMap.get(DcMotor.class, "backRightMotor");
        backLeftMotor = hardwareMap.get(DcMotor.class, "backLeftMotor");

        //Mecanum
        //frontRightMotor = hardwareMap.get(DcMotor.class, "frontRightMotor");
        //frontLeftMotor = hardwareMap.get(DcMotor.class, "frontLeftMotor");

        backRightMotor.setDirection(DcMotor.Direction.FORWARD);
        backLeftMotor.setDirection(DcMotor.Direction.FORWARD);

        //Mecanum
        //frontRightMotor.setDirection(DcMotor.Direction.FORWARD);
        //frontLeftMotor.setDirection((DcMotor.Direction.FORWARD));

        glyphVertical = hardwareMap.get(DcMotor.class, "glyphVertical");
        glyphHorizontal = hardwareMap.get(Servo.class, "glyphHorizontal");

        glyphVertical.setDirection(DcMotor.Direction.FORWARD);
        glyphHorizontal.setPosition(0);

        //relicHorizontal = hardwareMap.get(DcMotor.class, "relicHorizontal");

        //relicHorizontal.setDirection(DcMotor.Direction.FORWARD);
    }

    @Override
    public void start() {
        currentTime.reset();
    }

    @Override
    public void loop() {
        //Drive Code
        double leftPower;
        double rightPower;

        leftPower = -gamepad1.left_stick_y;
        rightPower = gamepad1.right_stick_y;

        backLeftMotor.setPower(leftPower * 0.8);
        backRightMotor.setPower(rightPower * 0.8);

        /* Mecanum Wheel Code
        double r = Math.hypot(gamepad1.left_stick_x, gamepad1.left_stick_y);
        double angle = Math.atan2(gamepad1.left_stick_y, gamepad1.left_stick_x) - Math.PI /4;
        double rightX = gamepad1.right_stick_x;

        final double v1 = r * Math.cos(angle) + rightX;
        final double v2 = r * Math.sin(angle) - rightX;
        final double v3 = r * Math.sin(angle) + rightX;
        final double v4 = r * Math.cos(angle) - rightX;

        frontLeftMotor.setPower(v1);
        frontRightMotor.setPower(v2);
        backLeftMotor.setPower(v3);
        backRightMotor.setPower(v4); */

        //Glyph code - The mechanism that lifts the glyphs
        //Glyph Vertical
        if (gamepad1.dpad_up) glyphVertical.setPower(-1); //up
        if (gamepad1.dpad_down) glyphVertical.setPower(1); //down
        if (gamepad1.dpad_down == false && gamepad1.dpad_up == false) glyphVertical.setPower(0);

        //Glyph Horizontal
        //The following is code to control the opening and closing of the glyph lift claw on controller 2.
        //There are four values to test various positions, but we will only want 2 long-term.
        if (gamepad2.y) glyphHorizontal.setPosition(180);
        if (gamepad2.x) glyphHorizontal.setPosition(120);
        if (gamepad2.b) glyphHorizontal.setPosition(60);
        if (gamepad2.a) glyphHorizontal.setPosition(0);
        //Relic Code
        //Out
        //if (gamepad2.a == true) relicHorizontal.setPower(1);
        //In
        //if (gamepad2.b == true) relicHoriztontal.setPower(-1);
    }
}
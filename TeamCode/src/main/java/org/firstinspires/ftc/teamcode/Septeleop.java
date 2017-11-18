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
    public DcMotor glyphHorizontal;

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
        glyphHorizontal = hardwareMap.get(DcMotor.class, "glyphHorizontal");

        glyphVertical.setDirection(DcMotor.Direction.FORWARD);
        glyphHorizontal.setDirection(DcMotor.Direction.FORWARD);

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
        glyphVertical.setPower(gamepad2.left_stick_y);

        //Glyph Horizontal
        glyphHorizontal.setPower(gamepad2.right_stick_x);
        //Relic Code
        //Out
        //if (gamepad2.a == true) relicHorizontal.setPower(1);
        //In
        //if (gamepad2.b == true) relicHoriztontal.setPower(-1);
    }
}
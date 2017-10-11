package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by BinaryBusters on 9/25/17.
 */

public class Septeleop extends OpMode {

    public DcMotor backRightMotor;
    public DcMotor backLeftMotor;
    //public DcMotor frontRightMotor;
    //public DcMotor frontLeftMotor;

    public DcMotor glyphVertical;
    public Servo glyphHorizontal;

    public DcMotor relicHorizontal;

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

        relicHorizontal = hardwareMap.get(DcMotor.class, "relicHorizontal");

        relicHorizontal.setDirection(DcMotor.Direction.FORWARD);
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

        double drive = -gamepad1.left_stick_y;
        double turn  =  gamepad1.right_stick_x;
        leftPower    = Range.clip(drive + turn, -1.0, 1.0) ;
        rightPower   = Range.clip(drive - turn, -1.0, 1.0) ;

        backLeftMotor.setPower(leftPower);
        backRightMotor.setPower(rightPower);

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

        if(gamepad1.left_stick_y == 0 && gamepad1.left_stick_x == 0) stop();

        //Glyph code
        //Glyph Vertical
        if(gamepad1.dpad_up == true) moveGlyphVertical(1);
        if(gamepad1.dpad_down == true) moveGlyphVertical(-1);

        //Glyph Horizontal
        //Open claw
        if(gamepad2.x == true) moveGlyphHorizontal(1);
        //Close Claw
        if(gamepad2.y == true) moveGlyphHorizontal(0);

        //Relic Code
        //Out
        if(gamepad2.a == true) moveRelic(1);
        //In
        if(gamepad2.b == true) moveRelic(-1);
    }

    public void stop() {
        //Mecanum Wheel
        //frontLeftMotor.setPower(0);
        //frontRightMotor.setPower(0);

        backLeftMotor.setPower(0);
        backRightMotor.setPower(0);
    }

    public void moveGlyphVertical(int direction) {
        glyphVertical.setPower(direction);
    }

    public void moveGlyphHorizontal(int direction) {
        glyphVertical.setTargetPosition(direction);
    }

    public void moveRelic(int direction) {
        relicHorizontal.setPower(direction);
    }
}
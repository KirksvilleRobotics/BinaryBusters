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

    public DcMotor glyphVertical;
    public Servo glyphHorizontal;

    //public DcMotor relicHorizontal;

    public ElapsedTime currentTime = new ElapsedTime();

    @Override
    public void init() {
        backRightMotor = hardwareMap.get(DcMotor.class, "backRightMotor");
        backLeftMotor = hardwareMap.get(DcMotor.class, "backLeftMotor");

        backRightMotor.setDirection(DcMotor.Direction.FORWARD);
        backLeftMotor.setDirection(DcMotor.Direction.FORWARD);

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

        //Glyph code - The mechanism that lifts the glyphs
        //Glyph Vertical
        glyphVertical.setPower(gamepad2.left_stick_y);

        //Glyph Horizontal
        if(gamepad2.x) glyphHorizontal.setPosition(90);
        if(gamepad2.y) glyphHorizontal.setPosition(0);
        //Relic Code
    }
}
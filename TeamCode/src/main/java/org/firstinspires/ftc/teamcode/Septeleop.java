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

    public Servo glyphLeft;
    public Servo glyphRight;

    //public DcMotor relicHorizontal;
    //public Servo relicGrab;

    //public Servo jewel;

    public ElapsedTime currentTime = new ElapsedTime();

    @Override
    public void init() {
        backRightMotor = hardwareMap.get(DcMotor.class, "backRightMotor");
        backLeftMotor = hardwareMap.get(DcMotor.class, "backLeftMotor");

        backRightMotor.setDirection(DcMotor.Direction.FORWARD);
        backLeftMotor.setDirection(DcMotor.Direction.FORWARD);

        backRightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        glyphVertical = hardwareMap.get(DcMotor.class, "glyphVertical");

        glyphVertical.setDirection(DcMotor.Direction.FORWARD);

        glyphLeft = hardwareMap.get(Servo.class, "glyphLeft");
        glyphRight = hardwareMap.get(Servo.class, "glyphRight");

        //relicHorizontal = hardwareMap.get(DcMotor.class, "relicHorizontal");
        //relicGrab = hardwareMap.get(Servo.class, "relicGrab");

        //relicHorizontal.setDirection(DcMotor.Direction.FORWARD);
        //relicGrab.setPosition(0);

        //jewel = hardwareMap.get(Servo.class, "jewel");
    }

    @Override
    public void start() {currentTime.reset();}

    @Override
    public void loop() {
        //Drive Code
        double leftPower;
        double rightPower;

        leftPower = -gamepad1.left_stick_y;
        rightPower = gamepad1.right_stick_y;

        backLeftMotor.setPower(leftPower);
        backRightMotor.setPower(rightPower);

        //Glyph code - The mechanism that lifts the glyphs
        //Glyph Vertical
        glyphVertical.setPower(gamepad2.left_stick_y);

        //Glyph Horizontal
        if(gamepad2.x) {
          //open
          glyphLeft.setPosition(0);
          glyphRight.setPosition(110);
        }
        if(gamepad2.y) {
          //close
          glyphLeft.setPosition(45);
          glyphRight.setPosition(45);
        }


        //Relic Code
    }
}

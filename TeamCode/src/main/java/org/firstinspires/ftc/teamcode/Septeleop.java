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

    public DcMotor glyphLeft;
    public DcMotor glyphRight;

    //public DcMotor relicHorizontal;

    public ElapsedTime currentTime = new ElapsedTime();

    int ClawMode = -1;
    double ClawPower;

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

        glyphLeft = hardwareMap.get(DcMotor.class, "glyphLeft");
        glyphRight = hardwareMap.get(DcMotor.class, "glyphRight");

        glyphLeft.setDirection(DcMotor.Direction.FORWARD);
        glyphRight.setDirection(DcMotor.Direction.FORWARD);


        //relicHorizontal = hardwareMap.get(DcMotor.class, "relicHorizontal");

        //relicHorizontal.setDirection(DcMotor.Direction.FORWARD);
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
        /*ClawPower = gamepad2.right_stick_y;
        if(gamepad2.x) ClawMode = 1;
        if(gamepad2.y) ClawMode =0;
        if(ClawMode==1) glyphHorizontal.setPower(-.13);
        if(ClawMode==0) glyphHorizontal.setPower(0.25 * ClawPower);*/

        glyphLeft.setPower(0.5 * gamepad2.right_stick_x);
        glyphRight.setPower(-0.5 * gamepad2.right_stick_x);

        //Relic Code
        //if(gamepad2.a) relicHorizontal.setPower(1);
    }
}
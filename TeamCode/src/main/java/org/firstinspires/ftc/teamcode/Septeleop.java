package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

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

    public DcMotor jewelLift;
    public Servo jewelLock;

    public ElapsedTime currentTime = new ElapsedTime();

    double topSpeed = 1.0;

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

        jewelLift = hardwareMap.get(DcMotor.class, "jewelLift");
        jewelLift.setDirection(DcMotor.Direction.REVERSE);

        jewelLock = hardwareMap.get(Servo.class, "jewelLock");
    }

    @Override
    public void start() {}

    @Override
    public void loop() {
        //Drive Code
        if(gamepad1.x) topSpeed = 1.0;
        if(gamepad2.y) topSpeed = 0.5;

        double leftPower;
        double rightPower;

        leftPower = -gamepad1.left_stick_y;
        rightPower = gamepad1.right_stick_y;

        backLeftMotor.setPower(leftPower * topSpeed);
        backRightMotor.setPower(rightPower * topSpeed);

        //Glyph code - The mechanism that lifts the glyphs
        //Glyph Vertical
        glyphVertical.setPower(gamepad2.left_stick_y);

        //Glyph Horizontal
        if(gamepad2.x) {
          //open
          glyphLeft.setPosition(0.1111);
          glyphRight.setPosition(0.65);
        }
        if(gamepad2.y) {
          //close
          glyphLeft.setPosition(0.3611);
          glyphRight.setPosition(0.4);
        }

        //color sensor arm
        //jewel lift
        if(gamepad2.dpad_up) jewelLift.setPower(1);
        else if(gamepad2.dpad_down) jewelLift.setPower(-1);
        else jewelLift.setPower(0);
        //jewel lock
        if(gamepad2.dpad_left) jewelLock.setPosition(0);
        if(gamepad2.dpad_right) jewelLock.setPosition(1);
    }
}

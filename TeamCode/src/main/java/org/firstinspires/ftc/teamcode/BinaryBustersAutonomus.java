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

@Autonomous(name = "BinaryBustersAutonomus")
public class BinaryBustersAutonomus extends LinearOpMode{
    public DcMotor backRightMotor;
    public DcMotor backLeftMotor;

    public DcMotor glyphVertical;
    public Servo glyphHorizontal;

    public DcMotor relicHorizontal;

    @Override
    public void runOpMode() throws InterruptedException {
        setUp();
        backRightMotor.setPower(-0.8);
        backLeftMotor.setPower(0.8);
        sleep(800);
        backRightMotor.setPower(0);
        backLeftMotor.setPower(0);
    }

    public void setUp() {
        backRightMotor = hardwareMap.get(DcMotor.class, "backRightMotor");
        backLeftMotor = hardwareMap.get(DcMotor.class, "backLeftMotor");

        backRightMotor.setDirection(DcMotor.Direction.FORWARD);
        backLeftMotor.setDirection(DcMotor.Direction.FORWARD);

        glyphVertical = hardwareMap.get(DcMotor.class, "glyphVertical");
        glyphHorizontal = hardwareMap.get(Servo.class, "glyphHorizontal");

        glyphVertical.setDirection(DcMotor.Direction.FORWARD);
        glyphHorizontal.setPosition(0);
    }
}

package org.firstinspires.ftc.teamcode;

/**
 * Created by BinaryBusters on 10/11/17.
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
import com.qualcomm.robotcore.hardware.ColorSensor;
import android.graphics.Color;


@Autonomous(name = "BBAutonomus")
public class BinaryBustersAutonomus extends LinearOpMode{
    //Hardware variables
    public DcMotor backRightMotor;
    public DcMotor backLeftMotor;

    public DcMotor jewelLift;
    public Servo jewelLock;

    public ColorSensor colorSensor;

    //Math Variables
    static final double     COUNTS_PER_MOTOR_REV    = 424.918 ;    // eg: TETRIX Motor Encoder
    static final double     DRIVE_GEAR_REDUCTION    = 2.0 ;     // This is < 1.0 if geared UP
    static final double     WHEEL_DIAMETER_INCHES   = 3.9375 ;     // For figuring circumference
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_INCHES * 3.1415);

    public ElapsedTime currentTime = new ElapsedTime();

    public void runOpMode() throws InterruptedException {}

    public void encoderDrive(double leftInches, double rightInches, double speed) {
        int leftTarget;
        int rightTarget;

        leftTarget = backLeftMotor.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
        rightTarget = backRightMotor.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);


        backLeftMotor.setTargetPosition(leftTarget);
        backRightMotor.setTargetPosition(rightTarget);

        backLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        backLeftMotor.setPower(speed);
        backRightMotor.setPower(speed);

        while(backLeftMotor.isBusy() && backRightMotor.isBusy()) {
            telemetry.addData("Status:", leftTarget);
            telemetry.update();
        }

        backLeftMotor.setPower(0);
        backRightMotor.setPower(0);


        backLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    public float checkColor() {
        //check color in front of the robot
        float hsvValues[] = {0F,0F,0F};
        Color.RGBToHSV(colorSensor.red() * 8, colorSensor.green() * 8, colorSensor.blue() * 8, hsvValues);
        if(hsvValues[0] > 0 && hsvValues[0] < 15) {
            //red
            return 1;
        } else if(hsvValues[0] > 200) {
            //blue
            return 0;
        }
        return -1;
    }

    public void dropJewel() {
        jewelLock.setPosition(0);

        telemetry.addData("Jewel Position:", "down");
        telemetry.update();
    }

    public void liftJewel() {
        jewelLift.setPower(1.0);
        sleep(1000);
        jewelLift.setPower(-1.0);
        jewelLock.setPosition(1);

        telemetry.addData("Jewel Position:", "up");
        telemetry.update();
    }

    public void setup() {
        backRightMotor = hardwareMap.get(DcMotor.class, "backRightMotor");
        backLeftMotor = hardwareMap.get(DcMotor.class, "backLeftMotor");

        backRightMotor.setDirection(DcMotor.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotor.Direction.FORWARD);

        backRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        backRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        jewelLift = hardwareMap.get(DcMotor.class, "jewelLift");
        jewelLift.setDirection(DcMotor.Direction.FORWARD);
        jewelLock = hardwareMap.get(Servo.class, "jewelLock");

        colorSensor = hardwareMap.get(ColorSensor.class, "colorSensor");
        colorSensor.enableLed(false);
    }
}

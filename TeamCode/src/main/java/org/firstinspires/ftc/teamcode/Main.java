package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.gamepad.ButtonReader;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.gamepad.ToggleButtonReader;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Project Main")
public class Main extends LinearOpMode {

    @Override
    public void runOpMode() {
        GamepadEx gamepad = new GamepadEx(gamepad1);
        ButtonReader forward = new ButtonReader(gamepad, GamepadKeys.Button.DPAD_UP);
        ButtonReader backward = new ButtonReader(gamepad, GamepadKeys.Button.DPAD_DOWN);
        ButtonReader pumpReader = new ButtonReader(gamepad, GamepadKeys.Button.X);
        ToggleButtonReader servoReader = new ToggleButtonReader(gamepad, GamepadKeys.Button.A);

        ServoEx servo = new SimpleServo(hardwareMap, "lm servo", 0, 180);

        Motor leds = new Motor(hardwareMap, "leds");
        Motor conveyor = new Motor(hardwareMap, "conveyor");
        conveyor.setRunMode(Motor.RunMode.VelocityControl);
        conveyor.setVeloCoefficients(1, 0.05, 0);

        Motor pump = new Motor(hardwareMap, "pump");

        servo.setPosition(1);

        leds.set(1);
        waitForStart();

        if (opModeIsActive()) {
            while (opModeIsActive()) {
                servoReader.readValue();
                if (servoReader.getState()) {
                    servo.setPosition(0.85);
                } else {
                    servo.setPosition(1);
                }

                if (pumpReader.isDown()) {
                    pump.set(1);
                } else {
                    pump.set(0);
                }

                if (forward.isDown()) {
                    conveyor.set(-0.3);
                } else if (backward.isDown()) {
                    conveyor.set(0.3);
                } else {
                    conveyor.set(0);
                }
            }
        }
    }
}

/*
Apex Robotics FTC Team 3916: Main TeleOp for SkyStone season (2019-2020)

Uses a Mecanum-style drivetrain for movement.
 */

package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="FTCLib TeleOp", group="Apex Robotics 3916")
//@Disabled
public class TeleOp_FTCLib extends LinearOpMode {

    private FTCLibRobotFunctions bot = new FTCLibRobotFunctions();

    @Override
    public void runOpMode() throws InterruptedException {
        //Initialize code and variables

        bot.init(hardwareMap, FTCLibRobotFunctions.DriveType.Mecanum);

        GamepadEx Gamepad1 = new GamepadEx(gamepad1);
        GamepadEx Gamepad2 = new GamepadEx(gamepad2);

        final double STICK_DEAD_ZONE = 0.1;
        double x = 0;
        double y = 0;
        double z = 0;

        //Wait for the driver to hit Start
        waitForStart();

        while (opModeIsActive()) {


            if (Gamepad1.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER) > 0.1) {
                //update z with left trigger, negative since left
                z = -1 * Gamepad1.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER);

            } else if (Gamepad1.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER) > 0.1 ) {
                //update z with right trigger
                z = Gamepad1.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER);

            } else {
                z = 0;
            }

            if (Math.abs(Gamepad1.getLeftY()) > STICK_DEAD_ZONE) {
                //update y with current y position
                y = Gamepad1.getLeftY();
            } else {
                y = 0;
            }
            if (Math.abs(Gamepad1.getLeftX()) > STICK_DEAD_ZONE) {
                //update x with current x position
                x = Gamepad1.getLeftX();
            } else {
                x = 0;
            }

            //Send the X, Y, and rotation (Z) to the mecanum method
            bot.mecanumDrivetrain.driveRobotCentric(x,y,z);

            //Add a little telemetry
            telemetry.addData("Status", "power: x:" + x + " y:" + y + " z:" + z);
            telemetry.update();


        }
    }
}
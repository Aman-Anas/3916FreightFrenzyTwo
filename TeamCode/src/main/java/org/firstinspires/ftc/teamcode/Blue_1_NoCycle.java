package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

/**
 * Autonomous Path
 * @author Aman Anas
 * @author Nathan Battle
 */
@Disabled
@Autonomous(name="Blue_1_NoCycle", group="Apex Robotics 3916")
public class Blue_1_NoCycle extends LinearOpMode {

    //CameraFunctions botCamera = new CameraFunctions();
    //RingDeterminationPipeline ringPipeline = new RingDeterminationPipeline();

    @Override
    public void runOpMode() throws InterruptedException {

        //Initialize telemetry and dashboard
        //telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        FtcDashboard dashboard = FtcDashboard.getInstance();

        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        FTCLibRobotFunctions bot = new FTCLibRobotFunctions();
        bot.initBot(hardwareMap);

        bot.slideMotor.encoder.reset();

        //Initialize the camera and vision
        //botCamera.initVision(hardwareMap, ringPipeline);

        //Construct trajectories for the robot to follow.
        //https://learnroadrunner.com/trajectorybuilder-functions.html
        TrajectorySequence traj1 = drive.trajectorySequenceBuilder(new Pose2d(0, 63.0, 1.5707963267948966))
                .lineToLinearHeading(new Pose2d(-52.0, 53.0, 1.5707963267948966))

                .lineToLinearHeading(new Pose2d(-57.0, 59.1, 1.5707963267948966 + Math.toRadians(-40)))

                .build();

        TrajectorySequence traj2 = drive.trajectorySequenceBuilder(new Pose2d(-57.0, 79.1, 1.5707963267948966+Math.toRadians(-40)))
                .lineToLinearHeading(new Pose2d(-28.7, 39.1, 1.5707963267948966+Math.toRadians(46)))
                .build();
        TrajectorySequence traj22 = drive.trajectorySequenceBuilder(new Pose2d(-28.7, 39.1, 1.5707963267948966+Math.toRadians(46)))
                .lineToLinearHeading(new Pose2d(-30.0, 42.0, 1.5707963267948966+Math.toRadians(46)))
                .build();
        TrajectorySequence traj3 = drive.trajectorySequenceBuilder(new Pose2d(-30.0, 42.0, 1.5707963267948966+Math.toRadians(46)))
                .lineToLinearHeading(new Pose2d(-54.0, 55.0, 1.5707963267948966+Math.toRadians(0)))
                .build();
/*/*
        TrajectorySequence traj4 = drive.trajectorySequenceBuilder(new Pose2d(41.0, 49.0, Math.toRadians(30)))
                .splineToLinearHeading(new Pose2d(39.0, 38.0, 0.0), 0.0)
                .lineToConstantHeading(new Vector2d(60.0, 38.0))
                .build();

*/

        //Wait until the driver presses start
        waitForStart();

        //Cache the camera analysis and print it in telemetry
        //double objAnalysis = ringPipeline.getAnalysis();
        //String objPosition = ringPipeline.position.toString();
        //telemetry.addData("Analysis: ", objAnalysis);
        //telemetry.addData("Position: ", objPosition);
        //telemetry.update();

        while (opModeIsActive() && !isStopRequested()){
            bot.runIntakeArmServo(TeleOpConfig.GATE_SERVO_MIN);
            bot.runIntakeBucketServo(TeleOpConfig.BUCKET_SERVO_MIN);
            //Follow the trajectory we defined earlier
            drive.followTrajectorySequence(traj1);

            bot.runDuckMotor(1);
            sleep(3000);
            bot.runDuckMotor(0);
            // drive

            drive.followTrajectorySequence(traj2);
            // drop off freight
            //bot.deliverFreight();
            bot.runIntakeBucketServo(TeleOpConfig.BUCKET_SERVO_MIN);
            bot.runIntakeMotor(1);
            bot.runSlideMotor(1);
            sleep(1350);
            bot.runSlideMotor(0);
            sleep(500);
            bot.runIntakeArmServo(TeleOpConfig.GATE_SERVO_MAX);
            sleep(500);
            bot.runIntakeBucketServo(TeleOpConfig.BUCKET_SERVO_MAX);
            bot.runIntakeArmServo(TeleOpConfig.GATE_SERVO_MIN);
            drive.followTrajectorySequence(traj22);
            bot.runSlideMotor(-1);
            bot.runIntakeMotor(0);
            sleep(1150);

            bot.runSlideMotor(0);

            // drive
            drive.followTrajectorySequence(traj3);
            // pick up freight and park
            //bot.runIntakeMotor(1);
            //sleep(1000);
            //bot.runIntakeMotor(0);
            //drive.followTrajectorySequence(traj4);

            //wait this long after move
            sleep(2000);
            //stop OpMode
            requestOpModeStop();
        }


    }
}
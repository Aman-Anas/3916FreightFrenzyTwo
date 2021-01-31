package org.firstinspires.ftc.teamcode;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

@Config
public class TeleOpConfig {

    //Variables to be tuned from FTCDashboard
    public static double STICK_DEAD_ZONE = 0.1;
    public static double FLYWHEEL_KP = 16;
    public static double FLYWHEEL_KI = 0;
    public static double FLYWHEEL_KD = 0;
    public static double WOBBLE_ARM_MULTIPLIER = 0.7;
    public static double WOBBLE_SERVO_MULTIPLIER = 1.5;
    public static double TRANSFER_SERVO_MULTIPLIER = 1;

}

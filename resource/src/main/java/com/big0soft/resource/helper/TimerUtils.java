package com.big0soft.resource.helper;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.big0soft.resource.timer.TimerModel;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class TimerUtils {
    private static Timer timer;

    public static void timer(Activity activity, long expireDate, OnTimerRateListener onTimerRateListener) {
        timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                Timer internalTimer = new Timer();
                internalTimer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        if (timer == null) {
                            internalTimer.cancel();
                            return;
                        }
                        if (expireDate < System.currentTimeMillis()) {
                            internalTimer.cancel();
                            timer.cancel();
                            return;
                        }
                        activity.runOnUiThread(() -> updateTimer(expireDate, onTimerRateListener));
                    }
                }, 0, 1000);

                new Handler(activity.getMainLooper()).postDelayed(internalTimer::cancel, 45000);
            }
        }, 0, 45000);
    }

    public static void stopTimer() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    public static void timer(long expireDate, OnTimerRateListener onTimerRateListener) {
        timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                Timer internalTimer = new Timer();
                internalTimer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        if (timer == null) {
                            internalTimer.cancel();
                            timer.cancel();
                            return;
                        }
//                        if (expireDate < System.currentTimeMillis()) {
//                            internalTimer.cancel();
//                            timer.cancel();
//                            return;
//                        }
                        updateTimer(expireDate, onTimerRateListener);
                    }
                }, 0, 1000);

                new Handler(Looper.getMainLooper()).postDelayed(internalTimer::cancel, 45000);
            }
        }, 0, 45000);
    }


    private static void updateTimer(long expireDate, OnTimerRateListener onTimerRateListener) {
        Log.i(TAGs.TAG, "updateTimer: "+expireDate);
        long currentTime = System.currentTimeMillis();
        long remainingTime = expireDate - currentTime;

        long remainingDays = Math.abs(remainingTime / (86400_000));
        long remainingHours = Math.abs(remainingTime / (3600_000) % 24);
        long remainingMinutes = Math.abs(remainingTime / (60_000) % 60);
        long remainingSeconds = Math.abs(remainingTime / 1000 % 60);

        String days = (remainingDays < 10) ? "0" + remainingDays : String.valueOf(remainingDays);
        String hours = (remainingHours < 10) ? "0" + remainingHours : String.valueOf(remainingHours);
        String minutes = (remainingMinutes < 10) ? "0" + remainingMinutes : String.valueOf(remainingMinutes);
        String seconds = (remainingSeconds < 10) ? "0" + remainingSeconds : String.valueOf(remainingSeconds);

        onTimerRateListener.onTimerRate(days, hours, minutes, seconds);

        // Cancel the timer after 45 seconds

    }
    public static long toMs(long days, long hours, long minutes, long seconds) {
        long remainingDays = TimeUnit.DAYS.toMillis(days);
        long remainingHours = TimeUnit.HOURS.toMillis(hours);
        long remainingMinutes = TimeUnit.MINUTES.toMillis(minutes);
        long remainingSeconds = TimeUnit.SECONDS.toMillis(seconds);
        return remainingDays + remainingHours + remainingMinutes + remainingSeconds;
    }

    public static TimerModel TimerToTimerModel(long expireDate) {
        long currentTime = System.currentTimeMillis();
        long remainingTime = expireDate - currentTime;

        long remainingDays = Math.abs(remainingTime / (86400_000));
        long remainingHours = Math.abs(remainingTime / (3600_000) % 24);
        long remainingMinutes = Math.abs(remainingTime / (60_000) % 60);
        long remainingSeconds = Math.abs(remainingTime / 1000 % 60);

        String days = (remainingDays < 10) ? "0" + remainingDays : String.valueOf(remainingDays);
        String hours = (remainingHours < 10) ? "0" + remainingHours : String.valueOf(remainingHours);
        String minutes = (remainingMinutes < 10) ? "0" + remainingMinutes : String.valueOf(remainingMinutes);
        String seconds = (remainingSeconds < 10) ? "0" + remainingSeconds : String.valueOf(remainingSeconds);
        return new TimerModel(days,hours,minutes,seconds);

    }

    public interface OnTimerRateListener {
        void onTimerRate(String days, String hours, String minutes, String seconds);
    }
}

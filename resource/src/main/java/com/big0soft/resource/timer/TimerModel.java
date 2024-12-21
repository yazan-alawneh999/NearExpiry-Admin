package com.big0soft.resource.timer;

import android.text.TextUtils;

public class TimerModel {
    private String days;
    private String hours;
    private String minutes;
    private String seconds;

    public final static TimerModel defaultTimer = new TimerModel("00", "00", "00", "00");

    public TimerModel() {

    }



    public String getDays() {
        return days;
    }

    public TimerModel setDays(String days) {
        this.days = days;
        return this;
    }

    public String getHours() {
        return hours;
    }

    public TimerModel setHours(String hours) {
        this.hours = hours;
        return this;
    }

    public String getMinutes() {
        return minutes;
    }

    public TimerModel setMinutes(String minutes) {
        this.minutes = minutes;
        return this;
    }

    public String getSeconds() {
        return seconds;
    }

    public TimerModel setSeconds(String seconds) {
        this.seconds = seconds;
        return this;
    }

    public TimerModel(String days, String hours, String minutes, String seconds) {
        this.days = days;
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    public boolean isZeroDays(){
        return isZero(days);
    }
    public boolean isZeroHours(){
        return isZero(hours);
    }
    public boolean isZeroMinutes(){
        return isZero(minutes);
    }
    public boolean isZeroSeconds(){
        return isZero(seconds);
    }


    public boolean isZero(String value){
        return value.equals("00")||value.equals("0") || TextUtils.isEmpty(value);
    }

    @Override
    public String toString() {
        return "TimerModel{" +
                "days='" + days + '\'' +
                ", hours='" + hours + '\'' +
                ", minutes='" + minutes + '\'' +
                ", seconds='" + seconds + '\'' +
                '}';
    }
}

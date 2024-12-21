package com.big0soft.resource.helper;

import androidx.core.util.Pair;

import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateValidatorPointForward;
import com.google.android.material.datepicker.MaterialDatePicker;

public class CalenderHelper {

    public static MaterialDatePicker<Pair<Long, Long>> createCalendarMultiChoice() {
        CalendarConstraints.Builder constraintsBuilder = new CalendarConstraints.Builder();
        constraintsBuilder.setValidator(DateValidatorPointForward.now());
        MaterialDatePicker.Builder<Pair<Long, Long>> builder = MaterialDatePicker.Builder.dateRangePicker();
        builder.setTitleText("time end key");
        builder.setCalendarConstraints(constraintsBuilder.build());
        return builder.build();
    }

    public static MaterialDatePicker<Long> createSimpleCalendar(String title) {
        CalendarConstraints.Builder constraintsBuilder = new CalendarConstraints.Builder()
                .setValidator(DateValidatorPointForward.now())
                .setOpenAt(System.currentTimeMillis());

        MaterialDatePicker.Builder<Long> builder = MaterialDatePicker.Builder.datePicker()
                .setTitleText(title)
                .setCalendarConstraints(constraintsBuilder.build());
        return builder.build();
    }

    public static MaterialDatePicker<Long> createSimpleCalendar() {
        return createSimpleCalendar("Expire Date");
    }

}

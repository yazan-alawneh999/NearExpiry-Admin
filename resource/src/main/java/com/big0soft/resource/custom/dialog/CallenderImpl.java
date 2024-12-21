package com.big0soft.resource.custom.dialog;

import androidx.core.util.Pair;

import com.google.android.material.datepicker.MaterialDatePicker;

public class CallenderImpl implements ICallender<Pair<Long, Long>> {

    @Override
    public MaterialDatePicker<Pair<Long, Long>> materialCallender() {
        return MaterialDatePicker.Builder
                .dateRangePicker()
                .build();
    }
}

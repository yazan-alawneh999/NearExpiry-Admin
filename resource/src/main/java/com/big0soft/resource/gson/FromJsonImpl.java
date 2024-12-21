package com.big0soft.resource.gson;

import com.big0soft.resource.data.FromJson;
import com.google.gson.Gson;

public class FromJsonImpl<T extends FromJson> implements FromJson {
    private final Class<T> mClass;

    public FromJsonImpl(Class<T> mClass) {
        this.mClass = mClass;
    }

    @Override
    public FromJson fromJson(String data) {
        return new Gson().fromJson(data,mClass);
    }
}

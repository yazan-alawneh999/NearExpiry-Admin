package com.big0soft.resource.gson;

import com.big0soft.resource.data.ToJson;
import com.google.gson.Gson;

public class ToJsonImpl<T extends ToJson> implements ToJson {
    private final T toJsonImpl;

    public ToJsonImpl(T toJsonImpl) {
        this.toJsonImpl = toJsonImpl;
    }

    @Override
    public String toJson() {
        return new Gson().toJson(toJsonImpl);
    }
}

package com.big0soft.resource.model;

import androidx.annotation.IdRes;

import com.big0soft.resource.data.FromJson;
import com.big0soft.resource.data.ToJson;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

@Deprecated
public class Response implements FromJson, ToJson {


    @SerializedName("message")
    private String message;
    @IdRes
    private transient int resMessage;
    @SerializedName(value = "state_code")
    private int responseCode;

    @SerializedName(value = "isError")
    private boolean error;

    public Response() {

    }

    public Response(String message, int responseCode, boolean error) {
        this.message = message;
        this.responseCode = responseCode;
        this.error = error;
    }

    public Response(String message, boolean error) {
        this.message = message;
        this.error = error;
    }

    public Response(int resMessage, boolean error) {
        this.resMessage = resMessage;
        this.error = error;
    }

    public Response(boolean error) {
        this.error = error;
    }

    public String message() {
        return message;
    }

    public Response setMessage(String message) {
        this.message = message;
        return this;
    }

    public boolean error() {
        return error;
    }

    public Response setError(boolean error) {
        this.error = error;
        return this;
    }

    public int responseCode() {
        return responseCode;
    }

    public Response setResponseCode(int responseCode) {
        this.responseCode = responseCode;
        return this;
    }

    @Override
    public String toString() {
        return "Response{" +
                "message='" + message + '\'' +
                ", resMessage=" + resMessage +
                ", error=" + error +
                '}';
    }

    @Override
    public FromJson fromJson(String data) {
        return new Gson().fromJson(data, getClass());
    }

    @Override
    public String toJson() {
        return new Gson().toJson(this);
    }

}

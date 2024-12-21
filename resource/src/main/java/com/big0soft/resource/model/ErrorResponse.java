package com.big0soft.resource.model;

import com.big0soft.resource.data.FromJson;
import com.big0soft.resource.data.ToJson;
import com.big0soft.resource.gson.FromJsonImpl;
import com.big0soft.resource.gson.ToJsonImpl;
import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;
import java.util.Objects;

public final class ErrorResponse implements FromJson, ToJson {
//    @SerializedName("timestamp")
    private transient String timestamp;
    @SerializedName("state_code")
    private  int status;
    @SerializedName("isError")
    private  boolean isError;

    /**
     * error type like INTERNAL_SERVER_ERROR
     */
    @SerializedName("error")
    private  String error;
    @SerializedName("message")
    private  String message;

    public ErrorResponse(String timestamp, int status, boolean isError, String error, String message) {
        this.timestamp = timestamp;
        this.status = status;
        this.isError = isError;
        this.error = error;
        this.message = message;
    }

    public ErrorResponse() {

    }

    public ErrorResponse(String message) {
        this.message = message;
        this.isError = true;
    }

    public ErrorResponse(int status, String error, String message) {
        this("", status, true, error, message);
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isError() {
        return isError;
    }

    public void setError(boolean error) {
        isError = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        ErrorResponse that = (ErrorResponse) obj;
        return Objects.equals(this.timestamp, that.timestamp) &&
                this.status == that.status &&
                this.isError == that.isError &&
                Objects.equals(this.error, that.error) &&
                Objects.equals(this.message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timestamp, status, isError, error, message);
    }

    @Override
    public String toString() {
        return "ErrorResponse[" +
                "timestamp=" + timestamp + ", " +
                "status=" + status + ", " +
                "isError=" + isError + ", " +
                "error=" + error + ", " +
                "message=" + message + ']';
    }


    @Override
    public FromJson fromJson(String data) {
        return new FromJsonImpl<>(getClass()).fromJson(data);
    }

    @Override
    public String toJson() {
        return new ToJsonImpl<>(this).toJson();
    }
}

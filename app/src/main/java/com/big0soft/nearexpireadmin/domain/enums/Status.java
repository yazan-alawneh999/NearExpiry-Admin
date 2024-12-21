package com.big0soft.nearexpireadmin.domain.enums;

import java.util.Objects;

public enum Status {
    SUCCESS, ERROR, LOADING,DONE;
    public static boolean isLoading(Status status) {
        return Objects.equals(status, Status.LOADING);
    }

    public static boolean isSuccess(Status status) {
        return Objects.equals(status,Status.SUCCESS);
    }

    public static boolean isError(Status status) {
        return Objects.equals(status,Status.ERROR);
    }

    public static boolean isDone(Status status) {
        return !isLoading(status) ;
    }

}

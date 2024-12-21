package com.big0soft.nearexpireadmin.data.validation;

import androidx.annotation.Nullable;

import com.big0soft.nearexpireadmin.R;

public class ValidateResult {
    private boolean success;
    private int message;

    public static ValidateResult successResult = new ValidateResult(true, 0);
    public static ValidateResult progressResult = new ValidateResult(true, 0);

    public ValidateResult(boolean isSuccess, Integer message) {
        this.success = isSuccess;
        this.message = message;
    }

    public static ValidateResult invalidProvider() {
        return new ValidateResult(false, R.string.invalid_provider);
    }

    public boolean isSuccess() {
        return success;
    }

    public int getMessage() {
        return message;
    }

    public void setMessage(int message) {
        this.message = message;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        ValidateResult that = (ValidateResult) obj;
        return this.success == that.success && this.message == that.message;
    }
}

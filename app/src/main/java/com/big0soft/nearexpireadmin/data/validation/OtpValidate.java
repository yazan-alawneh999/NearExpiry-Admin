package com.big0soft.nearexpireadmin.data.validation;

import com.big0soft.nearexpireadmin.domain.validation.BaseValidator;

public class OtpValidate extends BaseValidator {
    public static final int OTP_LENGTH =6;

    @Override
    public ValidateResult validate(String input) {
        return input.length() == OTP_LENGTH ? ValidateResult.successResult : new ValidateResult(false, com.big0soft.resource.R.string.error_otp_unacceptable_format);
    }
}
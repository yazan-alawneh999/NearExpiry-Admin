package com.big0soft.nearexpireadmin.data.validation;

import com.big0soft.nearexpireadmin.domain.validation.BaseValidator;

import java.util.regex.Pattern;

public class EmailValidator  extends BaseValidator {
    public static final Pattern EMAIL_ADDRESS
            = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
    );

    @Override
    public ValidateResult validate(String email) {
        boolean isValid = EMAIL_ADDRESS.matcher(email).matches();
        return new ValidateResult(isValid, isValid ? 0 : com.big0soft.resource.R.string.invalid_email);
    }


}

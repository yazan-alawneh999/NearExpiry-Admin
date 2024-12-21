package com.big0soft.nearexpireadmin.data.validation;

import com.big0soft.nearexpireadmin.domain.validation.BaseValidator;
import com.big0soft.resource.utils.RegularUtils;

public class PasswordValidator extends BaseValidator {

    public PasswordValidator() {
    }

    @Override
    public ValidateResult validate(String password) {
        if ( password.length() > 20) {
            return new ValidateResult(false, com.big0soft.resource.R.string.password_long);
        }
        boolean isValid = RegularUtils.isPassword(password);
        return new ValidateResult(isValid, isValid ? 0 : com.big0soft.resource.R.string.password_is_weak);
    }
}

package com.big0soft.nearexpireadmin.data.validation;

import com.big0soft.nearexpireadmin.R;
import com.big0soft.nearexpireadmin.domain.validation.BaseValidator;
import com.big0soft.resource.utils.StrUtils;

public class EmptyValidator extends BaseValidator {

    public EmptyValidator() {
    }

    @Override
    public ValidateResult validate(String input) {
        boolean isValid = !StrUtils.isEmpty(input);
        return new ValidateResult(isValid, isValid ? 0 : R.string.empty_filed);
    }
}
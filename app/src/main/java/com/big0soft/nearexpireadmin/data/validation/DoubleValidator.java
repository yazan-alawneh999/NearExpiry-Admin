package com.big0soft.nearexpireadmin.data.validation;

import com.big0soft.nearexpireadmin.domain.validation.BaseValidator;

public class DoubleValidator  extends BaseValidator {
    @Override
    public ValidateResult validate(String input) {
        if (input.length() > 6) {
            return new ValidateResult(false, com.big0soft.resource.R.string.invalid_double);
        }


        try {
            Double.parseDouble(input);
            return ValidateResult.successResult;
        } catch (Exception e) {
            return new ValidateResult(false, com.big0soft.resource.R.string.invalid_double);
        }


    }

}

package com.big0soft.nearexpireadmin.data.validation;

import com.big0soft.nearexpireadmin.R;
import com.big0soft.nearexpireadmin.domain.validation.BaseValidator;
import static com.big0soft.resource.utils.RegularUtils.isPhoneNumber;


public class PhoneValidator extends BaseValidator {
    @Override
    public ValidateResult validate(String input) {

        return isPhoneNumber(input) ? ValidateResult.successResult :new ValidateResult(false, R.string.invalid_phone);
    }
}

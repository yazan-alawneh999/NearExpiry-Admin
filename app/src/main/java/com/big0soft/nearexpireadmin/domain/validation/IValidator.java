package com.big0soft.nearexpireadmin.domain.validation;

import com.big0soft.nearexpireadmin.data.validation.ValidateResult;

public interface IValidator {
    ValidateResult validate(String input);
}

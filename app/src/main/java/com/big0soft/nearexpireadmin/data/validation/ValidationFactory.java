package com.big0soft.nearexpireadmin.data.validation;

import com.big0soft.nearexpireadmin.R;
import com.big0soft.nearexpireadmin.domain.enums.AuthProvider;
import com.big0soft.nearexpireadmin.domain.validation.BaseValidator;

import java.util.HashMap;
import java.util.Map;

public class ValidationFactory {
    static BaseValidator[] emailValidators;
    static BaseValidator[] passwordValidators;
    static BaseValidator[] otpValidators;
    static BaseValidator[] doubleInputValidators;
    static BaseValidator[] stringInputValidators;

    static Map<AuthProvider, BaseValidator[]> mapValidators;
    static Map<AuthProvider, BaseValidator[]> mapPasswordValidate;
    static {
        mapValidators = new HashMap<>();
        mapPasswordValidate = new HashMap<>();

        emailValidators = new BaseValidator[]{
                new EmptyValidator(),
                new EmailValidator()
        };
        otpValidators = new BaseValidator[]{
                new EmptyValidator(),
                new OtpValidate()
        };
        doubleInputValidators = new BaseValidator[]{
                new EmptyValidator(),
                new DoubleValidator()
        };
        stringInputValidators = new BaseValidator[]{
                new EmptyValidator()
        };
        mapValidators.put(AuthProvider.EMAIL, emailValidators);
        passwordValidators = new BaseValidator[]{
                new EmptyValidator(),
                new PasswordValidator()
        };
        mapPasswordValidate.put(AuthProvider.EMAIL, passwordValidators);
    }

    public static ValidateResult validatePassword(AuthProvider authProvider, String provider) {

        BaseValidator[] validators = mapPasswordValidate.get(authProvider);

        if (validators == null) {
            return new ValidateResult(false, R.string.invalid_provider);
        }
        for (BaseValidator validator : validators) {
            ValidateResult result = validator.validate(provider);
            if (!result.isSuccess()) {
                return result;
            }
        }
        return ValidateResult.successResult;
    }


    public static ValidateResult validate(AuthProvider authProvider, String provider) {

        BaseValidator[] validators = mapValidators.get(authProvider);

        if (validators == null) {
            return ValidateResult.invalidProvider();
        }
        for (BaseValidator validator : validators) {
            ValidateResult result = validator.validate(provider);
            if (!result.isSuccess()) {
                return result;
            }
        }
        return ValidateResult.successResult;
    }

    public static ValidateResult validateOtp(String otp) {
        for (BaseValidator validator : otpValidators) {
            ValidateResult validate = validator.validate(otp);
            if (!validate.isSuccess()) {
                return validate;
            }
        }
        return ValidateResult.successResult;
    }

    public static ValidateResult validateEmail(String email) {
        for (BaseValidator validator : emailValidators) {
            if (!validator.validate(email).isSuccess()) {
                return validator.validate(email);
            }

        }
        return ValidateResult.successResult;
    }

    public static ValidateResult validatePassword(String password) {
        for (BaseValidator validator : passwordValidators) {
            if (!validator.validate(password).isSuccess()) {
                return validator.validate(password);
            }

        }
        return ValidateResult.successResult;
    }

    public static ValidateResult validateConfirmPassword(String password, String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            return new ValidateResult(false, com.big0soft.resource.R.string.password_not_equals);
        }

        return ValidateResult.successResult;
    }

    public static ValidateResult validateDoubleInput(String input) {
        for (BaseValidator validator : doubleInputValidators) {
            if (!validator.validate(input).isSuccess()) {
                return validator.validate(input);
            }
        }
        return ValidateResult.successResult;
    }

    public static ValidateResult validateStringInput(String input) {
        for (BaseValidator validator : stringInputValidators) {
            if (!validator.validate(input).isSuccess()) {
                return validator.validate(input);
            }
        }
        return ValidateResult.successResult;
    }
}

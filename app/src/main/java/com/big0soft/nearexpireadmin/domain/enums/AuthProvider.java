package com.big0soft.nearexpireadmin.domain.enums;

import static com.big0soft.resource.utils.RegularUtils.isEmail;
import static com.big0soft.resource.utils.RegularUtils.isPhoneNumber;

public enum AuthProvider {
    EMAIL,PHONE_NUMBER;

    public static AuthProvider getAuthProviderByPattern(String pattern) {
        AuthProvider authProvider = null;
        if (isEmail(pattern)) {
            authProvider = EMAIL;
        } else if (isPhoneNumber(pattern)) {
            authProvider = PHONE_NUMBER;
        }
        return authProvider;
    }
}

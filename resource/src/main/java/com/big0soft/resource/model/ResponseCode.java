package com.big0soft.resource.model;

public final class ResponseCode {
    public static final int SUCCESS = 200;
    public static final int UNKNOWN_ERROR = 1000;
    public static final int ERROR_IN_APP = 1001;
    public static final int PROCESS_CANCEL = 1002;
    public static final int SERVER_IS_IN_MAINTENANCE = 1003;

    public static final int UUID_INVALID = 1200;
    public static final int UUID_NULL = 1201;


    public static final int EMAIL_CODE_START = 1100;
    /**
     * The email address is badly formatted.
     */
    public static final int INVALID_EMAIL = 1100;
    public static final int EMAIL_ALREADY_EXISTS = 1101;
    /**
     * An account already exists with the same email address but different sign-in credentials. Sign in using a provider associated with this email address.
     */
    public static final int EMAIL_USE_IN_DIFFERENT_PROVIDER = 1102;
    public static final int EMAIL_IS_DISABLED = 1103;
    public static final int EMAIL_IS_DELETED = 1104;
    public static final int USERNAME_NOT_REGISTERED = 1300;
    public static final int USERNAME_INVALID = 1301;
    public static final int USERNAME_EMPTY = 1302;
    public static final int PASSWORD_EMPTY = 1400;
    public static final int PASSWORD_NOT_MATCH = 1401;
    public static final int PASSWORD_IS_WEAK = 1402;
    /**
     * The password is invalid or the user does not have a password.
     */
    public static final int PASSWORD_IS_WRONG = 1403;

    public static final int ENTER_ROOM_SUCCESS = 1500;

    public static final int ROOM_NOT_FOUND = 1600;
    public static final int ROOM_ENTER_PLAYER_EXITED = 1601;
    public static final int ROOM_OWNER_EXITED = 1602;

    public static final int CREDENTIAL_CODE_START = 1700;
    /**
     * The supplied auth credential is malformed or has expired.
     */
    public static final int CREDENTIAL_EXPIRED = 1700;
    public static final int ERROR_IN_EMAIL_CREDENTIAL = 1701;
    /**
     * The supplied credentials do not correspond to the previously signed in user.
     */
    public static final int CREDENTIAL_MISMATCH = 1702;
    /**
     * This operation is sensitive and requires recent authentication. Log in again before retrying this request.
     */
    public static final int CREDENTIAL_REQUIRES_RE_LOGIN = 1703;
    public static final int CREDENTIAL_ERROR = 1704;


    public static boolean isUsernameError(int code) {
        return code >= 1300 && code <= 1399;
    }

    public static boolean isPasswordError(int code) {
        return code >= 1400 && code <= 1499;
    }
}

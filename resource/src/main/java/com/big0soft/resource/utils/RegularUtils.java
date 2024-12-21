package com.big0soft.resource.utils;


import java.util.regex.Pattern;

public class RegularUtils {


    public static final String REGEX_USERNAME_1 = "^[a-zA-Z]\\w{5,20}$";
    public static final String REGEX_USERNAME_2 = "^[A-Za-z][A-Za-z0-9]*$";
    /**
     * This pattern matches strings that start with either an English letter or an Arabic letter
     * followed by a mix of English letters, Arabic letters,
     * , and numbers, and does not contain any spaces or code elements.
     */
    public static final String REGEX_USERNAME_3 = "^[\\p{L}\\p{N}]+$";
    public static final String REGEX_USERNAME_4 = "^[\\p{L}\\p{N} ]+$";
    public static final String REGEX_USERNAME_5 = "^[\\u0621-\\u064A\\u0660-\\u0669 ]+$";
    public static final String PHONE_NUMBER_PATTERN = "^\\+?[0-9]{10,15}$";

    public static final String REGEX_PASSWORD = "^[a-zA-Z0-9]{6,20}$";


    public static final String REGEX_MOBILE = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";

    public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";


    public static final String REGEX_CHINESE = "^[一-龥],*$";


    public static final String REGEX_ID_CARD = "(^\\d{18}$)|(^\\d{15}$)";


    public static final String REGEX_URL = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";


    public static final String REGEX_IP_ADDR = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)";
    private static final String REGEX_HEX_COLOR = "^#([a-fA-F0-9]{6}|[a-fA-F0-9]{3})$";


    public static boolean isFollowPattern(String pattern, String input) {
        return Pattern.matches(pattern, input);
    }

    public static boolean isUsername(String username) {
        return Pattern.matches(REGEX_USERNAME_1, username);
    }

    public static boolean isUsernameNumbers(String username) {
        return Pattern.matches(REGEX_USERNAME_2, username);
    }


    public static boolean isPassword(String password) {
        return Pattern.matches(REGEX_PASSWORD, password);
    }

    public static boolean isPhoneNumber(String phone) {
        return isFollowPattern(PHONE_NUMBER_PATTERN, phone);
    }


    public static boolean isMobile(String mobile) {
        return Pattern.matches(REGEX_MOBILE, mobile);
    }


    public static boolean isEmail(String email) {
        return Pattern.matches(REGEX_EMAIL, email);
    }


    public static boolean isChinese(String chinese) {
        return Pattern.matches(REGEX_CHINESE, chinese);
    }

    public static String extractEmailUsername(String email) {
        if (!isEmail(email)) {
            throw new RuntimeException("invalid email");
        }
        return email.split("@")[0];
    }

    public static String parseUsernameToEmail(String username) {

        return parseUsernameToEmail(username, "big0soft");
    }

    public static String parseUsernameToEmail(String username, String domain) {
        String email = username + "@" + domain + ".com";
        if (!isEmail(email)) {
            throw new RuntimeException("invalid email");
        }
        return email;
    }


    public static boolean isIDCard(String idCard) {
        return Pattern.matches(REGEX_ID_CARD, idCard);
    }


    public static boolean isUrl(String url) {
        return Pattern.matches(REGEX_URL, url);
    }


    public static boolean isIPAddr(String ipAddr) {
        return Pattern.matches(REGEX_IP_ADDR, ipAddr);
    }

    public static boolean isHexColor(String src) {
        return Pattern.matches(REGEX_HEX_COLOR, src);
    }
}
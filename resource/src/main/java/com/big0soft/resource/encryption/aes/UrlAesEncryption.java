package com.big0soft.resource.encryption.aes;

import com.big0soft.resource.encryption.CustomKeyEncoder;

import java.security.GeneralSecurityException;

public class UrlAesEncryption implements CustomKeyEncoder {
    private final String password;

    public UrlAesEncryption(String password) {
        this.password = password;
    }



    @Override
    public String decode(String encodedKey) {
        return decode(password, encodedKey);
    }

    @Override
    public String decode(String password, String encodedKey) {
        try {
            return AESCrypt2.decrypt(password, encodedKey);
        } catch (GeneralSecurityException e) {
            return "error: " + e.getMessage();
        }
    }

    @Override
    public String encode(CharSequence rawKey) {
        return encode(password, rawKey);
    }

    @Override
    public String encode(String password, CharSequence rawKey) {
        try {
            return AESCrypt2.encrypt(password, rawKey.toString());
        } catch (GeneralSecurityException e) {
            return "error: " + e.getMessage();
        }
    }

    @Override
    public boolean matches(CharSequence rawKey, String encodedKey) {
        return matches(password, rawKey, encodedKey);
    }

    @Override
    public boolean matches(String password, CharSequence rawKey, String encodedKey) {
        try {
            return AESCrypt2.decrypt(password, encodedKey).equals(rawKey.toString());
        } catch (GeneralSecurityException e) {
            System.out.println("error: " + e.getMessage());
            return false;
        }
    }

}

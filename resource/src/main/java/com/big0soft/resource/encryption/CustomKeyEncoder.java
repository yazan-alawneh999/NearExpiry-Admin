package com.big0soft.resource.encryption;


public interface CustomKeyEncoder {

    String decode(String encodedKey);

    String decode(String password, String encodedKey);

    String encode(CharSequence rawKey);

    String encode(String password, CharSequence rawKey);

    boolean matches(CharSequence rawKey, String encodedKey);
    boolean matches(String password,CharSequence rawKey, String encodedKey);


}

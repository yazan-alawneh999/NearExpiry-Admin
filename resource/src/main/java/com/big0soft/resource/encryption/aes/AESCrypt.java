package com.big0soft.resource.encryption.aes;


import com.big0soft.resource.BuildConfig;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

final class AESCrypt {

    //AESCrypt-ObjC uses CBC and PKCS7Padding
    private static final String AES_MODE = "AES/CBC/PKCS5Padding";
    private static final String CHARSET = "UTF-8";

    //AESCrypt-ObjC uses SHA-256 (and so a 256-bit key)
    private static final String HASH_ALGORITHM = "SHA-256";

    //AESCrypt-ObjC uses blank IV (not the best security, but the aim here is compatibility)
    private static final byte[] ivBytes = {0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00};

    //togglable log option (please turn off in live!)
    public static boolean DEBUG_LOG_ENABLED = !BuildConfig.DEBUG;

    private static final char[] BASE64_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+!".toCharArray();


    /**
     * Generates SHA256 hash of the password which is used as key
     *
     * @param password used to generated key
     * @return SHA256 of the password
     */
    private static SecretKeySpec generateKey(final String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        final MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
        byte[] bytes = password.getBytes(StandardCharsets.UTF_8);
        digest.update(bytes, 0, bytes.length);
        byte[] key = digest.digest();

        log("SHA-256 key ", key);
        return new SecretKeySpec(key, "AES");
    }


    /**
     * Encrypt and encode message using 256-bit AES with key generated from password.
     *
     * @param password used to generated key
     * @param message  the thing you want to encrypt assumed String UTF-8
     * @return Base64 encoded CipherText
     * @throws GeneralSecurityException if problems occur during encryption
     */
    public static String encrypt(final String password, String message)
            throws GeneralSecurityException {

        try {
            final SecretKeySpec key = generateKey(password);

            log("message", message);

            byte[] cipherText = encrypt(key, ivBytes, message.getBytes(CHARSET));

            //NO_WRAP is important as was getting \n at the end
            String encoded = customBase64Encode(cipherText);
            log("Base64.NO_WRAP", encoded);
            return encoded;
        } catch (UnsupportedEncodingException e) {
            if (DEBUG_LOG_ENABLED)
                System.out.println("UnsupportedEncodingException:\n " + e.getMessage());
            throw new GeneralSecurityException(e);
        }
    }


    /**
     * More flexible AES encrypt that doesn't encode
     *
     * @param key     AES key typically 128, 192 or 256 bit
     * @param iv      Initiation Vector
     * @param message in bytes (assumed it's already been decoded)
     * @return Encrypted cipher text (not encoded)
     * @throws GeneralSecurityException if something goes wrong during encryption
     */
    public static byte[] encrypt(final SecretKeySpec key, final byte[] iv, final byte[] message)
            throws GeneralSecurityException {
        final Cipher cipher = Cipher.getInstance(AES_MODE);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);
        byte[] cipherText = cipher.doFinal(message);

        log("cipherText", cipherText);

        return cipherText;
    }


    /**
     * Decrypt and decode ciphertext using 256-bit AES with key generated from password
     *
     * @param password                used to generated key
     * @param base64EncodedCipherText the encrpyted message encoded with base64
     * @return message in Plain text (String UTF-8)
     * @throws GeneralSecurityException if there's an issue decrypting
     */
    public static String decrypt(final String password, String base64EncodedCipherText)
            throws GeneralSecurityException {

        try {
            final SecretKeySpec key = generateKey(password);

            log("base64EncodedCipherText", base64EncodedCipherText);
            byte[] decodedCipherText = customBase64Decode(base64EncodedCipherText);
            log("decodedCipherText", decodedCipherText);

            byte[] decryptedBytes = decrypt(key, ivBytes, decodedCipherText);

            log("decryptedBytes", decryptedBytes);
            String message = new String(decryptedBytes, CHARSET);
            log("message", message);


            return message;
        } catch (UnsupportedEncodingException e) {
            if (DEBUG_LOG_ENABLED)
                System.out.println("UnsupportedEncodingException:\n " + e.getMessage());

            throw new GeneralSecurityException(e);
        }
    }


    /**
     * More flexible AES decrypt that doesn't encode
     *
     * @param key               AES key typically 128, 192 or 256 bit
     * @param iv                Initiation Vector
     * @param decodedCipherText in bytes (assumed it's already been decoded)
     * @return Decrypted message cipher text (not encoded)
     * @throws GeneralSecurityException if something goes wrong during encryption
     */
    public static byte[] decrypt(final SecretKeySpec key, final byte[] iv, final byte[] decodedCipherText)
            throws GeneralSecurityException {
        final Cipher cipher = Cipher.getInstance(AES_MODE);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.DECRYPT_MODE, key, ivSpec);
        byte[] decryptedBytes = cipher.doFinal(decodedCipherText);

        log("decryptedBytes", decryptedBytes);

        return decryptedBytes;
    }


    private static void log(String what, byte[] bytes) {
        if (DEBUG_LOG_ENABLED)
            System.out.println(what + "[" + bytes.length + "] [" + bytesToHex(bytes) + "]");
    }

    private static void log(String what, String value) {
        if (DEBUG_LOG_ENABLED)
            System.out.println(what + "[" + value.length() + "] [" + value + "]");
    }


    /**
     * Converts byte array to hexidecimal useful for logging and fault finding
     */
    private static String bytesToHex(byte[] bytes) {
        final char[] hexArray = {'0', '1', '2', '3', '4', '5', '6', '7', '8',
                '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] hexChars = new char[bytes.length * 2];
        int v;
        for (int j = 0; j < bytes.length; j++) {
            v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    private AESCrypt() {
    }



    private static String customBase64Encode(byte[] input) {
        StringBuilder result = new StringBuilder(((input.length + 2) / 3) * 4);
        int outputCharCount = 0;
        int outputIndex = 0;

        while (outputIndex < input.length) {
            int remainingBytes = Math.min(3, input.length - outputIndex);
            int chunk = 0;
            for (int i = 0; i < remainingBytes; i++) {
                chunk |= (input[outputIndex++] & 0xFF) << (16 - i * 8);
            }
            for (int i = 0; i < 4; i++) {
                if (i <= remainingBytes) {
                    int index = (chunk >> (18 - i * 6)) & 0x3F;
                    result.append(BASE64_CHARS[index]);
                } else {
                    result.append(' ');
                }
            }
            outputCharCount += 4;
        }

        return result.toString();
    }

    private static byte[] customBase64Decode(String input) {
        StringBuilder cleanInput = new StringBuilder(input.length());
        for (char c : input.toCharArray()) {
            if (c != ' ' && c != '\n' && c != '\r' && c != '\t') {
                cleanInput.append(c);
            }
        }
        input = cleanInput.toString();

        int inputLength = input.length();
        int outputLength = inputLength * 3 / 4;
        byte[] output = new byte[outputLength];
        int outputIndex = 0;
        int inputIndex = 0;

        while (inputIndex < inputLength) {
            int chunk = 0;
            int chunkSize = 0;
            for (int i = 0; i < 4 && inputIndex < inputLength; i++) {
                char c = input.charAt(inputIndex++);
                if (c != ' ' && c != '\n' && c != '\r' && c != '\t') {
                    int charValue = indexOfBase64Char(c);
                    if (charValue >= 0) {
                        chunk |= charValue << (18 - i * 6);
                        chunkSize += 6;
                    } else {
                        throw new IllegalArgumentException("Invalid character in custom Base64 string");
                    }
                }
            }
            if (chunkSize >= 8) {
                output[outputIndex++] = (byte) ((chunk >> 16) & 0xFF);
            }
            if (chunkSize >= 16) {
                output[outputIndex++] = (byte) ((chunk >> 8) & 0xFF);
            }
            if (chunkSize >= 24) {
                output[outputIndex++] = (byte) (chunk & 0xFF);
            }
        }

        return Arrays.copyOf(output, outputIndex);
    }

    private static int indexOfBase64Char(char c) {
        for (int i = 0; i < BASE64_CHARS.length; i++) {
            if (c == BASE64_CHARS[i]) {
                return i;
            }
        }
        return -1;
    }

}

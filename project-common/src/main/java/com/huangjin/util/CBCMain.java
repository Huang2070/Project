package com.huangjin.util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import com.google.common.base.Strings;
import org.apache.commons.codec.binary.Base64;

/**
 * @Author: 黄金
 * @Description:
 * @Date: Create in 17:50 2023/4/10
 */
public class CBCMain {

    private static final String sKey = "ncotT4h8OevBoqIY";


    public static String encrypt(String value) {
        try {
            IvParameterSpec iv = new IvParameterSpec(sKey.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(sKey.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

            byte[] encrypted = cipher.doFinal(value.getBytes());

            return Base64.encodeBase64URLSafeString(encrypted);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static String decrypt(String encrypted) {
        try {
            IvParameterSpec iv = new IvParameterSpec(sKey.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(sKey.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] original = cipher.doFinal(Base64.decodeBase64(encrypted));

            return new String(original);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static String decryptFinal(String encrypted) {
        String decrypt = decrypt(encrypted);
        if (Strings.isNullOrEmpty(decrypt) || decrypt.length() < 11) {
            return null;
        }
        return decrypt.substring(11);
    }

    public static String decryptQQ(String encrypted) {
        String decrypt = decrypt(encrypted);
        if (Strings.isNullOrEmpty(decrypt) || !decrypt.contains(":")) {
            return null;
        }
        return decrypt.substring(decrypt.indexOf(":") + 1);
    }

    public static String decryptPhone(String encrypted) {
        String decrypt = decrypt(encrypted);
        if (Strings.isNullOrEmpty(decrypt) || !decrypt.contains(":")) {
            return null;
        }
        return decrypt.substring(decrypt.indexOf(":") + 1);
    }



    public static void main(String[] args) {
        String paramPlainStringPhone = "1590335206:13001011325";
        System.out.println("paramPlainStringPhone:" + paramPlainStringPhone);
        String encryptStrPhone = encrypt(paramPlainStringPhone);
        System.out.println("encryptStrPhone:" + encryptStrPhone);
        String decryptStrPhone = decrypt(encryptStrPhone);
        System.out.println("decryptStrPhone:" + decryptStrPhone);

        String phone = decryptPhone("fLDtxXMBsiR3jspqXUvZzosbQOV3kgTlKeNkuS5wx-U");
        System.out.println("phone:" + phone);

    }


}

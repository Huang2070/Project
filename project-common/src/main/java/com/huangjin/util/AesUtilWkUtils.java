package com.huangjin.util;

import java.nio.charset.StandardCharsets;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * @Author: 黄金
 * @Description:
 * @Date: Create in 17:50 2023/4/10
 */
public class AesUtilWkUtils {

    public static String encrypt(String value, String sKey) {
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



    public static String decrypt(String encrypted, String sKey) {
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


    //public static String decryptUrl(String encrypted, String sKey) {
    //    try {
    //        IvParameterSpec iv = new IvParameterSpec(sKey.getBytes(StandardCharsets.UTF_8));
    //        SecretKeySpec sKeySpec = new SecretKeySpec(sKey.getBytes(StandardCharsets.UTF_8), "AES");
    //
    //        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
    //        cipher.init(Cipher.DECRYPT_MODE, sKeySpec, iv);
    //        byte[] original = cipher.doFinal(Base64.d(encrypted));
    //
    //        return new String(original);
    //    } catch (Exception e) {
    //        e.printStackTrace();
    //    }
    //    return null;
    //}

}

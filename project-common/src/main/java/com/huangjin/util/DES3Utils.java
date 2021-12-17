package com.huangjin.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * @Author: 黄金
 * @Description:
 * @Date: Create in 11:44 2021-07-29
 */
public class DES3Utils {

    private static String encoding = "gb2312";


    /**
     * 加密
     *
     * 3DES加密模式: CBC
     * 填充: PKCS5Padding
     * 带偏移量参数
     * 输出：base64
     * 字符集：gb2312
     *
     * @param key
     * @param iv
     * @param data
     * @return
     * @throws Exception
     */
    public static String encrypt(String key, String iv, String data) throws Exception {
        SecretKey secretKey = new SecretKeySpec(key.getBytes(), "desede");

        Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
        IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ips);
        byte[] encryptData = cipher.doFinal(data.getBytes(encoding));
        return Base64.getEncoder().encodeToString(encryptData);
    }

    /**
     * 解密
     * @param key
     * @param iv
     * @param data
     * @return
     * @throws Exception
     */
    public static String decrypt(String key, String iv, String data) throws Exception {
        SecretKey secretKey = new SecretKeySpec(key.getBytes(), "desede");
        Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
        IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ips);

        byte[] decryptData = cipher.doFinal(Base64.getDecoder().decode(data.getBytes(encoding)));
        return new String(decryptData, "GB2312");
    }


    /**测试*/
    public static void main(String[] args) throws Exception {
        String key = "test@telefen.com~!@#$250";
        String iv = "88888888";
        String encryption = encrypt(key, iv, "15900006666");
        System.out.println(encryption);
        String decryption = decrypt(key, iv, encryption);
        System.out.println(decryption);
    }


}

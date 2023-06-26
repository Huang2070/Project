package com.huangjin.util;

import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class AesUtilWithBase64 {

    private static final String KEY_ALGORITHM = "AES";
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";

    private AesUtilWithBase64() {
    }


    //public static String ecbEncrypt(String sSrc,String sKey) throws Exception {
    //    byte[] raw = sKey.getBytes("utf-8");
    //    SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
    //    Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");//"算法/模式/补码方式"
    //    cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
    //    byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));
    //
    //    return Base64.getEncoder().encodeToString(encrypted);//此处使用BASE64做转码功能，同时能起到2次加密的作用。
    //}


    /**
     * 加密
     * @param content
     * @param key
     * @param cipherType
     * @return
     * @throws Exception
     */
    public static String encrypt(String content, String key, String cipherType) throws Exception {
        Cipher cipher = Cipher.getInstance(cipherType);
        byte[] byteContent = content.getBytes("utf-8");
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(Base64.decodeBase64(key), KEY_ALGORITHM));
        byte[] result = cipher.doFinal(byteContent);
        return Base64.encodeBase64String(result);
    }

    /**
     * 解密
     * @param content
     * @param key
     * @param cipherType
     * @return
     * @throws Exception
     */
    public static String decrypt(String content, String key, String cipherType) throws Exception {
        Cipher cipher = Cipher.getInstance(cipherType);
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(Base64.decodeBase64(key), KEY_ALGORITHM));
        byte[] result = cipher.doFinal(Base64.decodeBase64(content));
        return new String(result, "utf-8");
    }

    /**
     * 生成密钥
     * @return
     */
    public static String randomSecretKey() {
        KeyGenerator kg;
        try {
            kg = KeyGenerator.getInstance(KEY_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        kg.init(128);
        SecretKey secretKey = kg.generateKey();
        byte[] secretKeyByte = secretKey.getEncoded();
        return Base64.encodeBase64String(secretKeyByte);
    }


    private static byte[] to16BitBytes(String str) {
        int len= (str.length()/16+str.length()%16==0?0:1)*16;
        byte[] bs = new byte[len];
        System.arraycopy(str.getBytes(), 0, bs, 0, str.length());
        return bs;
    }


    public static String byteToHexString(byte[] bytes) {

        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < bytes.length; i++) {

            String strHex=Integer.toHexString(bytes[i]);

            if(strHex.length() > 3) {

                sb.append(strHex.substring(6));
            } else {

                if(strHex.length() < 2) {

                    sb.append("0" + strHex);
                } else {

                    sb.append(strHex);
                }
            }
        }
        return sb.toString();
    }

}

package com.huangjin.util;

import java.io.File;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Signature;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import com.google.common.collect.Maps;
import org.apache.commons.codec.Charsets;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import sun.misc.BASE64Encoder;

public class AEScbcUtil {


    /**
     * 获取加密参数
     * 新增函数
     * @return
     */
    public static String aesEncryptNew(String plainText, String aesKey, String aesIv) throws Exception {

        //AES加密
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
        SecretKeySpec skeySpec = new SecretKeySpec(aesKey.getBytes(StandardCharsets.UTF_8), "AES");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, new IvParameterSpec(aesIv.getBytes(StandardCharsets.UTF_8)));
        byte[] encrypted = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
        // Base64编码后的密文
        return Base64.encodeBase64String(encrypted);
    }



    public static String encrypt(String params, String key, String iv) {

        try {
            String content = aesEncrypt(params, key, iv).replace("\n", "");
            return content;
        } catch (Exception e) {
            return null;
        }
    }


    public static String castParams2String(Map<String, String> params) {
        StringBuilder stringBuilder = new StringBuilder();
        ArrayList<String> keyList = new ArrayList<>(params.keySet());
        Collections.sort(keyList);
        for (int i = 0; i < keyList.size(); i++) {
            String key = keyList.get(i);
            String value = params.get(key);
            if (StringUtils.isNotEmpty(value)) {
                if (i == keyList.size() - 1) {
                    stringBuilder.append(value);
                } else {
                    stringBuilder.append(value).append("@");
                }
            }
        }
        return stringBuilder.toString();
    }

    public static String castParams2Url(Map<String, String> params) {
        StringBuilder stringBuilder = new StringBuilder();
        ArrayList<String> keyList = new ArrayList<>(params.keySet());
        Collections.sort(keyList);
        for (int i = 0; i < keyList.size(); i++) {
            String key = keyList.get(i);
            try {
                if (i == keyList.size() - 1) {
                    stringBuilder.append(key).append("=")
                            .append(URLEncoder.encode(params.getOrDefault(key, ""), "UTF-8"));
                } else {
                    stringBuilder.append(key).append("=")
                            .append(URLEncoder.encode(params.getOrDefault(key, ""), "UTF-8")).append("&");
                }
            } catch (Exception e) {
            }
        }
        return stringBuilder.toString();
    }

    public static String castParams2UrlWithoutEncode(Map<String, String> params) {
        StringBuilder stringBuilder = new StringBuilder();
        ArrayList<String> keyList = new ArrayList<>(params.keySet());
        Collections.sort(keyList);
        for (int i = 0; i < keyList.size(); i++) {
            String key = keyList.get(i);
            try {
                if (i == keyList.size() - 1) {
                    stringBuilder.append(key).append("=")
                            .append(params.getOrDefault(key, ""));
                } else {
                    stringBuilder.append(key).append("=")
                            .append(params.getOrDefault(key, "")).append("&");
                }
            } catch (Exception e) {
            }
        }
        return stringBuilder.toString();
    }

    public static final Resource PUBLIC_KEY_RESOURCE =
            new ClassPathResource("abc" + File.separator + "certnew.cer");

    public static final Resource PRIVATE_KEY_RESOURCE =
            new ClassPathResource("abc" + File.separator + "p12new.pfx");
    //私钥文件获取时设置的密钥
    private static final String PFX_PWD = "115045";

    //alias名称
    private static final String ALIAS = "1";

    /**
     * 签名
     *
     * @return 签名后经过base64处理的字符串
     * @throws Exception
     */
    public static String sign(String str) {
        String base64Sign = "";
        InputStream fis = null;
        try {
            fis = PRIVATE_KEY_RESOURCE.getInputStream();
            KeyStore keyStore = KeyStore.getInstance("PKCS12");
            char[] pscs = PFX_PWD.toCharArray();
            keyStore.load(fis, pscs);
            PrivateKey priKey = (PrivateKey) (keyStore.getKey(ALIAS, pscs));


            // 签名
            Signature sign = Signature.getInstance("SHA256withRSA");
            sign.initSign(priKey);
            byte[] bysData = str.getBytes("UTF-8");
            sign.update(bysData);
            byte[] signByte = sign.sign();
            BASE64Encoder encoder = new BASE64Encoder();
            base64Sign = encoder.encode(signByte);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        return base64Sign;
    }


    /**
     * AES加密
     *
     * @param content    待加密的内容
     * @param encryptKey 加密密钥
     * @return 加密后的byte[]
     * @throws Exception
     */
    public static byte[] aesEncryptToBytes(String content, String encryptKey, String iv) throws Exception {
        //KeyGenerator kgen = KeyGenerator.getInstance("AES");
        //kgen.init(128, new SecureRandom(encryptKey.getBytes()));
        //SecretKeySpec skeySpec = new SecretKeySpec(kgen.generateKey().getEncoded(), "AES");

        SecretKeySpec skeySpec = new SecretKeySpec(encryptKey.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, new IvParameterSpec(iv.getBytes()));

        return cipher.doFinal(content.getBytes("utf-8"));
    }

    /**
     * AES加密为base 64 code
     *
     * @param content    待加密的内容
     * @param encryptKey 加密密钥
     * @return 加密后的base 64 code
     * @throws Exception
     */
    public static String aesEncrypt(String content, String encryptKey, String iv) throws Exception {
        return base64Encode(aesEncryptToBytes(content, encryptKey, iv));
    }

    /**
     * base 64 encode
     *
     * @param bytes 待编码的byte[]
     * @return 编码后的base 64 code
     */
    public static String base64Encode(byte[] bytes) {
        return new BASE64Encoder().encode(bytes);
    }

    /**
     * 以下是分行API签名工具
     */

    public static String submitSign(Map<String, String> paramMap, String key) {
        try {

            Map<String, String> newMap = new HashMap<>();
            paramMap.forEach((k, v) -> {
                if (StringUtils.isNotEmpty(v)) {
                    newMap.put(k, v);
                }
            });

            String params2Url = castParams2UrlWithoutEncode(newMap);
            String sign = SignUtil.md5(params2Url + key).toLowerCase();
            return sign;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }


    public static String encrypt3DES(String input, String key) {
        try {
            byte[] inputByte = input.getBytes(Charsets.UTF_8);
            byte[] inputKey = Hex.decodeHex(key.toCharArray());
            byte[] tripleDesResultByte = encryptECBMode(inputByte, inputKey);
            String tripleDesResult = Hex.encodeHexString(tripleDesResultByte);
            return tripleDesResult;
        } catch (Exception e) {
            e.printStackTrace();
            return input;
        }

    }

    public static String decrypt3DES(String input, String key) {
        try {
            byte[] inputByte = Hex.decodeHex(input.toCharArray());
            byte[] tripleDesResultByte = decryptECBMode(inputByte, decodeHex(key.toCharArray()));
            String tripleDesResult = new String(tripleDesResultByte, Charsets.UTF_8);
            return tripleDesResult;
        } catch (Exception e) {
            e.printStackTrace();
            return input;
        }
    }

    private static byte[] encryptECBMode(byte[] data, byte[] key) {
        try {
            DESedeKeySpec dks = new DESedeKeySpec(key);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
            SecretKey securekey = keyFactory.generateSecret(dks);
            Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, securekey);
            return cipher.doFinal(data);
        } catch (java.security.NoSuchAlgorithmException e1) {
            System.out.print("encryptECBMode:" + e1);
        } catch (javax.crypto.NoSuchPaddingException e2) {
            System.out.print("encryptECBMode:" + e2);
        } catch (Exception e3) {
            System.out.print("encryptECBMode:" + e3);
        }
        return null;
    }

    private static byte[] decryptECBMode(byte[] src, byte[] key) {
        try {
            DESedeKeySpec dks = new DESedeKeySpec(key);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
            SecretKey securekey = keyFactory.generateSecret(dks);
            Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, securekey);

            return cipher.doFinal(src);
        } catch (java.security.NoSuchAlgorithmException e1) {
            System.out.print("decryptECBMode:" + e1);
        } catch (javax.crypto.NoSuchPaddingException e2) {
            System.out.print("decryptECBMode:" + e2);
        } catch (Exception e3) {
            System.out.print("decryptECBMode:" + e3);
        }
        return null;
    }

    private static byte[] decodeHex(final char[] data) throws DecoderException {
        final int len = data.length;
        if ((len & 0x01) != 0) {
            throw new DecoderException("Odd number of characters.");
        }
        final byte[] out = new byte[len >> 1];
        for (int i = 0, j = 0; j < len; i++) {
            int f = toDigit(data[j], j) << 4;
            j++;
            f = f | toDigit(data[j], j);
            j++;
            out[i] = (byte) (f & 0xFF);
        }
        return out;
    }

    private static int toDigit(char ch, int index) throws DecoderException {
        int digit = Character.digit(ch, 16);
        if (digit == -1) {
            throw new DecoderException("Illegal hexadecimal charcter" + ch + "at index" + index);
        }
        return digit;
    }

}

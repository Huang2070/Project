package com.huangjin.util;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

/**
 * @author wb-fjx587232
 */
public class RSAUtilAliyun {
    /**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 244;

    /**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 256;
    /**
     * 加密算法RSA
     */
    public static final String KEY_ALGORITHM = "RSA";

    /**
     * 编码
     */
    private static final String ENCODING = "UTF-8";

    public static final String SIGN_TYPE = "SHA256WithRSA";

    /**
     * RSA签名
     *
     * @param priKey
     * @param params
     * @return
     */
    public static String rsaSign(String priKey, Map<String, Object> params, String signType) throws Exception {
        if (StringUtils.isBlank(priKey) || params == null || params.size() == 0) {
            throw new RuntimeException("sign params can not be empty");
        }
        String src = paramConverter(params);

        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(priKey));
        KeyFactory fac = KeyFactory.getInstance(KEY_ALGORITHM);
        RSAPrivateKey privateKey = (RSAPrivateKey) fac.generatePrivate(keySpec);
        Signature sigEng = Signature.getInstance(signType);
        sigEng.initSign(privateKey);
        sigEng.update(src.getBytes());
        byte[] signature = sigEng.sign();
        return Base64.encodeBase64String(signature).replaceAll("\r|\n", "");
    }

    /**
     * RSA验签
     *
     * @param pubKey
     * @param params
     * @return
     */
    public static boolean rsaSignVerify(String pubKey, Map<String, Object> params, String signType) throws Exception {
        if (StringUtils.isBlank(pubKey) || params == null || params.size() == 0) {
            throw new RuntimeException("sign params can not be empty");
        }
        Object sign = params.get("sign");

        if (sign == null || StringUtils.isBlank(sign.toString())) {
            throw new RuntimeException("sign can not be empty");
        }

        String src = paramConverter(params);

        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.decodeBase64(pubKey));
        KeyFactory fac = KeyFactory.getInstance(KEY_ALGORITHM);
        RSAPublicKey rsaPubKey = (RSAPublicKey) fac.generatePublic(keySpec);
        Signature sigEng = Signature.getInstance(signType);
        sigEng.initVerify(rsaPubKey);
        sigEng.update(src.getBytes());
        byte[] signature = Base64.decodeBase64(sign.toString());
        return sigEng.verify(signature);
    }

    /**
     * RSA算法公钥加密数据
     *
     * @param data
     * @param publicKey
     * @return
     */
    public static String encrypt(String data, String publicKey) throws Exception {
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(Base64.decodeBase64(publicKey));
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key pubKey = keyFactory.generatePublic(x509KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        return Base64.encodeBase64URLSafeString(cipher.doFinal(data.getBytes(StandardCharsets.UTF_8)));
    }


    /**
     * RSA算法私钥解密数据
     *
     * @param data
     * @param privateKey
     * @return
     */
    public static String decrypt(String data, String privateKey) throws Exception {
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKey));
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key priKey = keyFactory.generatePrivate(pkcs8KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, priKey);
        return new String(cipher.doFinal(Base64.decodeBase64(data)), StandardCharsets.UTF_8);
    }

    public static final String SIGN = "sign";

    /**
     * map转a=b&c=d
     *
     * @param paramMap
     * @return
     */
    public static String paramConverter(Map<String, Object> paramMap) {
        TreeMap<String, Object> sortMap = new TreeMap<>(paramMap);
        sortMap.remove(SIGN);
        StringBuilder sb = new StringBuilder();

        for (Map.Entry<String, Object> entry : sortMap.entrySet()) {
            sb.append(entry.getKey() + "=" + entry.getValue());
            sb.append("&");
        }
        String s = sb.toString();
        if (s.endsWith("&")) {
            s = StringUtils.substringBeforeLast(s, "&");
        }
        return s;
    }

    public static Map<String, String> initRSAKey() throws NoSuchAlgorithmException {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        kpg.initialize(2048);
        KeyPair keyPair = kpg.generateKeyPair();
        Key publicKey = keyPair.getPublic();
        String publicKeyStr = Base64.encodeBase64URLSafeString(publicKey.getEncoded());
        Key privateKey = keyPair.getPrivate();
        String privateKeyStr = Base64.encodeBase64URLSafeString(privateKey.getEncoded());
        Map<String, String> keyPairMap = new HashMap<>(10);
        keyPairMap.put("publicKey", publicKeyStr);
        keyPairMap.put("privateKey", privateKeyStr);
        return keyPairMap;
    }
}

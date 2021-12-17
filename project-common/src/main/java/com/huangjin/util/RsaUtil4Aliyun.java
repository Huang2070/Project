package com.huangjin.util;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.crypto.Cipher;

import com.google.common.base.Charsets;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

/**
 * @Author: 黄金
 * @Description:
 * @Date: Create in 11:56 2021-01-20
 */
@SuppressWarnings("unused")
public class RsaUtil4Aliyun {


    private static final String ENCODE = StandardCharsets.UTF_8.name();
    public static final String KEY_4_SIGN_LOCAL = "sign";
    public static final String KEY_4_SIGN = "sign";

    private RsaUtil4Aliyun() {
        //私有构造
    }

    /**
     * RSA签名
     *
     * @param priKey 私钥
     * @param params 请求参数
     * @return 签名信息
     */
    public static String sign(String priKey, String algorithm, Map<String, Object> params) throws Exception {
        if (StringUtils.isBlank(priKey) || params == null || params.size() == 0) {
            throw new RuntimeException("sign params can not be empty");
        }
        String src = paramConverter(params);

        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(priKey));
        KeyFactory fac = KeyFactory.getInstance("RSA");
        RSAPrivateKey privateKey = (RSAPrivateKey) fac.generatePrivate(keySpec);
        Signature sigEng = Signature.getInstance(algorithm);
        sigEng.initSign(privateKey);
        sigEng.update(src.getBytes(ENCODE));
        byte[] signature = sigEng.sign();
        //noinspection RegExpSingleCharAlternation
        return Base64.encodeBase64String(signature).replaceAll("\r|\n", "");
    }

    /**
     * RSA验签
     *
     * @param pubKey 公钥
     * @param params 参数
     * @return true:验签成功;false:验签失败
     */
    public static boolean verify(String pubKey, String algorithm, Map<String, Object> params) throws Exception {
        if (StringUtils.isBlank(pubKey) || params == null || params.size() == 0) {
            throw new RuntimeException("sign params can not be empty");
        }
        Object sign = params.get(KEY_4_SIGN_LOCAL);

        if (sign == null || StringUtils.isBlank(sign.toString())) {
            throw new RuntimeException("sign can not be empty");
        }

        String src = paramConverter(params);

        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.decodeBase64(pubKey));
        KeyFactory fac = KeyFactory.getInstance("RSA");
        RSAPublicKey rsaPubKey = (RSAPublicKey) fac.generatePublic(keySpec);
        Signature sigEng = Signature.getInstance(algorithm);
        sigEng.initVerify(rsaPubKey);
        sigEng.update(src.getBytes(ENCODE));
        byte[] signature = Base64.decodeBase64(sign.toString());
        return sigEng.verify(signature);
    }

    /**
     * RSA算法公钥加密数据
     *
     * @param data      待加密数据
     * @param publicKey 公钥
     * @return 加密数据
     */
    public static String encrypt(String data, String publicKey) throws Exception {
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(Base64.decodeBase64(publicKey));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        Key pubKey = keyFactory.generatePublic(x509KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        return Base64.encodeBase64URLSafeString(cipher.doFinal(data.getBytes(ENCODE)));
    }


    /**
     * RSA算法私钥解密数据
     *
     * @param data       待解密数据
     * @param privateKey 私钥
     * @return 解密后数据
     */
    public static String decrypt(String data, String privateKey) throws Exception {
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKey));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        Key priKey = keyFactory.generatePrivate(pkcs8KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, priKey);
        return new String(cipher.doFinal(Base64.decodeBase64(data)), ENCODE);
    }

    /**
     * map转a=b&c=d
     *
     * @param paramMap 待转换参数
     * @return 转换后参数
     */
    public static String paramConverter(Map<String, Object> paramMap) {
        TreeMap<String, Object> sortMap = new TreeMap<>(paramMap);

        boolean sign = sortMap.containsKey(KEY_4_SIGN_LOCAL);
        if (sign) {
            sortMap.remove(KEY_4_SIGN_LOCAL);
            sortMap.remove(KEY_4_SIGN);
        }
        List<String> args = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        for (Map.Entry<String, Object> entry : sortMap.entrySet()) {
            args.add(entry.getKey() + "=" + entry.getValue());
        }
        return String.join("&", args);
    }

    public static KeyPair generateRsaPairKey() throws NoSuchAlgorithmException {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(2048);
        java.security.KeyPair keyPair = kpg.generateKeyPair();
        Key publicKey = keyPair.getPublic();
        String publicKeyStr = Base64.encodeBase64String(publicKey.getEncoded());
        Key privateKey = keyPair.getPrivate();
        String privateKeyStr = Base64.encodeBase64URLSafeString(privateKey.getEncoded());
        return KeyPair.builder().publicKey(publicKeyStr).privateKey(privateKeyStr).build();
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Builder
    public static class KeyPair implements java.io.Serializable {
        public String publicKey;
        public String privateKey;
    }

    public static void main(String[] args) throws Exception {

        KeyPair keyPair = generateRsaPairKey();

        String mobile = "test001230912380123";
        long now = System.currentTimeMillis();
        String signatureMethod = "SHA256WithRSA";
        String privateScene=
            "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDElPI/HDE8xwETRcvvXjUbqoOayAmi7Vi5GXOREi+2UnIjK6dJoHwAwTds+4hPdY9n41RTa0NzJsqRC3X2EJs0xyl4fvsZbwINaX/kfFBag6970b/8YQc/dKVakAvIbXAnkTpiMp9oTAFftRdATpbsDj45J8HRGyEXrct3aXEYhL+NygI2cRF3OxMxLq2ecPYVHPT1zzkDaWF2409HEVew5aK1jKEaoSkZqNXFtcTujnOMO+7/OO9Htl3yaiBUBinmXszIdqdBYdksUbz0gqbS9RImoqxW4zf2j32DWs/+KDKqS7h3t5b3B+Dd+jBpVW9r8LWiFYs4NEZfB3aaMZeVAgMBAAECggEAJfWY/YTFITZQR9dvdM5dNPVtKkM8USDBxCRdA4NegTdUYOQ6DmaV61NkD4BWpvlNcsMb8mvA/4hbDz0GTziPr9ZRdLoYZKXh4JMhpg43OfM5vJAZIXPfAWrVzzIHhHFVed56RqH91BbxIPkH/1vtmrF5ZHH/5A/4COl/Y5SRySjqKrdu6BG/VUrjfbyDBhAKQriSqp+gxECEdJi9ZJNPZBSREaOHzjN2gY/DWEG1XDrwlpRYbTOrXupD5g20OndAf/B610R9gkkRC3A6u4lP5YGSxkViqbPlbUjoSg0TtasN5fEsyx1hRzCLKb4q2Zj+5sa2Oav0UVxUewzZLdXo1QKBgQDyYqEXI0nFKgTgP5J7snCypEKheYstVTpXKN0D0pVs4MKevTa6QN5iCC73EIZVa5kXeHsaHCkhVJe7YQSrkTp0Cble/1O0lyY61HTWaCsrQfWnMme4CevzG87a+wmiZ2UaGdLBdtvFkFNpGzx9Vy/wf7iB/cR2nTuqd9RNCxmy2wKBgQDPn7AY3QwpoMYdFWpqeJJ5J3HQf2U2q3eg3jv7r2CMQQlcpol090UPL0nT3VGhcDhcwSOCYaI2KJKNR8paetS2i7rgTt1iZSitZ1pWj9H7UTSnvuxYWi84XpRCv4422+j+ctXXnz/dCqp3rIr+RHWiNP3cXZJcNc/ubi6v4xASTwKBgAYRtCjpKEq4j6Cx1tAkGrBObqCuc26yKKDQFCCpYDZ2tkjVQg6N20OnIs3q6wIujInK6YIw3FLKVHZLqptFKqqHJ84aJJVsB3gHo9lOiG4v25/RIkm4beELfDV9fbNEVYK+zMktWblrsLr8BJq62Sdze0I+AXJa44iUMYuwUHdrAoGBAMQV9dNVmPILxG+dVTafDv7bUPNwDeAXCtFkcdQe6xkrJzNYmYNdMNzJdXlhkYHdoW2Afw/ZM8DgiqBhx+uyDAknaGQGN7GBBmzWqowMIm2vYQJ4H3DGI8iiEnYDwcVv5dnCDNIqtb8GHr8NtuuzLkH2uDcSLHNn4yw05WT6FqijAoGAP+IaKYXRVASm3Qlyjt3RcFo5l+ODZGRtLsssDuqxYTKbL/49hxfSMhIWTuzYawyQgV6vAaq/SzWHQ3OlE7uv519fSB6MZGlWOC5zHuF/atqWWOKp3U9EORDVTWSz8uQ8+edklLLpKvwsG0GXjWqLyWzhjBfRyrAY56CUA/5YWTA=";
        String publicKey =
            "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAxJTyPxwxPMcBE0XL7141G6qDmsgJou1YuRlzkRIvtlJyIyunSaB8AME3bPuIT3WPZ+NUU2tDcybKkQt19hCbNMcpeH77GW8CDWl/5HxQWoOve9G//GEHP3SlWpALyG1wJ5E6YjKfaEwBX7UXQE6W7A4+OSfB0RshF63Ld2lxGIS/jcoCNnERdzsTMS6tnnD2FRz09c85A2lhduNPRxFXsOWitYyhGqEpGajVxbXE7o5zjDvu/zjvR7Zd8mogVAYp5l7MyHanQWHZLFG89IKm0vUSJqKsVuM39o99g1rP/igyqku4d7eW9wfg3fowaVVva/C1ohWLODRGXwd2mjGXlQIDAQAB";

        InitDTO initDTO = InitDTO.builder()
            .uid("11111111")
            .timeStamp(now)
            .signType(signatureMethod)
            .scene("YOUKU")
            .build();
        Map<String, Object> params = initDTO.toMap();
        String sign =
            URLEncoder.encode(
                RsaUtil4Aliyun.sign(privateScene, signatureMethod, params)
            );
        String decode = URLDecoder.decode(sign, Charsets.UTF_8.displayName());
        params.put(RsaUtil4Aliyun.KEY_4_SIGN_LOCAL, decode);
        boolean verify = RsaUtil4Aliyun.verify(publicKey, signatureMethod, params);
    }

    @Builder
    public static class InitDTO {

        /**
         * uid
         * <p>
         * mobile
         */
        private String uid;
        /**
         * 时间戳（毫秒）
         */
        private Long timeStamp;
        /**
         * 除sign外所有参数组成的签名
         */
        private String sign;
        /**
         * 签名类型 如：SHA256WithRSA
         */
        private String signType;
        /**
         * 流量方
         * <p>
         * fixme
         */
        private String scene;

        //public String parseUrlParams() {
        //    return String.format("idCardEncrypted=%s&phoneEncrypted=%s&timeStamp=%s&uid=%s&scene=%s&sceneChannel=%s&signType=%s&sign=%s",
        //        idCardEncrypted, phoneEncrypted, timeStamp, uid, scene, sceneChannel, signType, sign
        //    );
        //}

        public Map<String, Object> toMap() {
            Map<String, Object> map = new HashMap<>(10);
            map.put("uid", this.uid);
            map.put("scene", this.scene);

            map.put("signType", this.signType);
            map.put("timeStamp", this.timeStamp);

            return map;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public Long getTimeStamp() {
            return timeStamp;
        }

        public void setTimeStamp(Long timeStamp) {
            this.timeStamp = timeStamp;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getSignType() {
            return signType;
        }

        public void setSignType(String signType) {
            this.signType = signType;
        }

        public String getScene() {
            return scene;
        }

        public void setScene(String scene) {
            this.scene = scene;
        }


    }

}

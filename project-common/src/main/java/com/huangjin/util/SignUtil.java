package com.huangjin.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.lang3.StringUtils;

/**
 * 签名工具
 *
 * @author jq
 */
public class SignUtil {

    protected final static String NAME_VALUE_SP = "=";

    protected final static String GROUP_SP = "&";

    /**
     * @param params 参数
     * @param secret 秘钥
     * @return
     * @throws Exception
     */
    public static String createSign(Map<String, String> params, String secret, String cryptoType) throws Exception {

        if (StringUtils.isBlank(cryptoType)) {
            cryptoType = HMAC_MD5;
        }
        if (!HMAC_SHA1.equals(cryptoType) && !HMAC_MD5.equals(cryptoType) && !HMAC_SHA256.equals(cryptoType)) {
            throw new Exception("cryptoType error ! 加密类型错误");
        }

        String data = castParams2String(params);
        String signature = getSignature(data, secret, cryptoType);

        return signature;
    }

    /**
     * map -》string 参数字典排序
     *
     * @param params
     * @return
     */
    public static String castParams2String(Map<String, String> params) {
        StringBuilder stringBuilder = new StringBuilder();
        ArrayList<String> keyList = new ArrayList<>(params.keySet());
        Collections.sort(keyList);
        for (int i = 0; i < keyList.size(); i++) {
            String key = keyList.get(i);
            if (i == keyList.size() - 1) {
                stringBuilder.append(key).append(NAME_VALUE_SP).append(String.valueOf(params.getOrDefault(key, "")));
            } else {
                stringBuilder.append(key).append(NAME_VALUE_SP).append(String.valueOf(params.getOrDefault(key, ""))).append(GROUP_SP);
            }
        }
        return stringBuilder.toString();
    }

    /**
     * 默认方式MD5方式
     *
     * @param params
     * @param secret
     * @return
     * @throws Exception
     */
    public static String createSign(Map<String, String> params, String secret) throws Exception {

        return createSign(params, secret, HMAC_MD5);
    }

    /**
     * @param params 参数
     * @param secret 秘钥
     * @return
     * @throws Exception
     */
    public static String createSignForXimalaya(Map<String, String> params, String secret) {


        String data = castParams2String(params);

        String signString = data + secret;

        return md5(signString);

    }


    /**
     * 定义加密方式
     * MAC算法可选以下多种算法
     * <pre>
     * HmacMD5
     * HmacSHA1
     * HmacSHA256
     * HmacSHA384
     * HmacSHA512
     * </pre>
     */
    public static final String HMAC_MD5 = "HmacMD5";
    public static final String HMAC_SHA1 = "HmacSHA1";
    public static final String HMAC_SHA256 = "HmacSHA256";

    /**
     * 生成签名数据_HmacSHA1加密
     *
     * @param data 待加密的数据
     * @param key  加密使用的key
     * @throws NoSuchAlgorithmException
     */
    public static String getSignature(String data, String key, String cryptoType) throws Exception {

        byte[] keyBytes = key.getBytes();
        // 根据给定的字节数组构造一个密钥。
        SecretKeySpec signingKey = new SecretKeySpec(keyBytes, cryptoType);
        Mac mac = Mac.getInstance(cryptoType);
        mac.init(signingKey);

        byte[] rawHmac = mac.doFinal(data.getBytes());

        String hexBytes = byte2hex(rawHmac);
        return hexBytes;
    }

    private static String byte2hex(final byte[] b) {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            // 以十六进制（基数 16）无符号整数形式返回一个整数参数的字符串表示形式。
            stmp = (Integer.toHexString(b[n] & 0xFF));
            if (stmp.length() == 1) {
                hs = hs + "0" + stmp;
            } else {
                hs = hs + stmp;
            }
        }
        return hs;
    }


    /**
     * 生成一个秘钥
     *
     * @return
     */
    public static String generateSecret() {
        //得到一个 指定算法密钥的密钥生成器
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(HMAC_SHA1);
            keyGenerator.init(128);
            //生成一个密钥
            SecretKey secretKey = keyGenerator.generateKey();
            return byte2hex(secretKey.getEncoded());
        } catch (Exception e) {
        }

        return null;

    }

    public static String md5(String sourceStr) {
        String result = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(sourceStr.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0) {
                    i += 256;
                }

                if (i < 16) {
                    buf.append("0");
                }

                buf.append(Integer.toHexString(i));
            }
            result = buf.toString();
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e);
        }
        return result;
    }

    /**
     * 使用指定哈希算法计算摘要信息
     * @param content 内容
     * @param algorithm 哈希算法
     * @return 内容摘要
     */
    public static String getMD5Digest(String content,String algorithm){
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            messageDigest.update(content.getBytes("utf8"));
            return byte2hex(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        String secret = "562My00awmOe8pZI";
//        Map<String, String> params = new HashMap<>();
//        params.put("app_id", "123456");
//        params.put("timestamp", "1572849394828");
//        String sign = createSign(params, secret);
//        System.out.println(sign);
        //System.out.println(System.currentTimeMillis());

        //System.out.println(getSignature("a","bbbb","HmacSHA512"));
        //System.out.println(getMD5Digest("abbbb","SHA-512"));
        System.out.println(md5("orderNo=xxxxxxx&partnerId=xxxxxxxxxxxx&timestamp=1551089728&goodsType=vip_38&mobile=LdR0CNshGNm aXDp1dV1NDg==&version=1.0&key=xxxxxxx"));
    }
}
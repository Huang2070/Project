package com.huangjin.util;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * @Author: 黄金
 * @Description:
 * @Date: Create in 12:04 2021-05-19
 */
public class Hmacsha256Util {

    public static String HMACSHA256(String data, String key) throws Exception {

        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");

        SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");

        sha256_HMAC.init(secret_key);

        byte[] array = sha256_HMAC.doFinal(data.getBytes("UTF-8"));

        StringBuilder sb = new StringBuilder();

        for (byte item : array) {

            sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));

        }

        return sb.toString();

    }





    public static String Sha256HMAC(String key, byte[] data) throws Exception {
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "HmacSHA256");
        sha256_HMAC.init(keySpec);
        byte[] hash = sha256_HMAC.doFinal(data);

        byte[] hexChars = new byte[hash.length * 2];
        for ( int j = 0; j < hash.length; j++ ) {
            byte[] s = String.format("%02x", hash[j] & 0xFF).getBytes();
            hexChars[j * 2] = s[0];
            hexChars[j * 2 + 1] = s[1];
        }
        return new String(hexChars);
    }

    // ver 为 auth-v2
    public static String Sign(String ver, String ak, String sk, byte[] data) throws Exception {
        int expiration = 1800;  //  有效时间, 单位是s，根据自己的业务的实际情况调整
        //long timestamp = System.currentTimeMillis() / 1000L;

        long timestamp = 1621569660;
        String signKeyInfo = String.format("%s/%s/%d/%d", ver, ak, timestamp, expiration);
        String signKey = Sha256HMAC(sk, signKeyInfo.getBytes());
        String signResult = Sha256HMAC(signKey, data);
        return String.format("%s/%s", signKeyInfo, signResult);
    }




    public static void main(String[] args) throws Exception {
        System.out.println(Sign( "auth-v2", "3ff1fad431eaaf9b4104a1db3516c86c", "5ec36777d1de23e8976d265789258764",
            "{\"subject\":1,\"mobile\":\"13396819820\",\"unique_order_key\":\"3255767881\"}".getBytes()));
    }

}

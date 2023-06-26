package com.huangjin.util;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import cn.hutool.crypto.BCUtil;
import cn.hutool.crypto.ECKeyUtil;
import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.asymmetric.SM2;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPublicKey;

/**
 * @Author: 黄金
 * @Description:
 * @Date: Create in 19:02 2022/9/20
 *
 * Sm2 签名工具
 */
public class HJFSMUtil {

    public static final String PRIVATE_KEY = "privateKey";
    public static final String PUBLIC_KEY = "publicKey";


    /**
     * SM2加签
     */
    public static String sign(String priKey, String signPlainData) {
        final SM2 sm2Sign = new SM2(priKey, null, null);
        sm2Sign.usePlainEncoding();
        byte[] sign = sm2Sign.sign(signPlainData.getBytes(StandardCharsets.UTF_8), null);
        return new String(Base64.getEncoder().encode(sign), StandardCharsets.UTF_8);
    }


    /**
     * SM2验签
     */
    public static boolean verify(String pubKey, String signPlainData, String signStr) {
        final SM2 sm2Virify = new SM2(null, ECKeyUtil.toSm2PublicParams(pubKey));
        sm2Virify.usePlainEncoding();
        return sm2Virify.verify(signPlainData.getBytes(StandardCharsets.UTF_8), Base64.getDecoder().decode(signStr.getBytes(StandardCharsets.UTF_8)));
    }


    /**
     * SM2 生成非对称密钥对
     */
    public static Map<String, String> generateKeyPair() {
        Map<String, String> keyMap = new HashMap<>();
        SM2 sm2 = SmUtil.sm2();
        byte[] privateKey = BCUtil.encodeECPrivateKey(sm2.getPrivateKey());
        byte[] publikKey = ((BCECPublicKey)sm2.getPublicKey()).getQ().getEncoded(false);
        keyMap.put(PRIVATE_KEY, new String(Base64.getEncoder().encode(privateKey), StandardCharsets.UTF_8));
        keyMap.put(PUBLIC_KEY, new String(Base64.getEncoder().encode(publikKey), StandardCharsets.UTF_8));
        return keyMap;
    }

}

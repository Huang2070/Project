package com.huangjin.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;

/**
 * class description<br/>
 *
 * @author huangjin
 * @version 1.0
 * @date 2021/5/18 16:24
 * @since JDK 1.8+
 */
@Slf4j
public enum AesUtils {

    /**
     * 加密
     */
    ENCRYPT {
        @Override
        public String doFinal(String str, String password) {

            try {
                Cipher cipher = Cipher.getInstance(CIPHERMODE);
                cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(Base64.decodeBase64(password), "AES"));
                return Base64.encodeBase64String(cipher.doFinal(AesUtils.to16BitBytes(str)));
            } catch (Exception e) {
                log.error("ENCRYPT error {} use pwd {}", str, password);
                e.printStackTrace();
                return null;
            }
        }
    },
    /**
     * 解密
     * hexStr 必须是16的倍数
     */
    DECRYPT {
        @Override
        public String doFinal(String base64Str, String pwd) {

            try {
                Cipher cipher = Cipher.getInstance(CIPHERMODE);
                cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(AesUtils.to16BitBytes(pwd), "AES"));
                byte[] result = cipher.doFinal(Base64.decodeBase64((base64Str)));
                return new String(result);

            } catch (Exception e) {
                log.error("DECRYPT error {} use pwd {}", base64Str, pwd);
                return null;
            }
        }
    };


    private static final String CIPHERMODE = "AES/ECB/NoPadding";


    private static byte[] to16BitBytes(String str) {
        int len= (str.length()/16+str.length()%16==0?0:1)*16;
        byte[] bs = new byte[len];
        System.arraycopy(str.getBytes(), 0, bs, 0, str.length());
        return bs;
    }

    public abstract String doFinal(String content, String pwd);

    public static void main(String[] args) throws Exception {
        String str = AesUtils.ENCRYPT.doFinal("13611112222", "R8mFkNvgHP3XGWUz");
        log.info("加密后：{}",str);

        log.info("解密后：{}",AesUtils.DECRYPT.doFinal(str, "R8mFkNvgHP3XGWUz"));
        System.out.println(AesUtils.DECRYPT.doFinal(str, "R8mFkNvgHP3XGWUz"));
    }
}
package com.huangjin.util;

import java.io.ByteArrayOutputStream;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.codec.binary.Base64;
import org.springframework.util.Base64Utils;

/**
 * @Author: 黄金
 * @Description:
 * @Date: Create in 16:54 2021-09-24
 */
public class RSAUtils {

    /** *//**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;


    public static RSAPublicKey loadPublicKeyByStr(String publicKeyStr) throws Exception	{
        try	{
            byte[]	buffer = Base64Utils.decode(publicKeyStr.getBytes());
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
            return (RSAPublicKey)keyFactory.generatePublic(keySpec);

        } catch(NoSuchAlgorithmException e)	{
            throw new Exception("⽆此算法");
        } catch(InvalidKeySpecException e)	{
            throw new Exception("公钥⾮法");
        } catch(NullPointerException e)	{
            throw new Exception("公钥数据为空");
        }
    }


    public static String encrypt(RSAPublicKey publicKey, String	plainTextData) throws Exception {
        if(publicKey == null) {
            throw new Exception("加密公钥为空, 请设置");
        }
        Cipher cipher = null;
        try	{
            //使⽤默认RSA
            cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            return Base64.encodeBase64String(cipher.doFinal(plainTextData.getBytes()));

        } catch(NoSuchAlgorithmException e){
            throw new Exception("⽆此加密算法");
        } catch(NoSuchPaddingException e){
            e.printStackTrace();
            return null;
        } catch(InvalidKeyException e)	{
            throw new Exception("加密公钥⾮法,请检查");
        } catch(IllegalBlockSizeException e){
            throw new Exception("明⽂⻓度⾮法");
        } catch(BadPaddingException e)	{
            throw new Exception("明⽂数据已损坏");
        }
    }

    /**
     * 分段加密
     * @param publicKey
     * @param plainTextData
     * @return
     * @throws Exception
     */
    public static String encryptPart(RSAPublicKey publicKey, String	plainTextData) throws Exception {
        if(publicKey == null) {
            throw new Exception("加密公钥为空, 请设置");
        }
        Cipher cipher = null;
        try	{
            //使⽤默认RSA
            cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);

            byte[] data = plainTextData.getBytes();
            int inputLen = data.length;

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int offSet = 0;
            byte[] cache;
            int i = 0;
            // 对数据分段加密
            while (inputLen - offSet > 0) {
                if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                    cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
                } else {
                    cache = cipher.doFinal(data, offSet, inputLen - offSet);
                }
                out.write(cache, 0, cache.length);
                i++;
                offSet = i * MAX_ENCRYPT_BLOCK;
            }
            byte[] encryptedData = out.toByteArray();
            out.close();

            return Base64.encodeBase64String(encryptedData);

        } catch(NoSuchAlgorithmException e){
            throw new Exception("⽆此加密算法");
        } catch(NoSuchPaddingException e){
            e.printStackTrace();
            return null;
        } catch(InvalidKeyException e)	{
            throw new Exception("加密公钥⾮法,请检查");
        } catch(IllegalBlockSizeException e){
            throw new Exception("明⽂⻓度⾮法");
        } catch(BadPaddingException e)	{
            throw new Exception("明⽂数据已损坏");
        }
    }
}

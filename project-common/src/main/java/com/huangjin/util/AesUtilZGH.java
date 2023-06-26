package com.huangjin.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * @version:2.0
 * @Description:Aes加密工具类 
 * @author: yzq 
 * @date: 2019年3月2日 下午1:29:55
 */
public class AesUtilZGH {
	/***
	 * 加密
	 * @param sSrc 加密的字符串
	 * @param skey 加密的key
	 * @param ivstr 加密的向量
	 * @return
	 */
	public static String Encrypt( String sSrc,String skey,String ivstr){
		try{
			if(sSrc==null){
				return null;
			}
			if( skey == null ) {
				System.out.print( "Key为空null" );
				return null;
			}
			// �ж�Key�Ƿ�Ϊ16λ
			if( skey.length() != 16 ) {
				System.out.print( "Key长度不是16位" );
				return null;
			}
			byte[] raw = skey.getBytes();
			SecretKeySpec skeySpec = new SecretKeySpec( raw, "AES" );
			Cipher cipher = Cipher.getInstance( "AES/CBC/PKCS5Padding" );
			IvParameterSpec iv = new IvParameterSpec( ivstr.getBytes() );
			cipher.init( Cipher.ENCRYPT_MODE, skeySpec, iv );
			byte[] encrypted = cipher.doFinal( sSrc.getBytes("utf-8") );
//			return org.apache.commons.codec.binary.Base64.encodeBase64String(encrypted);
			return Base64.getEncoder().encodeToString(encrypted);
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}

	/***
	 * AES 解密
	 * @param sSrc 需要解密的字符串
	 * @param skey 解密key
	 * @param ivstr 解密iv
	 * @return
	 */
	public static String Decrypt( String sSrc,String skey,String ivstr){
		try {
			// 判断参数是否为空
			if(sSrc==null){
				return null;
			}
			if( skey == null ) {
				System.out.print( "Key不能为空" );
				return null;
			}

			// 判断key是否为16位
			if( skey.length() != 16 ) {
				System.out.print( "Key必须 是16位" );
				return null;
			}
			byte[] raw = skey.getBytes( "ASCII" );
			SecretKeySpec skeySpec = new SecretKeySpec( raw, "AES" );
			Cipher cipher = Cipher.getInstance( "AES/CBC/PKCS5Padding" );
			IvParameterSpec iv = new IvParameterSpec( ivstr.getBytes() );
			cipher.init( Cipher.DECRYPT_MODE, skeySpec, iv );

			//byte[] encrypted1 = decodeBytes( sSrc );
			
//			byte[] encrypted1 = new BASE64Decoder().decodeBuffer(sSrc);
			byte[] encrypted1 = Base64.getDecoder().decode(sSrc);
			try {
				byte[] original = cipher.doFinal( encrypted1 );
				String originalString = new String( original ,"utf-8");
				return originalString;
			}
			catch( Exception e ) {
				System.out.println( e.toString() );
				return null;
			}
		}
		catch(Exception ex ) {
			return null;
		}
	}
	/***
	 * 序列化对象
	 * @param obj Serializable
	 * @return Stirng
	 */
	public static String serialize(Serializable obj) {
		String value = "";
		if(obj == null) {
			return value;
		}
		try {
			ByteArrayOutputStream serialObj = new ByteArrayOutputStream();
			ObjectOutputStream objStream = new ObjectOutputStream(serialObj);
			objStream.writeObject(obj);
			objStream.close();
			value = encodeBytes(serialObj.toByteArray());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}
	/***
	 * 反序列化对象
	 * @param str 需要反序列化的对象
	 * @return Object
	 */
	public static Object deserialize(String str) {

		if(str == null || str.length() == 0) {
			return null;
		}
		try {
			ByteArrayInputStream serialObj = new ByteArrayInputStream(
					decodeBytes(str));
			ObjectInputStream objStream = new ObjectInputStream(serialObj);
			return objStream.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/***
	 * 字符节转string
	 * @param bytes 需要转换的字符节
	 * @return string
	 */
	public static String encodeBytes(byte[] bytes) {
		StringBuffer strBuf = new StringBuffer();

		for (int i = 0; i < bytes.length; i++) {
			strBuf.append((char) (((bytes[i] >> 4) & 0xF) + ((int) 'a')));
			strBuf.append((char) (((bytes[i]) & 0xF) + ((int) 'a')));
		}

		return strBuf.toString();
	}
	/***
	 * 字符串转byte【】
	 * @param str 需要转换的字符换
	 * @return byte[]
	 */
	public static byte[] decodeBytes(String str) {
		byte[] bytes = new byte[str.length() / 2];
		for (int i = 0; i < str.length(); i += 2) {
			char c = str.charAt(i);
			bytes[i / 2] = (byte) ((c - 'a') << 4);
			c = str.charAt(i + 1);
			bytes[i / 2] += (c - 'a');
		}
		return bytes;
	}


}

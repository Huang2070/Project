package com.huangjin.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtils extends Properties {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Log logger = LogFactory.getLog(PropertiesUtils.class);
	//instance
	private static PropertiesUtils instance=null;
	
	/**
	 * private constructor
	 * <p>This use Singleton Design Pattern</p>
	 */
	private PropertiesUtils(String filePath){
		try {
			InputStream is= getClass().getResourceAsStream(filePath);
			load(is);
		} catch (IOException e) {
			logger.error("Load properties Error!",e);
			e.printStackTrace();
		}
	}
	
	/**
	 * create instance
	 */
	private static synchronized void makeInstance(String filePath){
		if (null==instance) {
			instance=new PropertiesUtils(filePath);
		}
	}
	
	/**
	 * Get the Instance of Properties by filePath
	 * @return JdbcInstance
	 */
	public static PropertiesUtils getInstance(String filePath){
		if (null!=instance) {
			return instance;
		}else{
			makeInstance(filePath);
			return instance;
		}
	}
	
	public static PropertiesUtils getInstance(String filePath,boolean boo){
		instance=new PropertiesUtils(filePath);
		return instance;
	}
	
	/**
	 * Get the Instance of Properties
	 * <b>Default path:</b>./config/config.properties
	 * @return
	 */
	public static PropertiesUtils getInstance(){
		if (null!=instance) {
			return instance;
		}else{
			//makeInstance(PropertiesUtils.class.getClassLoader().getResource("/config.properties").getPath());
			//makeInstance(System.getProperty("user.dir"));
			makeInstance("/config.properties");
			return instance;
		}
	}
	
	/**
	 * Get a new Instance of Properties
	 * <b>Default path:</b>./config/config.properties
	 * @return
	 */
	public static PropertiesUtils getNewInstance(){
		return new PropertiesUtils(new File("/cms.properties").getAbsolutePath());
	}
	
	/**
	 * Get a new Instance of Properties by filePath
	 * @param filePath The file path of properties
	 * @return
	 */
	public static PropertiesUtils getNewInstance(String filePath){		
		return new PropertiesUtils(new File(filePath).getAbsolutePath());
	}
	
	public static void main(String[] args) {
		System.out.println(PropertiesUtils.getInstance().get("com.letv.cms.memcache.server"));
	}
}

package com.huangjin.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * HTTP 连接API
 */
public class HttpClientUtils {
	private static final Logger logger = LoggerFactory.getLogger(HttpClientUtils.class);

//	private static final int TIMEOUT = 2000;

	 private static final int TIMEOUT = 100000;

	public static String post(String weburl, Map<String, String> params,
							  String encoding) {
		return post(weburl, params, TIMEOUT, encoding);
	}

	public static String post(String weburl, Map<String, String> params,
							  int soTimeout, String encoding) {
		logger.info("HttpClientUtils.post.URL={}", weburl);
		// 创建默认的httpClient实例.
		CloseableHttpClient httpClient = HttpClients.createDefault();
		//设置http的状态参数
		RequestConfig config = RequestConfig.custom()
				.setSocketTimeout(soTimeout)
				.setConnectTimeout(soTimeout)
				.setConnectionRequestTimeout(soTimeout)
				.build();
		UrlEncodedFormEntity uefEntity;
		try {
			// 参数处理
			List<NameValuePair> nvpsList = new ArrayList<NameValuePair>();
			for (Map.Entry<String, String> entry : params.entrySet()) {
				NameValuePair nvp = new BasicNameValuePair(entry.getKey().toString(), entry.getValue().toString());
				nvpsList.add(nvp);
			}
			uefEntity = new UrlEncodedFormEntity(nvpsList, encoding);
			// 创建httppost
			HttpPost httppost = new HttpPost(weburl);
			httppost.setEntity(uefEntity);
			httppost.setConfig(config);

			String result = "";
			long start = System.currentTimeMillis();
			CloseableHttpResponse response = httpClient.execute(httppost);
			int status = response.getStatusLine().getStatusCode();
			/*InputStream in = null;
			try {
				HttpEntity respEntity = response.getEntity();
				if (respEntity != null) {
					respEntity = new BufferedHttpEntity(respEntity);

					in = respEntity.getContent();
					result = IOUtils.toString(in, encoding);
				}
			} catch (IOException e) {
				logger.error(e.getMessage());
			} finally {
				response.close();
				if (in != null) {
					in.close();
				}
			}*/
			try {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					result = EntityUtils.toString(entity, encoding);
				}
			}finally {
				response.close();
			}
//			if (status != 200) {
//				logger.warn("httpclient status error. url=" + weburl
//						+ "  status=" + status);
//				return null;
//			}
			long cost = System.currentTimeMillis() - start;
			logger.info("Cost:{},URL={}", cost, weburl);
			return result;
		}catch (Exception e) {
			logger.error("http post error", e);
			return null;
		}finally{
			try {
				//关闭流并释放资源
				httpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static String post(String weburl, Map<String, String> params,
							  int soTimeout, String encoding, Map<String,String> header) {
		// 创建默认的httpClient实例.
		CloseableHttpClient httpClient = HttpClients.createDefault();
		//设置http的状态参数
		RequestConfig config = RequestConfig.custom()
				.setSocketTimeout(soTimeout)
				.setConnectTimeout(soTimeout)
				.setConnectionRequestTimeout(soTimeout)
				.build();
		UrlEncodedFormEntity uefEntity;
		try {
			// 参数处理
			List<NameValuePair> nvpsList = new ArrayList<NameValuePair>();
			for (Map.Entry<String, String> entry : params.entrySet()) {
				NameValuePair nvp = new BasicNameValuePair(entry.getKey().toString(), entry.getValue().toString());
				nvpsList.add(nvp);
			}
			uefEntity = new UrlEncodedFormEntity(nvpsList, encoding);
			// 创建httppost
			HttpPost httppost = new HttpPost(weburl);
			httppost.setEntity(uefEntity);
			httppost.setConfig(config);

			String result = "";
			long start = System.currentTimeMillis();
			CloseableHttpResponse response = httpClient.execute(httppost);
			int status = response.getStatusLine().getStatusCode();
			try {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					result = EntityUtils.toString(entity, encoding);
				}
			}finally {
				response.close();
			}
			if (status != 200) {
				logger.warn("httpclient status error. url=" + weburl
						+ "  status=" + status);
				return null;
			}
			long cost = System.currentTimeMillis() - start;
			logger.info("Cost:{},URL={}", cost, weburl);
			return result;
		}catch (Exception e) {
			logger.error("http post error", e);
			return null;
		}finally{
			try {
				//关闭流并释放资源
				httpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static String get(String weburl) {
		return get(weburl, TIMEOUT);
	}

	public static String get(String weburl,int soTimeout) {
		logger.info("HttpClientUtils.get.URL={}", weburl);
		// 创建默认的httpClient实例.
		CloseableHttpClient httpClient = HttpClients.createDefault();
		//设置http的状态参数
		RequestConfig config = RequestConfig.custom()
				.setSocketTimeout(soTimeout)
				.setConnectTimeout(soTimeout)
				.setConnectionRequestTimeout(soTimeout)
				.build();
		HttpGet httpGet = new HttpGet(weburl);
		httpGet.setConfig(config);
		httpGet.getRequestLine();
		try {
			String result = "";
			long start = System.currentTimeMillis();
			// 执行get请求.
			CloseableHttpResponse response = httpClient.execute(httpGet);
			int status = response.getStatusLine().getStatusCode();
			try {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					result = EntityUtils.toString(entity, "UTF-8");
				}
			}finally {
				response.close();
			}
			if (status != 200) {
				logger.warn("httpclient status error. url=" + weburl
						+ "  status=" + status);
			}
			long cost = System.currentTimeMillis() - start;
			logger.info("Cost:{},URL={}", cost, weburl);
			return result;
		} catch (Exception e) {
			logger.error("", e);
			return null;
		}finally{
			try {
				//关闭流并释放资源
				httpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * check if the http response code is 200
	 * @param url
	 * @return
	 */
	public static boolean checkResponseStatus(String url){
		try {
			URL checkUrl = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) checkUrl.openConnection();
			connection.setRequestMethod("GET");
			connection.setConnectTimeout(5*1000);
			connection.connect();

			int code = connection.getResponseCode();
			return code==HttpURLConnection.HTTP_OK?true:false;
		} catch (MalformedURLException e) {
			logger.error(url+"连接错误",e);
			return false;
		} catch (IOException e) {
			logger.error(url+"连接错误",e);
			return false;
		}
	}
	//post json数据
	public static String postJson(String url, String jsonstring, String encoding) throws IOException {
		HttpPost httpPost = new HttpPost(url);
		StringEntity entity = new StringEntity(jsonstring,"utf-8");
		entity.setContentType("application/json");
		httpPost.setEntity(entity);
		DefaultHttpClient client = new DefaultHttpClient();
		HttpResponse response = client.execute(httpPost);
		String charset = encoding == null ? EntityUtils.getContentCharSet(response.getEntity()) : encoding;
		String s = EntityUtils.toString(response.getEntity(), charset);
		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			response.getEntity().consumeContent();
			return s;
		} else {
			response.getEntity().consumeContent();
			httpPost.abort();
			return null;
		}
	}

	public static void main(String[] args){
		String url = "http://realapi.usmms.lecloud.com/v1/inner/custom/category/30000006?token=b5cb921139afe64a45bed176ca800fa7&platform=appcreater";
		String url_inner = "http://realapi.mms.lecloud.com/v1/inner/custom/category/828074?token=dac1ee096dffb05541da8831994f5682&platform=appcreater";

		String apiUrl = "http://api.uscms.lecloud.com/appVersion/getVersionAlert/30000004?type=android";
		String apiUrl_inner = "http://api.cms.lecloud.com/appVersion/getVersionAlert/828074?type=android";


		String ucUrl = "http://usuc.lecloud.com/user/userInfo.do?sessionId=9ce3791f-2d89-425e-9119-0ac663316c66";
		long start = System.currentTimeMillis();
		for (int i=0; i<100; i++) {
			String resultJson = HttpClientUtils.get(url);
			logger.info("Test：{}", resultJson);
		}
		long cost = System.currentTimeMillis() - start;
		logger.info("Total Cost:{},URL={}", cost, url);
	}


}


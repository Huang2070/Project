package com.huangjin.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.remoting.RemoteInvocationFailureException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

public class HttpUtil {

	public static final int DEFAULT_CONNECT_TIME_OUT = 45000;

	public static final int DEFAULT_READ_TIME_OUT = 45000;
	
	/**
	 * GET请求
	 * 
	 * @param urlPath
	 * @param data
	 * @return sTotalString 相应结果
	 */
	public static String get(String urlPath, String data) {
		return get(urlPath, data, null);
	}

	/**
	 * POST请求
	 * 
	 * @param urlPath 发送请求的URL
	 * @param data 请求参数
	 * @return sTotalString 相应结果
	 */
	public static String post(String urlPath, String data) {
		return post(urlPath, data, null);
	}

	/**
	 * put请求
	 * 
	 * @param urlPath 发送请求的URL
	 * @param data 请求参数
	 * @return sTotalString 相应结果
	 */
	public static String put(String urlPath, String data) {
		return put(urlPath, data, null);
	}

	/**
	 * delete请求
	 * 
	 * @param urlPath 发送请求的URL
	 * @param data 请求参数
	 * @return sTotalString 相应结果
	 */
	public static String delete(String urlPath, String data) {
		return delete(urlPath, data, null);
	}
	
	/**
	 * GET请求
	 * 
	 * @param urlPath
	 * @param data
	 * @return sTotalString 相应结果
	 */
	public static String get(String urlPath, String data, Map<String, String> header) {
		String sTotalString = "";
		BufferedReader reader = null;
		try {
			URL url = new URL(urlPath + "?" + data);
			if(urlPath.startsWith("https")) {
				trustAllHttpsCertificates();
				HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
					@Override
					public boolean verify(String urlHostName, SSLSession session) {
						return true;
					}
				});
			}
			// 打开和URL之间的连接
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			// 建立实际的连接
			connection.setConnectTimeout(30000);  
			connection.setReadTimeout(30000);
			if(header != null) {
				for (Map.Entry<String, String> entry : header.entrySet()) {
					connection.addRequestProperty(entry.getKey(), entry.getValue());
				}
			}
			connection.connect();

			// 定义 BufferedReader输入流来读取URL的响应
			String sCurrentLine = "";
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			while ((sCurrentLine = reader.readLine()) != null) {
				sTotalString += sCurrentLine + "\r\n";
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return sTotalString;
	}

	/**
	 * POST请求
	 * 
	 * @param urlPath 发送请求的URL
	 * @param data 请求参数
	 * @return sTotalString 相应结果
	 */
	public static String post(String urlPath, String data, Map<String, String> header) {
		PrintWriter out = null;
		String sTotalString = "";
		BufferedReader reader = null;
		try {
			URL url = new URL(urlPath);
			if(urlPath.startsWith("https")) {
				trustAllHttpsCertificates();
				HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
					@Override
					public boolean verify(String urlHostName, SSLSession session) {
						return true;
					}
				});
			}
			// 打开和URL之间的连接
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();

			// 发送POST请求必须设置如下两行
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setConnectTimeout(30000);  
			connection.setReadTimeout(30000);
			// connection.addRequestProperty("Content-type", "application/json");
			if(header != null) {
				for (Map.Entry<String, String> entry : header.entrySet()) {
					connection.addRequestProperty(entry.getKey(), entry.getValue());
				}
			}

			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(connection.getOutputStream());
			// 发送请求参数
			out.print(data);
			out.print(data);
			// flush输出流的缓冲
			out.flush();

			// 定义 BufferedReader输入流来读取URL的响应
			String sCurrentLine = "";
			reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			while ((sCurrentLine = reader.readLine()) != null) {
				sTotalString += sCurrentLine + "\r\n";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (reader != null) {
					reader.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return sTotalString;
	}

	/**
	 * put请求
	 * 
	 * @param urlPath 发送请求的URL
	 * @param data 请求参数
	 * @return sTotalString 相应结果
	 */
	public static String put(String urlPath, String data, Map<String, String> header) {
		PrintWriter out = null;
		String sTotalString = "";
		BufferedReader reader = null;
		try {
			URL url = new URL(urlPath);
			if(urlPath.startsWith("https")) {
				trustAllHttpsCertificates();
				HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
					@Override
					public boolean verify(String urlHostName, SSLSession session) {
						return true;
					}
				});
			}
			// 打开和URL之间的连接
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();

			connection.setRequestMethod("PUT");
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setConnectTimeout(30000);  
			connection.setReadTimeout(30000);
			if(header != null) {
				for (Map.Entry<String, String> entry : header.entrySet()) {
					connection.addRequestProperty(entry.getKey(), entry.getValue());
				}
			}
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(connection.getOutputStream());
			// 发送请求参数
			out.print(data);
			// flush输出流的缓冲
			out.flush();

			// 定义 BufferedReader输入流来读取URL的响应
			String sCurrentLine = "";
			reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			while ((sCurrentLine = reader.readLine()) != null) {
				sTotalString += sCurrentLine + "\r\n";
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (reader != null) {
					reader.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return sTotalString;
	}

	/**
	 * delete请求
	 * 
	 * @param urlPath
	 *            发送请求的URL
	 * @param data
	 *            请求参数
	 * @return sTotalString 相应结果
	 */
	public static String delete(String urlPath, String data, Map<String, String> header) {
		String sTotalString = "";
		BufferedReader reader = null;
		try {
			URL url = new URL(urlPath + "?" + data);
			if(urlPath.startsWith("https")) {
				trustAllHttpsCertificates();
				HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
					@Override
					public boolean verify(String urlHostName, SSLSession session) {
						return true;
					}
				});
			}
			// 打开和URL之间的连接
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();

			connection.setRequestMethod("DELETE");
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setConnectTimeout(30000);  
			connection.setReadTimeout(30000);
			if(header != null) {
				for (Map.Entry<String, String> entry : header.entrySet()) {
					connection.addRequestProperty(entry.getKey(), entry.getValue());
				}
			}
			// 定义 BufferedReader输入流来读取URL的响应
			String sCurrentLine = "";
			reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			while ((sCurrentLine = reader.readLine()) != null) {
				sTotalString += sCurrentLine + "\r\n";
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return sTotalString;
	}
	
	private static void trustAllHttpsCertificates() throws Exception {
		javax.net.ssl.TrustManager[] trustAllCerts = new javax.net.ssl.TrustManager[1];
		javax.net.ssl.TrustManager tm = new miTM();
		trustAllCerts[0] = tm;
		javax.net.ssl.SSLContext sc = javax.net.ssl.SSLContext
				.getInstance("SSL");
		sc.init(null, trustAllCerts, null);
		HttpsURLConnection.setDefaultSSLSocketFactory(sc
				.getSocketFactory());
	}

	static class miTM implements javax.net.ssl.TrustManager,
			javax.net.ssl.X509TrustManager {
		@Override
		public java.security.cert.X509Certificate[] getAcceptedIssuers() {
			return null;
		}

		public boolean isServerTrusted(
				java.security.cert.X509Certificate[] certs) {
			return true;
		}

		public boolean isClientTrusted(
				java.security.cert.X509Certificate[] certs) {
			return true;
		}

		@Override
		public void checkServerTrusted(
				java.security.cert.X509Certificate[] certs, String authType)
				throws java.security.cert.CertificateException {
			return;
		}

		@Override
		public void checkClientTrusted(
				java.security.cert.X509Certificate[] certs, String authType)
				throws java.security.cert.CertificateException {
			return;
		}
	}


	public static HttpURLConnection getURLConnection(String url) {

		return getURLConnection(url, DEFAULT_CONNECT_TIME_OUT, DEFAULT_READ_TIME_OUT);
	}

	private static HttpURLConnection getURLConnection(String urlStr, int connectTimeout, int readTimeout) throws RemoteInvocationFailureException {

		if (StringUtils.isBlank(urlStr)) {
			return null;
		}
		HttpURLConnection httpConn = null;
		// logger.debug("请求URL:" + urlStr);
		try {
			URL remoteUrl = new URL(urlStr);
			httpConn = (HttpURLConnection) remoteUrl.openConnection();
			httpConn.setConnectTimeout(connectTimeout);
			httpConn.setReadTimeout(readTimeout);
			return httpConn;
		} catch (MalformedURLException e) {
			// logger.error("", e);
			throw new RemoteInvocationFailureException("远程访问异常[" + urlStr + "]", e);
		} catch (IOException e) {
			// logger.error("", e);
			throw new RemoteInvocationFailureException("网络IO异常[" + urlStr + "]", e);
		} finally {
			if (httpConn != null) {
				httpConn.disconnect();
			}
		}
	}

}
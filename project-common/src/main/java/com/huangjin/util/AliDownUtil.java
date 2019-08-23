package com.huangjin.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.jar.JarFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: 黄金
 * @Description:
 * @Date: Create in 11:43 2019-07-29
 */
public class AliDownUtil {

    private static Logger logger = LoggerFactory.getLogger(AliDownUtil.class);

    /**
     * 下载文件到本地磁盘
     *
     * @param url
     * @param dest
     * @return
     */
    public static boolean download(String url, File dest) {
        File dir = dest.getParentFile();
        if (!dir.exists()) {
            dir.mkdirs();
        }

        long timeStamp = -1;
        if (dest.exists()) {
            timeStamp = dest.lastModified();
        }

        URLConnection connection = null;
        try {
            connection = new URL(url).openConnection();
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            connection.setRequestProperty("User-Agent", "Pandora Boot Dev Tools");
            connection.setUseCaches(false);
            if (timeStamp > 0) {
                connection.setIfModifiedSince(timeStamp);
            }
            if (shouldContinueToRead(connection)) {
                saveToFile(connection.getInputStream(), dest);
            }

            return true;
        } catch (IOException e) {
            logger.error("fail to download " + url + " to " + dest);
        } finally {
            disconnectIfNecessary(connection);
        }

        return false;
    }

    private static boolean shouldContinueToRead(URLConnection connection) throws IOException {
        if (connection instanceof HttpURLConnection) {
            HttpURLConnection httpURLConnection = (HttpURLConnection)connection;
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                if (responseCode == HttpURLConnection.HTTP_NOT_MODIFIED) {
                    // System.out.println("no need to download");
                } else {
                    logger.error("access to " + connection.getURL() + " fails with error code " + responseCode);
                }
                return false;
            }
        }
        return true;
    }

    private static void disconnectIfNecessary(URLConnection connection) {
        if (connection instanceof HttpURLConnection) {
            HttpURLConnection httpURLConnection = (HttpURLConnection)connection;
            httpURLConnection.disconnect();
        }
    }

    private static int BUFFER_SIZE = 1024 * 1024;

    public static File createTempDir(String prefix, String suffix) {
        File tempDir = null;
        try {
            tempDir = File.createTempFile(prefix, suffix);
            tempDir.delete();
            tempDir.mkdir();
            tempDir.deleteOnExit();
        } catch (IOException e) {
            System.err.println("could not create temporary directory with prefix " + prefix + " and suffix " + suffix);
        }
        return tempDir;
    }

    public static void listFiles(File dir, List<String> result) {
        if (dir.isDirectory()) {
            File[] files = dir.listFiles();
            if (files == null) {
                return;
            }

            for (File file : files) {
                listFiles(file, result);
            }
        } else if (dir.isFile()) {
            result.add(dir.getAbsolutePath());
        }
    }

    public static void copyFile(File src, File dest) throws FileNotFoundException, IOException {
        copy(new FileInputStream(src), new FileOutputStream(dest));
    }

    public static void saveToFile(InputStream is, File file) throws FileNotFoundException, IOException {
        copy(is, new FileOutputStream(file));

    }

    @SuppressWarnings("resource")
    private static void copy(InputStream is, OutputStream os) throws IOException {
        try {
            is = new BufferedInputStream(is);
            os = new BufferedOutputStream(os);
            byte[] buffer = new byte[BUFFER_SIZE];
            int read;
            while ((read = is.read(buffer)) != -1) {
                os.write(buffer, 0, read);
            }
        } finally {
            silentClose(is);
            silentClose(os);
        }
    }

    public static void silentClose(Closeable stream) {
        if (stream == null) {
            return;
        }

        try {
            stream.close();
        } catch (IOException e) {
            // ignore
        }
    }

    public static void silentClose(JarFile jarFile) {
        if (jarFile == null) {
            return;
        }

        try {
            jarFile.close();
        } catch (IOException e) {
            // ignore
        }
    }
}

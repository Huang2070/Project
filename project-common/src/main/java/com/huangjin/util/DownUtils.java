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
 * @author dabin.xudb
 * @date 2017/11/21
 */
public class DownUtils {

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

            URLConnection newConnection = shouldContinueToRead(connection);
            saveToFile(newConnection.getInputStream(), dest);
            return true;

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            disconnectIfNecessary(connection);
        }

        return false;
    }

    private static URLConnection shouldContinueToRead(URLConnection connection) throws IOException {
        URLConnection returnConnection = connection;

        if (returnConnection instanceof HttpURLConnection) {

            while(true) {
                HttpURLConnection httpURLConnection = (HttpURLConnection)returnConnection;
                int responseCode = httpURLConnection.getResponseCode();
                if (responseCode != HttpURLConnection.HTTP_OK) {

                    String redirectUrl = httpURLConnection.getHeaderField("Location");

                    returnConnection = new URL(redirectUrl).openConnection();
                    returnConnection.setConnectTimeout(5000);
                    returnConnection.setReadTimeout(5000);
                    returnConnection.setRequestProperty("User-Agent", "Pandora Boot Dev Tools");
                    returnConnection.setUseCaches(false);
                } else {
                    break;
                }
            }

        }
        return returnConnection;
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

    /**
     * 创建文件
     *
     * @param path
     * @param name
     * @return
     */
    public static File createFile(String path, String name) {
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return new File(path + name);
    }
}

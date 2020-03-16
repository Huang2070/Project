package com.huangjin.util;

import com.google.common.collect.Lists;

import java.io.*;
import java.util.List;

/**
 * Created by huang on 2019-3-13.
 */
public class IOUtils {

    /**
     * 逐行读取输入流
     * @param in
     * @return
     * @throws IOException
     */
    public static List<String> readInputStream(InputStream in) throws IOException {
        List<String> list = Lists.newArrayList();
        //防止路径乱码,如果utf-8乱码,改GBK
        InputStreamReader isr = new InputStreamReader(in, "UTF-8");
        BufferedReader br = new BufferedReader(isr);
        String line = "";
        while ((line = br.readLine()) != null) {
            list.add(line);
        }
        br.close();
        isr.close();
        return list;
    }

    /**
     * 逐行读取文件
     * @param path
     * @return
     * @throws IOException
     */
    public static List<String> readFile(String path) throws IOException {
        List<String> list = Lists.newArrayList();
        FileInputStream fis = new FileInputStream(path);
        //防止路径乱码,如果utf-8乱码,改GBK
        InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
        BufferedReader br = new BufferedReader(isr);
        String line = "";
        while ((line = br.readLine()) != null) {
            list.add(line);
        }
        br.close();
        isr.close();
        fis.close();
        return list;
    }


    /**
     * 清空文件文本内容
     * @param fileName
     * @throws IOException
     */
    public static void clearInfoForFile(String fileName) throws IOException {
        File file = new File(fileName);
        try {
            if(!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write("");
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

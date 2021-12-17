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



    /**
     * @Author：
     * @Description：获取某个目录下所有直接下级文件，不包括目录下的子目录的下的文件，所以不用递归获取
     * @Date：
     */
    public static List<String> getFileNames(String path) {
        List<String> files = Lists.newArrayList();
        File file = new File(path);
        File[] tempList = file.listFiles();

        for (int i = 0; i < tempList.length; i++) {
            if (tempList[i].isFile()) {
                //文件路径
                //files.add(tempList[i].toString());
                //文件名，不包含路径
                String fileName = tempList[i].getName();
                files.add(fileName);
            }
            if (tempList[i].isDirectory()) {
                //这里就不递归了，
            }
        }
        return files;
    }


    /**
     * @Author：
     * @Description：获取某个目录下所有直接下级文件，不包括目录下的子目录的下的文件，所以不用递归获取
     * @Date：
     */
    public static List<File> getFiles(String path) {
        List<File> files = Lists.newArrayList();
        File file = new File(path);
        File[] tempList = file.listFiles();

        for (int i = 0; i < tempList.length; i++) {
            if (tempList[i].isFile()) {
                files.add(tempList[i]);
            }
        }
        return files;
    }


    /**
     * 通过文件路径直接修改文件名
     *
     * @param filePath    需要修改的文件的完整路径
     * @param newFileName 需要修改的文件的名称
     * @return
     */
    public static void fixFileName(String filePath, String newFileName) {
        File f = new File(filePath);

        // 判断原文件是否存在（防止文件名冲突）
        if (!f.exists()) {
            return;
        }

        //newFileName = newFileName.trim();
        // 文件名不能为空
        if ("".equals(newFileName) || newFileName == null) {
            return;
        }

        String newFilePath = null;
        // 判断是否为文件夹
        if (f.isDirectory()) {
            newFilePath = filePath.substring(0, filePath.lastIndexOf("/")) + "/" + newFileName;
        } else {
            newFilePath = filePath.substring(0, filePath.lastIndexOf("/")) + "/" + newFileName
                + filePath.substring(filePath.lastIndexOf("."));
        }
        File nf = new File(newFilePath);
        try {
            // 修改文件名
            f.renameTo(nf);
        } catch (Exception err) {
            err.printStackTrace();
        }
    }
}

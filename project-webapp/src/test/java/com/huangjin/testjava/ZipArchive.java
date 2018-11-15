package com.huangjin.testjava;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by huang on 2018-4-10.
 */
public class ZipArchive {
    private static ZipArchive instance = new ZipArchive();
    private String destPath;//压缩文件的存放位置
    private String zipName;//压缩文件名
    private ZipOutputStream zos;

    public ZipArchive() {
        this("C:\\zipArchive","test.zip");// 默认压缩到C盘下
    }

    public ZipArchive(String destPath,String zipName) {
        this.destPath = destPath;
        this.zipName=zipName;
        init(this.zipName);
    }

    //更改zipName后应该重新初始化zos
    private void init(String zipName) {
        File file = new File(destPath);
        if (!file.exists()) {
            file.mkdir();
        }
        FileOutputStream dest = null;
        try {
            dest = new FileOutputStream(destPath + "\\\\" + zipName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // 输出文件
        BufferedOutputStream buffOut = new BufferedOutputStream(dest); // buffout用于压缩缓冲
        zos = new ZipOutputStream(buffOut); // 用ZipOut包装buffOut，
    }

    public void setDestPath(String destPath) {
        this.destPath = destPath;
    }

    public String getDestPath() {
        return destPath;
    }

    public static ZipArchive getInstance() {
        return instance;
    }

    private void archive(ByteArrayInputStream inputStream, String innerFileName) {
        archive(zos, inputStream, innerFileName);
    }

    private void archive(ZipOutputStream zos, ByteArrayInputStream inputStream,
                         String innerFileName) {
        try {
            zos.putNextEntry(new ZipEntry(innerFileName));
            int len;
            while ((len = inputStream.read()) != -1) {
                zos.write(len);
            }
            //zos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 压缩在压缩,
     * @param inputStream 压缩的内容
     * @param innerFileName 解压缩后文件名
     * @param innerZipName  内层压缩的文件名
     * @throws IOException
     */
    private void doubleArchive(ByteArrayInputStream inputStream,
                               String innerFileName, String innerZipName) throws IOException {
        // 因为是双重压缩，通过一个ByteArrayOutputStream的缓冲区实现
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(
                1024 * 1024);
        ZipOutputStream tempOut = new ZipOutputStream(byteArrayOutputStream);

        // 第一次压缩，压缩的结果放到xmlOut中
        archive(tempOut, inputStream, innerFileName);
        tempOut.flush();
        tempOut.finish();

        // 第二次压缩，压缩的结果放到最终输入的out中
        archive(zos,
                new ByteArrayInputStream(byteArrayOutputStream.toByteArray()),
                innerZipName);

    }

    private void closeAll() throws IOException{
        zos.flush();
        zos.close();
    }




    public static void main(String[] args) throws IOException {
        ZipArchive archive = ZipArchive.getInstance();
        archive.archive(new ByteArrayInputStream("gannisdf".getBytes()), "aaa.txt");
        archive.doubleArchive(new ByteArrayInputStream("sdfsdfsdfgannisdfsdfsdfsdf".getBytes()), "C:\\Users\\huang\\Desktop\\aaa.txt","C:\\Users\\huang\\Desktop\\shit.zip");
        archive.closeAll();
    }
}

package com.huangjin.paper;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author: 黄金
 * @Description:
 * @Date: Create in 11:12 2023/6/11
 */
@Slf4j
@Component
public class H2DbTools {

    // 数据库连接URL
    private static final String JDBC_URL = "jdbc:h2:tcp://localhost/~/h2/bin/h2db_paper";
    // 连接数据库的用户名
    private static final String USER = "huangjin";
    // 连接数据库的密码
    private static final String PASSWORD = "huangjin";
    // 连接数据库的驱动类(由H2数据库提供)
    private static final String DRIVER_CLASS = "org.h2.Driver";


    public static void executeUpdate(String sql) throws Exception {
        // 加载H2数据库驱动
        Class.forName(DRIVER_CLASS);
        // 根据连接URL，用户名，密码获取数据库连接
        Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(sql);
        // 释放资源
        stmt.close();
        // 关闭连接
        conn.close();
    }


    public static JSONArray executeQuery(String sql, List<String> columns) throws Exception {
        // 加载H2数据库驱动
        Class.forName(DRIVER_CLASS);
        // 根据连接URL，用户名，密码获取数据库连接
        Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        // 遍历结果集
        JSONArray result = new JSONArray();
        while (rs.next()) {
            JSONObject item = new JSONObject();

            for(String column : columns) {
                item.put(column, rs.getString(column));
            }
            result.add(item);
        }

        // 释放资源
        stmt.close();
        // 关闭连接
        conn.close();

        return result;
    }

    /**
     * 读取文件内容
     * @return
     * @throws IOException
     */
    public static StringBuffer readFile(String filePath) throws IOException {
        FileReader f = null;
        try {
            StringBuffer str = new StringBuffer();
            char[] buf = new char[1024];
            f = new FileReader(filePath);
            while(f.read(buf) > 0) {
                str.append(buf);
            }

            System.out.println(str);
            return str;
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            f.close();
        }
        return null;
    }
}

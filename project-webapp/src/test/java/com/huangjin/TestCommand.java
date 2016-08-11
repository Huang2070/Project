package com.huangjin;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by huangjin on 2016/8/1.
 */
public class TestCommand {

    private static Log logger = LogFactory.getLog(TestCommand.class);

    public static void sshCommand(String host,String user,String psw,int port,String command) throws Exception {
        Session session = null;
        JSch jsch = new JSch();

        session = jsch.getSession(user, host ,port);


    }

    public static void main(String[] args) {

    }



    /**
     * 处理命令执行结果
     * @param process
     * @return
     */
    private static Map<String,String> getProcessResult(Process process){
        Map<String,String> result = new HashMap<String, String>();
        if(null != process) {
            BufferedReader errorStreamReader = null;
            BufferedReader inputStreamReader = null;
            try {
                int exitValue = process.waitFor();
                errorStreamReader = new BufferedReader(new InputStreamReader(
                        process.getErrorStream()));
                inputStreamReader = new BufferedReader(new InputStreamReader(
                        process.getInputStream()));
                // parse error info
                if (errorStreamReader.ready()) {
                    StringBuilder buffer = new StringBuilder();
                    String line;
                    while ((line = errorStreamReader.readLine()) != null) {
                        buffer.append(line);
                    }
                    result.put("error", buffer.toString());
                }

                // parse info
                if (inputStreamReader.ready()) {
                    StringBuilder buffer = new StringBuilder();
                    String line;
                    while ((line = inputStreamReader.readLine()) != null) {
                        buffer.append(line);
                    }
                    result.put("output", buffer.toString());
                }

                errorStreamReader.close();
                inputStreamReader.close();
                if (0 != exitValue) {
                    logger.error("call shell failed. error code is :" + exitValue);
                }else{
                    logger.info("call shell success. code is :" + exitValue);
                }

            } catch (Exception e) {
                logger.info("处理命令执行结果失败 " + e);
            }
        }
        return result;
    }
}

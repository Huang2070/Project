package com.huangjin.util;

import java.util.Base64;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * pid编码处理
 * @author yifeng
 * @date 2019/5/10 下午2:02
 */
public class PidCodeUtile {
    private static final String PATTERN = "^c[a-zA-Z]{1}[0-9]+";

    private static Logger logger = LoggerFactory.getLogger(PidCodeUtile.class);

    /**
     * 加密
     *
     * @param pid
     * @return
     */
    public static String encodePid(Long pid) {
        String str = String.valueOf(pid << 2);
        byte[] textByte = str.getBytes();
        return 'X' + Base64.getEncoder().encodeToString(textByte).replaceAll("\r|\n", "");
    }

    /**
     * 解密
     *
     * @param pid
     * @return
     */
    public static Long decodePid(String pid, String mark) {
        if (StringUtils.isEmpty(pid)) {
            return null;
        } else {
            pid = pid.replaceFirst(PATTERN, "");
            if (StringUtils.isEmpty(pid) && pid.startsWith(mark)) {
                return NumberUtils.toLong(pid);
            } else {
                try {
                    String str = !StringUtils.isEmpty(pid) && pid.startsWith(mark)
                        ? pid.substring(mark.length()) : pid;
                    byte[] bb = Base64.getDecoder().decode(str);
                    return NumberUtils.toLong(new String(bb, "UTF-8")) >> 2;
                } catch (Exception e) {
                    logger.warn("PidCodeUtile decodePid message:{}",e.getMessage(),e);
                    return null;
                }
            }
        }
    }
}

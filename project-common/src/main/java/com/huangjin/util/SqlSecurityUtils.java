package com.huangjin.util;

/**
/* SqlSecurityUtils
 *
 * @author mbg.generated
 * @version 2019/03/19 08:15:06
 */
public class SqlSecurityUtils {

    /**
     * trimSql
     *
     * @param slice
     * @return 
     */
    public static String trimSql(String slice) {
        if (slice == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (char c : slice.toCharArray()) {
            if ((c >= 'a' && c <= 'z')) {
                sb.append(c);
            } else if ((c >= '0' && c <= '9')) {
                sb.append(c);
            } else if ((c >= 'A' && c <= 'Z')) {
                sb.append(c);
            } else if (c == '_' || c == '-' || c == '.' || c == ' ' || c == ',') {
                sb.append(c);
            } else if (c == '>' || c == '<' || c == '=' || c == '\"' || c == '\'') {
                sb.append(c);
            } else if (c == '(' || c == ')') {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
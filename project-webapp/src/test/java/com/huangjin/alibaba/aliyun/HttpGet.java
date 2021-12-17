package com.huangjin.alibaba.aliyun;

import java.net.URLEncoder;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * @author: wb-wb606477
 * @date: 2021/1/26 12:30
 * @descript:
 */
public class HttpGet {

    private String url;

    private String pubKey4Aliyun;

    private String priKey4Local;

    private String secretKey;

    private String secretKeyWithRSA;

    private String signType = "SHA256WithRSA";

    private Map<String, Object> values = new TreeMap<String, Object>();

    public HttpGet(String url, String pubKey4Aliyun, String priKey4Local) {
        this.url = url;
        this.pubKey4Aliyun = pubKey4Aliyun;
        this.priKey4Local = priKey4Local;
        secretKey = RandomStringUtils.randomNumeric(32);
    }

    public HttpGet addParam(String paramName, String paramValue) {



        if (paramName == null || "".equals(paramName)) {
            return this;
        }

        if("sign".equals(paramName)){
            throw new IllegalArgumentException("sign");
        }


        if("secretKey".equals(paramName)){
            throw new IllegalArgumentException("secretKey");
        }

        if (paramValue == null || "".equals(paramValue)) {
            return this;
        }

        try {
            values.put(paramName, URLEncoder.encode(AesUtil.encrypt(paramValue, secretKey)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    public String build() throws Exception {
        StringBuilder queryStringBuilder = new StringBuilder();
        queryStringBuilder.append(url + "?");
        values.put("secretKey",URLEncoder.encode(RSAUtil.encrypt(secretKey,pubKey4Aliyun)));
        values.put("signType", URLEncoder.encode(AesUtil.encrypt(signType, secretKey)));
        values.put("timeStamp", URLEncoder.encode(AesUtil.encrypt(String.valueOf(System.currentTimeMillis()), secretKey)));
        values.put("requestId", URLEncoder.encode(AesUtil.encrypt(UUID.randomUUID().toString().replaceAll("-", ""), secretKey)));
        String encryptedQueryString = RSAUtil.paramConverter(values);
        String s = RSAUtil.rsaSign(priKey4Local, values, signType);
        queryStringBuilder.append(encryptedQueryString);
        /**
         * sign 必须放在最后一位，其他参数随机摆放就可以
         */
        queryStringBuilder.append("&sign="+s);
        return queryStringBuilder.toString();
    }

}
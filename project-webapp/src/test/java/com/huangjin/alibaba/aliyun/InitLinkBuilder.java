package com.huangjin.alibaba.aliyun;


/**
 * @author: wb-wb606477
 * @date: 2021/1/26 13:12
 * @descript:
 */
public class InitLinkBuilder {

    private final static String httpPath = "/api/finance/gateway/customer/init";

    private InitLinkBuilder(){}

    private HttpGet httpGet;

    public static InitLinkBuilder newInstance(String url, String pubKey4Aliyun, String priKey4Local){
        InitLinkBuilder initLinkBuilder = new InitLinkBuilder();
        initLinkBuilder.httpGet = new HttpGet(url +initLinkBuilder.getHttpPath(),pubKey4Aliyun,priKey4Local);
        return initLinkBuilder;
    }

    public String getHttpPath(){
        return  httpPath;
    }

    public InitLinkBuilder addParam(String param , String value){
        httpGet.addParam(param,value);
        return this;
    }

    public String build(){
        try {
            return httpGet.build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}

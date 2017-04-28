package com.huangjin.enumeration;

import com.huangjin.util.PropertiesUtils;

/**
 * Created by huang on 2017-4-21.
 */
public class Constant {
    public static final Integer CONTENT_CACHE_NUM = Integer.parseInt(PropertiesUtils.getInstance().getProperty("content.cache.num"));
    public static final Integer CONTENT_CACHE_TIME = Integer.parseInt(PropertiesUtils.getInstance().getProperty("content.cache.time"));
}

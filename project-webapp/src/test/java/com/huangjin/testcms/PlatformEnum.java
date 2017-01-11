package com.huangjin.testcms;

/**
 * Created by huang on 2016-9-6.
 */
public enum PlatformEnum {

    UC("uc", 6789),
    PROXY("proxy", 1234),
    HSEA("hsea", 2345);

    private String platformName;
    private int platformId;

    PlatformEnum(String platformName, int platformId) {
        this.platformName = platformName;
        this.platformId = platformId;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public int getPlatformId() {
        return platformId;
    }

    public void setPlatformId(int platformId) {
        this.platformId = platformId;
    }
}

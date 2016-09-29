package com.huangjin.enumeration;

/**
 * Created by apple on 8/5/14.
 */
public enum ShareEnum {

    QQ("qzone","分享到QQ空间","#"),
    KAIXIN("kaixin001","分享到开心网","#"),
    RENREN("renren","分享到人人网","#"),
    SINA("tsina","分享到新浪微博","#"),
    DOUBAN("douban","分享到豆瓣网","#"),
    BAI("sohu","分享到搜狐白社会","#"),
    TIEBA("tieba","分享到百度贴吧","#"),
    HI("hi","分享到百度空间","#"),
    T163("t163","分享到网易微博","#"),
    TSOHU("tsohu","分享到搜狐微博","#"),
    TQQ("tqq","分享到腾讯微博","#"),
    QQFRIEND("tqf","分享到腾讯朋友","#"),
    SQQ("sqq","分享到QQ好友","#"),
    FX("fx","分享到飞信","#"),
    TAOBAO("taobao","分享到我的淘宝","#"),
    TQF("tqf","分享到腾讯朋友","#");

    private String shareId;
    private String shareName;
    private String shareLink;
    private ShareEnum(String shareId, String shareName, String shareLink){
        this.shareId = shareId;
        this.shareName = shareName;
        this.shareLink = shareLink;
    }

    public static ShareEnum getShareEnum(String id){
        for(ShareEnum se:values()){
            if(id.equals(se.getShareId())){
                return se;
            }
        }
        return null;
    }

    public String getShareId() {
        return shareId;
    }

    public void setShareId(String shareId) {
        this.shareId = shareId;
    }

    public String getShareName() {
        return shareName;
    }

    public void setShareName(String shareName) {
        this.shareName = shareName;
    }

    public String getShareLink() {
        return shareLink;
    }

    public void setShareLink(String shareLink) {
        this.shareLink = shareLink;
    }
}

package com.huangjin.vo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * Created by huang on 2017-3-15.
 */
public class ActivityVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 活动名称
     */
    private String acTitle;

    /**
     * 活动介绍
     */
    private String acDesc;

    /**
     * 奖品列表
     */
    private String prize;

    /**
     * 活动开始时间
     */
    private String acStartTime;

    /**
     * 活动截止时间
     */
    private String acEndTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAcTitle() {
        return acTitle;
    }

    public void setAcTitle(String acTitle) {
        this.acTitle = acTitle;
    }

    public String getAcDesc() {
        return acDesc;
    }

    public void setAcDesc(String acDesc) {
        this.acDesc = acDesc;
    }

    public List<HashMap<String, String>> getPrize() {
        return JSON.parseObject(prize, new TypeReference<List<HashMap<String, String>>>() {});
    }

    public void setPrize(String prize) {
        this.prize = prize;
    }

    public String getAcStartTime() {
        return acStartTime;
    }

    public void setAcStartTime(String acStartTime) {
        this.acStartTime = acStartTime;
    }

    public String getAcEndTime() {
        return acEndTime;
    }

    public void setAcEndTime(String acEndTime) {
        this.acEndTime = acEndTime;
    }
}

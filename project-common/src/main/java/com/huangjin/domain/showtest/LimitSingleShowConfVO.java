package com.huangjin.domain.showtest;

import java.util.Date;
import java.util.List;

/**
 * @Author: 黄金
 * @Description:
 * @Date: Create in 16:03 2019-08-22
 */
public class LimitSingleShowConfVO {

    /**
     * 主键
     */
    private Long id;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;

    /**
     * 被限制的节目
     */
    private List<LimitShowVo> limitShowVos;

    /**
     * 被限制的节目类别
     */
    private String showCategoryName;

    /**
     * 最短视频时长
     */
    private Integer minVideoLength;

    /**
     * 最大视频时长
     */
    private Integer maxVideoLength;

    /**
     * 开始生效时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 最后修改者名字
     */
    private String lastModifierName;

    /**
     * 是否限制自剪辑视频
     */
    private Integer limitSelfEdited;

    /**
     * 是否限制云剪辑视频
     */
    private Integer limitCloudEdited;

    /**
     * 是否限制其他来源视频
     */
    private Integer limitOtherSource;

    /**
     * 忽略限制的pgy用户
     */
    private List<UserInfoDO> userInfoDOS;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public List<LimitShowVo> getLimitShowVos() {
        return limitShowVos;
    }

    public void setLimitShowVos(List<LimitShowVo> limitShowVos) {
        this.limitShowVos = limitShowVos;
    }

    public String getShowCategoryName() {
        return showCategoryName;
    }

    public void setShowCategoryName(String showCategoryName) {
        this.showCategoryName = showCategoryName;
    }

    public Integer getMinVideoLength() {
        return minVideoLength;
    }

    public void setMinVideoLength(Integer minVideoLength) {
        this.minVideoLength = minVideoLength;
    }

    public Integer getMaxVideoLength() {
        return maxVideoLength;
    }

    public void setMaxVideoLength(Integer maxVideoLength) {
        this.maxVideoLength = maxVideoLength;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getLastModifierName() {
        return lastModifierName;
    }

    public void setLastModifierName(String lastModifierName) {
        this.lastModifierName = lastModifierName;
    }

    public Integer getLimitSelfEdited() {
        return limitSelfEdited;
    }

    public void setLimitSelfEdited(Integer limitSelfEdited) {
        this.limitSelfEdited = limitSelfEdited;
    }

    public Integer getLimitCloudEdited() {
        return limitCloudEdited;
    }

    public void setLimitCloudEdited(Integer limitCloudEdited) {
        this.limitCloudEdited = limitCloudEdited;
    }

    public Integer getLimitOtherSource() {
        return limitOtherSource;
    }

    public void setLimitOtherSource(Integer limitOtherSource) {
        this.limitOtherSource = limitOtherSource;
    }

    public List<UserInfoDO> getUserInfoDOS() {
        return userInfoDOS;
    }

    public void setUserInfoDOS(List<UserInfoDO> userInfoDOS) {
        this.userInfoDOS = userInfoDOS;
    }
}

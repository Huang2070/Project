package com.huangjin.domain.showtest;

import java.util.Date;
import java.util.List;

/**
 * @Author: 黄金
 * @Description:
 * @Date: Create in 16:05 2019-08-22
 */
public class UserInfoDO {

    /**
     * 主键
     */
    private Long id;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 账号名称
     */
    private String uname;

    /**
     * 优酷UID
     */
    private Long ykUid;

    /**
     * 优酷YID
     */
    private Long ykYid;

    /**
     * 优酷用户名
     */
    private String ykName;

    /**
     * 主营分类
     */
    private String category;

    /**
     * 账号等级
     */
    private Integer level;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 创建者ID
     */
    private Integer creatorId;

    /**
     * 创建人
     */
    private String creatorName;

    /**
     * 最后修改时间
     */
    private Date uptime;

    /**
     * 最后修改者ID
     */
    private Integer upCreatorId;

    /**
     * 每日计划排播数
     */
    private Integer planDailyCount;

    /**
     * 最后修改者
     */
    private String upCreatorName;


    /**
     * 任务ID
     */
    private Long taskid;

    /**
     * 对应机构ID
     */
    private Long companyId;

    /**
     * 状态 1正常2禁用
     */
    private Integer status;


    public Long getTaskid() {
        return taskid;
    }

    public void setTaskid(Long taskid) {
        this.taskid = taskid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public Long getYkUid() {
        return ykUid;
    }

    public void setYkUid(Long ykUid) {
        this.ykUid = ykUid;
    }

    public String getYkName() {
        return ykName;
    }

    public void setYkName(String ykName) {
        this.ykName = ykName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public Date getUptime() {
        return uptime;
    }

    public void setUptime(Date uptime) {
        this.uptime = uptime;
    }

    public Integer getUpCreatorId() {
        return upCreatorId;
    }

    public void setUpCreatorId(Integer upCreatorId) {
        this.upCreatorId = upCreatorId;
    }

    public String getUpCreatorName() {
        return upCreatorName;
    }

    public void setUpCreatorName(String upCreatorName) {
        this.upCreatorName = upCreatorName;
    }

    public Integer getPlanDailyCount() {
        return planDailyCount;
    }

    public void setPlanDailyCount(Integer planDailyCount) {
        this.planDailyCount = planDailyCount;
    }

    public Long getYkYid() {
        return ykYid;
    }

    public void setYkYid(Long ykYid) {
        this.ykYid = ykYid;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}

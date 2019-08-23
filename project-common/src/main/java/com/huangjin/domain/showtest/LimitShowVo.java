package com.huangjin.domain.showtest;

/**
 * @Author: 黄金
 * @Description:
 * @Date: Create in 16:04 2019-08-22
 */
public class LimitShowVo {
    /**
     * 节目编号
     */
    private String showId;

    /**
     * long类型的节目id
     */
    private Long showLongId;

    /**
     * 节目名
     */
    private String showName;

    /**
     * 节目描述前50字
     */
    private String descHead;

    /**
     * 发行年份
     */
    private Integer releaseYear;

    /**
     * 导演
     */
    //private List<YoukuPersonSimpleDTO> director;

    /**
     * 主演
     */
    //private List<YoukuPersonSimpleDTO> starring;

    /**
     * 是否完结
     */
    private Integer completed;

    /**
     * 是否独播
     */
    private Integer exclusive;


    /**
     *
     * @return
     */
    private String description;

    public String getShowId() {
        return showId;
    }

    public void setShowId(String showId) {
        this.showId = showId;
    }

    public Long getShowLongId() {
        return showLongId;
    }

    public void setShowLongId(Long showLongId) {
        this.showLongId = showLongId;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public String getDescHead() {
        return descHead;
    }

    public void setDescHead(String descHead) {
        this.descHead = descHead;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Integer getCompleted() {
        return completed;
    }

    public void setCompleted(Integer completed) {
        this.completed = completed;
    }

    public Integer getExclusive() {
        return exclusive;
    }

    public void setExclusive(Integer exclusive) {
        this.exclusive = exclusive;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

package com.huangjin.state;

/**
 * @Author: 黄金
 * @Description:
 * @Date: Create in 20:00 2020-07-21
 */
public interface DistributeState {

    /**
     * 更新草稿
     */
    void save(DistributeContext context);

    /**
     * 更新已经审核通过的投放
     */
    String configFinish(DistributeContext context);


}

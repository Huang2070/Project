package com.huangjin.vo;

import com.huangjin.util.annotation.ExcelColumn;

import java.io.Serializable;

/**
 * Created by huang on 2019-2-12.
 */

public class ExcelVO implements Serializable {

    private static final long serialVersionUID = -2623927628402868224L;

    //商品id
    @ExcelColumn(order = 1)
    private Long goodsId;

    //增值税号
    @ExcelColumn(order = 2)
    private String addedValueTaxNo;

    //消费税号
    @ExcelColumn(order = 3)
    private String consumerTaxNo;

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getConsumerTaxNo() {
        return consumerTaxNo;
    }

    public void setConsumerTaxNo(String consumerTaxNo) {
        this.consumerTaxNo = consumerTaxNo;
    }

    public String getAddedValueTaxNo() {
        return addedValueTaxNo;
    }

    public void setAddedValueTaxNo(String addedValueTaxNo) {
        this.addedValueTaxNo = addedValueTaxNo;
    }
}

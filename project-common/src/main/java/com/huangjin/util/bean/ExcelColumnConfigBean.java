package com.huangjin.util.bean;

/**
 * Created by huang on 2019-2-12.
 */
public class ExcelColumnConfigBean implements Comparable<ExcelColumnConfigBean> {
    private int order;
    private String column;
    private String fieldName;
    private Class<?> type;

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Class<?> getType() {
        return type;
    }

    public void setType(Class<?> type) {
        this.type = type;
    }

    @Override
    public int compareTo(ExcelColumnConfigBean o) {
        if(this.order>o.order){
            return 1;
        }else if(this.order<o.order){
            return -1;
        }
        return 0;
    }
}

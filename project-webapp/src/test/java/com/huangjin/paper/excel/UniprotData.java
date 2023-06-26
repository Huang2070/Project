package com.huangjin.paper.excel;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: 黄金
 * @Description:
 * @Date: Create in 17:37 2023/6/12
 */
public class UniprotData {

    private String uniprotId;

    private String reviewed;

    private String entryName;

    private String proteinNames;

    private String geneNames;

    public String getUniprotId() {
        return uniprotId;
    }

    public void setUniprotId(String uniprotId) {
        this.uniprotId = uniprotId;
    }

    public String getReviewed() {
        return reviewed;
    }

    public void setReviewed(String reviewed) {
        this.reviewed = reviewed;
    }

    public String getEntryName() {
        return entryName;
    }

    public void setEntryName(String entryName) {
        this.entryName = entryName;
    }

    public String getProteinNames() {
        return proteinNames;
    }

    public void setProteinNames(String proteinNames) {
        this.proteinNames = proteinNames;
    }

    public String getGeneNames() {
        return geneNames;
    }

    public void setGeneNames(String geneNames) {
        this.geneNames = geneNames;
    }
}

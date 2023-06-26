package com.huangjin.paper.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;

import com.huangjin.paper.H2DbTools;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

/**
 * @Author: 黄金
 * @Description:
 * @Date: Create in 17:41 2023/6/11
 */
@Slf4j
public class ExcelTools {


    @Test
    public void uniprotData() {

        //先删除原记录
        String deleteSql = "delete from UNIPROT_DATABASE";
        try {
            H2DbTools.executeUpdate(deleteSql);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String fileName = "/Users/hj_2021/paper/uniprot.xlsx";
        // 这里默认每次会读取100条数据 然后返回过来 直接调用使用数据就行
        // 具体需要返回多少行可以在`PageReadListener`的构造函数设置
        EasyExcel.read(fileName, UniprotData.class, new PageReadListener<UniprotData>(dataList -> {

            StringBuilder insertSql = new StringBuilder("insert into UNIPROT_DATABASE (uniprot_id,gene_name,protein_names) values ");

            for (UniprotData uniprotData : dataList) {
                //处理proteinNames
                String proteinNames = uniprotData.getProteinNames();
                if(StringUtils.isNotEmpty(proteinNames)) {
                    proteinNames = proteinNames.replace("'", "''");
                    if(proteinNames.contains("(")) {
                        proteinNames = proteinNames.substring(0, proteinNames.indexOf("(")).trim();
                    }
                }

                insertSql.append("(")
                    .append("'").append(uniprotData.getUniprotId()).append("'").append(",")
                    .append("'").append(uniprotData.getGeneNames()).append("'").append(",")
                    .append("'").append(proteinNames).append("'")
                    .append("),");
            }

            String insertSqlStr = insertSql.toString();
            insertSqlStr = insertSqlStr.substring(0, insertSqlStr.length() - 1);

            System.out.println(insertSqlStr);
            try {
                H2DbTools.executeUpdate(insertSqlStr);
            } catch (Exception e) {
                e.printStackTrace();
            }
        })).sheet().doRead();
    }

}

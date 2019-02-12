package com.huangjin.tools;

import com.huangjin.util.excel.ExcelUtils;
import com.huangjin.vo.ExcelVO;
import org.junit.Test;

import java.io.*;
import java.util.List;

/**
 * Created by huang on 2019-2-12.
 */
public class ExcelLoad {


    @Test
    public void test() throws IOException {
        FileInputStream fis = new FileInputStream("C:\\Users\\huang\\Downloads\\增值税消费税数据修复.xlsx");

        List<ExcelVO> excelVOs = ExcelUtils.getPart(fis, ExcelVO.class, false, Integer.MAX_VALUE, 0);

        BufferedWriter sqlWriter = new BufferedWriter(new FileWriter(new File("C:\\Users\\huang\\Downloads\\fix.sql")));
        for(ExcelVO excelVO : excelVOs) {
            String udpateSql = "update GLOBAL_PRODUCT.TB_GOODS_EDIT set vat_no = '" + excelVO.getAddedValueTaxNo() +
                    "', consumer_tax_no = '" + excelVO.getConsumerTaxNo() + "' where goods_id = " + excelVO.getGoodsId() + ";";
            sqlWriter.write(udpateSql);
            sqlWriter.newLine();
        }

        sqlWriter.newLine();
        sqlWriter.newLine();
        sqlWriter.newLine();

        for(ExcelVO excelVO : excelVOs) {
            String udpateSql = "update GLOBAL_PRODUCT.TB_GOODS set vat_no = '" + excelVO.getAddedValueTaxNo() +
                    "', consumer_tax_no = '" + excelVO.getConsumerTaxNo() + "' where goods_id = " + excelVO.getGoodsId() + ";";
            sqlWriter.write(udpateSql);
            sqlWriter.newLine();
        }

        sqlWriter.close();
    }
}

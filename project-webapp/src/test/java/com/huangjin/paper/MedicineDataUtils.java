package com.huangjin.paper;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Set;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.junit.Test;

/**
 * @Author: 黄金
 * @Description:
 * @Date: Create in 11:52 2023/6/11
 */
public class MedicineDataUtils {


    /**
     * 中药名称
     */
    final public static String medicineName = "竹茹";

    /**
     * 中药成分文件
     */
    public static String medicineComponentFilePath = "/Users/hj_2021/private/Project/project-webapp/src/test/java/com/huangjin/paper/medicineComponent";

    /**
     * 中药成分靶点文件
     */
    public static String medicineComponentTargetFilePath = "/Users/hj_2021/private/Project/project-webapp/src/test/java/com/huangjin/paper/medicineComponentTarget";

    /**
     * 获取中药有效成分
     * @throws Exception
     */
    @Test
    public void insertAvailMedicineComponent() throws Exception {

        //先删除原记录
        String deleteSql = "delete from medicine_component where medicine_name = '" + medicineName + "'";
        H2DbTools.executeUpdate(deleteSql);

        StringBuilder insertSql = new StringBuilder("insert into medicine_component (medicine_name,mol_id,molecule_name,OB,DL) values ");
        JSONArray array = readFileGetJSONArray(medicineComponentFilePath);
        for(Object obj : array) {
            JSONObject medicineComponent = (JSONObject)obj;
            Double ob = medicineComponent.getDouble("ob");
            Double dl = medicineComponent.getDouble("dl");

            if(ob >= 30 && dl >= 0.18) {
                insertSql.append("(")
                    .append("'").append(medicineName).append("'").append(",")
                    .append("'").append(medicineComponent.getString("MOL_ID")).append("'").append(",")
                    .append("'").append(medicineComponent.getString("molecule_name").replace("'", "''")).append("'").append(",")
                    .append("'").append(medicineComponent.getString("ob")).append("'").append(",")
                    .append("'").append(medicineComponent.getString("dl")).append("'")
                    .append("),");
            }
        }

        String insertSqlStr = insertSql.toString();
        insertSqlStr = insertSqlStr.substring(0, insertSqlStr.length() - 1);

        System.out.println(insertSqlStr);
        H2DbTools.executeUpdate(insertSqlStr);
    }

    /**
     * 获取中药有效成分靶点
     * @throws Exception
     */
    @Test
    public void insertAvailMedicineComponentTarget() throws Exception {

        //先删除原记录
        String deleteSql = "delete from medicine_component_target where medicine_name = '" + medicineName + "'";
        H2DbTools.executeUpdate(deleteSql);

        //查找中药有效成分
        String selectAvailMedicineComponent = "select mol_id from medicine_component where medicine_name = '" + medicineName + "'";

        List<String> molIdList = Lists.newArrayList();
        JSONArray result = H2DbTools.executeQuery(selectAvailMedicineComponent, Lists.newArrayList("mol_id"));
        for(Object obj : result) {
            JSONObject item = (JSONObject)obj;
            String mol_id = item.getString("mol_id");
            molIdList.add(mol_id);
        }

        //插入中药靶点表
        String insertSql = "insert into medicine_component_target (medicine_name,mol_id,molecule_name,target_name) values ";

        //去重
        Set<String> targetNameSet = Sets.newHashSet();

        JSONArray array = readFileGetJSONArray(medicineComponentTargetFilePath);
        for(Object obj : array) {
            JSONObject medicineComponentTarget = (JSONObject)obj;
            String molId = medicineComponentTarget.getString("MOL_ID");
            if(molIdList.contains(molId)) {

                String targetName = medicineComponentTarget.getString("target_name");
                //去重
                if(!targetNameSet.add(targetName)) {
                    continue;
                }

                //处理targetName中的特殊字符
                targetName = targetName.replace("'", "''");

                StringBuilder insertSqlStr = new StringBuilder(insertSql);
                insertSqlStr.append("(")
                    .append("'").append(medicineName).append("'").append(",")
                    .append("'").append(medicineComponentTarget.getString("MOL_ID")).append("'").append(",")
                    .append("'").append(medicineComponentTarget.getString("molecule_name").replace("'", "''")).append("'").append(",")
                    .append("'").append(targetName).append("'")
                    .append(");");

                System.out.println(insertSqlStr);
                H2DbTools.executeUpdate(insertSqlStr.toString());
            }
        }
    }

    @Test
    public void updateMedicineComponentTargetGeneSymbol() throws Exception {

        //查找中药有效成分
        String selectAvailMedicineComponentTarget = "select * from medicine_component_target where medicine_name = '" + medicineName + "'";
        JSONArray queryTargetResult = H2DbTools.executeQuery(selectAvailMedicineComponentTarget, Lists.newArrayList("id", "target_name"));
        for(Object obj : queryTargetResult) {
            JSONObject item = (JSONObject)obj;
            String id = item.getString("id");
            String targetName = item.getString("target_name").replace("'", "''").trim();

            //匹配uniprot
            String matchUniprot = "select * from UNIPROT_DATABASE where protein_names like '%" + targetName + "%'";
            JSONArray matchResult = H2DbTools.executeQuery(matchUniprot, Lists.newArrayList("uniprot_id", "gene_name"));
            if(matchResult.size() > 0) {
                for(Object uniprotObj : matchResult) {
                    JSONObject uniprotItem = (JSONObject)uniprotObj;
                    String uniprotId = uniprotItem.getString("uniprot_id");
                    String geneName = uniprotItem.getString("gene_name");
                    String updateTarget = "update medicine_component_target set uniprot_id = '" + uniprotId + "', gene_name = '" + geneName + "' where id = " + id;
                    System.out.println(updateTarget);

                    H2DbTools.executeUpdate(updateTarget);
                }
            }
        }
    }



    /**
     * 逐行读取JSONArray格式的文件，json文件需要格式化
     * @param filePath
     * @return
     * @throws IOException
     */
    public static JSONArray readFileGetJSONArray(String filePath) throws IOException {

        JSONArray jsonArray = new JSONArray();
        //逐行读取文件
        List<String> jsonLine = readFileByLine(filePath);

        //保存单个json
        String tmpJsonStr = "";
        for(int i = 1; i < jsonLine.size(); i++) {
            String line = jsonLine.get(i);
            tmpJsonStr = tmpJsonStr + line;

            //单个json结束
            if(line.trim().equals("},")) {
                //去逗号
                tmpJsonStr = tmpJsonStr.substring(0, tmpJsonStr.length() - 1);
                JSONObject obj = JSON.parseObject(tmpJsonStr);
                jsonArray.add(obj);
                tmpJsonStr = "";
            }
        }

        return jsonArray;
    }



    /**
     * 逐行读取文件
     * @param path
     * @return
     * @throws IOException
     */
    public static List<String> readFileByLine(String path) throws IOException {
        List<String> list = Lists.newArrayList();
        FileInputStream fis = new FileInputStream(path);
        //防止路径乱码,如果utf-8乱码,改GBK
        InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
        BufferedReader br = new BufferedReader(isr);
        String line = "";
        while ((line = br.readLine()) != null) {
            list.add(line);
        }
        br.close();
        isr.close();
        fis.close();
        return list;
    }

}

package com.huangjin.paper;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSONArray;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.Data;
import org.junit.Test;

/**
 * @Author: 黄金
 * @Description:
 * @Date: Create in 16:58 2023/6/18
 */
public class NetworkDataUtils {

    /**
     *
     * @throws Exception
     */
    @Test
    public void getNetworkData() throws Exception {

        String getNetworkSql = "select * from medicine_component_target where gene_name is not null";

        JSONArray result = H2DbTools.executeQuery(getNetworkSql, Lists.newArrayList("MEDICINE_NAME","MOL_ID","GENE_NAME"));
        List<MedicineTarget> list = result.toJavaList(MedicineTarget.class);

        Map<String, List<MedicineTarget>> maps = list.stream().collect(Collectors.groupingBy(MedicineTarget::getMedicineName));

        Map<String, List<String>> componentCodeListMap = Maps.newHashMap();
        for(Entry<String, List<MedicineTarget>> entry : maps.entrySet()) {
            String medicineName = entry.getKey();
            String componentCode = this.getMedicineCode(medicineName);

            List<String> componentCodeList = Lists.newArrayList();

            List<MedicineTarget> medicineTargetList = entry.getValue();
            String molIdTmp = null;
            Integer componentNum = 1;
            for(MedicineTarget medicineTarget : medicineTargetList) {
                String molId = medicineTarget.getMolId();
                String componentCodeTmp = componentCode;
                if(molIdTmp == null) {
                    molIdTmp = molId;
                } else if(!molId.equals(molIdTmp)) {
                    molIdTmp = molId;
                    componentNum++;
                }
                componentCodeTmp = componentCodeTmp + componentNum;

                componentCodeList.add(componentCodeTmp);

                System.out.printf("%s,%s,%s%n", medicineName, componentCodeTmp, medicineTarget.getGeneName());
            }
            componentCodeListMap.put(medicineName, componentCodeList);
        }

        for(Entry<String, List<String>> entry : componentCodeListMap.entrySet()) {
            String medicineName = entry.getKey();
            List<String> medicineCodeList = entry.getValue();
            for(String medicineCode : medicineCodeList) {
                System.out.printf("%s,%s%n", medicineName, medicineCode);
            }

        }
    }

    @Data
    static class MedicineTarget {
        private String medicineName;
        private String molId;
        private String geneName;
    }


    private String getMedicineCode(String medicineName) {
        Map<String, String> medicineMap = Maps.newHashMap();
        medicineMap.put("半夏","BX");
        medicineMap.put("茯苓","FL");
        medicineMap.put("竹茹","ZR");
        medicineMap.put("枳实","ZS");
        medicineMap.put("陈皮","CP");
        medicineMap.put("甘草","GC");
        return medicineMap.get(medicineName);
    }
}

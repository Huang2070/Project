package com.huangjin.testjava;


//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:spring/*"})

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.huangjin.domain.User;
import com.huangjin.util.MathUtil;
import com.huangjin.util.TimeUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.File;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class TestClass {
    @Test
    public void test() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        System.out.println(sdf.format(Long.parseLong("1493888196000")));


        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse("2019-01-22 20:03:21");
        long ts = date.getTime();
        res = String.valueOf(ts);

        System.out.println(res);
        
    }

    @Test
    public void intParse() {
        boolean result = Integer.parseInt("1") == 1;
        System.out.println(result);
    }

    private String dateToStamp(String s) throws ParseException {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        res = String.valueOf(ts);
        return res;
    }


    @Test
    public void test1() {
        Integer i = 2;
        Integer j = 3;
        System.out.println((float)i/j*100);
        System.out.println((float)i/j*100 > 100);
    }


    @Test
    public void test2() {
        Date dayId = null;
        String criticalTimeStr = "15:00";
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        try {
            Date now = df.parse(df.format(new Date()));
            Date criticalTime = df.parse(criticalTimeStr);

            if(now.after(criticalTime)) {
                dayId = TimeUtil.getYesterDay();
            } else {
                dayId = TimeUtil.getBeforeYesterDay();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(dayId);
    }


    @Test
    public void test3() {
        Date date = new Date(1549209600000L);
        System.out.println(date);

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            System.out.println(sdf.format(date));

            Date date1 = sdf.parse("2018-05-23 08:00:00");

            Date date2 = sdf.parse("2018-06-15 08:00:00");

            System.out.println(date1.getTime());
            System.out.println(date2.getTime());
        } catch(Exception e) {

        }
    }

    @Test
    public void test4() {
        for(int i = 0; i < 10; i++) {
            System.out.println(i);
        }
    }

    @Test
    public void test5() {
        Map<String, Object> jsonMap = Maps.newHashMap();
        jsonMap.put("obj1", 1);
        jsonMap.put("obj2", "123");
        List<String> stringList = Lists.newArrayList();
        stringList.add("huangjin");
        stringList.add("liumingfang");
        jsonMap.put("obj3", stringList);
        jsonMap.put("obj4", new Date());
        String jsonStr = JSON.toJSONString(jsonMap);
        System.out.println(jsonStr);

        JSONObject jsonObject = JSON.parseObject(jsonStr);
        Date date = jsonObject.getDate("obj4");

        List<String> obj3 = Arrays.asList(jsonObject.getJSONArray("obj3").toArray(new String[]{}));
        System.out.println(date);
        System.out.println(obj3);
    }


    @Test
    public void test6() {
        List<Integer> list = Lists.newArrayList(0,1,2,3,4,5,6,7,8,9);

        List<Integer> subList = list.subList(0, 1);
        System.out.println(subList);
    }

    @Test
    public void test7() {
        String f = "100";
        Float f1 = Float.parseFloat(f);

        float f2 = (float)100/100*100;

        System.out.println(f1 == f2);
    }

    @Test
    public void test8() {
            Date date = new Timestamp(1529020800000L);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

            Calendar nowCal = Calendar.getInstance();
            nowCal.setTime(new Date());

        Boolean result = calendar.get(Calendar.YEAR) == nowCal.get(Calendar.YEAR)
                    && calendar.get(Calendar.MONTH) == nowCal.get(Calendar.MONTH)
                    && calendar.get(Calendar.DAY_OF_MONTH) == nowCal.get(Calendar.DAY_OF_MONTH);
        System.out.println(result);
    }

    @Test
    public void test10() {
        try {
            System.out.println(1);
            throw new RuntimeException();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(1.5);
        } finally {
            System.out.println(2);
        }

        System.out.println(3);
    }

    @Test
    public void test12() {
        List<Long> list = null;
        for(Long num : list) {
            System.out.println(num);
        }
    }

    @Test
    public void test13() {
        System.out.println(new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss").format(new Date()));
    }

    @Test
    public void test14() {
        String path = "C:\\Users\\huang\\Downloads\\抠图\\浦江（1549864-2075997）\\01";
        List<File> files = this.getFileList(new File(path));

        int i = 0;
        for(File file : files) {
            String originName = file.getName(); //文件名
            if(originName.contains("副本")) {
                String newName = originName.substring(0, originName.indexOf("副")-1) + ".png";
                this.rename(path, originName, newName);
                //System.out.println(newName);
                i++;
            }
        }
        System.out.println(i);
    }


    @Test
    public void test15() {
        String path = "C:\\Users\\huang\\Downloads\\抠图\\浦江（1549864-2075997）\\";
        String pathnew = "";
        for(int i = 1; i <= 30; i++) {
            if(i < 10) {
                pathnew = path + "0" + i;
            } else {
                pathnew = path + i;
            }

            List<File> files = this.getFileList(new File(pathnew));
            for(File file : files) {
                String originName = file.getName(); //文件名
                if(StringUtils.isNotBlank(originName)) {
                    try {
                        String editGoodIdstr = originName.substring(0, originName.indexOf("."));
                        if (!StringUtils.isNumeric(editGoodIdstr)) {
                            System.out.println(originName);
                        }
                    } catch (Exception e) {
                        System.out.println(originName);
                    }

                }
            }
        }
    }


    private List<File> getFileList(File dir) {
        File[] files = dir.listFiles(); // 该文件目录下文件全部放入数组
        List<File> filelist = Lists.newArrayList();

        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) { // 判断是文件还是文件夹
                    this.getFileList(new File(files[i].getAbsolutePath())); // 获取文件绝对路径
                } else {
                    filelist.add(files[i]);
                }
            }
        }
        return filelist;
    }

    private void rename(String path, String oldName, String newName) {

        if(!oldName.equals(newName)){
            File oldFile=new File(path+"/"+oldName);
            File newFile=new File(path+"/"+newName);
            if(!oldFile.exists()){
                return;
            }
            if(newFile.exists()){
                System.out.println(newName+" 已存在");
            }
            else {
                oldFile.renameTo(newFile);
            }
        }else {
            System.out.println("新文件名和旧文件名相同。。。");
        }
    }


    @Test
    public void test17() {
        String timeStr = "2019-01-25 20:03:21";
        Timestamp timestamp = Timestamp.valueOf(timeStr);
        System.out.println(timestamp.getTime());
    }

}


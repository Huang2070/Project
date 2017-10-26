package com.huangjin.testjava;


//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:spring/*"})

import com.huangjin.enumeration.Constant;
import com.huangjin.util.PropertiesUtils;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TestClass {
    @Test
    public void test() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        System.out.println(sdf.format(Long.parseLong("1493888196000")));


        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse("2017-05-05 10:20:26");
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
}

package com.huangjin.testjava;


//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:spring/*"})

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TestClass {
    @Test
    public void tset() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        System.out.println(sdf.format(Long.parseLong("1484809379000")));
    }
}

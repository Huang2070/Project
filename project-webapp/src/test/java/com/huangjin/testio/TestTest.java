package com.huangjin.testio;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.activation.MimetypesFileTypeMap;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.huangjin.domain.Aaa;
import com.huangjin.domain.User;
import com.huangjin.util.CharacterUtil;
import com.huangjin.util.DateUtil;
import com.huangjin.util.IOUtils;
import com.huangjin.util.JsonConvertUtil;
import com.huangjin.util.TimeUtil;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.quartz.CronExpression;
import org.springframework.util.AntPathMatcher;

import static java.math.BigDecimal.ROUND_DOWN;
import static java.util.regex.Pattern.compile;

/**
 * Created by huang on 2016-11-8.
 */
public class TestTest {
    public static void main(String[] args) {
        try {
            BufferedInputStream in = new BufferedInputStream(System.in);

            byte[] bytes = new byte[10];
            int bytesReads = in.read(bytes);

            while(bytesReads != -1) {
                String str = new String(bytes);
                if(str.substring(0, 4).equals("exit")) {
                    System.exit(0);
                } else {
                    System.out.println(str.substring(0, bytesReads));
                }

                bytesReads = in.read(bytes);
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testFileReader() {
        FileReader f = null;
        try {
            StringBuffer str = new StringBuffer();
            char[] buf = new char[1024];
            f = new FileReader("C:\\Users\\huang\\Desktop\\sdf.txt");
            while(f.read(buf) > 0) {
                str.append(buf);
            }
            str.toString();
            System.out.println(str);
            f.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }



    @Test
    public void testFilter() {
        PrintStream ps = null;
        try {
            FileOutputStream fos = new FileOutputStream("C:\\Users\\huang\\Desktop\\sdf.txt", true);
            ps = new PrintStream(fos);
            ps.println("123");
            ps.println(new TestTest());
            ps.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testFileWriter() {
        try {
            //打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
            FileWriter writer = new FileWriter("C:\\Users\\huang\\Desktop\\sdf.txt", true);
            writer.write("321");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testBasic() throws IOException {
        File file = new File("C:\\Users\\huang\\Desktop\\sdf.txt");
        File tmpFile = new File("C:\\Users\\huang\\Desktop\\ddd.txt");
        FileUtils.copyFile(file, tmpFile); //生成临时文件

        System.out.println(file.getName());
    }


    @Test
    public void testTime() {
        Calendar calendarStart = Calendar.getInstance();
        calendarStart.set(2018, 2, 1, 1, 1, 1);
        long start = calendarStart.getTimeInMillis();

        Calendar calendarEnd = Calendar.getInstance();
        calendarEnd.set(2018, 3, 1, 1, 1, 1);
        long end = calendarEnd.getTimeInMillis();

        System.out.println(start);
        System.out.println(end);
    }

    @Test
    public void testJoin() {
        System.out.println(Joiner.on(",").join(Lists.newArrayList(0)) + ",0");
    }

    @Test
    public void test() {
        Map<Long, Long> testMap = Maps.newHashMap();
        testMap.put(1L, 2L);
        testMap.put(3L, 4l);
        List<Long> editGoodsIds = Lists.newArrayList();
        for(Map.Entry<Long, Long> entry : testMap.entrySet()) {
            editGoodsIds.add(entry.getValue());
        }
        System.out.println(editGoodsIds);
    }

    @Test
    public void testStream() {
        List<User> userList = Lists.newArrayList();
        User user1 = new User("huangjin", "1");
        User user2 = new User("huangjin", "2");
        User user3 = new User("huangjin", "3");
        User user4 = new User("lmf", "4");
        User user5 = new User("lmf", "5");
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);
        userList.add(user5);
        Map<String, List<User>> userMap = userList.stream().collect(Collectors.groupingBy(User::getUsername));
        System.out.println(userMap);

        List<String> passwordList = Lists.newArrayList();
        passwordList.addAll(userList.stream().map(User::getPassword).collect(Collectors.toList()));
        System.out.println(passwordList);


        userList = userList.stream().sorted(Comparator.comparing(User::getPassword).reversed())
            .collect(Collectors.toList());
        for(User user : userList) {
            System.out.println(user.getUsername() + "---" + user.getPassword());
        }

    }

    @Test
    public void testJsoup() {
        String html = "<!DOCTYPE html>\n" +
                "<html lang=\"zh-CN\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                "    <title></title>\n" +
                "</head>\n" +
                "    <body>\n" +
                "        <div>\n" +
                "            <form name=\"itemForm\" id=\"itemForm\" method=\"post\" action=\"/uploadAndSaveImg\" enctype=\"multipart/form-data\">\n" +
                "                <input name=\"imgFile\" type=\"file\" multiple=\"multiple\">\n" +
                "                <input type=\"submit\" value=\"确定\">\n" +
                "                <p>\n" +
                "                    <video src=\"/i/movie.ogg\" controls=\"controls\">\n" +
                "                        your browser does not support the video tag\n" +
                "                    </video>\n" +
                "\n" +
                "                    <video src=\"/i/movie.hehe\" controls=\"controls\">\n" +
                "                        your browser does not support the video tag\n" +
                "                    </video>\n" +
                "                </p>\n" +
                "            </form>\n" +
                "        </div>\n" +
                "    </body>\n" +
                "</html>";


        String html2 = "<br/>\n" +
                "<div data-filter=\"allStyle\"></div>\n" +
                "<div>\n" +
                "  <img src=\"//pop.nosdn.127.net/4aefd357-d205-48eb-8a02-052547b3b965\" width=\"100%\">\n" +
                "  <br/>\n" +
                "  <br/></div>\n" +
                "<img src=\"http://pop.nosdn.127.net/7ef74df4-cfb0-4539-9618-2af2f954229d\" style=\"width: 100%; height: 100%;\">\n" +
                "<img src=\"http://pop.nosdn.127.net/ab9954f8-989a-4a29-a0cf-5790df9e7c40\" style=\"width: 100%; height: 100%;\">\n" +
                "<img src=\"http://pop.nosdn.127.net/f893c0cb-1e06-4a3b-9dfe-09d64d4b39a8\" style=\"width: 100%; height: 100%;\">\n" +
                "<img src=\"http://pop.nosdn.127.net/e647144a-a907-4689-b170-c9b4382fe02f\" style=\"width: 100%; height: 100%;\">\n" +
                "<img src=\"http://pop.nosdn.127.net/1fc300e1-46b3-4b01-b0cb-72a40b270a2d\" style=\"width: 100%; height: 100%;\">\n" +
                "<img src=\"http://pop.nosdn.127.net/cfb241f1-1d78-4a0e-8b16-b89d10347724\" style=\"width: 100%; height: 100%;\">\n" +
                "<img src=\"http://pop.nosdn.127.net/5374a5b8-742a-40dd-bcfc-79ec4d6d19e3\" style=\"width: 100%; height: 100%;\">\n" +
                "<img src=\"http://pop.nosdn.127.net/49845161-d484-4fe8-8afc-53f0fd4506e2\" style=\"width: 100%; height: 100%;\">\n" +
                "<img src=\"http://pop.nosdn.127.net/fa80e9d3-dfc1-4f46-8225-5feaa6fc1e03\" style=\"width: 100%; height: 100%;\">\n" +
                "<img src=\"http://pop.nosdn.127.net/3aa29ec1-1c5a-4bee-bcaa-89e85bcc36ab\" style=\"width: 100%; height: 100%;\">\n" +
                "<img src=\"http://pop.nosdn.127.net/586b3aaa-cea2-4565-b441-8e4d306520cc\" style=\"width: 100%; height: 100%;\">\n" +
                "<img src=\"http://pop.nosdn.127.net/462c464a-9e34-4303-a672-a479fb97549d\" style=\"width: 100%; height: 100%;\">\n" +
                "<img src=\"http://pop.nosdn.127.net/82746253-e696-4b78-aaa1-921c9ba25b9e\" style=\"width: 100%; height: 100%;\">\n" +
                "<img src=\"http://pop.nosdn.127.net/7c2ce377-7e75-49c1-a075-534b488537d9\" style=\"width: 100%; height: 100%;\">\n" +
                "<img src=\"http://pop.nosdn.127.net/ccca320d-a34b-4faf-89ef-13400e24ce98\" style=\"width: 100%; height: 100%;\">\n" +
                "<img src=\"http://pop.nosdn.127.net/9f610706-fa38-4c97-b986-2e2d987ab970\" style=\"width: 100%; height: 100%;\">\n" +
                "<img src=\"http://pop.nosdn.127.net/a18f8927-fc2a-4481-91e6-e4547ca6a012\" style=\"width: 100%; height: 100%;\">\n" +
                "<img src=\"http://pop.nosdn.127.net/5920b790-3c3f-456b-9743-9bb6f9c516dc\" style=\"width: 100%; height: 100%;\">\n" +
                "<img src=\"http://pop.nosdn.127.net/bc25d43c-2770-41b0-aba0-627b6deb28ef\" style=\"width: 100%; height: 100%;\">\n" +
                "<img src=\"http://pop.nosdn.127.net/d39094be-2663-4ead-aaad-19495e058fcc\" style=\"width: 100%; height: 100%;\">\n" +
                "<img src=\"http://pop.nosdn.127.net/c2be0ff9-903a-4407-97a5-dde40a5e7a12\">";

        Document doc = Jsoup.parse(html2);
        Elements imgElements = doc.select("img[src]");
        for(Element element : imgElements) {
            String src = element.attr("src");
            if(src.equals("http://pop.nosdn.127.net/7ef74df4-cfb0-4539-9618-2af2f954229d")) {
                element.remove();
            }

            if(src.equals("http://pop.nosdn.127.net/c2be0ff9-903a-4407-97a5-dde40a5e7a12")) {
                element.attr("src", "aaa");
            }
        }
        System.out.println(doc.toString());
    }

    @Test
    public void test12() {
        List<Long> longList = Lists.newArrayList();
        Object obj = "1";
        longList.add((Long)obj);
        System.out.println(longList);
    }

    @Test
    public void test14() {
        User user1 = new User();
        user1.setUsername("huangjin");
        user1.setPassword("1");

        User user2 = new User();
        user2.setUsername("huangjin");
        user1.setPassword("2");

        User user3 = new User();
        user3.setUsername("liumingfang");
        user1.setPassword("3");

        Set<User> sets = Sets.newHashSet();
        sets.add(user1);
        sets.add(user2);
        sets.add(user3);

        System.out.println(sets.size());
        for(User user : sets) {
            System.out.println(user.getUsername());
        }
    }

    @Test
    public void test16() {
        Map<Long, Object> map = Maps.newHashMap();
        map.put(1L, "1");
        String result = (String)map.get(2L);
        System.out.println(result);
    }

    @Test
    public void test17() {
        try {
            String filePath = this.getClass().getResource("/offshelf.txt").getPath();
            List<String> lines = IOUtils.readFile(filePath);
            for(String str : lines) {
                System.out.println(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Test
    public void test18() {
        String filePath = this.getClass().getResource("/").getPath();
        System.out.println(filePath);
    }

    @Test
    public void test19() {
        PrintStream psError = null;
        PrintStream psInfo = null;

        try {
            String sourcesPath = this.getClass().getResource("/").getPath();

            FileOutputStream fosError = new FileOutputStream(sourcesPath + "errorLog.log", true); //错误日志文件
            FileOutputStream fosInfo = new FileOutputStream(sourcesPath + "runLog.log", true); //日志文件

            psError = new PrintStream(fosError);
            psInfo = new PrintStream(fosInfo);

            psError.println("123");
            psInfo.println("123");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            psError.close();
            psInfo.close();
        }
    }

    @Test
    public void test20() {
        User use = new User();
        System.out.println(use.getId() == 0);
    }

    @Test
    public void test21() {
        List<Integer> list1 = Lists.newArrayList(1,2,3,4,5);

        List<Integer> list2 = Lists.newArrayList(1,2,3,4,5);

        list1.removeAll(list2);

        System.out.println(list1);
        System.out.println(list2);
    }

    @Test
    public void test22() {
        List<User> users = Lists.newArrayList();
        User user1 = new User("1", "2");
        User user2 = new User("3", "4");
        users.add(user1);
        users.add(user2);

        Map<String, User> userMap = Maps.uniqueIndex(users, new Function<User, String>() {
            @Override
            public String apply(User user) {
                return user.getUsername();
            }
        });

        for(Map.Entry<String, User> entry : userMap.entrySet()) {
            System.out.println(entry.getKey());
        }
    }

    @Test
    public void test23() {
        String a = "1";
        String b = "2";
        Set<String> aaa = Sets.newHashSet();
        System.out.println(aaa.add(a));
        System.out.println(aaa.add(b));
        System.out.println(aaa.add(a));
    }

    @Test
    public void test24() {
        long timeMills = System.currentTimeMillis();
        System.out.println(String.valueOf(timeMills));
        System.out.println(RandomUtils.nextInt(1, 1000));

    }

    @Test
    public void test25() {
        String s1 = new String("aaa");
        String s2 = "aaa";
        System.out.println(s1 == s2);    // false

        s1 = new String("bbb").intern();
        s2 = "bbb";
        System.out.println(s1 == s2);    // true

        s1 = "ccc";
        s2 = "ccc";
        System.out.println(s1 == s2);    // true

        s1 = new String("ddd").intern();
        s2 = new String("ddd").intern();
        System.out.println(s1 == s2);    // true

        s1 = "ab" + "cd";
        s2 = "abcd";
        System.out.println(s1 == s2);    // true

        String temp = "hh";
        s1 = "a" + temp;
        // 如果调用s1.intern 则最终返回true
        s2 = "ahh";
        System.out.println(s1 == s2);    // false

        temp = "hh".intern();
        s1 = "a" + temp;
        s2 = "ahh";
        System.out.println(s1 == s2);    // false

        temp = "hh".intern();
        s1 = ("a" + temp).intern();
        s2 = "ahh";
        System.out.println(s1 == s2);    // true

        s1 = new String("1");    // 同时会生成堆中的对象 以及常量池中1的对象，但是此时s1是指向堆中的对象的
        s1.intern();            // 常量池中的已经存在
        s2 = "1";
        System.out.println(s1 == s2);    // false

        String s3 = new String("1") + new String("1");    // 此时生成了四个对象 常量池中的"1" + 2个堆中的"1" + s3指向的堆中的对象（注此时常量池不会生成"11"）
        s3.intern();    // jdk1.7之后，常量池不仅仅可以存储对象，还可以存储对象的引用，会直接将s3的地址存储在常量池
        String s4 = "11";    // jdk1.7之后，常量池中的地址其实就是s3的地址
        System.out.println(s3 == s4); // jdk1.7之前false， jdk1.7之后true

        s3 = new String("2") + new String("2");
        s4 = "22";        // 常量池中不存在22，所以会新开辟一个存储22对象的常量池地址
        s3.intern();    // 常量池22的地址和s3的地址不同
        System.out.println(s3 == s4); // false

        // 对于什么时候会在常量池存储字符串对象，我想我们可以基本得出结论: 1. 显示调用String的intern方法的时候; 2. 直接声明字符串字面常量的时候，例如: String a = "aaa";
        // 3. 字符串直接常量相加的时候，例如: String c = "aa" + "bb";  其中的aa/bb只要有任何一个不是字符串字面常量形式，都不会在常量池生成"aabb". 且此时jvm做了优化，不会同时生成"aa"和"bb"在字符串常量池中
    }

    @Test
    public void test26() {
        String str = "huangjin111";
        for (int i = str.length(); --i>=0;){
            if (!Character.isDigit(str.charAt(i))){
                System.out.println(false);
            }
        }
    }

    @Test
    public void test27() {
        Set<Integer> sets = Sets.newHashSet(1,2,3,4,5,6,7,8,9,0);

        List<List<Integer>> list = Lists.partition(Lists.newArrayList(sets), 10);

        System.out.println(list);

    }


    @Test
    public void test28() {
        List<User> users = Lists.newArrayList();
        User user1 = new User("huangjin", "1");
        User user2 = new User("huangjin", "2");
        User user3 = new User("huangjin", "3");
        User user4 = new User("lmf", "4");
        User user5 = new User("lmf", "5");
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        users.add(user5);

        for(User user : users) {
            user.setUsername("hehe");
        }

        for(User user : users) {
            System.out.println(user.toString());
        }
    }

    @Test
    public void test29() {
        Map<String, Integer> map = Maps.newHashMap();
        map.put("1-1", 1);
        map.put("1-2", 1);
        map.put("2-1", 2);
        map.put("3-1", 3);

        List<Integer> list = Lists.newArrayList(map.values());
        Set<Integer> set = Sets.newHashSet(map.values());
        System.out.println(list);
        System.out.println(set);
    }


    @Test
    public void test31() {
        Object ddd = 2018435.0;

        System.out.println(Math.round(Double.parseDouble(ddd.toString())));
    }

    @Test
    public void test33() {
        String url = "/inperoid/query/**";
        String matchUrl = "/inperoid/query/";

        AntPathMatcher pathMatcher = new AntPathMatcher();
        System.out.println(pathMatcher.match(url, matchUrl));
    }

    @Test
    public void test35() {
        int threads = Math.min(Runtime.getRuntime().availableProcessors() * 2, 10);
        System.out.println(Runtime.getRuntime().availableProcessors());
        System.out.println(threads);
    }

    @Test
    public void test37() throws IOException {
        List<String> list = IOUtils.readFile("C:\\Users\\huang\\Downloads\\bbb.sql");
        FileWriter fw = new FileWriter("C:\\Users\\huang\\Downloads\\ddd.sql", true);
        BufferedWriter bw = new BufferedWriter(fw);
        for(String str : list) {
            String goodsId = str.substring(0, str.indexOf("\t"));
            String title = str.substring(str.indexOf("\t") + 1);

            String udpateSql = "update tb_goods_label_edit set invoice_title = '" + title + "' where edit_goods_id = " + goodsId + ";";
            bw.write(udpateSql);
            bw.newLine();
        }

        bw.close();
        fw.close();
    }

    @Test
    public void test337() throws IOException {
        List<String> list = IOUtils.readFile("C:\\Users\\huang\\Downloads\\qqqqq.sql");
        FileWriter fw = new FileWriter("C:\\Users\\huang\\Downloads\\updateForbidRule.sql", true);
        BufferedWriter bw = new BufferedWriter(fw);
        for(String str : list) {
            String id = str.substring(0, str.indexOf("\t"));
            String businessType = str.substring(str.lastIndexOf("\t") + 1);

            String udpateSql = "update tb_forbid_rule set business_type = " + businessType + " where id = " + id + ";";
            bw.write(udpateSql);
            bw.newLine();
        }

        bw.close();
        fw.close();
    }

    /**
     * Lists.newArrayList如果参数是null, 则会生成一个包含null的list, 而不是一个空list
     */
    @Test
    public void test39() {
        Map<Integer, Long> map = Maps.newHashMap();
        long l = map.get(123);

        List list = Lists.newArrayList(l);
        List list2 = Arrays.asList(l);
        System.out.println(list);
    }

    /**
     * new一个对象, 不会new里面的对象
     */
    @Test
    public void test41() {
        User use = new User();
        System.out.println(use);
        System.out.println(use.isMan());
    }

    /**
     * 计算Date类型时间差(天数)
     * @throws ParseException
     */
    @Test
    public void test43() throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date time = df.parse("2018-06-06 11:30:24");
        Long offShelfDays = (new Date().getTime() - time.getTime()) / (1000 * 60 * 60 * 24);
        System.out.println(offShelfDays);

    }


    @Test
    public void test44() {
        boolean result = TimeUtil.isValidDate("2019-01-19 12:00:00", "yyyy-MM-dd HH:mm:ss");
        System.out.println(result);
    }

    @Test
    public void test45() {
        String img = "http://r1.ykimg.com/050B00005BD17EEEADA7B2063E038B0D";
        if(img.indexOf(".com/") != -1) {

            Integer index = img.indexOf(".com/") + 5;

            String imgUrl = img.substring(0, index);
            System.out.println(imgUrl);
            String imgId = img.substring(index);
            System.out.println(imgId);

            if(imgId.startsWith("050")) {
                imgId = "050C" + imgId.substring(4);
            } else if(imgId.startsWith("054")) {
                imgId = "0541" + imgId.substring(4);
            }

            String replaceImg = imgUrl + imgId;
            System.out.println(replaceImg);
        }
    }

    @Test
    public void test47() throws IOException {
        List<String> list = IOUtils.readFile("C:\\Users\\huang\\Downloads\\1.txt");
        FileWriter fw = new FileWriter("C:\\Users\\huang\\Downloads\\2.sql", true);
        BufferedWriter bw = new BufferedWriter(fw);

        String sql = "insert into tb_forbid_rule (id, supplier_id, brand_id, category_id, edit_goods_id, forbid_reason, status, type, operator) values ";
        bw.write(sql);
        bw.newLine();
        for(String str : list) {
            String insert = "(seq," + str + ",";
            insert = insert + "0,0,0,'贸易模式不合规人工禁发',3,1,'operator'),";
            bw.write(insert);
            bw.newLine();
        }
        bw.close();
        fw.close();
    }

    @Test
    public void test49() {
        String str = "1,2,3,4,5";

        List<String> strList = Arrays.asList(str);

        System.out.println(strList);
    }

    @Test
    public void test51() {
        String str = "我是中国人";
        System.out.println(str.length());

        String str2 = "111abc";
        System.out.println(str2.length());

        String str3 = "  sd sd d  ";
        System.out.println("|" + str3.trim() + "|");
    }

    @Test
    public void test53() {
        User user = new User();
        System.out.println(user.getId());
        System.out.println(user.getMoney());

    }


    @Test
    public void test55() {
        Object obj = null;
        String str = (String)obj;
        System.out.println(str);
    }

    @Test
    public void test57() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 0);
        System.out.println(cal.getTime());

    }

    @Test
    public void test59() throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date time = df.parse("2018-06-06 11:11:24");

        Calendar c1 = Calendar.getInstance();
        c1.setTime(time);

        Calendar now = Calendar.getInstance(); //当前时间
        Integer timeDiffHour = now.get(Calendar.HOUR_OF_DAY) - c1.get(Calendar.HOUR_OF_DAY);
        Integer timeDiffMinute = now.get(Calendar.MINUTE) - c1.get(Calendar.MINUTE);
        System.out.println(timeDiffHour);
        System.out.println(timeDiffMinute);
    }

    @Test
    public void test65() throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = sdf.parse("2019-07-26 00:00:00");
        System.out.println(now.getTime());

        String value0 = (new Timestamp(1558593599000L)).toString();
        String value1 = (new Timestamp(1565076007000L)).toString();
        String value2 = (new Timestamp(1565076002000L)).toString();
        System.out.println(value0);
        System.out.println(value1);
        System.out.println(value2);

        System.out.println(new Date((long)Integer.MAX_VALUE*1000));

        System.out.println(Integer.MAX_VALUE);

        System.out.println((long)Integer.MAX_VALUE*1000);
    }


    @Test
    public void test67() throws IOException {
        BufferedWriter goodsIdsWriter = new BufferedWriter(new FileWriter(new File("C:\\Users\\huang\\Downloads\\hehehehehe.txt")));

        List<Long> list = Lists.newArrayList();
        for(Long i = 1L; i < 100000; i++) {
            list.add(i);
        }

        for (Long item : list) {
            if(item % 1000 == 0) {
                System.out.println("currently @ " + item);
            }

            goodsIdsWriter.write(item + "");
            goodsIdsWriter.write(System.getProperty("line.separator"));
        }
        goodsIdsWriter.close();
    }


    @Test
    public void test69() {
        System.out.print(1 + System.getProperty("line.separator") + 2);
    }

    @Test
    public void test71() {
        System.out.println(this.getClass().getClassLoader().getResource("").getPath());
    }

    @Test
    public void test73() {
        Pattern p = Pattern.compile("(满.*减)|(满.*折)|(件.*折)|(半价)");
        Matcher m = p.matcher("折叠椅"); //要验证的字符串
        System.out.println(m.find());
    }

    @Test
    public void test77() {
        BigDecimal ChinaGDP = new BigDecimal(13.00);
        BigDecimal USAGDP = new BigDecimal(20.00);

        int year = 2019;
        for(int i = 1; i < 33; i++) {
            ChinaGDP = ChinaGDP.multiply(BigDecimal.valueOf(1.06)).setScale(2, ROUND_DOWN);
            USAGDP = USAGDP.multiply(BigDecimal.valueOf(1.02)).setScale(2, ROUND_DOWN);
            System.out.println(year + "---China:" + ChinaGDP + "---USA:" + USAGDP);
            year++;
        }
    }

    @Test
    public void test79() {

        String str ="中华人民共(和国)，简称(中国)。";

        Matcher mat = Pattern.compile("(?<=\\()(\\S+)(?=\\))").matcher(str);
        while(mat.find()){
            System.out.println(mat.group());
        }

        String result = str.substring(str.lastIndexOf("(") + 1, str.lastIndexOf(")"));
        System.out.println(result);
    }

    @Test
    public void test81() {
        Integer x = new Integer(123);
        Integer y = new Integer(123);
        System.out.println(x == y);    // false
        System.out.println(x.equals(y)); //true
        Integer z = Integer.valueOf(123);
        Integer k = Integer.valueOf(123);
        System.out.println(z == k);   // true

        Integer a = 1111;
        Integer b = 1111;
        System.out.println(a == b);
    }

    @Test
    public void test83() {
        String a = "aaa";
        a = "bbb";
        System.out.println(a);

    }

    @Test
    public void test85() {
        int count = CharacterUtil.getChineseCharCount("长度必须是100  to  200是大大大啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊~！@#￥%……&*（））——+}{}|}“”：》《》-+*/？aaaaa啊啊啊啊");
        System.out.println(count);
    }

    @Test
    public void test87() throws IOException {
        IOUtils.clearInfoForFile("C:\\Users\\huang\\Desktop\\qqqqq.sql");

        File file = new File("C:\\Users\\huang\\Desktop\\qqqqq.sql");
        FileOutputStream fosInfo = new FileOutputStream(file, true); //日志文件
        PrintStream psInfo = new PrintStream(fosInfo);
        psInfo.println(100);
        psInfo.println(300);
        psInfo.close();
    }

    @Test
    public void test89() {
        Map<Integer, List<Long>> map = Maps.newHashMap();
        List<Long> list = map.get(1);

        for(int i = 0; i < 3; i++) {
            if(CollectionUtils.isEmpty(list)) {
                list = Lists.newArrayList(2L);
                map.put(1, list);
            } else {
                list.add(3L);
            }
        }

        System.out.println(map.get(1));
    }

    @Test
    public void test91() {
        List<Integer> list = Lists.newArrayList(1,2,3);
        list.removeAll(null);
        System.out.println(list);
    }

    @Test
    public void test93() {
        List<Integer> list = Lists.newArrayList(1,2,3,4,5,6,7,8,9);
        List<Integer> list2 = Lists.newArrayList(1,2,3);
        Iterator<Integer> iterator = list2.iterator();

        while(iterator.hasNext()) {
            Integer i = iterator.next();
            if(i == 3) {
                list.remove(i);
            }
        }
        System.out.println(list);
    }

    @Test
    public void test95() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(new Date()));
    }

    @Test
    public void test97() {
        String url = "https://pop.nosdn.127.net/6d4f9342-101b-4c74-b135-f967f686c048";
        if(url.startsWith("http:")) {
            url = url.substring(5);
        } else if(url.startsWith("https:")) {
            url = url.substring(6);
        }
        System.out.println(url);
    }

    @Test
    public void test99() throws IOException {
        final File file = new File("C:\\Users\\huang\\Desktop\\ddd.txt");
        InputStream inputStream = null;

        try {
            inputStream = new FileInputStream(file);
            inputStream.close();
            System.out.println(file.getName());
            new Thread(() -> {
                try {
                    Thread.sleep(5000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            inputStream.close();
            System.out.println("end");
        }
    }

    @Test
    public void test101() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        User user = new User();
        user.setDate(new Date());
        user.setId(1);
        user.setUsername("huangjin");
        Map<String, Object> param = BeanUtils.describe(user);

        for(Map.Entry<String, Object> entry : param.entrySet()) {
            System.out.println(entry.getKey() + "---" + entry.getValue());
        }
    }


    @Test
    public void test103() {
        String str = "huangjin(1919191)";
        String code = str.substring(str.lastIndexOf("(") + 1, str.lastIndexOf(")"));
        System.out.println(code);
    }

    @Test
    public void test105() {
        Set<String> set = Sets.newHashSet();
        set.add("1");
        set.add("2");
        set.add("3");

        System.out.println(StringUtils.join(set.toArray(), ","));
    }

    @Test
    public void test107() {
        String strFormat = "贵司【%s】%s公司，在考拉平台申请的“%s”品牌新建申请，已被审核拒绝；审核拒绝原因为：%s；请及时前往https://partner.kaola.com/ 登陆进入信息与权限-品牌资质管理菜单跟进处理；";

        String str = String.format(strFormat, 1, "2", "3", null);
        System.out.println(str);
    }


    @Test
    public void test109() {

        String url = "https://dianliuegg.youku.com/share/video/XNDI1MzIwMzgwOA==?routeType=FAXIAN&sharekey=710ca9597e0bb9c7ffb975cd104cb5ca7&refer=pgy_bd.yl151641_00003050_000000_iUvEzm_19071800";
        if (url.startsWith("http")) {

            if(url.contains("dianliu")) {
                Pattern p = compile("/([a-zA-Z0-9]*==)");
                Matcher m = p.matcher(url);
                if (m.find()) {
                    String urlVid = m.group(1);
                    if (StringUtils.isNotEmpty(urlVid)) {
                        System.out.println(urlVid);
                    }
                }
            }
        }
    }

    @Test
    public void test111() {
        Aaa aaa = new Aaa();
        System.out.println(aaa.getClass());

        Type type = aaa.getClass().getGenericSuperclass();
        System.out.println(type);

        ParameterizedType pt = (ParameterizedType) type;
        Type[] ts = pt.getActualTypeArguments();
        Class c = (Class) ts[0];
        System.out.println(c);
    }

    @Test
    public void test113() {

        List<String> lst = new ArrayList<String>();
        lst.add("aaa");
        lst.add("bbb");
        lst.add("ccc");
        lst.add("ddd");
        lst.add("eee");
        lst.add("fff");
        Iterator<String> iterator = lst.iterator();

        StringBuilder sb = new StringBuilder();
        //iterator.hasNext()如果存在元素的话返回true
        while(iterator.hasNext()) {
            //iterator.next()返回迭代的下一个元素

            sb.append(iterator.next());
            if(iterator.hasNext()) {
                sb.append(",");
            }
        }
        System.out.println(sb);
    }


    @Test
    public void test115() {
        String searchStr = "hehe";
        String str = "notin(video_source, \"" + searchStr + "\")";
        System.out.println(str);

        Map<String, String> map = Maps.newHashMap();

        String str1 = map.get(null);
        System.out.println(str1);
    }


    @Test
    public void test119() {

        Date date = new Date();// 取时间
        Calendar calendar = new GregorianCalendar();

        calendar.setTime(date);

        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        System.out.println(calendar.getTime());

    }

    @Test
    public void test121() {
        String str = "111.3";
        Double dou = Double.parseDouble(str);
        Long l = dou.longValue();
        System.out.println(l);


        String str1 = "hehexixixlalalal";
        System.out.println(str1.contains("hehe"));

    }

    @Test
    public void test123() {
        String str = "CCTV,天津卫视,CCTV-1,CCTV-2,CCTV-3,CCTV-4,CCTV-5,CCTV-6,CCTV-7,CCTV-8,CCTV-13";

        System.out.println(str.indexOf("CCTV-1"));

    }

    @Test
    public void test127() {
        Integer count = 10000;
        this.count(count);
        System.out.println(count);

        User user = new User();
        user.setId(1);
        this.setUser(user);
        System.out.println(user.getId());
    }


    private void count(Integer count) {
        count++;
    }

    private void setUser(User user) {
        user.setId(2);
    }

    @Test
    public void test129() throws Exception {
        List<String> list = IOUtils.readFile("/Users/huangjin/Documents/newToken.txt");
        FileWriter fw = new FileWriter("/Users/huangjin/Documents/newTokenUpdate.sql", true);
        BufferedWriter bw = new BufferedWriter(fw);

        for(String str : list) {

            String[] splitStr = str.split(",");
            if(splitStr.length != 2) {
                throw new Exception("长度不合法");
            }

            String uid = splitStr[0];
            String token = splitStr[1];

            String sql = "update t_pgy_userinfo_channel set access_token = '" + token + "' where channel = 'WEIBO' and uid = '" + uid + "'";
            bw.write(sql);
            bw.newLine();
        }
        bw.close();
        fw.close();
    }

    @Test
    public void test131() {
        List<String> list = null;

        for(String str : list) {
            System.out.println(str);
        }
    }


    @Test
    public void test133() {
        File file = new File("/Users/huangjin/Documents/1416eZ4YPugjZh5R-VSVideo-Merged.mp4");
        String contentType = new MimetypesFileTypeMap().getContentType(file);

        System.out.println(contentType);
    }

    @Test
    public void test135() {
        Date date = DateUtil.parseDate("20191023");

        System.out.println(date);
    }

    @Test
    public void test137() {
        String weiboText = "#未来演技派#@辣目洋子 转型做演员！着实期待！之前看过一些短片就觉得小姐姐演技不错！果然，期待作为演员洋子未来的发展！http://t.cn/Ai1NcvKU";

        Pattern topicTagPattern = Pattern.compile("#([^#]{1,40})#");

        Set<String> topicList = new LinkedHashSet<>();

        Matcher matcher = topicTagPattern.matcher(weiboText);
        while (matcher.find()){
            String topicName = matcher.group(1);
            topicList.add(topicName);
        }
        System.out.println(topicList);
    }

    @Test
    public void test139() {
        User user = new User();
        user.setUsername("huangjin");

        this.setUserName(user);
        System.out.println(user.getUsername());
    }

    private void setUserName(User user) {
        user.setUsername("fangfang");
    }


    @Test
    public void test141() {
        //开始时间
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, 30);

        Integer startMonth = calendar.get(Calendar.MONTH) + 1;
        Integer startDate = calendar.get(Calendar.DATE);
        String startTimeStr = startMonth + "月" + startDate + "日";

        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + 3);
        Integer endMonth = calendar.get(Calendar.MONTH) + 1;
        Integer endDate = calendar.get(Calendar.DATE) + 1;
        String endTimeStr = endMonth + "月" + endDate + "日";

        String winDesc = startTimeStr + "-" + endTimeStr;

        System.out.println(winDesc);
    }


    @Test
    public void test143() {
        StringBuilder sb = new StringBuilder();
        sb.append("ing");
        sb.append("");

        System.out.println(sb.length());
    }

    @Test
    public void test145() {
        String rule = "[{\"ruleCode\":\"hehe\",\"ruleInfo\":{\"start_time\":111,\"end_time\":222}},"
            + "{\"ruleCode\":\"xixi\",\"ruleInfo\":{\"dayExp\":111,\"peopleExp\":222}}]";

        List<JSONObject> list = JSON.parseArray(rule, JSONObject.class);

        for(JSONObject jsonObject : list) {

            for(Entry entry : jsonObject.entrySet()) {
                System.out.println(entry.getKey());
                System.out.println(entry.getValue());
            }
        }
    }

    @Test
    public void test147() {
        ForkJoinPool pool = ForkJoinPool.commonPool();

        System.out.println(pool.getPoolSize());
    }

    @Test
    public void test149() throws IOException {
        List<String> list = IOUtils.readFile("/Users/huangjin/Downloads/test.json");
        for(String item : list) {
            if(!item.startsWith("{")) {
                continue;
            }
            JSONObject jsonObject = JSONObject.parseObject(item);
            System.out.println(item);
        }
    }

    @Test
    public void test151() throws IOException {
        List<String> list = IOUtils.readFile("/Users/huangjin/Downloads/test.json");
        for(String item : list) {
            if(!item.startsWith("{")) {
                continue;
            }
            JSONObject jsonObject = JSONObject.parseObject(item);
            System.out.println(item);
        }
    }

    @Test
    public void test153() throws IOException {
        Long l = 3210873l;
        Double d = l.doubleValue();

        System.out.println(d/(1000*1000));
    }

    @Test
    public void test155() {
        String str = "成功. retMsg={\"data\":{\"description\":\"\",\"error_code\":0,"
            + "\"item_id\":\"@9VwVhfbHX54gbHCuNN0xQs780mztOvmAPZR1oQKlKVEaZvf460zdRmYqig357zEBK+218yZD2CKkhoXn4WpTyg"
            + "==\"},\"extra\":{\"logid\":\"202002181715590100140471940412AEBC\",\"now\":1582017359979}}";

        String str1 = str.substring(str.indexOf("retMsg=") + 7);

        System.out.println(str1);
    }

    @Test
    public void test157() {
        String json = "{\"data\":{\"description\":\"\",\"error_code\":0,\"list\":[{\"cover\":\"https://p9-dy.byteimg"
            + ".com/img/tos-cn-p-0015/defd31dd368a468da5a9b05af25e91e7_1581846673~c5_300x400"
            + ".jpeg?from=2563711402_large\",\"create_time\":1581846667,\"is_reviewed\":true,\"is_top\":false,"
            + "\"item_id\":\"@9VwVhfbHX54gbHCuNN0xQs780mzqNP+LPpZyqAqjL1QUbvL760zdRmYqig357zEBEu9pgEGtvxzC4CdxD1E7Wg"
            + "==\",\"share_url\":\"https://www.iesdouyin"
            + ".com/share/video"
            +
            "/QDlWd1ZoZmJIWDU0Z2JIQ3VOTjB4UXM3ODBtenFOUCtMUHBaeXFBcWpMMVFVYnZMNzYwemRSbVlxaWczNTd6RUJFdTlwZ0VHdHZ4ekM0Q2R4RDFFN1dnPT0=/?region=CN&mid=6793964200273300231&u_code=gh7me18g&titleType=title\",\"statistics\":{\"comment_count\":19,\"digg_count\":419,\"download_count\":0,\"forward_count\":2,\"play_count\":23640,\"share_count\":3},\"title\":\"#王牌对王牌 TFBOYS变身呆萌波斯猫，老阿姨们尖叫不已\"},{\"cover\":\"https://p3-dy.byteimg.com/img/tos-cn-p-0015/d15da1624e784a9cac2fdc82db303fa9_1581309908~c5_300x400.jpeg?from=2563711402_large\",\"create_time\":1581309903,\"is_reviewed\":true,\"is_top\":false,\"item_id\":\"@9VwVhfbHX54gbHCuNN0xQs780mzoO/+GOpNzrA+uKlcaafL660zdRmYqig357zEB5Hqiu6ESE+3J8HTUgIQ/CQ==\",\"share_url\":\"https://www.iesdouyin.com/share/video/QDlWd1ZoZmJIWDU0Z2JIQ3VOTjB4UXM3ODBtem9PLytHT3BOenJBK3VLbGNhYWZMNjYwemRSbVlxaWczNTd6RUI1SHFpdTZFU0UrM0o4SFRVZ0lRL0NRPT0=/?region=CN&mid=6791659247018101517&u_code=gh7me18g&titleType=title\",\"statistics\":{\"comment_count\":165,\"digg_count\":3719,\"download_count\":38,\"forward_count\":2,\"play_count\":184075,\"share_count\":26},\"title\":\"钟丽缇隔空大喊要给张伦硕生宝宝，弱弱的问一句这么大岁数还能生吗？\"}]},\"extra\":{\"logid\":\"2020021813140301000806508523D6F4E1\",\"now\":1582002844078}}";

        ReadContext readContext = JsonPath.parse(json);

        Integer errorCode = JsonConvertUtil.getJsonPathInt(readContext.read("$.data.error_code"));
        System.out.println(errorCode);

        int size = readContext.read("$.data.list.length()");
        System.out.println(size);

        String itemId = readContext.read("$.data.list[" + 1 + "].item_id");
        System.out.println(itemId);

        Integer commentCount = JsonConvertUtil.getJsonPathInt(readContext.read("$.data.list[" + 1 + "].statistics.comment_count"));
        System.out.println(commentCount);
    }


    @Test
    public void test159() {
        String json = "[{\"ruleCode\":\"MAX_EXP\",\"ruleInfo\":{\"userType\":\"alipayId\",\"dayMaxExp\":1,"
            + "\"peopleMaxExp\":10000}},{\"ruleCode\":\"MEGA_PEOPLE\",\"ruleInfo\":{\"userType\":\"alipayId\","
            + "\"megaPeopleIds\":\"41634\"}},{\"ruleCode\":\"NOT_LOGIN\",\"ruleInfo\":{\"userType\":\"ytid\"}},"
            + "{\"ruleCode\":\"CLIENT_TYPE\",\"ruleInfo\":{\"client\":\"ANDROID\"}}]";

        JSONArray jsonArray = JSONArray.parseArray(json);

        System.out.println(jsonArray);

        List<JSONObject> ruleDetail = Lists.newArrayList();
        if(!jsonArray.isEmpty()) {
            for(Object object : jsonArray) {
                ruleDetail.add((JSONObject)object);
            }
        }

        System.out.println(ruleDetail);
    }

    @Test
    public void test161() {
        String str = "12313213";
        System.out.println(NumberUtils.isDigits(str));

        String str1 = "12313213aa";
        System.out.println(NumberUtils.isDigits(str1));
    }

    @Test
    public void test163() throws ParseException {
        String cron = "* * 10-15 * * ? ";
        System.out.println(filterWithCronTime(cron));//true，我当前时间为15:36，
        System.out.println(filterWithCronTime(cron));//false，我当前时间为15:36，
    }

    /**
     * 校验在当前时间是否满足时间规则表达式
     * @param cron
     * @return
     * @throws ParseException
     */
    private  Boolean filterWithCronTime(String cron) throws ParseException {
        if(StringUtils.isBlank(cron)){
            return false;
        }
        CronExpression exp = new CronExpression(cron);
        Boolean inCron = exp.isSatisfiedBy(new Date());
        return inCron;
    }
}
